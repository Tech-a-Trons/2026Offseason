package org.firstinspires.ftc.teamcode.Reyansh.Subsystems;

import static com.sun.tools.javac.jvm.ByteCodes.error;
import static java.lang.Math.atan2;
import static java.lang.System.out;

import com.pedropathing.geometry.PedroCoordinates;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.extensions.pedro.PedroComponent;

public class TurretOdo implements Subsystem {


    public static TurretOdo INSTANCE = new TurretOdo();
    Pose cachedPose = null;
    double x = 0;
    double y = 0;

    double tx = 121;
    double ty = 121;
    double heading = 0;

    public void init(HardwareMap hardwareMap) {
        Servo turret1 = hardwareMap.get(Servo.class, "turret1");
        Servo turret2 = hardwareMap.get(Servo.class, "turret2");
    }

    @Override
    public void periodic() {
        cachedPose = PedroComponent.follower().getPose();

        x = cachedPose.getY();
        y = cachedPose.getX();
        heading = (cachedPose.getHeading() * 360) % 360;

        double fieldangle = atan2(ty-y,tx-x);
        double angle = fieldangle - heading;

//         = Servo1.getPosition();
        // calculate the error
//        error = reference - encoderPosition;
//
//        // rate of change of the error
//        derivative = (error - lastError) / timer.seconds();
//
//        // sum of all error over time
//        integralSum = integralSum + (error * timer.seconds());
//
//        out = (Kp * error) + (Ki * integralSum) + (Kd * derivative);
//        armMotor.setPower(out);
//
//        lastError = error;

    }

}
