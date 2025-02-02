package org.firstinspires.ftc.teamcode.CircuitRunners;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Zero",group="TeleOp")
public class Zeroing extends OpMode {
    private Robot robot;

    public void init() {
        robot=new Robot(this,false,false);
    }

    public void stop() {
        robot.driveController.resetEncoders();
    }

    public void loop() {
        robot.driveController.moduleLeft.moduleRotate(.5*-gamepad1.left_stick_y);
        robot.driveController.moduleRight.moduleRotate(.5*-gamepad1.right_stick_y);
        telemetry.addData("Left Sensor Heading",robot.driveController.moduleLeft.getCurrentOrientation().getAngle());
        telemetry.addData("Right Sensor Heading",robot.driveController.moduleRight.getCurrentOrientation().getAngle());
    }
}