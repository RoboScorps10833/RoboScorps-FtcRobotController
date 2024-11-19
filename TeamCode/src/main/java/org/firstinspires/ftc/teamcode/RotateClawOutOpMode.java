/*
 * A script for testing out if the servo for extending out the claw.
 * Press the start button to test this out
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

public class RotateClawOutOpMode extends OpMode {
    Servo clawPlacementServo;
    double clawOutPosition = 1.0;
    double clawRetractedPosition = 0.05;


    @Override
    public void init() {
        clawPlacementServo = hardwareMap.get(Servo.class, "ClawPlacementServo");
        clawPlacementServo.setPosition(clawRetractedPosition);
    }

    @Override
    public void loop() {
        // press start button to start
        if (gamepad1.start) {
            clawPlacementServo.setPosition(clawOutPosition);
        }
    }
}
