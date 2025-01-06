package org.firstinspires.ftc.teamcode.mechanisms;


import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.components.ProgrammingBoard;

/**
 * Class for controlling the overall components of the robot,
 * like the claw, arm, and other things.
 */
public class Robot {
    ProgrammingBoard board = new ProgrammingBoard();

    private double armPower;

    private double linearExtenderPower;

    private boolean clawOpenState;
    private double clawClosedPosition;
    private double clawOpenPosition;

    private double rotatorPosition;
    private double deltaPosition;
    private double outer_position;

    private boolean inverseControlState;


    /**
     * The Initalization function for the robot class, which sets up the robot
     * This must be called before using the class.
     */
    public void init() {
        /* Wheels */

        // Reverse the right side motors. This may be wrong for your setup.
        // If your robot moves backwards when commanded to go forwards,
        // reverse the left side instead.
        // See the note about this earlier on this page.
        board.frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        // backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        board.frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        board.backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        /* Arm */
        armPower = 0;

        /* Linear Extender */
        board.linearExtenderServo.setPower(0);
        linearExtenderPower = 0;

        /* Claw */
        // Opener stuff
        clawOpenState = false;
        clawClosedPosition = 0.0;
        clawOpenPosition = 0.9;

        // Rotation stuff
        rotatorPosition = 0.60; // Set to start in middle position

        outer_position = 0.25;

        board.clawOpenerServo.setPosition(clawClosedPosition);

        //board.clawRotatorServo.setPosition(rotatorPosition);

        //board.clawPlacementServo.setDirection(Servo.Direction.REVERSE);

        /* Driver Control */
        inverseControlState = false;
    }

    /**
     * Where everything in the robot updates every loop
     * State based things
     */
    public void update() {
        updateClawPosition();
        updateClawRotation();
        updateArmPower();
        updateLinearExtenderPower();
    }

    /**
     * Inverses the controls of the steering.
     * Is a toggle
     */
    public void inverseControls() {
        inverseControlState = !inverseControlState;
    }

    /**
     * Getter for current inverse control state
     * True = Inversed
     * False = Original direction
     * @return
     */
    public boolean getInversionState() {
        return inverseControlState;
    }

    /**
     * Steers mecanum wheels given the vectors x, y, and rx.
     * Stolen from GM0
     * @param x
     * @param y
     * @param rx
     *
     * TODO Set PID control
     * TODO Set this up with FTCLib instead of stolen gm0 code
     * TODO Figure out how to use Roadrunner for auto
     */
    public void steer (double x, double y, double rx) {

        if (inverseControlState) {
            y = y * -1;
            x = x * -1;
            //rx = rx * -1;
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

    public void setArmPower(double power) {
        armPower = power;
    }

    /**
     * Function to control arm power (temperary)
     * TODO Set PID on the arm
     * TODO Use Angle of the arm to control instead of power
     */
    private void updateArmPower() {
        board.armMotor.setPower(armPower);
    }

    public void setLinearExtenderPower(double power) {
        linearExtenderPower = power;
    }

    /**
     * Function to control linear extender power (temperary)
     * TODO Set PID on the linear extender
     * TODO Figure out how to encoder this since we're using a continous servo
     * TODO Use linear extender in distance extended instead of power.
     */
    private void updateLinearExtenderPower() {
        board.linearExtenderServo.setPower(linearExtenderPower);
    }

    /**
     * Sets state of claw to "open"
     */
    public void openClaw() {
        clawOpenState = true;
    }

    /**
     * Sets state of claw to "closed"
     */
    public void closeClaw() {
        clawOpenState = false;
    }
    private void updateClawPosition() {
        if (clawOpenState) {
            board.clawOpenerServo.setPosition(clawOpenPosition);
            //telemetry.addData("Claw Action", "Open");
        }
        if (!clawOpenState){
            board.clawOpenerServo.setPosition(clawClosedPosition);
            //telemetry.addData("Claw Action", "Close");
        }
    }

    /**
     * Moves the claw the amount you move it. Positive is moving clockwise
     *
     * TODO Make this based on position
     * TODO Wait for the others to finish the claw on the bot
     * TODO Add limits on what the rotation can be.
     *
     * @param direction Can either be CW or CCW
     */
    public void rotateClaw(double deltaPosition, String direction) {
        switch (direction) {
            case "CW":
                rotatorPosition = rotatorPosition + deltaPosition;
                break;
            case "CCW":
                rotatorPosition = rotatorPosition - deltaPosition;
                break;
            default:
                break;
        }
    }

    /**
     * Sets the claw rotation.
     */
    public void setClawRotation(double rotationPosition) {
        rotatorPosition = rotationPosition;
    }

    private void updateClawRotation() {
        board.clawRotatorServo.setPosition(rotatorPosition);
    }
}
