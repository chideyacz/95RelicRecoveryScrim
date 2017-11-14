package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


/**
 * Created by HomeFolder on 11/9/17.
 */

@TeleOp
public class ScrimTeleop extends OpMode {

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
        liftLB = hardwareMap.servo.get("liftLB");
        liftLT = hardwareMap.servo.get("liftLT");
        liftRT = hardwareMap.servo.get("liftRT");
        liftRB = hardwareMap.servo.get("liftRB");


        //lslideStartPosition=motorLiftL.getCurrentPosition();
        //rslideStartPosition=motorLiftR.getCurrentPosition();
        liftLTStart = liftLT.getPosition();
        liftLBStart = liftLB.getPosition();
        liftRTStart = liftRT.getPosition();
        liftRBStart = liftRB.getPosition();



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

            bleft1.setPower(gamepad1.left_stick_y*.8);
            bleft2.setPower(gamepad1.left_stick_y * .8);
            bright1.setPower(gamepad1.right_stick_y * .8);
            bright2.setPower(gamepad1.right_stick_y * .8);

//        if (gamepad1.right_stick_x < -.1) {
//            bleft1.setPower(gamepad1.right_stick_y);
//            bleft2.setPower(gamepad1.right_stick_y);
//            bright1.setPower(Math.abs(gamepad1.right_stick_x));
//            bright2.setPower(Math.abs(gamepad1.right_stick_x));
//        } else if (gamepad1.right_stick_x > .1) {
//            bleft1.setPower(-gamepad1.right_stick_y);
//            bleft2.setPower(-gamepad1.right_stick_y);
//            bright1.setPower(Math.abs(gamepad1.right_stick_x));
//            bright2.setPower(Math.abs(gamepad1.right_stick_x));
//        } else {
//            bleft1.setPower(gamepad1.left_stick_y);
//            bleft2.setPower(gamepad1.left_stick_y);
//            bright1.setPower(gamepad1.left_stick_y);
//            bright2.setPower(gamepad1.left_stick_y);
//        }


        //Intake Flywheels
        if (gamepad2.a) {
            intakeL.setPower(1);
            intakeR.setPower(-.7);
        }
        else if (gamepad2.y) {
             intakeL.setPower(-1);
            intakeR.setPower(.7);
        }
         else {
            intakeL.setPower(0);
            intakeR.setPower(0);
        }

        //Block-Arm Mechanism
        if (gamepad2.x) {
            grabL.setPosition(GRAB_LEFT_IN);
            grabR.setPosition(GRAB_RIGHT_IN);
        }
        if (gamepad2.b) {
            grabL.setPosition(GRAB_LEFT_OUT);
            grabR.setPosition(GRAB_RIGHT_OUT);
        }

        //Linear Slide(s)
        if (gamepad2.left_stick_y < -.1) {
            motorLiftL.setPower(gamepad2.left_stick_y);
            motorLiftR.setPower(gamepad2.left_stick_y);
        } else if (gamepad2.left_stick_y > .1) {
            motorLiftL.setPower(gamepad2.left_stick_y);
            motorLiftR.setPower(gamepad2.left_stick_y);
        } else {
            motorLiftL.setPower(0);
            motorLiftR.setPower(0);
        }



        //Lift Blocks
        if (gamepad2.right_stick_y < -.1) {

            liftLBStart+=.05;
            liftLTStart+=.05;
            liftRTStart-=.05;
            liftRBStart-=.05;
        }
        else if (gamepad2.right_stick_y > .1) {
            liftLBStart+=.05;
            liftLTStart+=.05;
            liftRTStart-=.05;
            liftRBStart-=.05;
        } else {
            liftLBStart = .45;
            liftLTStart = .45;
            liftRBStart = .45;
            liftRTStart = .45;
        }

        liftLB.setPosition(liftLBStart);
        liftLT.setPosition(liftLTStart);
        liftRB.setPosition(liftRBStart);
        liftRT.setPosition(liftRTStart);


    }
}





