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

    private void deviceSetup() {
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

    // Classes
    DriveTrain driveTrain;
    driveTrain = new DriveTrain(this.frontLeftMotor, this.frontRightMotor, this.backLeftMotor, this.backRightMotor);


    @Override
    public void runOpMode() throws InterruptedException {

        deviceSetup();

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

