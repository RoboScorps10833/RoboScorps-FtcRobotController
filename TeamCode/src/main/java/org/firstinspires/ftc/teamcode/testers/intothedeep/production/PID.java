package org.firstinspires.ftc.teamcode.testers.intothedeep.production;

import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * It's a PID Controller. Duh
 */
public class PID {

    // Shamelessly implementing Ctrl-Alt-Ftc's PID controller
    // https://www.ctrlaltftc.com/the-pid-controller

    double Kp;
    double Ki;
    double Kd;

    double reference;

    double integralSum = 0;
    double lastError = 0;

    ElapsedTime timer = new ElapsedTime();

    public PID(double Kp, double Ki, double Kd) {
        this.Kp = Kp;
        this.Ki = Ki;
        this.Kd = Kd;
    }

    public double calculate(double target, double reference) {
        /**
         *
         * Implementation comes from a variety of resources
         * - https://www.ctrlaltftc.com/the-pid-controller
         * - http://brettbeauregard.com/blog/2011/04/improving-the-beginners-pid-introduction/
         */



        double error = reference - target; // Proportional
        this.integralSum = this.integralSum + (error * timer.seconds()); // Integral
        double derivative = (error - this.lastError) / timer.seconds(); // Derivative

        double out = (this.Kp * error) + (Ki * this.integralSum) + (this.Kd * derivative);

        // Reset
        this.lastError = error;
        this.timer.reset();

        return out;
    }

}
