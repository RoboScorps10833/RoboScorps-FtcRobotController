
/*
 * Made by Pearl Kamalu on June 3, following the tutorial on
 * game manual 0 for field centric controls.
 * 
 * https://gm0.org/en/latest/docs/software/tutorials/mecanum-drive.html
 *  
 * Single controler Operation Mode
 * 
 */

package org.firstinspires.ftc.teamcode.testers;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
//@Disabled
public class MecanumWithInverseTeleOp extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        // Declare our motors
        // Make sure your ID's match your configuration
        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("FrontLeftMotor");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("BackLeftMotor");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("FrontRightMotor");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("BackRightMotor");

       // DcMotor armMotor = hardwareMap.get(DcMotor.class, "ArmMotor");

        // Reverse the right side motors. This may be wrong for your setup.+
        
        // If your robot moves backwards when commanded to go forwards,
        // reverse the left side instead.
        // See the note about this earlier on this page.
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        // backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        double regularMultiplyer = 1;

        waitForStart();

        boolean inverseControlState = false;

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            if (gamepad1.dpad_down) {
                inverseControlState = true;
            }

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
/*
            if (gamepad1.x) { // going up
                armMotor.setPower(regularMultiplyer);
            } if (gamepad1.y) { // going down
                armMotor.setPower(-regularMultiplyer);
            } else {
                armMotor.setPower(0);
            }
*/
        }
    }
}