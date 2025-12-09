package org.firstinspires.ftc.teamcode.mechanisms;

import android.util.Size;

import com.pedropathing.control.KalmanFilterParameters;
import com.pedropathing.ftc.FTCCoordinates;
import com.pedropathing.geometry.PedroCoordinates;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.Path;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;

// Called RobotCamera because SDK already has Camera as a class...
// Not going to be touched until main core of robot is working.
public class RobotCamera extends SubsystemBase {

    private ProgrammingBoard board;

    private Position cameraPosition;
    private YawPitchRollAngles cameraOrientation;
    public AprilTagProcessor aprilTag;

    private VisionPortal visionPortal;
    public orentationStateMachine stateMachine;

    public RobotCamera(ProgrammingBoard board) {
        this.board = board;
        this.stateMachine = new orentationStateMachine(new KalmanFilterParameters( 1, 1));
    }

    public void initAprilTag() {
        cameraPosition = new Position(DistanceUnit.INCH, 0, 0,0, 0 );
        cameraOrientation = new YawPitchRollAngles(AngleUnit.DEGREES, 0, 0, 0, 0);


        aprilTag = AprilTagProcessor.easyCreateWithDefaults();
//        aprilTag = new AprilTagProcessor.Builder()
//                .setCameraPose(cameraPosition, cameraOrientation)
//                .build();

        visionPortal = new VisionPortal.Builder()
                .setCamera(board.webcam)
                .setCameraResolution(new Size(640, 480))
                .addProcessor(aprilTag)
                .build();

        // need to configure this in order for this to measure right.
    }



    public void listCollectedPoses(Telemetry telemetry) {

        List<AprilTagDetection> currentDetections = aprilTag.getDetections();
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
            } else {
                telemetry.addLine(String.format("\n==== (ID %d) Unknown", detection.id));
                telemetry.addLine(String.format("Center %6.0f %6.0f   (pixels)", detection.center.x, detection.center.y));
            }
        }   // end for() loop

        // Add "key" information to telemetry
        telemetry.addLine("\nkey:\nXYZ = X (Right), Y (Forward), Z (Up) dist.");
        telemetry.addLine("PRY = Pitch, Roll & Yaw (XYZ Rotation)");
    }

    private Pose findCurrnetPose() {
        // Generate a pose for each april tag and throw it into a list.

        // Do some nice fusion.
        /*
         * I would like to do some fusion here: keep a running kalman filter for pose.
         * If there's both april tags for the baskets, use their positions and throw them into the
         * filter. If not, update with PedroPathing's current
         *
         *
         */


        return new Pose(0,0,0, FTCCoordinates.INSTANCE).getAsCoordinateSystem(PedroCoordinates.INSTANCE);
    }

    /**
     * Returns a PedroPathing pose extrapolated from the camera. Used to find actual pose
     * and correct for acculimated error of the bot.
     *
     * @return Pose. Uses a fusion algorithm to extrapolate the actual pose from camera reading
     */
    public Pose getHeadingFromCamera(Pose currentPose) {
        /*
         * Would like to implement with Kalman filter and low pass with some fusion magic
         * in order to throw this into localization.
         *
         *
         * for now, picking the last one that's found because its easier and I'm not sure how this is going to work
         * and that competition is a week away
         */

        Pose newPose;

        // Failsafe if no detections that you can calulate data from are found
        newPose = currentPose;

        List<AprilTagDetection> currentDections = aprilTag.getDetections();

        for (AprilTagDetection detection: currentDections) {
            if (detection.metadata != null) {
                if (detection.id == 20 || detection.id == 24) {
                    newPose = new Pose(
                            detection.robotPose.getPosition().x,
                            detection.robotPose.getPosition().y,
                            detection.robotPose.getOrientation().getYaw(AngleUnit.RADIANS)
                    );

                }
            }
        }

        return newPose;
    }
}
