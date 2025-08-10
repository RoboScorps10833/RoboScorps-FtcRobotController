package org.firstinspires.ftc.teamcode.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.ProgrammingBoard;
import org.firstinspires.ftc.teamcode.mechanisms.Drivebase;

import java.util.ArrayList;
import java.util.List;

@TeleOp(name="MecanumOpMode", group="Production")
//@Disabled
public class MecanumTeleOp extends OpMode {
    /*
     * DISABLE DASHBOARD BEFORE MATCH!!!!
     *
     * Generally, throw only controls in here.
     * You can only get the controllers variables only with an OpMode or LinearOpMode
     */

    private ProgrammingBoard board = new ProgrammingBoard();
    private Drivebase drivebase = new Drivebase(board);

    // Roadrunner
    private FtcDashboard dash = FtcDashboard.getInstance();
    private List<Action> runningActions = new ArrayList<>(); // Queue for roadrunner actions

    @Override
    public void init() {
        board.init(hardwareMap);
    }

    @Override
    public void loop() {
        TelemetryPacket packet = new TelemetryPacket();

        // TODO: Add field view of robot with FTCDashboard

        double x = gamepad1.left_stick_x;
        double y = -gamepad1.left_stick_y;
        double theta = gamepad1.right_stick_x;

        if (gamepad1.dpad_down) {
            drivebase.inverseControls();
        }

        drivebase.steer(x,y,theta);

        /*
         * Run roadrunner actions (basically a single loop runBlocking())
         * Queue by using `runningActions.add(...)
         *
         * See https://rr.brott.dev/docs/v1-0/guides/teleop-actions/ for details
         */
        List<Action> newActions = new ArrayList<>();
        for (Action action : runningActions) {
            action.preview(packet.fieldOverlay());
            if (action.run(packet)) {
                newActions.add(action);
            }
        }
        runningActions = newActions;

        dash.sendTelemetryPacket(packet);
    }
}
