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
    StateMachine stateMachine = new StateMachine();
    ProgrammingBoard board = new ProgrammingBoard();
    Robot robot = new Robot(board, stateMachine);

    @Override
    public void init() {
        board.initialize(hardwareMap);
        stateMachine.initialize();
        robot.initialize();

    }

    @Override
    public void init_loop() {
        // update state machine stuff here
        // also multithreading(??) --> future problem.
    }


    @Override
    public void loop() {
        // Actions here I guess
    }

    @Override
    public void start() {
        /*
         * Start up the state machine here.
         * I don't want to put it in init since the match hasn't stated yet...
         * Also useful when starting and stopping the robot during practice :>
         */

    }


    @Override
    public void stop() {
        /*
         * Pause the state machine so it won't noise itself to oblivion.
         * The bot is stopped, so state needs to stop updating as well
         */
    }




}
