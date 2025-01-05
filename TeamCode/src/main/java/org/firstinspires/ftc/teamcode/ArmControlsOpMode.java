package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
@Disabled
public class ArmControlsOpMode extends OpMode {
    DcMotor armMotor;
    // Arbitrary multiplyer to make the thing bigger.
    // Don't make bigger than one
    double regularMultiplyer = 0.5;
    double turboMultiplyer = 1.0;

    boolean turboMode = false;

    @Override
    public void init() {
        armMotor = hardwareMap.get(DcMotor.class, "ArmMotor");
        // Not sure if we need to reverse direction
        //armMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void loop() {
        // changing turbo and regular mode
        if (gamepad2.x) {
            turboMode = false;
        } else if (gamepad2.b) {
            turboMode = true;
        }

        // controls
        if (gamepad2.y) { // going up
            if (turboMode) {
                armMotor.setPower(turboMultiplyer);
            } else {
                armMotor.setPower(regularMultiplyer);
            }
        } if (gamepad2.a) { // going down
            if (turboMode) {
                armMotor.setPower(-turboMultiplyer);
            } else {
                armMotor.setPower(-regularMultiplyer);
            }
        } else {
            armMotor.setPower(0);
        }

    }
}
