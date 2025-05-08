package org.firstinspires.ftc.teamcode.mechanisms;


import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.components.ProgrammingBoard;

/**
 * Class for controlling the overall components of the robot,
 * like the claw, arm, and other things.
 */
public class Robot {
    ProgrammingBoard board;

    private double armPower;

    private double linearExtenderPower;

    private boolean clawOpenState;
    private double clawClosedPosition;
    private double clawOpenPosition;

    private double rotatorPosition;
    private double deltaPosition;
    private double outer_position;

    private boolean inverseControlState;
    private double clawRotaterPower;

    private int armPosition;

    public Robot(ProgrammingBoard programming_board) {
        this.board = programming_board;
    }

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
        this.armPower = 0.5;
        this.armPosition = board.armMotor.getCurrentPosition();
        board.armMotor.setPower(this.armPower);
        board.armMotor.setTargetPosition(armPosition);

        /* Linear Extender */
        board.linearExtenderServo.setPower(0);
        this.linearExtenderPower = 0;

        /* Claw */
        // Opener stuff
        this.clawOpenState = false;
        this.clawClosedPosition = 3;
        this.clawOpenPosition = 0.8;

        // Rotation stuff
        this.rotatorPosition = 0.60; // Set to start in middle position
        this.clawRotaterPower = 0.5;
        this.outer_position = 0.25;


        //board.clawRotatorServo.setPosition(this.rotatorPosition);

        //board.clawPlacementServo.setDirection(Servo.Direction.REVERSE);

        /* Driver Control */
        this.inverseControlState = true;
    }

    /**
     * Where everything in the robot updates every loop
     * State based things
     */
    public void update() {
        //updateClawPosition();
        if (this.clawOpenState) {
            board.clawOpenerServo.setPosition(this.clawOpenPosition);
            //telemetry.addData("Claw Action", "Open");
        }
        if (!this.clawOpenState){
            board.clawOpenerServo.setPosition(this.clawClosedPosition);
            //telemetry.addData("Claw Action", "Close");
        }

        //updateClawRotation();
        //        updateArmPower();
//        updateLinearExtenderPower();
    }

    /**
     * Inverses the controls of the steering.
     * Is a toggle
     */
    public void inverseControls() {
        this.inverseControlState = !this.inverseControlState;
    }

    /**
     * Getter for current inverse control state
     * True = Inversed
     * False = Original direction
     * @return
     */
    public boolean getInversionState() {
        return this.inverseControlState;
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

        if (this.inverseControlState) {
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
        this.armPower = power;
        board.armMotor.setPower(this.armPower);
    }

    public void changeArmPosition(int change) {
        this.armPosition = this.armPosition + change;

       // if (this.armPosition < -800) { this.armPosition = -800; }

        board.armMotor.setTargetPosition(this.armPosition);


    }

    /**
     * Function to control arm power (temperary)
     * TODO Set PID on the arm
     * TODO Use Angle of the arm to control instead of power
     */
//    private void updateArmPower() {
//        board.armMotor.setPower(this.armPower);
//    }

    public void setLinearExtenderPower(double power) {
        this.linearExtenderPower = power;
        board.linearExtenderServo.setPower(this.linearExtenderPower);
    }

    public void setClawPower(double power) {
        board.clawRotatorServo.setPower(power);
    }

    /**
     * Function to control linear extender power (temperary)
     * TODO Set PID on the linear extender
     * TODO Figure out how to encoder this since we're using a continous servo
     * TODO Use linear extender in distance extended instead of power.
     */
//    private void updateLinearExtenderPower() {
//        board.linearExtenderServo.setPower(this.linearExtenderPower);
//    }

    /**
     * Sets state of claw to "open"
     */
    public void openClaw() {
        this.clawOpenState = true;
        updateClawPosition();
    }

    /**
     * Sets state of claw to "closed"
     */
    public void closeClaw() {
        this.clawOpenState = false;
        updateClawPosition();
    }


    private void updateClawPosition() {
        if (this.clawOpenState) {
            board.clawOpenerServo.setPosition(this.clawOpenPosition);
            //telemetry.addData("Claw Action", "Open");
        }
        if (!this.clawOpenState){
            board.clawOpenerServo.setPosition(this.clawClosedPosition);
            //telemetry.addData("Claw Action", "Close");
        }
    }


    /**
     * Opens and closes the the claw the amount you move it. Positive is moving clockwise
     *
     * TODO Make this based on position
     * TODO Wait for the others to finish the claw on the bot
     * TODO Add limits on what the rotation can be.
     *
     * @param direction Can either be CW or CCW
     */
    public void openClawRotation(String direction) {
        switch (direction) {
            case "CW":
                // this.rotatorPosition = this.rotatorPosition + deltaPosition;
                // board.clawRotatorServo.setPosition(this.rotatorPosition);
                board.clawRotatorServo.setPower(2*this.clawRotaterPower);
                break;
            case "CCW":
                // this.rotatorPosition = this.rotatorPosition - deltaPosition;
                //board.clawRotatorServo.setPosition(this.rotatorPosition);
                board.clawRotatorServo.setPower(-this.clawRotaterPower);
                break;
            default:
                // board.clawRotatorServo.setPosition(this.rotatorPosition);
                board.clawRotatorServo.setPower(0);
                break;
        }
    }

    /**
     * Do the same above, but with position
     *
     * TODO Make this based on position
     * TODO Wait for the others to finish the claw on the bot
     * TODO Add limits on what the rotation can be.
     *
     * @param direction Can either be CW or CCW
     *//*
    public void rotateClawPosition(double deltaPosition, String direction) {
        switch (direction) {
            case "CW":
                this.rotatorPosition = this.rotatorPosition + deltaPosition;
                board.clawRotatorServo.setPosition(this.rotatorPosition);
                break;
            case "CCW":
                this.rotatorPosition = this.rotatorPosition - deltaPosition;
                board.clawRotatorServo.setPosition(this.rotatorPosition);
                break;
            default:
                board.clawRotatorServo.setPosition(this.rotatorPosition);
                break;
        }
    }*/
    /**
     * Sets the claw rotation.
     */
    public void setClawRotation(double rotationPosition) {
        this.rotatorPosition = rotationPosition;
    }

    //private void updateClawRotation() {
    //    board.clawRotatorServo.setPosition(this.rotatorPosition);
    //}

}
