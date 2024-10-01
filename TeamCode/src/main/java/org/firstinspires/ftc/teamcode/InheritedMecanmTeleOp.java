package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp
public class InheritedMecanmTeleOp extends RobotOpMode {
    @Override // Override to implement your code
    public void run() throws InterruptedException {
        double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
        double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
        double rx = gamepad1.right_stick_x; // Rotation is backwards

        steer(x,y,rx);
    }
}