package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.RunCommand;

import org.firstinspires.ftc.teamcode.mechanisms.Base;

import kotlin.time.Instant;

@TeleOp(name="VeryBuggyTeleOp")
public class ProductionTeleOp extends Base {
    @Override
    public void init() {
        initHardware(hardwareMap);
        register(intake, outtake);
    }


    @Override
    public void loop() {
        resetCache();
        runSolversLib();

        // Drivebase will be replaced with PedroPathing later
        double x = gamepad1.left_stick_x;
        double y = -gamepad1.left_stick_y;
        double theta = gamepad1.right_stick_x;

        if (gamepad1.dpad_down) {
            drivebase.inverseControls();
        }

        drivebase.steer(x,y,theta);

        scheduleCommand(new InstantCommand(outtake::spinUp));

        // "Get balls" controls
        if (gamepad2.x) {
            scheduleCommand(new InstantCommand(intake::spinUp));
        } else {
            scheduleCommand(new InstantCommand(intake::spinDown));
        }

        // Shoot balls
        if (gamepad2.right_trigger > 0.9) {
            scheduleCommand(new InstantCommand(intake::openGate));
        } else {
            scheduleCommand(new InstantCommand(intake::closeGate));
        }

        // Decided to implement that you need to press both at the same time
        // Don't want to schedule a command with the opposite thing in there
        // Could do so if I had some if statements or a state machine.

        // TODO: Create a state machine (so commands won't conflict)


    }



}
