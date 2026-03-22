package org.firstinspires.ftc.teamcode.Reyansh;

import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Reyansh.Subsystems.ColorSensor;
import org.firstinspires.ftc.teamcode.Reyansh.Subsystems.Intaker;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

import java.util.function.Supplier;

import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.ftc.Gamepads;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;
@TeleOp(name = "Reyansh's TeleOp")
public class Teleop extends NextFTCOpMode {
    public Teleop() {

        // Where you add code components that will be used in your opmode, and by putting things here it is reusable
        addComponents(
                // This is where all of the subsystems are called
                new SubsystemComponent(
                        ColorSensor.INSTANCE,
                        Intaker.INSTANCE

                ),
                // Allows hardware calls to be done in one instant
                BulkReadComponent.INSTANCE,
                // The Next FTC binding system to attach commands to buttons
                BindingsComponent.INSTANCE
        );
    }

    boolean intaketoggle = false;
    private Follower follower;
    public static Pose startingPose; //See ExampleAuto to understand how to use this
    private boolean automatedDrive;
    private Supplier<PathChain> pathChain;
    private TelemetryManager telemetryM;
    private double slowModeMultiplier = 1;

    @Override
    public void onInit () {
        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(startingPose == null ? new Pose() : startingPose);
        follower.update();
        telemetryM = PanelsTelemetry.INSTANCE.getTelemetry();
        Intaker.INSTANCE.init(hardwareMap);
    }
    @Override
    public void onWaitForStart() {

    }
    @Override
    public void onStartButtonPressed() {
        follower.startTeleopDrive();

        Gamepads.gamepad1().a()
                .whenBecomesTrue(() -> {
                    intaketoggle = !intaketoggle;
                    if (intaketoggle) {
                        Intaker.INSTANCE.forward();
                    } else {
                            Intaker.INSTANCE.forward();
                        }
                    }
                );

    }

    @Override
    public void onUpdate(){

        follower.update();
        telemetryM.update();

        follower.setTeleOpDrive(
                -gamepad1.left_stick_y,
                -gamepad1.left_stick_x,
                -gamepad1.right_stick_x,
                true // Robot Centric
        );
    }

    @Override
    public void onStop() {

    }
}

