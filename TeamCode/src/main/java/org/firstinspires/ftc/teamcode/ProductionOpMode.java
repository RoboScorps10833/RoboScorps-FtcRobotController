
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

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class ProductionOpMode extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        // Declare our motors
        // Make sure your ID's match your configuration
        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("FrontLeftMotor");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("BackLeftMotor");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("FrontRightMotor");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("BackRightMotor");

        DcMotor armMotor = hardwareMap.get(DcMotor.class, "ArmMotor");

        // Reverse the right side motors. This may be wrong for your setup.
        // If your robot moves backwards when commanded to go forwards,
        // reverse the left side instead.
        // See the note about this earlier on this page.
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        // backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        // Arm
        double armPower = 0.5;

        CRServo linearExtenderServo = hardwareMap.get(CRServo.class, "LinearExtenderServo");
        linearExtenderServo.setPower(0);
        double servoPower = 0.5;

        Servo clawRotatorServo;
        Servo clawOpenerServo;

        // Opener stuff
        boolean clawOpenState = false;
        double clawClosedPosition = 0.0;
        double clawOpenPosition = 0.9;

        // Rotation stuff
        double rotatorPosition = 0.5; // always set to start in middle
        double deltaPosition = 0.001;

        clawOpenerServo = hardwareMap.get(Servo.class, "ClawOpenerServo");
        clawOpenerServo.setPosition(clawClosedPosition);

        clawRotatorServo = hardwareMap.get(Servo.class, "ClawRotatorServo");
        clawRotatorServo.setPosition(rotatorPosition);

        waitForStart();

        boolean inverseControlState = false;

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            // Inversion
            if (gamepad1.dpad_down) {
                inverseControlState = true;
            }

            // Steering
            double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
            double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            double rx = gamepad1.right_stick_x; // Rotation is backwards

            if (inverseControlState) {
                y = y * -1;
                x = x * -1;
                rx = rx * -1;
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
            if (gamepad1.a) { // going up
                armMotor.setPower(armPower);
            } if (gamepad1.b) { // going down
                armMotor.setPower(-armPower);
            } else {
                armMotor.setPower(0);
            }

            // Linear Extender
            if (gamepad1.x) {
                linearExtenderServo.setPower(-servoPower);
            } else if (gamepad1.y) {
                linearExtenderServo.setPower(servoPower);
            } else {
                linearExtenderServo.setPower(0);
            }

            // Opening/closing claw

            // I literally don't know why the trigger is a float, but here it is...
            if (gamepad1.left_trigger > 0.9) {
                clawOpenState = true;
                // telemetry.addData("OPENSTATE", "on");
            }
            if (gamepad1.right_trigger > 0.9) {
                clawOpenState = false;
                //telemetry.addData("OPENSTATE", "off");
            }

            //telemetry.addData("LeftController", gamepad1.left_trigger);
            //telemetry.addData("right controller", gamepad1.right_trigger);


            if (clawOpenState == true) {
                clawOpenerServo.setPosition(clawOpenPosition);
                //telemetry.addData("Claw Action", "Open");
            }
            if (!clawOpenState){
                clawOpenerServo.setPosition(clawClosedPosition);
                //telemetry.addData("Claw Action", "Close");
            }

            // rotating claw

            if (gamepad1.right_bumper) {
                rotatorPosition = rotatorPosition + deltaPosition;
               // telemetry.addData("RotatorPosition", rotatorPosition);
               // telemetry.addData("rotator button", "right");
            } else if (gamepad1.left_bumper) {
                rotatorPosition = rotatorPosition - deltaPosition;
               //telemetry.addData("RotatorPosition", rotatorPosition);
               // telemetry.addData("RotatorPosition", "left");
            }

            clawRotatorServo.setPosition(rotatorPosition);
        }
    }
}