package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;
import com.seattlesolvers.solverslib.hardware.motors.MotorGroup;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

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

    public MotorEx intakeMotorInner, intakeMotorOuter;
    public MotorGroup intakeMotors;
    public ServoEx gateServo;
    public MotorEx leftFlywheelMotor, rightFlywheelMotor;
    public MotorGroup FlywheelMotors;

    public void init(HardwareMap hardwareMap) {
        // -------------- Drivebase -------------- //
        frontLeftMotor = hardwareMap.get(DcMotorEx.class, "FrontLeftMotor");
        //frontLeftMotor.setDirection(DcMotorEx.Direction.REVERSE);
        frontRightMotor = hardwareMap.get(DcMotorEx.class, "FrontRightMotor");
        //frontRightMotor.setDirection(DcMotorEx.Direction.REVERSE);
        backLeftMotor = hardwareMap.get(DcMotorEx.class, "BackLeftMotor");
        //backLeftMotor.setDirection(DcMotorEx.Direction.REVERSE);
        backRightMotor = hardwareMap.get(DcMotorEx.class, "BackRightMotor");
        //backRightMotor.setDirection(DcMotorEx.Direction.REVERSE);

        // Intake
        intakeMotorInner = new MotorEx(hardwareMap, "IntakeMotorInner");
        intakeMotorOuter = new MotorEx(hardwareMap, "IntakeMotorOuter");

        intakeMotors = new MotorGroup(intakeMotorInner, intakeMotorOuter);
        intakeMotors.setRunMode(Motor.RunMode.RawPower);

        gateServo = new ServoEx(hardwareMap, "GateServo",0 , 100);
        gateServo.setInverted(false);

        leftFlywheelMotor = new MotorEx(hardwareMap, "LeftFlywheelMotor");
        rightFlywheelMotor = new MotorEx(hardwareMap, "RightFlywheelMotor");


        // Outtake
        FlywheelMotors = new MotorGroup(leftFlywheelMotor, rightFlywheelMotor);
        FlywheelMotors.setRunMode(Motor.RunMode.RawPower);
        //FlywheelMotors.setVeloCoefficients(0,0,0);
        //FlywheelMotors.setFeedforwardCoefficients(0,0,0);


    }

    // TODO: Bulk reading method with Lynx Module
    // DONE: Add FTCLib for feedforward and pid controllers
    // TODO: Get LED bling >:)
    // TODO: Tune the motor's pidf...

}
