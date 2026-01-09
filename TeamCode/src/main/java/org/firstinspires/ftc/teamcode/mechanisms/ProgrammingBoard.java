package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.hardware.motors.CRServoEx;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;
import com.seattlesolvers.solverslib.hardware.motors.MotorGroup;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

import org.firstinspires.ftc.robotcore.external.hardware.camera.Camera;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;

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
    public com.qualcomm.robotcore.hardware.CRServo gateServo;
    public MotorEx leftFlywheelMotor, rightFlywheelMotor;
    public MotorGroup FlywheelMotors;

    public WebcamName webcam;

    public void init(HardwareMap hardwareMap) {
        // -------------- Drivebase -------------- //
        frontLeftMotor = hardwareMap.get(DcMotorEx.class, "FrontLeftMotor");
        //frontLeftMotor.setDirection(DcMotorEx.Direction.REVERSE);
        frontRightMotor = hardwareMap.get(DcMotorEx.class, "FrontRightMotor");
        //frontRightMotor.setDirection(DcMotorEx.Direction.REVERSE);
        backLeftMotor = hardwareMap.get(DcMotorEx.class, "BackLeftMotor");
        backLeftMotor.setDirection(DcMotorEx.Direction.REVERSE);
        backRightMotor = hardwareMap.get(DcMotorEx.class, "BackRightMotor");
        backRightMotor.setDirection(DcMotorEx.Direction.REVERSE);

        // Intake
        intakeMotorInner = new MotorEx(hardwareMap, "IntakeMotorInner");
        intakeMotorOuter = new MotorEx(hardwareMap, "IntakeMotorOuter");

        intakeMotors = new MotorGroup(intakeMotorInner, intakeMotorOuter);
        intakeMotors.setRunMode(Motor.RunMode.RawPower);

        gateServo = hardwareMap.get(CRServo.class, "GateServo");

        leftFlywheelMotor = new MotorEx(hardwareMap, "LeftFlywheelMotor", 28, 6000);
        leftFlywheelMotor.setInverted(true);
        leftFlywheelMotor.setRunMode(Motor.RunMode.VelocityControl);
        leftFlywheelMotor.setVeloCoefficients(0.01, 0.0, 0.0);
        leftFlywheelMotor.setFeedforwardCoefficients(215.0, 1.1);

        rightFlywheelMotor = new MotorEx(hardwareMap, "RightFlywheelMotor", 28, 6000);
        rightFlywheelMotor.setRunMode(Motor.RunMode.VelocityControl);
        //rightFlywheelMotor.setInverted(true);
        rightFlywheelMotor.setVeloCoefficients(0.01, 0.0, 0.0);
        rightFlywheelMotor.setFeedforwardCoefficients(215.0, 1.2);

        // Outtake
//        FlywheelMotors = new MotorGroup(leftFlywheelMotor, rightFlywheelMotor);
//        FlywheelMotors.setRunMode(Motor.RunMode.VelocityControl);


        //FlywheelMotors.setVeloCoefficients(0,0,0);
        //FlywheelMotors.setFeedforwardCoefficients(0,0,0);


        webcam = hardwareMap.get(WebcamName.class, "Webcam 1"); // Left as default name
    }

    // TODO: Bulk reading method with Lynx Module
    // DONE: Add FTCLib for feedforward and pid controllers
    // TODO: Get LED bling >:)
    // TODO: Tune the motor's pidf...

}
