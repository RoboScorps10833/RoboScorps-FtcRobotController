package org.firstinspires.ftc.teamcode.teleop;

import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.ProgrammingBoard;
import org.firstinspires.ftc.teamcode.mechanisms.Robot;

@TeleOp(name="MecanumOpMode", group="Production")
//@Disabled
public class MecanumTeleOp extends OpMode {
    /*
     * Generally, throw only controls in here.
     * You can only get the controllers variables only with an OpMode or LinearOpMode
     */

    private ProgrammingBoard board = new ProgrammingBoard();
    private Robot bot = new Robot(board);

    // TODO: Implement queue for RR actions in teleop

    @Override
    public void init() {
        board.init(hardwareMap);
    }

    @Override
    public void loop() {
        double x = gamepad1.left_stick_x;
        double y = -gamepad1.left_stick_y;
        double theta = gamepad1.right_stick_x;

        if (gamepad1.dpad_down) {
            bot.inverseControls();
        }

        bot.steer(x,y,theta);
    }
}
