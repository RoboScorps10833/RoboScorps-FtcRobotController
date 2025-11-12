package org.firstinspires.ftc.teamcode.mechanisms;

import com.pedropathing.geometry.Pose;


// Not going to be touched until main core of robot is working.
public class Camera {

    ProgrammingBoard board;
    public Camera(ProgrammingBoard board) {
        this.board = board;
    }

    /**
     * Returns a PedroPathing pose extrapolated from the camera. Used to find actual pose
     * and correct for acculimated error of the bot.
     * @return Pose. Uses a fusion algorithm to extrapolate the actual pose from camera reading
     */
    public Pose getHeadingFromCamera(Pose currentPose) {
        /*
         * Would like to implement with Kalman filter and low pass with some fusion magic
         * in order to throw this into localization.
         */

        // Dummy Pose for now.
        return currentPose;
    }
}
