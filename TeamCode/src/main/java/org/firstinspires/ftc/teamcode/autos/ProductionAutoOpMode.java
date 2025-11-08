package org.firstinspires.ftc.teamcode.autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.mechanisms.ProgrammingBoard;

@Autonomous(name="Production Auto", group="Production", preselectTeleOp = "MecanumTeleOp")
public class ProductionAutoOpMode extends OpMode {



    // Custom Made Components
    ProgrammingBoard board;


    @Override
    public void init() {


        board = new ProgrammingBoard();
        board.init(hardwareMap);

        // Initialize other components here.

        // Put vision initialization here

        /*
         * TODO: Implement Visual Localization (Extra)
         * TODO: Convert Camera location to RR Pose2d (Extra)
         * There's a tutorial for getting global pose for your robot by reading the field's
         * AprilTags on the walls.
         *
         * https://ftc-docs.firstinspires.org/en/latest/apriltag/vision_portal/apriltag_localization/apriltag-localization.html#introduction
         *
         * When the season starts, I would like to test this out, but this isn't really necessary.
         * It's very nice for roadrunner, and figuring out where the global position of the robot
         * is automatically, and makes us flexible in competition, but then again we can manually
         * code it.
         */

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
