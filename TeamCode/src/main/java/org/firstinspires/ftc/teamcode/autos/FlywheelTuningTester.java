package org.firstinspires.ftc.teamcode.autos;



import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.mechanisms.ProgrammingBoard;

@TeleOp
public class FlywheelTuningTester extends OpMode {
    ProgrammingBoard board;
    ElapsedTime timer = new ElapsedTime();

    double time;
    boolean flywheelToggle = true;

    TelemetryPacket packet = new TelemetryPacket();

    @Override
    public void init() {
        board = new ProgrammingBoard();
        board.init(hardwareMap);

    }


    @Override
    public void loop() {
        board.intakeMotors.set(0.9);

        if (gamepad1.a) {
            flywheelToggle = !flywheelToggle;
        }

        if (flywheelToggle) {
            // 28 ticks per revoltuion
            // 6000 revolutions per minute
            // 100 revolutions per second
            board.leftFlywheelMotor.setVelocity(testerConfigurables.targetTicks);
            board.rightFlywheelMotor.setVelocity(testerConfigurables.targetTicks);
        } else {
            board.leftFlywheelMotor.setVelocity(0);
            board.rightFlywheelMotor.setVelocity(0);
        }

        board.leftFlywheelMotor.setVeloCoefficients(
                testerConfigurables.kPLeft,
                testerConfigurables.kILeft,
                testerConfigurables.kDLeft
        );
        board.leftFlywheelMotor.setFeedforwardCoefficients(
                testerConfigurables.kSLeft,
                testerConfigurables.kVLeft
        );

        board.rightFlywheelMotor.setVeloCoefficients(
                testerConfigurables.kPRight,
                testerConfigurables.kIRight,
                testerConfigurables.kDRight

        );
        board.rightFlywheelMotor.setFeedforwardCoefficients(
                testerConfigurables.kSRight,
                testerConfigurables.kVRight
        );

        time = timer.seconds();

        packet.put("time", time);
        packet.put("Actual Speed Left", board.leftFlywheelMotor.getCorrectedVelocity());
        packet.put("Actual Speed Right", board.rightFlywheelMotor.getCorrectedVelocity());

        packet.put("Goal", testerConfigurables.targetTicks);
        FtcDashboard.getInstance().sendTelemetryPacket(packet);
    }

}

@Config
class testerConfigurables {
    // PID Controller
    public static double kPLeft = 0.01; // Proportional
    public static double kILeft = 0.0; // Intergral
    public static double kDLeft = 0; // Derivitive

    // FeedForward
    public static double kSLeft = 215; // Static Friction
    public static double kVLeft = 1.1; // Velocity
    // PID Controller
    public static double kPRight = 0.01; // Proportional
    public static double kIRight = 0.0; // Intergral
    public static double kDRight = 0.0; // Derivitive

    // FeedForward
    public static double kSRight = 215.0; // Static Friction
    public static double kVRight = 1.2; // Velocity
    public static double targetTicks = 1000;

}
