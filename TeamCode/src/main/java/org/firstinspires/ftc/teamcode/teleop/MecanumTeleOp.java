package org.firstinspires.ftc.teamcode.teleop;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.Camera;
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.teamcode.mechanisms.Flywheel;
import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.ProgrammingBoard;
import org.firstinspires.ftc.teamcode.mechanisms.Drivebase;
import org.firstinspires.ftc.teamcode.mechanisms.RobotCamera;

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
    private Flywheel outtake = new Flywheel(board);

    private Intake intake = new Intake(board);


    public boolean gateOpen = false;


    @Override
    public void init() {
        board.init(hardwareMap);
    }

    @Override
    public void loop() {

        // TODO: Add field view of robot with Panels

        telemetry.addData("motorVoltage", board.intakeMotorInner.getCurrent(CurrentUnit.AMPS));

        double x = -gamepad1.left_stick_x;
        double y = gamepad1.left_stick_y;
        double theta = -gamepad1.right_stick_x;

        if (gamepad1.dpad_down) {
            drivebase.inverseControls();
        }

        drivebase.steer(x,y,theta);

        if (gamepad2.right_trigger > 0.9) {
            outtake.spinUp();
        } else {
            outtake.spinDown();
        }

        // Temperary, just for testing!
        if (gamepad2.left_trigger > 0.9) {
            intake.spinUp();
        } else {
            intake.spinDown();
        }

        if (gamepad2.x) {
            intake.spinUpGate();
        } else {
            intake.spinDownGate();
        }

        if (gamepad2.right_bumper) {
            intake.spinUpGate();
        } else {
            intake.spinDownGate();
        }

    }
}
