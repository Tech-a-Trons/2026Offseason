package org.firstinspires.ftc.teamcode.Reyansh.Subsystems;

import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.impl.MotorEx;

public class Intaker implements Subsystem {
    public static final Intaker INSTANCE = new Intaker();
    // put hardware, commands, etc here


        MotorEx intake = new MotorEx("intake");


    public void forward() {
        intake.setPower(1);
    }

    public void backward() {
        intake.setPower(1);
    }

    public void slight() {
        intake.setPower(0.35);
    }
    public void advance() {
        intake.setPower(0.60);
    }
    @Override
    public void initialize() {
        // initialization logic (runs on init)
    }

    @Override
    public void periodic() {
        // periodic logic (runs every loop)
    }
}