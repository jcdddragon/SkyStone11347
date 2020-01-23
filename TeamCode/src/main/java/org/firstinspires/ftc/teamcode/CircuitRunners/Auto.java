package org.firstinspires.ftc.teamcode.CircuitRunners;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import static org.firstinspires.ftc.teamcode.CircuitRunners.RobotUtil.toCm;

@Autonomous(name="WA Autonomous 1.0", group="Diffy")
public class Auto extends LinearOpMode {
    public enum SSPos{
        LEFT,
        CENTER,
        RIGHT
    }
    private Robot robot;
    private DriveController drive=robot.driveController;
    private Tracking track;
    private SSPos pos;
    @Override

    public void runOpMode(){

        track=new Tracking(hardwareMap,telemetry);
        track.initializeCamera();
        robot=new Robot(this,true);
        robot.initIMU();
        while(!this.opModeIsActive()) {
            double x = track.getSkyStoneX();
            double y = track.getSkyStoneY();
            pos = SSPos.LEFT;
            pos = SSPos.RIGHT;
            pos = SSPos.CENTER;

        }
        waitForStart();
        drive.drive(Vector2d.FORWARD,toCm(30),.7,this);
        drive.rotateRobot(new Angle(90, Angle.AngleType.NEG_180_TO_180_CARTESIAN), this);
        robot.intakeOn();
        drive.drive(Vector2d.BACKWARD, toCm(84), .7, this);
        drive.rotateRobot(new Angle(180, Angle.AngleType.NEG_180_TO_180_CARTESIAN), this);
        // snag the foundation
        drive.rotateRobot(new Angle(90, Angle.AngleType.NEG_180_TO_180_CARTESIAN), this);
        drive.drive(Vector2d.FORWARD, toCm(48), .7, this);

    }
}