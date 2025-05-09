/*
 * A simple op mode that tests the function of the robot.
 * It just makes the arm go up in one direction.
 * You can change the direction in the script
 *
 */

package org.firstinspires.ftc.teamcode.testers;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous
@Disabled
public class ArmTestOpMode extends OpMode {
    DcMotor armMotor;
    double power = 0.75;
    boolean flipped = true;


    @Override
    public void init() {
        armMotor = hardwareMap.get(DcMotor.class, "ArmMotor");
        armMotor.setPower(power);

        if (flipped) {power = power * -1;}
    }


    @Override
    public void loop() {


    }


}
