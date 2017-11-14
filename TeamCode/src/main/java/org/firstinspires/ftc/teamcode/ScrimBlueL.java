package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by HomeFolder on 11/12/17.
 */

@Autonomous
public class ScrimBlueL extends ScrimRobot {

    private ElapsedTime runtime = new ElapsedTime();

    enum AutoState{DriveForward, Left, DriveMore, Raise, VerticalBlocks, Release, Stop}
    ScrimBlueL.AutoState state = ScrimBlueL.AutoState.DriveForward;



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
                    state = ScrimBlueL.AutoState.Left;
                    break;
                }

            case Left:
                if (drive(driveState.TurnLeft, .5, 1.5)) {
                    state = ScrimBlueL.AutoState.DriveMore;
                    break;
                }

            case DriveMore:
                if (drive(driveState.Forwards, .5, .7)) {
                    state = ScrimBlueL.AutoState.Raise;
                    break;
                }

            case Raise:
                if (liftandRelease(driveState.LiftSlide, .8, 0, 1.5)) {
                    state = ScrimBlueL.AutoState.VerticalBlocks;
                    break;
                }

            case VerticalBlocks:
                if (liftandRelease(driveState.MoveBlocks, .8, .5, 1.5)) {
                    state = ScrimBlueL.AutoState.Release;
                    break;
                }

            case Release:
                if (liftandRelease(driveState.Release, 0, 0, 0)) {
                    state = ScrimBlueL.AutoState.Stop;
                    break;
                }

            case Stop:
                stop();

        }






    }
}
