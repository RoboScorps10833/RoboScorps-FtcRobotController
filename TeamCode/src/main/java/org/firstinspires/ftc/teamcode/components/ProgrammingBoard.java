package org.firstinspires.ftc.teamcode.components;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

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
    public double armPower;

    public CRServo linearExtenderServo;
    public double servoPower;

    public Servo clawRotatorServo;
    public Servo clawOpenerServo;

    public boolean clawOpenState;
    public double clawClosedPosition;
    public double clawOpenPosition;

    public double rotatorPosition;
    public double deltaPosition;
    public double outer_position;

    //Servo clawPlacementServo;

    public boolean inverseControlState;

    public void init(HardwareMap hardwareMap) {
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

    /*
    * Going to control things on the robot in the robot class.
    * OpMode calls robot, which calls hardware map
    */

}