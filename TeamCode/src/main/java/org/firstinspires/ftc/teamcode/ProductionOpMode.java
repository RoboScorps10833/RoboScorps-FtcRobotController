
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

import org.firstinspires.ftc.teamcode.components.ProgrammingBoard;

@TeleOp
public class ProductionOpMode extends OpMode {
    ProgrammingBoard board = new ProgrammingBoard();

    @Override
    public void init() {
        board.init(hardwareMap);
    }

    @Override
    public void loop() {
        //clawPlacementServo.setPosition(outer_position);


        // Inversion
        if (gamepad1.dpad_down) {
            board.inverseControlState = !board.inverseControlState;
        }

        // Steering
        double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
        double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
        double rx = gamepad1.right_stick_x; // Rotation is backwards

        if (board.inverseControlState) {
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

        board.frontLeftMotor.setPower(frontLeftPower);
        board.backLeftMotor.setPower(backLeftPower);
        board.frontRightMotor.setPower(frontRightPower);
        board.backRightMotor.setPower(backRightPower);

        // Arm
        if (gamepad2.x) { // going up
            board.armMotor.setPower(board.armPower);
        } if (gamepad2.y) { // going down
            board.armMotor.setPower(-board.armPower);
        } else {
            board.armMotor.setPower(0);
        }

        // Linear Extender
        if (gamepad2.b) {
            board.linearExtenderServo.setPower(-board.servoPower);
        } else if (gamepad2.a) {
            board.linearExtenderServo.setPower(board.servoPower);
        } else {
            board.linearExtenderServo.setPower(0);
        }

        // Opening/closing claw

        // I literally don't know why the trigger is a float, but here it is...
        if (gamepad2.left_trigger > 0.9) {
            board.clawOpenState = true;
            // telemetry.addData("OPENSTATE", "on");
        }
        if (gamepad2.right_trigger > 0.9) {
            board.clawOpenState = false;
            //telemetry.addData("OPENSTATE", "off");
        }

        //telemetry.addData("LeftController", gamepad2.left_trigger);
        //telemetry.addData("right controller", gamepad2.right_trigger);


        if (board.clawOpenState) {
            board.clawOpenerServo.setPosition(board.clawOpenPosition);
            //telemetry.addData("Claw Action", "Open");
        }
        if (!board.clawOpenState){
            board.clawOpenerServo.setPosition(board.clawClosedPosition);
            //telemetry.addData("Claw Action", "Close");
        }

        // rotating claw

        if (gamepad2.right_bumper) {
            board.rotatorPosition = board.rotatorPosition + board.deltaPosition;
            // telemetry.addData("RotatorPosition", rotatorPosition);
            // telemetry.addData("rotator button", "right");
        } else if (gamepad2.left_bumper) {
            board.rotatorPosition = board.rotatorPosition - board.deltaPosition;
            //telemetry.addData("RotatorPosition", rotatorPosition);
            // telemetry.addData("RotatorPosition", "left");
        }

        board.clawRotatorServo.setPosition(board.rotatorPosition);
    }
}
