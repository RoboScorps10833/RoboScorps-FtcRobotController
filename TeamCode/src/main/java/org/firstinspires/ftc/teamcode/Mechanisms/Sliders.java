package org.firstinspires.ftc.teamcode.Mechanisms;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class Sliders extends OpMode {
 DcMotor RightVertExtMotor;
 DcMotor LeftVertExtMotor;

 @Override
    public void init (){
     RightVertExtMotor = hardwareMap.get(DcMotor.class,"RLift");
     LeftVertExtMotor = hardwareMap.get(DcMotor.class, "LLift");
 }

 @Override
    public void loop (){
     if (gamepad1.a){
         RightVertExtMotor.setPower(1);
         LeftVertExtMotor.setPower(1);
     } else {
         RightVertExtMotor.setPower(0);
         LeftVertExtMotor.setPower(0);

     }
 }

}
