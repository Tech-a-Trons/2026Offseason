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
    @Override
    public void periodic() {
        // periodic logic (runs every loop)
    }
}