package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


/**
 * Created by HomeFolder on 11/9/17.
 */

public class ScrimTeleop extends OpMode {

    DcMotor bleft1, bleft2, bright1, bright2, intakeR, intakeL, motorLiftL, motorLiftR;
    Servo liftLT, liftLB, liftRT, liftRB, grabL, grabR;

    @Override
    public void init() {
        bleft1 = hardwareMap.dcMotor.get("bleft1");
        bleft2 = hardwareMap.dcMotor.get("belft2");
        bright1 = hardwareMap.dcMotor.get("bright1");
        bright2 = hardwareMap.dcMotor.get("bright2");
        intakeR = hardwareMap.dcMotor.get("intakeR");
        intakeL = hardwareMap.dcMotor.get("intakeL");
        motorLiftL = hardwareMap.dcMotor.get("motorLiftL");
        motorLiftR = hardwareMap.dcMotor.get("motorLiftR");
        liftLT = hardwareMap.servo.get("liftLT");
        liftLB = hardwareMap.servo.get("liftLB");
        liftRT = hardwareMap.servo.get("liftRT");
        liftRB = hardwareMap.servo.get("liftRB");
        grabL = hardwareMap.servo.get("grabL");
        grabR = hardwareMap.servo.get("grabR");


        bleft1.setDirection(DcMotorSimple.Direction.REVERSE);
        bleft2.setDirection(DcMotorSimple.Direction.REVERSE);
        intakeL.setDirection(DcMotorSimple.Direction.FORWARD);

        bleft1.setPower(0);
        bleft2.setPower(0);
        bright1.setPower(0);
        bright2.setPower(0);
        intakeL.setPower(0);
        intakeR.setPower(0);
        motorLiftL.setPower(0);
        motorLiftR.setPower(0);

        liftLT.setPosition(0);
        liftLB.setPosition(0);
        liftRT.setPosition(0);
        liftRB.setPosition(0);
        grabL.setPosition(0);
        grabR.setPosition(0);
    }

    @Override
    public void loop() {

        motorLiftL.setPower(gamepad2.left_trigger);
        motorLiftR.setPower(gamepad2.right_trigger);


        if (gamepad1.right_stick_x < 0) {
            bleft1.setPower(gamepad1.right_stick_y);
            bleft2.setPower(gamepad1.right_stick_y);
            bright1.setPower(Math.abs(gamepad1.right_stick_x));
            bright2.setPower(Math.abs(gamepad1.right_stick_x));
        } else if (gamepad1.right_stick_x > 0) {
            bleft1.setPower(-gamepad1.right_stick_y);
            bleft2.setPower(-gamepad1.right_stick_y);
            bright1.setPower(Math.abs(gamepad1.right_stick_x));
            bright2.setPower(Math.abs(gamepad1.right_stick_x));
        } else {
            bleft1.setPower(gamepad1.left_stick_y);
            bleft2.setPower(gamepad1.left_stick_y);
            bright1.setPower(gamepad1.left_stick_y);
            bright2.setPower(gamepad1.left_stick_y);
        }


        //Intake Mechanism
        if (gamepad1.a) {
            intakeL.setPower(.8);
            intakeR.setPower(.8);
        } else {
            intakeL.setPower(0);
            intakeR.setPower(0);
        }

        //Block-Grabbing Mechanism
        if (gamepad1.b) {
            grabL.setPosition(.5);
            grabR.setPosition(.5);
        }

        if (gamepad2.b) {
            grabL.setPosition(0);
            grabR.setPosition(0);
        }


    }
}





