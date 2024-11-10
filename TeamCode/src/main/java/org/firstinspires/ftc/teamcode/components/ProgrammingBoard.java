package org.firstinspires.ftc.teamcode.components;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

/**
 * A class that is used to wrap around all the components on the robot, as well as provide a way
 * to easily initialize all of the components in the robot when creating OpModes
 */
public class ProgrammingBoard {
    /*
     * TODO:
     *
     * Need to implement the following things:
     *   - motors
     *     - drivetrain
     *   - servo
     *     - arm
     *   - touch sensor
     *     - armrest
     *   - imu
     * In the future, I want to add some bling with LED and robot state,
     * with the LED strip color = robot state, but not now.
     * Would be fun to see for drivers.
     */

    // Using FtcLib to provide methods
    public MotorEx[] drivetrainMotors = new MotorEx[4];
    // Aliases for motors
    public MotorEx frontLeftMotor;
    public MotorEx frontRightMotor;
    public MotorEx backLeftMotor;
    public MotorEx backRightMotor;

    // might need to define the gamepad elsewhere, somehow
    //public GamepadEx = new GamepadEx(gamepad1);

    public IMU imu;

    /**
     * Abstracts the initialization of all the hardware on the robot
     *
     * @param hwMap plug in `hardwareMap` here
     */
    public void initialize(HardwareMap hwMap) {
        drivetrainMotors[0] = new MotorEx(hwMap, "frontLeftMotor");
        drivetrainMotors[1] = new MotorEx(hwMap, "frontRightMotor");
        drivetrainMotors[2] = new MotorEx(hwMap, "backLeftMotor");
        drivetrainMotors[3] = new MotorEx(hwMap, "backRightMotor");

        frontLeftMotor = drivetrainMotors[0];
        frontRightMotor = drivetrainMotors[1];
        backLeftMotor = drivetrainMotors[2];
        backRightMotor = drivetrainMotors[3];

        /*TODO:
        *  Tune the motors for FtcLib
        *  Set the Coeffecents for every motor
        *  Set the mode of each motor
        * */

        // I'm not sure where the USB is facing, but this is OK for now.
        RevHubOrientationOnRobot RevOrientation = new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD
        );

        imu.initialize(new IMU.Parameters(RevOrientation));

    }

    /* Gamepad methods */
    // I would like to define them here, but I can't use gamepad1 nro 2 here
    // implentation later problem


    /* Motor Methods
    *
    * Using FtcLib to take care of this stuff, but might need some other wrappers
    */


    /* Servos */


    /* Touch Sensor */


    /* imu */

}
