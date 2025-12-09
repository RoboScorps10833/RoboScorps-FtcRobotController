package org.firstinspires.ftc.teamcode.mechanisms;

import com.bylazar.camerastream.PanelsCameraStream;
import com.bylazar.telemetry.PanelsTelemetry;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.PedroCoordinates;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.CommandScheduler;
import com.seattlesolvers.solverslib.command.Subsystem;


import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

import java.util.List;
import java.util.function.Supplier;

/**
 * A class to abstract away library boilerplate, creation of class, and
 * definition of utility functions.
 */
public abstract class Base extends OpMode {

    // Sensors and components
    public ProgrammingBoard board;
    public Drivebase drivebase;
    public Flywheel outtake;
    public Intake intake;
    public RobotCamera vision;

    public List<LynxModule> allHubs;

    // Pedropathing
    public Follower follower;

    /*
     * Coordinate Pathing hell
     */

    public static Pose shootingPose;
    public static Pose startingPose;

    public void initPoses() {

    }

    public void initTeleOp() {
        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose((startingPose == null ? new Pose() : startingPose));
        follower.update();

        follower.startTeleOpDrive();
    }

    public void initHardware(HardwareMap hwMap) {
        // Bulk Reading
        allHubs = hardwareMap.getAll(LynxModule.class);
        for (LynxModule hub : allHubs) {
            // Optimization - Bulk readings.
            // Lots of shennigans on threads, so trying to get loop cycles down
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL); // I might regret doing this
        }

        board = new ProgrammingBoard();
        board.init(hwMap);

        drivebase = new Drivebase(board);
        outtake = new Flywheel(board);
        intake = new Intake(board);
        vision = new RobotCamera(board);
        vision.initAprilTag();
    }

    // DO NOT TOUCH!!!!!

    /**
     * Resets cache for all of the bulk reads.
     * MUST include in init_loop, otherwise you'll break reading all sensors.
     */
    public void resetCache() {
        for (LynxModule hub : allHubs) {
            hub.clearBulkCache();
        }
    }

    /*
     * Hot garbage boilerplate for SolversLib
     * Stolen from the implementation of CommandOpMode
     */

    public void register(Subsystem... subsystems) {
        CommandScheduler.getInstance().registerSubsystem(subsystems);
    }

    /**
     * Must be put in init_loop, otherwise all commands will not run.
     */
    public void runSolversLib() {
        CommandScheduler.getInstance().run();
    }

    public void scheduleCommand(Command... commands) {
        CommandScheduler.getInstance().schedule(commands);
    }

    // Might add an enable and disable for the scheduler later...


    /**
     * Conversion from IRL field to coordinates for Pedropathing
     *
     */
    public double irlcoordsToPPcoords(double coordinate) {
        return (coordinate / 360.0) * 144;
    }


    // magic telemetry

    public void initMagicTelemetry() {
        //PanelsCameraStream.startStream(aprilTag);
    }


    /**
     * calculate the auto aim for the bot
     */
    public double getAimingAngle(Pose robotPose, Pose targetPosition) {
        // y goes first because the function is weird
        // Finding the angle is basically a triangle, so do some trig
        return Math.atan2(targetPosition.getY() - robotPose.getY(), targetPosition.getX() - robotPose.getX());
    }

    // Use the command to fetch an automatic path chain from
    public Supplier<PathChain> pathChain;
    public Pose redAllianceBasket = new Pose(0,0, Math.toRadians(0));
    public Pose blueAllianceBasket = new Pose(0,0, Math.toRadians(0));
}
