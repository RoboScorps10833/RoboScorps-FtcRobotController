
package org.firstinspires.ftc.teamcode.testers;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
@Disabled
public class ClawPositionSetterOpMode extends OpMode {
    Servo clawOpenerServo;

    // Opener stuff
    boolean clawOpenState = false;
    double clawClosedPosition = 0.01;
    double clawOpenPosition = 0.9;



    @Override
    public void init() {
        clawOpenerServo = hardwareMap.get(Servo.class, "ClawOpenerServo");
        clawOpenerServo.setPosition(clawClosedPosition);


        // Might need to set direction. Need to test it

    }

    @Override
    public void loop() {
        // Opening/closing claw

        // I literally don't know why the trigger is a float, but here it is...
        if (gamepad1.left_trigger > 0.9) {
            clawOpenState = true;
        } else if (gamepad1.right_trigger > 0.9) {
            clawOpenState = false;
        }

        if (clawOpenState) {
            clawOpenerServo.setPosition(clawOpenPosition);
        } else if (!clawOpenState) {
            clawOpenerServo.setPosition(clawClosedPosition);
        }

        // rotating claw

    }
}
