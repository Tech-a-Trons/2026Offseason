package org.firstinspires.ftc.teamcode.Pranav;

import com.qualcomm.robotcore.hardware.HardwareMap;

import dev.nextftc.control.ControlSystem;
import dev.nextftc.core.commands.Command;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.controllable.RunToVelocity;
import dev.nextftc.hardware.impl.MotorEx;

public class Intake implements Subsystem {
    public static final Intake INSTANCE = new Intake(hardwareMap);
    public Intake(HardwareMap hardwareMap) { }
    private MotorEx motor = new MotorEx("intake");
    private ControlSystem controlSystem = ControlSystem.builder()
            .posPid(0.005, 0, 0)
            .elevatorFF(0)
            .build();

    public Command into = new RunToVelocity(controlSystem,499).requires(this);
    public Command out = new RunToVelocity(controlSystem,-499).requires(this);
    public Command off = new RunToVelocity(controlSystem,0).requires(this);

    @Override
    public void periodic() {
        motor.setPower(controlSystem.calculate(motor.getState()));
    }
}
