package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by HomeFolder on 11/12/17.
 */

@Autonomous
public class ScrimBlueR extends ScrimRobot {

    private ElapsedTime currentTime = new ElapsedTime();
    private ScrimRobot robot = new ScrimRobot();

    enum AutoState{DriveForward, Left, DriveMore, Raise, VerticalBlocks, Release, Stop}
    AutoState state = AutoState.DriveForward;

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void loop() {
        start();
        telemetry.addData("Time ", currentTime);
        super.loop();
        telemetry.addData("Robot State: ", state);

        switch(state) {
            case DriveForward:
                if (drive(driveState.Forwards, .5, 2)) {
                    state = AutoState.Left;
                    break;
                }

            case Left:
                if (drive(driveState.TurnRight, .5, 2)) {
                    state = AutoState.DriveMore;
                    break;
                }

            case DriveMore:
                if (drive(driveState.Forwards, .5, .7)) {
                    state = AutoState.Raise;
                    break;
                }

            case Raise:
                if (liftandRelease(driveState.LiftSlide, .8, 0, 1.5)) {
                    state = AutoState.VerticalBlocks;
                    break;
                }

            case VerticalBlocks:
                if (liftandRelease(driveState.MoveBlocks, .8, .5, 1.5)) {
                    state = AutoState.Release;
                    break;
                }

            case Release:
                if (robot.liftandRelease(driveState.Release, 0, 0, 0)) {
                    state = AutoState.Stop;
                    break;
                }

            case Stop:
                robot.stop();

        }






    }
}
