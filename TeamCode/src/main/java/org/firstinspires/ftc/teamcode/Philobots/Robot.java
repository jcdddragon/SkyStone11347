package org.firstinspires.ftc.teamcode.Philobots;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

import static android.os.SystemClock.sleep;

public class Robot {
    DriveController driveController;
    private BNO055IMU imu;
    Telemetry telemetry;
    HardwareMap hardwareMap;
    private OpMode opMode;

    private DcMotor leftIntake,rightIntake;
    private DcMotor leftLift,rightLift;
    private static final double ENCODER_TICKS_PER_LEVEL=200.0;
    private Servo leftFourB,rightFourB,grab;
    int liftLevel=1;
    public Robot (OpMode opMode, boolean isAuto) {

        this.hardwareMap = opMode.hardwareMap;
        initMechanisms();
        this.telemetry = opMode.telemetry;
        this.opMode = opMode;
        driveController = new DriveController(this);
        imu = opMode.hardwareMap.get(BNO055IMU.class, "imu");
    }


    public void initIMU () {
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.mode = BNO055IMU.SensorMode.IMU;
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;

        imu.initialize(parameters);
    }
    public void initMechanisms(){
        leftIntake=hardwareMap.dcMotor.get("leftIntake");
        rightIntake=hardwareMap.dcMotor.get("rightIntake");
        leftIntake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightIntake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        grab=hardwareMap.servo.get("grab");
        leftFourB=hardwareMap.servo.get("leftFB");
        rightFourB=hardwareMap.servo.get("rightFB");
        rightLift=hardwareMap.dcMotor.get("rightLift");
        leftLift=hardwareMap.dcMotor.get("leftLift");
        leftLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFourB.setDirection(Servo.Direction.REVERSE);
        leftFourB.setDirection(Servo.Direction.FORWARD);
    }

    public Angle getRobotHeading () {
        //heading is of NEG_180_TO_180_HEADING type by default (no need for conversion)
        double heading = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;

        telemetry.addData("Robot Heading", heading);
        return new Angle(heading, Angle.AngleType.NEG_180_TO_180_HEADING);
    }

    public void wait (int millis, LinearOpMode linearOpMode) {
        long startTime = System.currentTimeMillis();
        while (millis > System.currentTimeMillis() - startTime && linearOpMode.opModeIsActive()) {}
    }
    public void wait (int millis, OpMode OpMode) {
        long startTime = System.currentTimeMillis();
        while (millis > System.currentTimeMillis() - startTime) {}
    }
    public void intake(double power){
        leftIntake.setPower(-power);
        rightIntake.setPower(power);
    }
    public void intakeOn() {
        this.intake(1);
    }
    public void intakeOff(){
        this.intake(0);
    }
//    public void dropFoundation(){
//        leftSF.setPosition(1);
//        rightSF.setPosition(1);
//    }
//    public void liftFoundation(){
//        leftSF.setPosition(0);
//        rightSF.setPosition(0);
//
//    }
    public void fourBarPosition(double pos){

    }

    public void addLevel() {
        liftLevel++;
    }
    public void subLevel(){
        if(liftLevel==1){
            return;
        }
        liftLevel--;
    }
    public void setLiftPower(double power){
        leftLift.setPower(power);
        rightLift.setPower(power);
    }
    public int getLevel(){
        return liftLevel;
    }
    public double getLiftEncoder(){
        return ((leftLift.getCurrentPosition()+rightLift.getCurrentPosition())/2.0);
    }
    public void modulePower(String module,double power){
        if (module.toLowerCase().equals("left")){
            driveController.moduleLeft.directDrive(power);
        }
        else if (module.toLowerCase().equals("right")){
            driveController.moduleRight.directDrive(power);
        }
    }


}