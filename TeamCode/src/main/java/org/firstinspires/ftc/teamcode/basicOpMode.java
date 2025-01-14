/*
 * This is a template for a basic OpMode with the current code so far.
 *
 * You can copy this as much as you want, just... don't use it production as it
 * is empty
 *
 * - some-n3rd (11/6/2024)
 *
 * Note: the disabled annotation means it won't show up in the OpMode menu.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.components.ProgrammingBoard;
import org.firstinspires.ftc.teamcode.controlsystem.StateMachine;
import org.firstinspires.ftc.teamcode.mechanisms.Robot;

@TeleOp
@Disabled
public class basicOpMode extends OpMode {
    ProgrammingBoard board = new ProgrammingBoard();
    Robot robot = new Robot(board);

    @Override
    public void init() {
        board.init(hardwareMap);
    }

    @Override
    public void start() {
        robot.init();

    }

    @Override
    public void init_loop() {
        robot.update();
    }


    @Override
    public void loop() {
        // Actions here I guess
    }

    @Override
    public void stop() {
        // Something goes here
    }




}
