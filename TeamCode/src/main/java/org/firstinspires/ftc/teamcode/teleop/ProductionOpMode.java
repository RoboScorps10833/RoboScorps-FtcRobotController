
/*
 * Production OpMode for the RoboScorps' 2025 Season
 * 
 * https://gm0.org/en/latest/docs/software/tutorials/mecanum-drive.html
 *  
 * Single controler Operation Mode
 * 
 */

package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.ProgrammingBoard;
import org.firstinspires.ftc.teamcode.mechanisms.Robot;

/**
 * The Production OpMode for the robot
 */
@TeleOp
public class ProductionOpMode extends OpMode {
    ProgrammingBoard board = new ProgrammingBoard();
    Robot robot = new Robot(board);


    @Override
    public void init() {
        board.init(hardwareMap);
    }

    @Override
    public void start() {
        // Against rules for robot to move before the match starts
        // Using OpMode's start function
        robot.init();
    }

    @Override
    public void init_loop() {
        robot.update();
    }

    @Override
    public void loop() {
        /*
         * I guess I'm setting up keybindings here.
         * Can't set up in robot class, since gamepad derives from OpMode
         */
        // Inversion
        if (gamepad1.dpad_down) {
            robot.inverseControls();
        }

        // Steering
        double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
        double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
        double rx = gamepad1.right_stick_x; // Rotation is backwards
        robot.steer(x,y,rx);

        // Arm

        //0.5 is just a power value that works temperalrily
        if (gamepad2.x) { // going up
            robot.changeArmPosition(1);
            //robot.setArmPower(0.5);
        } if (gamepad2.y) { // going down
            robot.changeArmPosition(-1);
            //robot.setArmPower(-0.5);
        } else {
            //robot.setArmPower(0);
        }

        // Linear Extender

        //0.5 is a power value that works so far
        if (gamepad2.a) {
            robot.setLinearExtenderPower(-0.90);
        } else if (gamepad2.b) {
            robot.setLinearExtenderPower(0.90);
        } else {
            robot.setLinearExtenderPower(0);
        }

        // Opening/closing claw

        // I literally don't know why the trigger is a float, but here it is...
        if (gamepad2.left_trigger > 0.9) {
            robot.openClaw();
            // telemetry.addData("OPENSTATE", "on");
        }
        if (gamepad2.right_trigger > 0.9) {
            robot.closeClaw();
            //telemetry.addData("OPENSTATE", "off");
        }

        //telemetry.addData("LeftController", gamepad2.left_trigger);
        //telemetry.addData("right controller", gamepad2.right_trigger);


        // rotating claw

        // 0.001 is an arbutary number that works
        // Miracle how this works because of floating point error


        if (gamepad2.right_bumper) {
            robot.setClawPower(-0.25);
            //telemetry.addData("Thing", "Right pressed");
        } else if (gamepad2.left_bumper) {
            robot.setClawPower(0.25);
           // telemetry.addData("Thing", "Left pressed");
        } else {
            robot.setClawPower(0);
          //  telemetry.addData("Thing", "Nothing");
        }

    }
}
