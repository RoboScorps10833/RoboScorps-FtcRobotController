package org.firstinspires.ftc.teamcode.mechanisms;

import com.arcrobotics.ftclib.drivebase.MecanumDrive;

import org.firstinspires.ftc.teamcode.components.ProgrammingBoard;
import org.firstinspires.ftc.teamcode.controlsystem.StateMachine;

/**
 * Class for controlling the overall components of the robot,
 * like the claw, arm, and other things.
 */
public class Robot {
    private ProgrammingBoard board;
    private StateMachine stateMachine;
    private MecanumDrive driveBase;

    /*
     * I also need a heading attribute, which comes from the state machine class
     * and is updated in the robot.update() function.
     */

    /**
     * Constructor for the robot class
     *
     * @param externalBoard Takes in an external `ProgrammingBoard`
     * @param externalMachine Takes in an external `StateMachine`
     *
     */
    public Robot(ProgrammingBoard externalBoard, StateMachine externalMachine) {

        // we want to use the external user's stuff, not ours
        board = externalBoard;
        stateMachine = externalMachine;

        driveBase = new MecanumDrive(
                board.frontLeftMotor,
                board.frontRightMotor,
                board.backLeftMotor,
                board.backRightMotor
        );


    }

    public void initialize() {
        // Just for setting up the stray variables inside
    }

    /* For later implementation. for debugging and monitoring purposes
    public String toString() {

    }
    */

    /**
     * Throw this into the init_loop section of the robot.
     *
     */
    public void update() {
        // the things we're using are external,
        // so we need to make sure we're updating the things we need.

        // Later Problem

    }

    /**
     * A function stolen from gm0 when first learning how to use mecanum wheels.
     * It's been put here for future use, but please don't use this.
     * Note: This is field centric steering.
     *
     * It is much prefered to use the drive methods
     *
     * @param leftstick_x put it here
     * @param leftstick_y put it here
     * @param rightstick_x put it here
     * @param strafe_multiplier you can just put it at 1.1
     */
    public void legacyControllerSteer(double leftstick_x, double leftstick_y, double rightstick_x, double strafe_multiplier) {
        double y = -leftstick_y; // Remember, Y stick value is reversed
        double x = leftstick_x * strafe_multiplier; // Counteract imperfect strafing
        double rx = rightstick_x; // Rotation is backwards

        // Denominator is the largest motor power (absolute value) or 1
        // This ensures all the powers maintain the same ratio,
        // but only if at least one is out of the range [-1, 1]
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double frontLeftPower = (y + x + rx) / denominator;
        double backLeftPower = (y - x + rx) / denominator;
        double frontRightPower = (y - x - rx) / denominator;
        double backRightPower = (y + x - rx) / denominator;

        // Go around FtcLib and set the power of the actual motor
        board.frontLeftMotor.motor.setPower(frontLeftPower);
        board.frontRightMotor.motor.setPower(frontRightPower);
        board.backLeftMotor.motor.setPower(backLeftPower);
        board.backRightMotor.motor.setPower(backRightPower);
    }
}
