/*
 * A linear extender testing teleop. In order to test this, the servo for the
 * linear extender and its bounds needs to be already set up.
 *
 * The up on the D pad is moving it up and down on the d pad makes it go down.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
@Disabled
public class LinearExtenderContinousTeleOpMode extends OpMode {
    CRServo linearExtenderServo;
    double power = 0.5;
    boolean flipped = false;

    @Override
    public void init() {
        linearExtenderServo = hardwareMap.get(CRServo.class, "LinearExtenderServo");
        linearExtenderServo.setPower(power);

        if (flipped) {power = power * -1;}
    }

    @Override
    public void loop() {

    }
}
