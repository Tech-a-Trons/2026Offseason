package org.firstinspires.ftc.teamcode.Reyansh;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Pranav.Intake;
import org.firstinspires.ftc.teamcode.Reyansh.Subsystems.ColorSensor;
import org.firstinspires.ftc.teamcode.Reyansh.Subsystems.Intaker;

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
    @Override
    public void onInit () {
        Intake Intake = new Intake(hardwareMap);

    }
    @Override
    public void onWaitForStart() {

    }
    @Override
    public void onStartButtonPressed() {
        Gamepads.gamepad1().a()
                .whenBecomesTrue(
                        Intaker.INSTANCE.forward()
    );

    }

    @Override
    public void onUpdate(){

    }

    @Override
    public void onStop() {

    }
}

