package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class ClawControlOpMode extends OpMode {
    Servo clawRotatorServo;
    Servo clawOpenerServo;

    // Opener stuff
    boolean clawOpenState = false;
    double clawClosedPosition = 0.01;
    double clawOpenPosition = 0.9;

    // Rotation stuff
    double rotatorPosition = 0.5; // always set to start in middle
    double deltaPosition = 0.05;

    @Override
    public void init() {
        clawOpenerServo = hardwareMap.get(Servo.class, "ClawOpenerServo");
        clawOpenerServo.setPosition(clawClosedPosition);

        clawRotatorServo = hardwareMap.get(Servo.class, "ClawRotatorServo");
        clawRotatorServo.setPosition(rotatorPosition);

        // Might need to set direction. Need to test it

    }

    @Override
    public void loop() {
        // Opening/closing claw

        // I literally don't know why the trigger is a float, but here it is...
        if (gamepad2.left_trigger > 0.9) {
            clawOpenState = true;
        } else if (gamepad2.right_trigger > 0.9) {
            clawOpenState = false;
        }

        if (clawOpenState) {
            clawOpenerServo.setPosition(clawOpenPosition);
        } else if (!clawOpenState) {
            clawOpenerServo.setPosition(clawClosedPosition);
        }

        // rotating claw

        if (gamepad2.right_bumper) {
            rotatorPosition = rotatorPosition + deltaPosition;
        } else if (gamepad2.left_bumper) {
            rotatorPosition = rotatorPosition - deltaPosition;
        }

        clawRotatorServo.setPosition(rotatorPosition);

    }
}
