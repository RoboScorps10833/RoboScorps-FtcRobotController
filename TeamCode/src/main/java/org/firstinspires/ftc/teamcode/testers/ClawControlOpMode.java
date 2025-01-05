package org.firstinspires.ftc.teamcode.testers;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
@Disabled
public class ClawControlOpMode extends OpMode {
    Servo clawRotatorServo;
    Servo clawOpenerServo;

    // Opener stuff
    boolean clawOpenState = false;
    double clawClosedPosition = 0.0;
    double clawOpenPosition = 0.9;

    // Rotation stuff
    double rotatorPosition = 0.5; // always set to start in middle
    double deltaPosition = 0.001;

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
        if (gamepad1.left_trigger > 0.9) {
            clawOpenState = true;
           // telemetry.addData("OPENSTATE", "on");
        }
        if (gamepad1.right_trigger > 0.9) {
            clawOpenState = false;
            //telemetry.addData("OPENSTATE", "off");
        }

        //telemetry.addData("LeftController", gamepad1.left_trigger);
        //telemetry.addData("right controller", gamepad1.right_trigger);


        if (clawOpenState == true) {
            clawOpenerServo.setPosition(clawOpenPosition);
            telemetry.addData("Claw Action", "Open");
        }
        if (!clawOpenState){
            clawOpenerServo.setPosition(clawClosedPosition);
            telemetry.addData("Claw Action", "Close");
        }

        // rotating claw

        if (gamepad1.right_bumper) {
            rotatorPosition = rotatorPosition + deltaPosition;
            telemetry.addData("RotatorPosition", rotatorPosition);
            telemetry.addData("rotator button", "right");
        } else if (gamepad1.left_bumper) {
            rotatorPosition = rotatorPosition - deltaPosition;
            telemetry.addData("RotatorPosition", rotatorPosition);
            telemetry.addData("RotatorPosition", "left");
        }

        clawRotatorServo.setPosition(rotatorPosition);

    }
}
