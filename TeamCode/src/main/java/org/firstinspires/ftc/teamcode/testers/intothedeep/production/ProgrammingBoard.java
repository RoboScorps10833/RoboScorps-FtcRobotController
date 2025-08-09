package org.firstinspires.ftc.teamcode.testers.intothedeep.production;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * A class that abstracts away hardware mapping.
 * Simply use init() to init the board and you're done!
 */
public class ProgrammingBoard {
    /*
     *
     * In the future, I want to add some bling with LED and robot state,
     * with the LED strip color = robot state, but not now.
     * Would be fun to see for drivers.
     */

    public DcMotor frontLeftMotor;
    public DcMotor backLeftMotor;
    public DcMotor frontRightMotor;
    public DcMotor backRightMotor;

    public DcMotor armMotor;

    public DcMotor linearExtenderServo;

    public CRServo clawRotatorServo;
    public Servo clawOpenerServo;

    //public Servo clawPlacementServo;


    /**
     * Initalizes the hardware map for the robot
     * @param hardwareMap Put the OpMode's hardwareMap here
     */
    public void init(HardwareMap hardwareMap) {
        // Declare our motors
        // Make sure your ID's match your configuration
        frontLeftMotor = hardwareMap.dcMotor.get("FrontLeftMotor");
        backLeftMotor = hardwareMap.dcMotor.get("BackLeftMotor");
        frontRightMotor = hardwareMap.dcMotor.get("FrontRightMotor");
        backRightMotor = hardwareMap.dcMotor.get("BackRightMotor");

        armMotor = hardwareMap.get(DcMotor.class, "ArmMotor");

        armMotor.setTargetPosition(armMotor.getCurrentPosition());
        armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); // Don't kill your ears

        linearExtenderServo = hardwareMap.get(DcMotor.class, "LinearExtenderMotor");

        clawOpenerServo = hardwareMap.get(Servo.class, "ClawOpenerServo");
        clawRotatorServo = hardwareMap.get(CRServo.class, "ClawRotatorServo");
        //clawPlacementServo = hardwareMap.get(Servo.class,"ClawPlacementServo");

    }

    /*
    * Control things on the robot is done in the robot class.
    * OpMode calls robot, which calls hardware map
    */

}