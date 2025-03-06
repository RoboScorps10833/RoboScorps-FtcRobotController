package org.firstinspires.ftc.teamcode.testers;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
//@Disabled
public class ArmEncoderControl extends OpMode {
    DcMotor armMotor;
    int position;
    int difference = 5;

    @Override
    public void init() {
        armMotor = hardwareMap.get(DcMotor.class, "ArmMotor");
        armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        position = armMotor.getCurrentPosition();

        armMotor.setTargetPosition(position);
        armMotor.setPower(0.5);
        armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    @Override
    public void loop() {
        if (gamepad1.y) {
            position = position + difference;
            //armMotor.setPower(0.5);
        } else if (gamepad1.x) {
            position = position - difference;
            //armMotor.setPower(-0.5);
        } else {
           // armMotor.setPower(0);
        }

        if (position < -800) { position = -800; }

        armMotor.setTargetPosition(position);
        telemetry.addData("Current Position", position);
        telemetry.addData("Moving?", armMotor.isBusy());

    }
}
