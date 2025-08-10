package org.firstinspires.ftc.teamcode.autos;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.mechanisms.ProgrammingBoard;

@Autonomous(name="Production Auto", group="Production", preselectTeleOp = "MecanumTeleOp")
@Config
public class ProductionAutoOpMode extends OpMode {

    // Provided by Roadrunner
    Pose2d initialPose;
    MecanumDrive drive;

    // Custom Made Components
    ProgrammingBoard board;


    @Override
    public void init() {
        initialPose = new Pose2d(0,0,Math.toRadians(0));
        drive = new MecanumDrive(hardwareMap, initialPose);

        board = new ProgrammingBoard();
        board.init(hardwareMap);

        // Initialize other components here.

        // Put vision initialization here

        // Trajectory definition manual entry...
            // Red and left
            // Red and right
            // Blue and left
            // Blue and right

        // Calculate other trajectories here


        // Your can move on initialization,
        // it just needs to be in the size limit before you press run.

        // Initialization of positions of mechanisms
    }

    @Override
    public void init_loop() {
        // Run the vision pipeline here if we have one
    }

    @Override
    public void start() {
        // Select the trajectory and build it
    }

    @Override
    public void loop() {
        // Run the actual teleop
    }

    @Override
    public void stop() {
        // Close the vision portal
    }
}
