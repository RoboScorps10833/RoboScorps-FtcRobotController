package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
@Disabled
public class ClawRotatorOpMode extends OpMode {
    Servo clawRotatorServo;

    // Rotation stuff
    double rotatorPosition = 0.5; // always set to start in middle
    double deltaPosition = 0.05;

    @Override
    public void init() {

        clawRotatorServo = hardwareMap.get(Servo.class, "ClawRotatorServo");
        clawRotatorServo.setPosition(rotatorPosition);

        // Might need to set direction. Need to test it
        //clawRotatorServo.setDirection(Servo.Direction.REVERSE);

    }

    @Override
    public void loop() {

        if (gamepad1.right_bumper) {
            rotatorPosition = rotatorPosition + deltaPosition;
        } else if (gamepad1.left_bumper) {
            rotatorPosition = rotatorPosition - deltaPosition;
        }

        clawRotatorServo.setPosition(rotatorPosition);

    }
}
