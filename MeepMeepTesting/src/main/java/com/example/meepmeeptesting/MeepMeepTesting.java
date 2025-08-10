package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;


/*
 * This is the file for visualizing your files with MeepMeep, a Roadrunner
 * trajectory visualizer.
 *
 * Note that you can't use variables or custom made actions with MeepMeep.
 *
 * To see your trajectory visualized, throw it after the `drive ->` and run
 * the MeepMeep configuration. To go back to programming your code, switch the
 * configuration back to TeamCode. You do not need to connect to the Control Hub
 * to use MeepMeep.
 *
 * RESOURCES:
 * - The MeepMeep github: https://github.com/rh-robotics/MeepMeep?tab=readme-ov-file
 * - Roadrunner Reference: https://rr.brott.dev/docs/v1-0/builder-ref/
 */

/**
 * MeepMeep runner, a Roadrunner trajectory visualizer.
 * Run this class for testing and visualizing your RR trajectories.
 */
public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)

                // Paste your trajectory after the `drive ->` to be visualized
                // Comment out custom actions and hard code the values of your variables
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(0, 0, 0))
                        .forward(30)
                        .turn(Math.toRadians(90))
                        .forward(30)
                        .turn(Math.toRadians(90))
                        .forward(30)
                        .turn(Math.toRadians(90))
                        .forward(30)
                        .turn(Math.toRadians(90))
                        .build());


        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}