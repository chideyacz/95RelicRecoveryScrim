package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by HomeFolder on 11/12/17.
 */

@Autonomous
public class ScrimBlueRTest extends ScrimRobotTest{

        private ElapsedTime currentTime = new ElapsedTime();
        private ScrimRobot robot = new ScrimRobot();
        private double DriveForwardTime = 1.0;
        private double LeftTime = 2.5;
        private double DriveMoreTime = 3.2;
        private double RaiseTime = 7;


        enum AutoState{DriveForward, Left, DriveMore, Raise, VerticalBlocks, Release, Stop}
        AutoState state = AutoState.DriveForward;

        @Override
        public void init() {
            super.init();
            start();
        }

        @Override
        public void loop() {

            telemetry.addData("Time ", currentTime);
            super.loop();
            telemetry.addData("Robot State: ", state);

            switch(state) {
                case DriveForward:
                    if (drive(.8, .8, DriveForwardTime)) {
                        state = AutoState.Stop;
                        break;
                    }

                case Left:
                    if (drive(-.5, .5, LeftTime)) {
                        state = AutoState.DriveMore;
                        break;
                    }

                case DriveMore:
                    if (drive(.8, .8, DriveMoreTime)) {
                        state = AutoState.Stop;
                        break;
                    }

                /*case Raise:
                    if (lift(.8, 10)) {
                        state = org.firstinspires.ftc.teamcode.ScrimBlueR.AutoState.VerticalBlocks;
                        break;
                    }

                case VerticalBlocks:
                    if (rotate()) {
                        state = org.firstinspires.ftc.teamcode.ScrimBlueR.AutoState.Release;
                        break;
                    }

                case Release:
                    if (release()) {
                        state = org.firstinspires.ftc.teamcode.ScrimBlueR.AutoState.Stop;
                        break;
                    }*/

                case Stop:
                    stop();

            }






        }
    }


