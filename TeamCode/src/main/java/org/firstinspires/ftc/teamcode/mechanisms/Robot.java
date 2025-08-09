package org.firstinspires.ftc.teamcode.mechanisms;

/**
 * Class that implements the bot's mechanisms and abstracts it away.
 * Also acts as an interface to the mechanism's actions for Roadrunner.
 */
public class Robot {
    // Call if you need hardware
    ProgrammingBoard board;

    // Variables go here so you can call them inside methods
    private boolean controlsInversed;

    public Robot(ProgrammingBoard board) {
        this.board = board;
    }

    /**
     * The Initialization of the robot. Place this in `init()` of the TeleOp.
     */
    public void init() {
        this.controlsInversed = false;
    }

    /**
     * Updates the robot and its functions at every loop
     */
    public void update() {
        // Hint: Program for one loop, then update here
    }

    /**
     * Steers robot in x, y, and angle described.
     * Robot centric mecanum steering function for TeleOp.
     * (Can't use roadrunner in real time TT)
     *
     * @param x Power to move forward
     * @param y Power to strafe (move side to side)
     * @param rx Change in angle
     *
     *  Shamelessly stolen from gm0.
     */
    public void steer(double x, double y, double rx) {
        if (this.controlsInversed) {
            y = y * -1;
            x = x * -1;
        }

        // Denominator is the largest motor power (absolute value) or 1
        // This ensures all the powers maintain the same ratio,
        // but only if at least one is out of the range [-1, 1]
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double frontLeftPower = (y + x + rx) / denominator;
        double backLeftPower = (y - x + rx) / denominator;
        double frontRightPower = (y - x - rx) / denominator;
        double backRightPower = (y + x - rx) / denominator;

        board.frontLeftMotor.setPower(frontLeftPower);
        board.backLeftMotor.setPower(backLeftPower);
        board.frontRightMotor.setPower(frontRightPower);
        board.backRightMotor.setPower(backRightPower);
    }

    /**
     * Inverses the controls. Is a toggle.
     */
    public void inverseControls() {
        this.controlsInversed = !this.controlsInversed;
    }

    // Components should be subclasses of this class Robot to use the board
    // Actions should not be of the class that they implement, but of the robot class

    // TODO: Create Classes for the individual Mecnhanisms
}
