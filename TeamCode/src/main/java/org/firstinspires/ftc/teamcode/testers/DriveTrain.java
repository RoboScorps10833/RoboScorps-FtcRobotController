package org.firstinspires.ftc.teamcode.testers;

import com.qualcomm.robotcore.hardware.DcMotor;

public class DriveTrain {
    /*
     * A class for implementing the DriveTrain Controls
     * Using Imlementation instead of Inhearatance for functionalities
     * That way, we only need to worry about one class to use, as well
     * as that everything is contanierized into it's own pretty little box
     *
     * Things to do
     * - Figure out how to throw roadrunner into this
     * - Figure out how I'm going to tune this
     * - How to implement PID control into here
     */

    DcMotor FrontLeftMotor;
    DcMotor FrontRightMotor;
    DcMotor BackLeftMotor;
    DcMotor BackRightMotor;

    DriveTrain(DcMotor front_left_motor,
               DcMotor front_right_motor,
               DcMotor back_left_motor,
               DcMotor back_right_motor) {

        this.FrontLeftMotor = front_left_motor;
        this.FrontRightMotor = front_right_motor;
        this.BackLeftMotor = back_left_motor;
        this.BackRightMotor = back_right_motor;

    }

    public void steer(double x, double y, double rx) {
        /*
         * A basic function that steers the robot based on a vector `(x,y)`
         * and an angle of rotation `rx`
         */

        // Denominator is the largest motor power (absolute value) or 1
        // This ensures all the powers maintain the same ratio,
        // but only if at least one is out of the range [-1, 1]
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double frontLeftPower = (y + x + rx) / denominator;
        double backLeftPower = (y - x + rx) / denominator;
        double frontRightPower = (y - x - rx) / denominator;
        double backRightPower = (y + x - rx) / denominator;

        this.FrontLeftMotor.setPower(frontLeftPower);
        this.FrontRightMotor.setPower(backLeftPower);
        this.BackLeftMotor.setPower(frontRightPower);
        this.BackRightMotor.setPower(backRightPower);
    }


}
