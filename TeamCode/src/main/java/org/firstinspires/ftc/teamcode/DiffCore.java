package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.RobotLog;
import com.qualcomm.robotcore.hardware.Gamepad;

public class DiffCore extends OpMode {
    DcMotor topL;
    DcMotor topR;
    DcMotor bottomL;
    DcMotor bottomR;
    BNO055IMU imu;




    public void init(){
        Cassete leftDrive=new Cassete(new DcMotor (hardwareMap.get("topL")));
        Cassete rightDrive=new Cassete();

        DcMotor[] motors={topL,topR,bottomL,bottomR};
        for(DcMotor mot:motors) {
            mot.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }
    public void loop(){
        // Not sure what to loop yet, probably debug stuff using RobotLog and telemetry updates, since this is not the main loop.

    }


// return 1 if wheel rotated clockwise, 0 if moved counterclockwise

    public int DiffDrive(double angle, double power){
        double beforeAngle=(rightDrive.getWheelAngle()+leftDrive.getWheelAngle())/2;

    }


}