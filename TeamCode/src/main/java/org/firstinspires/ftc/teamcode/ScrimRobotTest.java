package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by HomeFolder on 11/12/17.
 */

public class ScrimRobotTest extends OpMode {

    DcMotor bleft1, bleft2, bright1, bright2, intakeR, intakeL, motorLiftL, motorLiftR;
    Servo liftLT, liftLB, liftRT, liftRB, grabL, grabR;
    public int lslideStartPosition;
    public int rslideStartPosition;
    ElapsedTime currentTime = new ElapsedTime();
    public double liftLTStart = 1.2;
    public double liftLBStart = 1.2;
    public double liftRTStart = 1.2;
    public double liftRBStart = 1.2;
    private final double GRAB_LEFT_IN = .98;
    private final double GRAB_RIGHT_IN = 0.0;
    private final double GRAB_LEFT_OUT = .90;
    private final double GRAB_RIGHT_OUT = .04;

    public static enum driveState {
        Forwards, Backwards, Raise, TurnLeft, TurnRight, PivotLeft, PivotRight, LiftSlide, MoveBlocks, Release
    }


    @Override
    public void init() {
        bleft1 = hardwareMap.dcMotor.get("bleft1");
        bleft2 = hardwareMap.dcMotor.get("bleft2");
        bright1 = hardwareMap.dcMotor.get("bright1");
        bright2 = hardwareMap.dcMotor.get("bright2");
        intakeR = hardwareMap.dcMotor.get("intakeR");
        intakeL = hardwareMap.dcMotor.get("intakeL");
        motorLiftL = hardwareMap.dcMotor.get("motorLiftL");
        motorLiftR = hardwareMap.dcMotor.get("motorLiftR");

        grabL = hardwareMap.servo.get("grabL");
        grabR = hardwareMap.servo.get("grabR");
        liftLT = hardwareMap.servo.get("liftLT");
        liftLB = hardwareMap.servo.get("liftLB");
        liftRT = hardwareMap.servo.get("liftRT");
        liftRB = hardwareMap.servo.get("liftRB");

//        lslideStartPosition = motorLiftL.getCurrentPosition();
//        rslideStartPosition = motorLiftR.getCurrentPosition();
        liftLTStart = liftLT.getPosition();
        liftLBStart = liftLB.getPosition();
        liftRTStart = liftRT.getPosition();
        liftRBStart = liftRB.getPosition();

//        bleft1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        bleft2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        bright1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        bright2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        motorLiftL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        motorLiftR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        bright1.setDirection(DcMotorSimple.Direction.REVERSE);
        bright2.setDirection(DcMotorSimple.Direction.REVERSE);
        intakeR.setDirection(DcMotorSimple.Direction.REVERSE);
        motorLiftL.setDirection(DcMotorSimple.Direction.REVERSE);

        bleft1.setPower(0);
        bleft2.setPower(0);
        bright1.setPower(0);
        bright2.setPower(0);
        intakeL.setPower(0);
        intakeR.setPower(0);
        motorLiftL.setPower(0);
        motorLiftR.setPower(0);

//        liftLT.setPosition(0);
//        liftLB.setPosition(0);
//        liftRT.setPosition(0);
//        liftRB.setPosition(0);
        grabL.setPosition(GRAB_LEFT_OUT);
        grabR.setPosition(GRAB_RIGHT_OUT);

        telemetry.addData("Servo R", grabL.getPosition());
        telemetry.addData("Servo L", grabR.getPosition());
    }

    @Override
    public void loop() {
        telemetry.addData("Power", bleft1.getPower());
    }

    public void stop() {
        telemetry.addData("Time: ", currentTime.seconds());
        bleft1.setPower(0);
        bleft2.setPower(0);
        bright1.setPower(0);
        bright2.setPower(0);
        intakeL.setPower(0);
        intakeR.setPower(0);
        motorLiftL.setPower(0);
        motorLiftR.setPower(0);

    }

    public void start() {
        currentTime.reset();
    }

    public boolean drive(double powerLeft, double powerRight, double time) {
        bleft1.setPower(powerLeft);
        bleft2.setPower(powerLeft);
        bright1.setPower(powerRight);
        bright2.setPower(powerRight);

        if (currentTime.seconds() == time) {
            return true;
        } else {
            return false;
        }
    }


    public boolean lift(double power, double time) {

        motorLiftL.setPower(power);
        motorLiftR.setPower(power);

        if (currentTime.seconds() == time) {
            return true;
        } else {
            return false;
        }
    }

    public boolean rotate() {

        if (liftLB.getPosition() == liftLBStart &&  liftLT.getPosition() == liftLTStart && liftRB.getPosition() == liftRBStart && liftRT.getPosition() == liftRTStart) {
            return true;
        }
        else {
            return false;
        }
    }


    public boolean release() {
        if (grabL.getPosition() == GRAB_LEFT_OUT &&
        grabR.getPosition() == GRAB_RIGHT_OUT) {
            return true;
        }
        else {
            return false;
        }
    }


}


