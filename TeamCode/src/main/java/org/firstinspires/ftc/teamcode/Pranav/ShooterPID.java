package org.firstinspires.ftc.teamcode.Pranav;

import com.qualcomm.robotcore.hardware.DcMotorEx;

import dev.nextftc.core.subsystems.Subsystem;

public class ShooterPID implements Subsystem {
    private DcMotorEx flywheelMotor;
    private ControlSystem controller;

    @Override
    public void init() {
        flywheelMotor = hardwareMap.get(DcMotorEx.class, "flywheel");

        controller = ControlSystem.builder()
                .velPid(0.001, 0.0, 0.0)
                .basicFF(0.003, 0.08, 0.0)
                .build();

        controller.setGoal(new KineticState(0.0, 0.0));
    }

    @Override
    public void loop() {
        if (gamepad1.aWasPressed()) {
            controller.setGoal(new KineticState(0.0, 2000.0));
        } else if (gamepad1.bWasPressed()) {
            controller.setGoal(new KineticState(0.0, 0.0));
        } else if (gamepad1.xWasPressed()) {
            controller.setGoal(new KineticState(0.0, 1000.0));
        }

        flywheelMotor.setPower(controller.calculate(new KineticState(
                flywheelMotor.getCurrentPosition(),
                flywheelMotor.getVelocity()))
        );
    }
}