package org.firstinspires.ftc.teamcode.testers.intothedeep;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name="Claw Init Position Tester", group="Claw")
@Disabled
public class ClawPositionSetter extends OpMode {
    Servo clawPlacementServo;
    double outer_position = 1.0;

    @Override
    public void init() {
        clawPlacementServo = hardwareMap.get(Servo.class,"ClawPlacementServo");
        clawPlacementServo.setPosition(outer_position);
    }

    @Override
    public void loop() {

    }
}
