/*
 * A simple op mode that tests the function of the robot.
 * It just makes the arm go up in one direction.
 * You can change the direction in the script
 *
 */

package org.firstinspires.ftc.teamcode.testers.intothedeep;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name="Claw Servo Init Position Tester", group="Claw")
@Disabled
public class ClawOpenerTestMode extends OpMode {
    Servo clawOpenerServo;
    double power = 0.75;
    boolean flipped = true;


    @Override
    public void init() {
        clawOpenerServo = hardwareMap.get(Servo.class, "ClawOpenerServo");
        clawOpenerServo.setPosition(0.5);

        if (flipped) {power = power * -1;}
    }


    @Override
    public void loop() {


    }


}
