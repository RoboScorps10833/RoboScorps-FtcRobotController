package org.firstinspires.ftc.teamcode.testers.intothedeep;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
@TeleOp(name="Linear Extender", group="Extender")
@Disabled
public class LinearExtenderContinousOpMode extends OpMode {
    CRServo linearExtenderServo;
    double power = 0.5;

    @Override
    public void init() {
        linearExtenderServo = hardwareMap.get(CRServo.class, "LinearExtenderServo");
        linearExtenderServo.setPower(0);

    }

    @Override
    public void loop() {
        if (gamepad1.x) {
            linearExtenderServo.setPower(-power);
        } else if (gamepad1.y) {
            linearExtenderServo.setPower(power);
        } else {
            linearExtenderServo.setPower(0);
        }
    }
}
