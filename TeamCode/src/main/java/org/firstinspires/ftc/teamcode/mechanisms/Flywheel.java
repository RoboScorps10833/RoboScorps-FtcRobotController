package org.firstinspires.ftc.teamcode.mechanisms;

import com.seattlesolvers.solverslib.command.SubsystemBase;

public class Flywheel extends SubsystemBase {

    ProgrammingBoard board;

    public Flywheel(ProgrammingBoard board) {
        // Want to use the external board provided
        this.board = board;
    }

    /**
     * Turns on the Flywheel
     */
    public void spinUp() {
        board.FlywheelMotors.set(1);
    }

    /**
     * Turns off the flywheel
     */
    public void spinDown() {
        board.FlywheelMotors.set(0);
    }

    public void calculateFlywheelSpeeds() {
        // For future use if need be.

        // TODO: Do tests to generate linear regression curve for shooter
    }
}
