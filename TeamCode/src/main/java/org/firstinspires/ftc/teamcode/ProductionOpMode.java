
/*
 * Made by Pearl Kamalu on June 3, following the tutorial on
 * game manual 0 for field centric controls.
 * 
 * https://gm0.org/en/latest/docs/software/tutorials/mecanum-drive.html
 *  
 * Single controler Operation Mode
 * 
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class ProductionOpMode extends OpMode {
    DcMotor frontLeftMotor;
    DcMotor backLeftMotor;
    DcMotor frontRightMotor;
    DcMotor backRightMotor;

    DcMotor armMotor;
    double armPower;

    CRServo linearExtenderServo;
    double servoPower;

    Servo clawRotatorServo;
    Servo clawOpenerServo;

    boolean clawOpenState;
    double clawClosedPosition;
    double clawOpenPosition;

    double rotatorPosition;
    double deltaPosition;
    double outer_position;

    //Servo clawPlacementServo;

    boolean inverseControlState;


    @Override
    public void init() {
        // Declare our motors
        // Make sure your ID's match your configuration
        frontLeftMotor = hardwareMap.dcMotor.get("FrontLeftMotor");
        backLeftMotor = hardwareMap.dcMotor.get("BackLeftMotor");
        frontRightMotor = hardwareMap.dcMotor.get("FrontRightMotor");
        backRightMotor = hardwareMap.dcMotor.get("BackRightMotor");

        armMotor = hardwareMap.get(DcMotor.class, "ArmMotor");

        // Reverse the right side motors. This may be wrong for your setup.
        // If your robot moves backwards when commanded to go forwards,
        // reverse the left side instead.
        // See the note about this earlier on this page.
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        // backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        // Arm
        armPower = 0.5;

        linearExtenderServo = hardwareMap.get(CRServo.class, "LinearExtenderServo");
        linearExtenderServo.setPower(0);
        servoPower = 0.5;

        // Opener stuff
        clawOpenState = false;
        clawClosedPosition = 0.0;
        clawOpenPosition = 0.9;

        // Rotation stuff
        rotatorPosition = 0.60; // always set to start in middle
        deltaPosition = 0.001; // Miracle how this works because of floating point error

        outer_position = 0.25;

        clawOpenerServo = hardwareMap.get(Servo.class, "ClawOpenerServo");
        clawOpenerServo.setPosition(clawClosedPosition);

        clawRotatorServo = hardwareMap.get(Servo.class, "ClawPlacementServo");
        //clawRotatorServo.setPosition(rotatorPosition);

        //clawPlacementServo = hardwareMap.get(Servo.class,"ClawPlacementServo");
        //clawPlacementServo.setDirection(Servo.Direction.REVERSE);

        inverseControlState = false;
    }

    @Override
    public void loop() {
        //clawPlacementServo.setPosition(outer_position);


        // Inversion
        if (gamepad1.dpad_down) {
            inverseControlState = !inverseControlState;
        }

        // Steering
        double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
        double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
        double rx = gamepad1.right_stick_x; // Rotation is backwards

        if (inverseControlState) {
            y = y * -1;
            x = x * -1;
            //rx = rx * -1;
        }

        // Denominator is the largest motor power (absolute value) or 1
        // This ensures all the powers maintain the same ratio,
        // but only if at least one is out of the range [-1, 1]
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double frontLeftPower = (y + x + rx) / denominator;
        double backLeftPower = (y - x + rx) / denominator;
        double frontRightPower = (y - x - rx) / denominator;
        double backRightPower = (y + x - rx) / denominator;

        frontLeftMotor.setPower(frontLeftPower);
        backLeftMotor.setPower(backLeftPower);
        frontRightMotor.setPower(frontRightPower);
        backRightMotor.setPower(backRightPower);

        // Arm
        if (gamepad2.x) { // going up
            armMotor.setPower(armPower);
        } if (gamepad2.y) { // going down
            armMotor.setPower(-armPower);
        } else {
            armMotor.setPower(0);
        }

        // Linear Extender
        if (gamepad2.b) {
            linearExtenderServo.setPower(-servoPower);
        } else if (gamepad2.a) {
            linearExtenderServo.setPower(servoPower);
        } else {
            linearExtenderServo.setPower(0);
        }

        // Opening/closing claw

        // I literally don't know why the trigger is a float, but here it is...
        if (gamepad2.left_trigger > 0.9) {
            clawOpenState = true;
            // telemetry.addData("OPENSTATE", "on");
        }
        if (gamepad2.right_trigger > 0.9) {
            clawOpenState = false;
            //telemetry.addData("OPENSTATE", "off");
        }

        //telemetry.addData("LeftController", gamepad2.left_trigger);
        //telemetry.addData("right controller", gamepad2.right_trigger);


        if (clawOpenState) {
            clawOpenerServo.setPosition(clawOpenPosition);
            //telemetry.addData("Claw Action", "Open");
        }
        if (!clawOpenState){
            clawOpenerServo.setPosition(clawClosedPosition);
            //telemetry.addData("Claw Action", "Close");
        }

        // rotating claw

        if (gamepad2.right_bumper) {
            rotatorPosition = rotatorPosition + deltaPosition;
            // telemetry.addData("RotatorPosition", rotatorPosition);
            // telemetry.addData("rotator button", "right");
        } else if (gamepad2.left_bumper) {
            rotatorPosition = rotatorPosition - deltaPosition;
            //telemetry.addData("RotatorPosition", rotatorPosition);
            // telemetry.addData("RotatorPosition", "left");
        }

        clawRotatorServo.setPosition(rotatorPosition);
    }
}
