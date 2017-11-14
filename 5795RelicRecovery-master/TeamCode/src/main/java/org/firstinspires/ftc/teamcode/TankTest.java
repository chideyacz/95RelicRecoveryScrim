package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by HomeFolder on 9/29/17.
 */

public class TankTest extends OpMode {

    DcMotor fleft;
    DcMotor bleft;
    DcMotor fright;
    DcMotor bright;
    Servo bumper;
    double position;
    //Gamepad gamepad;


    @Override
    public void init() {

        fleft = hardwareMap.dcMotor.get("fleft");
        bleft = hardwareMap.dcMotor.get("bleft");
        fright= hardwareMap.dcMotor.get("fright");
        bright = hardwareMap.dcMotor.get("bright");
        bumper = hardwareMap.servo.get("bumper");

        fright.setDirection(DcMotorSimple.Direction.REVERSE);
        bright.setDirection(DcMotorSimple.Direction.REVERSE);

        fleft.setPower(0);
        bleft.setPower(0);
        fright.setPower(0);
        bright.setPower(0);
        bumper.setPosition(0);

    }

    @Override
    public void loop() {

        fleft.setPower(gamepad1.left_stick_y);
        bleft.setPower(gamepad1.left_stick_y);
        fright.setPower(gamepad1.right_stick_y);
        bright.setPower(gamepad1.right_stick_y);


        if(gamepad1.b) {
            position = bumper.getPosition();
            bumper.setPosition(position + 5);
        }


    }

}
