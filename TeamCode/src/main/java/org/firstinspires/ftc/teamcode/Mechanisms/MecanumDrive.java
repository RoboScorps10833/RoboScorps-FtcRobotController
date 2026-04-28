package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class MecanumDrive extends OpMode {
    DcMotor frontLeftMotor;
    DcMotor frontRightMotor;
    DcMotor backLeftMotor;
    DcMotor backRightMotor;

    @Override
    public void init() {
        frontLeftMotor = hardwareMap.get(DcMotor.class,"frontLeftMotor");
        frontRightMotor = hardwareMap.get(DcMotor.class,"frontRightMotor");
        backLeftMotor = hardwareMap.get(DcMotor.class,"backLeftMotor");
        backRightMotor = hardwareMap.get(DcMotor.class,"backRightMotor");
    }
    public void moveDriveTrain() {
        double y = -gamepad1.left_stick_y;
        double x = gamepad1.right_stick_x;
        double rx = gamepad1.right_stick_x;
        // on line 23, if it's right or left

        double frontRightPower = y - x - rx;
        double frontLeftPower = y + x + rx;
        double backRightPower = y + x - rx;
        double backLeftPower = y - x + rx;

        frontRightMotor.setPower(frontRightPower);
        frontLeftMotor.setPower(frontLeftPower);
        backRightMotor.setPower(backRightPower);
        backLeftMotor.setPower(backLeftPower);

    }

    @Override
    public void init_loop() {

    }

    @Override
    public void loop() {
        moveDriveTrain();

    }

}


