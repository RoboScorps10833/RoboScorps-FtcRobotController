package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

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
        frontRightMotor.setDirection(DcMotor.Direction.FORWARD);
        frontLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backRightMotor.setDirection(DcMotor.Direction.REVERSE);
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRightMotor.setPower(frontRightPower);
        frontLeftMotor.setPower(frontLeftPower);
        backRightMotor.setPower(backRightPower);
        backLeftMotor.setPower(backLeftPower);

    }

    @Override
    public void init_loop() {

    }

    @Override
    //Driver Movements
    public void loop() {
        if (gamepad1.left_stick_y > 0){
          frontRightMotor.setPower(1);
          frontLeftMotor.setPower(1);
          backLeftMotor.setPower(-1);
          backRightMotor.setPower(-1);
        } else if (gamepad1.left_stick_y < 0) {
            //move left
            frontRightMotor.setPower(-1);
            frontLeftMotor.setPower(-1);
            backLeftMotor.setPower(-1);
            backRightMotor.setPower(-1);
        }
        if (gamepad1.right_stick_x > 0){
            //move right
            frontRightMotor.setPower(1);
            frontLeftMotor.setPower(1);
            backLeftMotor.setPower(1);
            backRightMotor.setPower(1);
        } else if (gamepad1.right_stick_x < 0) {
            //move backwards
            frontRightMotor.setPower(-1);
            frontLeftMotor.setPower(-1);
            backLeftMotor.setPower(1);
            backRightMotor.setPower(1);
        }

    }

}


