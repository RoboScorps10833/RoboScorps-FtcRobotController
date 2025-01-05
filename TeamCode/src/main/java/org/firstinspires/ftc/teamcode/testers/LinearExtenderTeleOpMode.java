/*
 * A linear extender testing teleop. In order to test this, the servo for the
 * linear extender and its bounds needs to be already set up.
 *
 * The up on the D pad is moving it up and down on the d pad makes it go down.
 */

package org.firstinspires.ftc.teamcode.testers;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
@Disabled
public class LinearExtenderTeleOpMode extends OpMode {
    Servo linearExtenderServo;
    double position;
    // In percent of the motor
    double change_position = 0.05;

//    ElapsedTime time = new ElapsedTime();

    @Override
    public void init() {
        linearExtenderServo = hardwareMap.get(Servo.class, "ArmMotor");

        // Robot is set to closed position by default
        position = 0;
        linearExtenderServo.setPosition(position);
    }


    @Override
    public void loop() {
        if (gamepad1.dpad_up){
            position = position + change_position;
        } else if (gamepad1.dpad_down) {
            position = position + change_position;
        }
        linearExtenderServo.setPosition(position);
    }

}
