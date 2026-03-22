package org.firstinspires.ftc.teamcode.Reyansh.Subsystems;

import android.health.connect.datatypes.units.Velocity;

import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import dev.nextftc.control.ControlSystem;
import dev.nextftc.control.KineticState;
import dev.nextftc.extensions.pedro.PedroComponent;

public class TurretPID extends OpMode {
    private DcMotorEx flywheelMotor;
    private ControlSystem controller;

    Pose CachedPose = null;

    @Override
    public void init() {
        flywheelMotor = hardwareMap.get(DcMotorEx.class, "Motor1");

        controller = ControlSystem.builder()
                .velPid(0.001, 0.0, 0.0)
                .basicFF(0.003, 0.08, 0.0)
                .build();

        controller.setGoal(new KineticState(0.0, 0.0));
    }

    double yt = 121 - 72;
    double xt = 121 - 72;



    @Override
    public void loop() {
        Pose cachedPose = PedroComponent.follower().getPose();

        double x = cachedPose.getY() - 72;
        double y = cachedPose.getX() - 72;

        double distance = Math.sqrt(Math.pow(yt-y, 2)  + Math.pow(xt-x, 2));

        double Velocity = (distance * 200) + 200;

        controller.setGoal(new KineticState(0.0, Velocity));
//
//        if (gamepad1.aWasPressed()) {
//            controller.setGoal(new KineticState(0.0, 2000.0));
//        } else if (gamepad1.bWasPressed()) {
//            controller.setGoal(new KineticState(0.0, 0.0));
//        } else if (gamepad1.xWasPressed()) {
//            controller.setGoal(new KineticState(0.0, 1000.0));
//        }

        flywheelMotor.setPower(controller.calculate(new KineticState(
                flywheelMotor.getCurrentPosition(),
                flywheelMotor.getVelocity()))
        );
    }



}