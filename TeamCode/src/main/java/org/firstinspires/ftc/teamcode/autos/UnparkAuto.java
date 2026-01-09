package org.firstinspires.ftc.teamcode.autos;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

@Autonomous
public class UnparkAuto extends OpMode {

    /*
     *
     * 1 - Blue Small Shooting Area
     * 2 - Blue Target
     * 3 - Red Small Shooting Area
     * 4 - Red Target
     */
    private final int parkingLocation = 4;

    private PathChain unpark;
    private boolean parked = false;
    Follower follower;

    @Override
    public void init() {
        follower = Constants.createFollower(hardwareMap);
    }

    @Override
    public void init_loop() {
        telemetry.addData("Autonomous is ready :>", "");
        telemetry.addData("Robot is using placement ", parkingLocation);

        follower.update();

    }

    @Override
    public void start() {
        // Building the paths. Driving team requests that robot be placed in center of arena

        switch (parkingLocation) {
            case 1:
                unpark = follower.pathBuilder()
                        .addPath(new BezierLine(new Pose(0,0), new Pose(30,0)))
                        .setConstantHeadingInterpolation(0)
                        .build();
                break;
            case 3:
                unpark = follower.pathBuilder()
                        .addPath(new BezierLine(new Pose(0,0), new Pose(30,0)))
                        .setConstantHeadingInterpolation(0)
                        .build();
                break;
            case 2:
                unpark = follower.pathBuilder()
                        .addPath(new BezierLine(new Pose(0,0), new Pose(50, 0)))
                        .setConstantHeadingInterpolation(0)
                        .addPath(new BezierLine(new Pose(60,0), new Pose(50, -25)))
                        .build();
                break;
            case 4:
                unpark = follower.pathBuilder()
                        .addPath(new BezierLine(new Pose(0,0), new Pose(60, 0)))
                        .setConstantHeadingInterpolation(0)
                        .addPath(new BezierLine(new Pose(-50,0), new Pose(50, 25)))
                        .build();
                break;
        }
    }
    @Override
    public void loop() {
        follower.update();

        if (!follower.isBusy()) {
            if (!parked) {
                follower.followPath(unpark);
            }
        }
    }
}
