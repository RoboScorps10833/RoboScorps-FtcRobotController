package org.firstinspires.ftc.teamcode.autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.mechanisms.ProgrammingBoard;

@Autonomous
//@Disabled
public class EncoderTestser extends OpMode {
    ProgrammingBoard board;

    @Override
    public void init() {
        board = new ProgrammingBoard();
        board.init(hardwareMap);
    }

    @Override
    public void loop() {
        telemetry.addData("Front Left:", board.frontLeftMotor.getCurrentPosition());
        telemetry.addData("Front Right:", board.frontRightMotor.getCurrentPosition());
        telemetry.addData("Back Left:", board.backLeftMotor.getCurrentPosition());
        telemetry.addData("Back Right:", board.backRightMotor.getCurrentPosition());
    }
}
