package org.firstinspires.ftc.teamcode.autos;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.MecanumDrive;

/**
 * This is a practice auto opmode using roadrunner, from
 * https://rr.brott.dev/docs/v1-0/guides/centerstage-auto/.
 *
 * This is just a follow along to their tutorial, but for this codebase.
 * Its more like they did it using linearOpMode, while I used OpMode
 */

/***
 * Dummy lift class, actually isn't on the robot
 */
class DummyLift {
    // Actual hardware mapping variable
    private DcMotorEx lift;

    public DummyLift(HardwareMap hardwareMap) {
        lift = hardwareMap.get(DcMotorEx.class, "dummyLiftMotor");
        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lift.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    class DummyLiftUp implements Action {
        private boolean initalized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            // Checks initalization, so it can run its init and have its own loop
            if (!initalized) {
                lift.setPower(0.8);
                initalized = true;
            }

            double pos = lift.getCurrentPosition();
            packet.put("liftPos", pos);
            if (pos < 3000.0) {
                return true; // Lets it run
            } else {
                lift.setPower(0);
                return false;
            }
        }

    }

    // Dummy action
    public Action dummyLiftUp() {
        return new DummyLiftUp();
    }

    // You want to program this for one interation of running.
    // Return true when still running, false when done.
    class DummyLiftDown implements Action {
        private boolean initalized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initalized) {
                lift.setPower(-0.8);
                initalized = true;
            }

            double pos = lift.getCurrentPosition();
            packet.put("liftPos", pos);
            if (pos > 100.0) {
                return true;
            } else {
                lift.setPower(0);
                return false;
            }
        }
    }

    public Action dummyLiftDown() {
        return new DummyLiftDown();
    }
}

/***
 * Dummy Claw class
 */
class DummyClaw {
    private Servo claw;
    public DummyClaw(HardwareMap hardwareMap) {
        claw = hardwareMap.get(Servo.class, "dummyClaw");
    }

    // This instantly returns false since this only needs to run once
    class OpenClaw implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            claw.setPosition(0.55);
            return false;
        }
    }

    public Action openClaw() {
        return new OpenClaw();
    }

    class CloseClaw implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            claw.setPosition(1.0);
            return false;
        }
    }

    public Action closeClaw() {
        return new CloseClaw();
    }

}
@Autonomous(name="Practice OpMode", group="Autonomous")
@Disabled
public class ExampleAutoOp extends OpMode {
    Pose2d initialPose;
    MecanumDrive drive;

    DummyClaw claw;
    DummyLift lift;

    // Placeholder for position
    // Some teams use this to set their trajectory based on their location
    int visionOutputPosition = 1;

    // Watch your namespaces!
    TrajectoryActionBuilder tab1;
    TrajectoryActionBuilder tab2;
    TrajectoryActionBuilder tab3;
    Action trajectoryActionCloseOut;

    public void init() {
        initialPose = new Pose2d(11.8, 61.7, Math.toRadians(90));
        drive = new MecanumDrive(hardwareMap, initialPose);

        claw = new DummyClaw(hardwareMap);
        lift = new DummyLift(hardwareMap);

        // Do your vision pipeline here, as it takes a long time to do

        // Build the trajectories.
        // Typically you want these do be done ASAP so they can calculate.
        tab1 = drive.actionBuilder(initialPose)
                .lineToYSplineHeading(33, Math.toRadians(0))
                .waitSeconds(2)
                .setTangent(Math.toRadians(90))
                .lineToY(38)
                .setTangent(Math.toRadians(0))
                .lineToX(32)
                .strafeTo(new Vector2d(44.5, 30))
                .turn(Math.toRadians(180))
                .lineToX(47.5)
                .waitSeconds(3);
        tab2 = drive.actionBuilder(initialPose)
                .lineToY(37)
                .setTangent(Math.toRadians(0))
                .lineToX(18)
                .waitSeconds(3)
                .setTangent(Math.toRadians(0))
                .lineToXSplineHeading(46, Math.toRadians(180))
                .waitSeconds(3);
        tab3 = drive.actionBuilder(initialPose)
                .lineToYSplineHeading(33, Math.toRadians(180))
                .waitSeconds(2)
                .strafeTo(new Vector2d(46, 30))
                .waitSeconds(3);

        // Parking basically
        trajectoryActionCloseOut = tab1.endTrajectory().fresh()
                .strafeTo(new Vector2d (48,12))
                .build();


        // Your can move on initialization,
        // it just needs to be in the size limit before you press run.

        // Initialization positions
        Actions.runBlocking(claw.closeClaw());
    }
    @Override
    public void init_loop() {
        // Some teams let their vision pipeline run here to figure out their position
        int position = visionOutputPosition;
        telemetry.addData("position during Init", position);
        telemetry.update();
    }

    @Override
    public void start() {
        int startPosition = visionOutputPosition;
        telemetry.addData("Starting Position", startPosition);
        telemetry.update();
    }

    @Override
    public void loop() {
        Action trajectoryActionChosen;
        // If you have a vision pipeline, you can chose where you start
        if (visionOutputPosition == 1) {
            trajectoryActionChosen = tab1.build();
        } else if (visionOutputPosition == 2) {
            trajectoryActionChosen = tab2.build();
        } else {
            trajectoryActionChosen = tab3.build();
        }

        // Where everything actually runs.
        Actions.runBlocking(
                new SequentialAction(
                        trajectoryActionChosen,
                        lift.dummyLiftUp(),
                        claw.openClaw(),
                        lift.dummyLiftDown(),
                        trajectoryActionCloseOut
                )
        );
    }
}

// And all of this for 30 seconds TT

