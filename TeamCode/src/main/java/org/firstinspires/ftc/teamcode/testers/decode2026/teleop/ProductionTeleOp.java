package org.firstinspires.ftc.teamcode.testers.decode2026.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.testers.decode2026.mechanisms.Base;


@TeleOp(name="ProductionOpMode?")
public class ProductionTeleOp extends Base {
    @Override
    public void init() {
        initHardware(hardwareMap);
        register(intake, outtake);

        initTeleOp();

    }


    @Override
    public void loop() {
        resetCache();
        runSolversLib();
        follower.update();

        // do stuff with enums or true/false to set red/blue alliance
        boolean red_alliance = false;

        // follower cannot be accessed outside of base.java or the opmodes
        // can only be implemented here :/
        if (drivebase.controlsInversed) {
            follower.setTeleOpDrive(
                    -gamepad1.left_stick_y,
                    -gamepad1.left_stick_x,
                    -gamepad1.right_stick_x,
                    true
            );
        } else {
            follower.setTeleOpDrive(
                    gamepad1.left_stick_y,
                    gamepad1.left_stick_x,
                    -gamepad1.right_stick_x,
                    true
            );
        }

        if (outtake.flywheelToggle) {
            scheduleCommand(new InstantCommand(outtake::spinUp));
        } else {
            scheduleCommand(new InstantCommand(outtake::spinDown));
        }

        if (gamepad2.right_trigger > 0.9) {
            outtake.flywheelToggle = !outtake.flywheelToggle;
        }

        if (gamepad1.dpad_down) {
            drivebase.inverseControls();
        }

        telemetry.addData("Controls Inverted", drivebase.controlsInversed);

        // "Get balls" controls
        if (gamepad2.left_trigger > 0.9) {
            scheduleCommand(new InstantCommand(intake::spinUp));
        } else {
            scheduleCommand(new InstantCommand(intake::spinDown));
        }


//        if (gamepad1.b) {
//            // lazy generate pathchain
//            Pose cameraPose = vision.getHeadingFromCamera(follower.getPose());
//            Pose basketPose = red_alliance ? redAllianceBasket : blueAllianceBasket;
//            pathChain = () -> follower.pathBuilder()
//                            .addPath(new BezierPoint(cameraPose))
//                                    .setLinearHeadingInterpolation(follower.getHeading(), getAimingAngle(cameraPose,basketPose))
//                                            .build();
//            scheduleCommand(new FollowPathCommand(follower, pathChain.get()));
//        }

        // Decided to implement that you need to press both at the same time
        // Don't want to schedule a command with the opposite thing in there
        // Could do so if I had some if statements or a state machine.

        // TODO: Create a state machine (so commands won't conflict)



    }



}
