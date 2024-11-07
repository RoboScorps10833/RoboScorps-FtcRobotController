package org.firstinspires.ftc.teamcode.mechanisms;

import org.firstinspires.ftc.teamcode.components.ProgrammingBoard;
import org.firstinspires.ftc.teamcode.controlsystem.StateMachine;

/**
 * Class for controlling the overall components of the robot,
 * like the claw, arm, and other things.
 */
public class Robot {
    ProgrammingBoard board;
    StateMachine stateMachine;

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

        // Not sure what else we need for now

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
    public void updateRobotState() {
        // the things we're using are external,
        // so we need to make sure we're updating the things we need.

        // Later Problem

    }
}
