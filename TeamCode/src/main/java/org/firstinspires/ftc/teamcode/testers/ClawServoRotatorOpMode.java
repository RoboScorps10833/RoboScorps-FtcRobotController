package org.firstinspires.ftc.teamcode.testers;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
@Disabled
public class ClawServoRotatorOpMode extends OpMode {
    CRServo clawRotatorServo;

    double rotatorPower = 0.25;

    @Override
    public void init() {

        clawRotatorServo = hardwareMap.get(CRServo.class, "ClawRotatorServo");
        //clawRotatorServo.setPosition(rotatorPosition);

        // Might need to set direction. Need to test it
        //clawRotatorServo.setDirection(Servo.Direction.REVERSE);

    }

    @Override
    public void loop() {

        if (gamepad1.right_bumper) {
            clawRotatorServo.setPower(-rotatorPower);
            telemetry.addData("Thing", "Right pressed");
        } else if (gamepad1.left_bumper) {
            clawRotatorServo.setPower(rotatorPower);
            telemetry.addData("Thing", "Left pressed");
        } else {
            clawRotatorServo.setPower(0);
            telemetry.addData("Thing", "Nothing");
        }

        //clawRotatorServo.setPosition(rotatorPosition);

    }
}
