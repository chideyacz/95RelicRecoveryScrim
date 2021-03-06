package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import static org.firstinspires.ftc.teamcode.ScrimRobot.driveState.Forwards;

/**
 * Created by HomeFolder on 11/11/17.
 */

public class ScrimRobot extends OpMode {

    DcMotor bleft1, bleft2, bright1, bright2, intakeR, intakeL, motorLiftL, motorLiftR;
    Servo liftLT, liftLB, liftRT, liftRB, grabL, grabR;
    public int lslideStartPosition;
    public int rslideStartPosition;
    ElapsedTime currentTime = new ElapsedTime();
    public double liftLTStart;
    public double liftLBStart;
    public double liftRTStart;
    public double liftRBStart;
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


        bleft1.setDirection(DcMotorSimple.Direction.REVERSE);
        bleft2.setDirection(DcMotorSimple.Direction.REVERSE);
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

    public boolean drive(driveState state, double power, double time) {


        switch (state) {
            case Forwards:
                bleft1.setPower(power);
                bleft2.setPower(power);
                bright1.setPower(power);
                bright2.setPower(power);

                if (currentTime.seconds() == time) {
                    bleft1.setPower(0);
                    bleft2.setPower(0);
                    bright1.setPower(0);
                    bright2.setPower(0);
                    return false;
                } else {
                    return true;
                }


            case Backwards:
                currentTime.reset();
                bleft1.setPower(power);
                bleft2.setPower(power);

                if (currentTime.seconds() == time) {
                    bleft1.setPower(0);
                    bleft2.setPower(0);
                    bright1.setPower(0);
                    bright2.setPower(0);
                    return false;
                } else {
                    return true;
                }

            case TurnRight:
                currentTime.reset();
                bleft1.setPower(power);
                bleft2.setPower(power);
                bright1.setPower(-power);
                bright2.setPower(-power);

                if (currentTime.seconds() == time) {
                    bleft1.setPower(0);
                    bleft2.setPower(0);
                    bright1.setPower(0);
                    bright2.setPower(0);
                    return false;
                } else {
                    return true;
                }

            case TurnLeft:
                currentTime.reset();
                bleft1.setPower(-power);
                bleft2.setPower(-power);
                bright1.setPower(power);
                bright2.setPower(power);

                if (currentTime.seconds() == time) {
                    bleft1.setPower(0);
                    bleft2.setPower(0);
                    bright1.setPower(0);
                    bright2.setPower(0);
                    return false;
                } else {
                    return true;
                }

            case PivotLeft:
                currentTime.reset();
                bleft1.setPower(power);
                bleft2.setPower(power);
                bright1.setPower(0);
                bright2.setPower(0);

                if (currentTime.seconds() == time) {
                    bleft1.setPower(0);
                    bleft2.setPower(0);
                    bright1.setPower(0);
                    bright2.setPower(0);
                } else {
                    return true;
                }

            case PivotRight:
                currentTime.reset();
                bleft1.setPower(0);
                bleft2.setPower(0);
                bright1.setPower(power);
                bright2.setPower(power);

                if (currentTime.seconds() == time) {
                    bleft1.setPower(0);
                    bleft2.setPower(0);
                    bright1.setPower(0);
                    bright2.setPower(0);
                    return false;
                } else {
                    return true;
                }

        }

        return false;

    }


    public boolean liftandRelease(driveState state, double power, double position, double time) {


        switch (state) {


            case LiftSlide:
                currentTime.reset();
                motorLiftR.setPower(power);
                motorLiftL.setPower(power);

                if (currentTime.seconds() == time) {
                    bleft1.setPower(0);
                    bleft2.setPower(0);
                    bright1.setPower(0);
                    bright2.setPower(0);
                    return false;
                } else {
                    return true;
                }


            case MoveBlocks:
                currentTime.reset();
                liftLT.setPosition(position);
                liftLB.setPosition(position);
                liftRB.setPosition(position);
                liftRT.setPosition(position);

                if (currentTime.seconds() == time) {

                    return false;
                } else {
                    return true;
                }

            case Release:
                currentTime.reset();
                grabL.setPosition(GRAB_LEFT_OUT);
                grabR.setPosition(GRAB_RIGHT_OUT);


                bleft1.setPower(.2);
                bleft2.setPower(.2);
                bright1.setPower(.2);
                bright2.setPower(.2);

                if (currentTime.seconds() == .5) {

                    return false;
                } else {
                    return true;
                }


        }

        return false;
    }
}

