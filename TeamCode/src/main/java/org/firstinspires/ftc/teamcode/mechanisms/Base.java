package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.List;

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

    public List<LynxModule> allHubs;

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
        vision = new Camera(board);
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
}
