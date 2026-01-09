package org.firstinspires.ftc.teamcode.mechanisms;

import com.seattlesolvers.solverslib.command.SubsystemBase;

public class Flywheel extends SubsystemBase {

    ProgrammingBoard board;
    public boolean flywheelToggle = true;
    private double tickSpeed = 1000;

    public Flywheel(ProgrammingBoard board) {
        // Want to use the external board provided
        this.board = board;
    }

    /**
     * Turns on the Flywheel
     */
    public void spinUp() {
        board.rightFlywheelMotor.setVelocity(tickSpeed);
        board.leftFlywheelMotor.setVelocity(tickSpeed);

    }

    /**
     * Turns off the flywheel
     */
    public void spinDown() {
        board.rightFlywheelMotor.setVelocity(0);
        board.leftFlywheelMotor.setVelocity(0);

    }

    public void calculateFlywheelSpeeds() {
        // For future use if need be.

        // TODO: Do tests to generate linear regression curve for shooter
    }
}
