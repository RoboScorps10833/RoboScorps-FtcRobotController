package org.firstinspires.ftc.teamcode.mechanisms;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;

public class Intake {

    ProgrammingBoard board;

    double rollerPower;
    double reversePower;
    int openGateAngle;
    int closedGateAngle;

    double currentThreshold;

    public Intake(ProgrammingBoard board) {
        // Make sure we want to use the object passed to this
        this.board = board;

        rollerPower = 0.9;
        reversePower = -0.5;

        openGateAngle = 0;
        closedGateAngle = 90;

        // Find this in an opmode for the future
        currentThreshold = 0.0;

        // TODO: Create a counter for the balls in the intake
        // TODO: Add distance and color sensor

    }

    /**
     * Turns on the roller wheel assembly. Should be implemented as continious
     * since method also has an unstuck feature for the roller intake as well.
     */
    public void spinUp() {
        board.intakeMotors.set(rollerPower);

        if (board.intakeMotorInner.getCurrent(CurrentUnit.AMPS)>currentThreshold) {
            board.intakeMotors.set(reversePower);
        }
    }

    /**
     * Turns off the roller wheel assembly
     */
    public void spinDown() {
        board.intakeMotors.set(0);
    }


    /**
     * Turns the gate to the open position
     */
    public void openGate() {
        board.gateServo.set(openGateAngle);
    }

    /**
     * Turns the gate to the closed position
     */
    public void closeGate() {
        board.gateServo.set(closedGateAngle);
    }

    public void unstuckRollers() {
        board.intakeMotors.set(reversePower);
    }
}
