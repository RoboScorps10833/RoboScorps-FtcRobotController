package org.firstinspires.ftc.teamcode.autos;

import com.pedropathing.control.KalmanFilterParameters;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.mechanisms.ProgrammingBoard;
import org.firstinspires.ftc.teamcode.mechanisms.RobotCamera;
import org.firstinspires.ftc.teamcode.mechanisms.orentationStateMachine;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import java.util.List;


/**
 * Tests Kalman filter capililities and building it ...
 */
@Autonomous(name="VisionTester")
public class VisionPoseTester extends OpMode {
    ProgrammingBoard board;
    RobotCamera camera;

    Pose currentPose;
    Pose startinPose = new Pose(0,0,0);

    Pose newPose;
    orentationStateMachine stateMachine;

    @Override
    public void init() {
        board = new ProgrammingBoard();
        board.init(hardwareMap);
        camera = new RobotCamera(board);
        camera.initAprilTag();

        // Covariance
        stateMachine = new orentationStateMachine(
                new KalmanFilterParameters(1, 1)
        );

        currentPose = startinPose;
    }

    @Override
    public void loop() {
        List<AprilTagDetection> currentDetections = camera.aprilTag.getDetections();
        telemetry.addData("# AprilTags Detected", currentDetections.size());

        // Step through the list of detections and display info for each one.
        for (AprilTagDetection detection : currentDetections) {
            if (detection.metadata != null) {
                telemetry.addLine(String.format("\n==== (ID %d) %s", detection.id, detection.metadata.name));
                telemetry.addLine(String.format("XYZ %6.1f %6.1f %6.1f  (inch)",
                        detection.robotPose.getPosition().x,
                        detection.robotPose.getPosition().y,
                        detection.robotPose.getPosition().z));
                telemetry.addLine(String.format("PRY %6.1f %6.1f %6.1f  (deg)",
                        detection.robotPose.getOrientation().getPitch(AngleUnit.DEGREES),
                        detection.robotPose.getOrientation().getRoll(AngleUnit.DEGREES),
                        detection.robotPose.getOrientation().getYaw(AngleUnit.DEGREES)));

                // Actual Pose computation going on
                if (detection.id == 20 || detection.id == 24){
                    newPose = new Pose(
                            detection.robotPose.getPosition().x,
                            detection.robotPose.getPosition().y,
                            detection.robotPose.getOrientation().getYaw(AngleUnit.RADIANS)
                    );
//                    stateMachine.updatePose(newPose, stateMachine.getCurrentPose());

                }

            } else {
                telemetry.addLine(String.format("\n==== (ID %d) Unknown", detection.id));
                telemetry.addLine(String.format("Center %6.0f %6.0f   (pixels)", detection.center.x, detection.center.y));
            }

        }

        // Add "key" information to telemetry
        telemetry.addLine("\nkey:\nXYZ = X (Right), Y (Forward), Z (Up) dist.");

        telemetry.addLine(String.format("Current Pose - X: %3.3f, Y: %3.3f, Heading(rads): %3.3f", stateMachine.getCurrentPose().getX(), stateMachine.getCurrentPose().getY(), stateMachine.getCurrentPose().getHeading()));


    }
}
