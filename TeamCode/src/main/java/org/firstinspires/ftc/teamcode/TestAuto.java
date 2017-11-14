package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Created by moham on 9/8/17.
 */

@Autonomous
public class TestAuto extends OpMode {
    VuforiaLocalizer vuforia;
    int cameraMonitorViewId;
    VuforiaTrackables relicTrackables;
    VuforiaTrackable relicTemplate;
    RelicRecoveryVuMark vuMark;
    @Override
    public void init() {
        cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        parameters.vuforiaLicenseKey = "AY1sGR7/////AAAAGUIEPxq1Q0OHlg+rWY6E5Kh4qSsw5c/A1GkGZpCg2zSlxfB97RdicKHfBIuhCnfS1Lx+HQL68oWdyLX5SGlgYVH1BW8TiWpRnNFQ4o0f1qwG5P67klb5EQilENwYt7EIBFTSjFOAWxX9vWyjhL+rW6u9k9dGnCut3ukqr+JML+UjSEXIAhXWEAVu86UH/Y70mx8cobzt3UqSv4PpNStZHPB6OakhBgIiCe2Leh04/kWJ+uAsIyO6bukEOilVcSC0A7LsbvNwOzbqPI1poFBqVXeEP5xqenaGIfgLAjj6g2b4sHXosmphZyAiL8v8XXrq/Uz3KTD1B8Lj9aLcs7c0mZ02yZnqCfOpIhYoUmKRVBgB";

        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate");
        relicTrackables.activate();
    }

    @Override
    public void loop() {

        vuMark = RelicRecoveryVuMark.from(relicTemplate);
        if (vuMark != RelicRecoveryVuMark.UNKNOWN) {
            telemetry.addData("VuMark", "%s visible", vuMark);
        }
    }

}
