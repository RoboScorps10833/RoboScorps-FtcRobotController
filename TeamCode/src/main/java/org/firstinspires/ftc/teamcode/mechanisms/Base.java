package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

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
    public Camera vision;


    public void initHardware(HardwareMap hwMap) {
        board = new ProgrammingBoard();
        board.init(hwMap);

        drivebase = new Drivebase(board);
        outtake = new Flywheel(board);
        intake = new Intake(board);
        vision = new Camera(board);
    }

}
