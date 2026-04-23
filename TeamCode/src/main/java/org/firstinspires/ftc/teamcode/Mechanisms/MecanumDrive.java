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

    public void moveDriveTrain(){
        double y = 0;
        double x = 0;
        double rx = 0;
        y = -gamepad1.left_stick_y;
        x = gamepad1.right_stick_x;
        rx = gamepad1.right_stick_x;
        // y might be speed?? , x turn and rx strafe

        frontRightMotor.setPower(y + x + rx);
        backLeftMotor.setPower(y - x + rx);
        frontLeftMotor.setPower(y - x - rx);
        backRightMotor.setPower(y + x - rx);

    }


    public void init() {
        frontLeftMotor = hardwareMap.get(DcMotor.class,"frontLeftMotor");
        frontRightMotor = hardwareMap.get(DcMotor.class,"frontRightMotor");
        backLeftMotor = hardwareMap.get(DcMotor.class,"backLeftMotor");
        backRightMotor = hardwareMap.get(DcMotor.class,"backRightMotor");
    }

    @Override
    public void init_loop() {

    }

    @Override
    public void loop() {

    }

}


