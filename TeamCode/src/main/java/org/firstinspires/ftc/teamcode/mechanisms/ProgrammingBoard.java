package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * A class that abstracts away hardware mapping and setting making.
 * Based on the programming board on "Learn Java for FTC"
 */
public class ProgrammingBoard {
    /*
     * Control things on the robot is done in the robot class.
     * OpMode calls robot, which calls hardware map
     *
     * TLDR: Board initalizes, Robot handles loop, OpMode does controls.
     */

    // Help with the motors
    // https://ftctechnh.github.io/ftc_app/doc/javadoc/com/qualcomm/robotcore/hardware/DcMotorEx.html
    public DcMotorEx frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor;
    public DcMotorEx leftFlywheelMotor, rightFlywheelMotor, intakeMotor;

    public void init(HardwareMap hardwareMap) {
        // -------------- Drivebase -------------- //
        frontLeftMotor = hardwareMap.get(DcMotorEx.class, "FrontLeftMotor");
        //frontLeftMotor.setDirection(DcMotorEx.Direction.REVERSE);
        frontRightMotor = hardwareMap.get(DcMotorEx.class, "FrontRightMotor");
        //frontRightMotor.setDirection(DcMotorEx.Direction.REVERSE);
        backLeftMotor = hardwareMap.get(DcMotorEx.class, "BackLeftMotor");
        //backLeftMotor.setDirection(DcMotorEx.Direction.REVERSE);
        backRightMotor = hardwareMap.get(DcMotorEx.class, "BackRightMotor");
        backRightMotor.setDirection(DcMotorEx.Direction.REVERSE);

        leftFlywheelMotor = hardwareMap.get(DcMotorEx.class, "LeftFlywheelMotor");
        rightFlywheelMotor = hardwareMap.get(DcMotorEx.class, "RightFlywheelMotor");
        intakeMotor = hardwareMap.get(DcMotorEx.class, "IntakeMotor");
    }

    // TODO: Bulk reading method with Lynx Module
    // DONE: Add FTCLib for feedforward and pid controllers
    // TODO: Get LED bling >:)
    // TODO: Tune the motor's pidf...

}
