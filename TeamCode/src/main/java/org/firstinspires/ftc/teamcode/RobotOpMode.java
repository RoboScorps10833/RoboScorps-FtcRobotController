/*
* This is a Template teleop for creating scripts.
*/

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

// OpMode decided by user
public class RobotOpMode extends LinearOpMode {

    // Put device variables here
    private DcMotor frontLeftMotor;
    private DcMotor backLeftMotor;
    private DcMotor frontRightMotor;
    private DcMotor backRightMotor;

    private void setup() {
        /*
         * All setup things should be called here
         */

        this.frontLeftMotor = hardwareMap.dcMotor.get("FrontLeftMotor");
        this.backLeftMotor = hardwareMap.dcMotor.get("BackLeftMotor");
        this.frontRightMotor = hardwareMap.dcMotor.get("FrontRightMotor");
        this.backRightMotor = hardwareMap.dcMotor.get("BackRightMotor");

        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        // backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

    }

    public void steer(double x, double y, double rx) {
        /*
        * A basic function that steers the robot based on a vector `(x,y)`
        * and an angle of rotation `rx`
        */

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
    }

    @Override
    public void runOpMode() throws InterruptedException {

        setup();

        // Boilerplate

        waitForStart();
        if (isStopRequested()) return;

        while (opModeIsActive()) {
            // Where the user puts there code when they write
            run();
        }

    }

    public void run() throws InterruptedException {
        // Empty to be over written
    }



}

