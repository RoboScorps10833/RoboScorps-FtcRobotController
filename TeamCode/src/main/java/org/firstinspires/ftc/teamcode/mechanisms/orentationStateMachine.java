package org.firstinspires.ftc.teamcode.mechanisms;

import com.pedropathing.control.KalmanFilter;
import com.pedropathing.control.KalmanFilterParameters;
import com.pedropathing.geometry.Pose;

public class orentationStateMachine {

    private Pose currentPose;
    private KalmanFilter xFilter;
    private KalmanFilter yFilter;
    private KalmanFilter headingFilter;

    private KalmanFilterParameters parameters;

    public orentationStateMachine(KalmanFilterParameters params) {
        currentPose = new Pose(0, 0, Math.toRadians(0));
        xFilter = new KalmanFilter(params);
        yFilter = new KalmanFilter(params);
        headingFilter = new KalmanFilter(params);
    }

    public void updatePose(Pose updatePose, Pose updateProjection) {
        xFilter.update(updatePose.getX(), updateProjection.getX());
        yFilter.update(updatePose.getY(), updateProjection.getY());
        headingFilter.update(updatePose.getHeading(), updateProjection.getHeading());

        currentPose = new Pose(xFilter.getState(), yFilter.getState(), headingFilter.getState());
    }

    public Pose getCurrentPose() {
        return this.currentPose;
    }
}
