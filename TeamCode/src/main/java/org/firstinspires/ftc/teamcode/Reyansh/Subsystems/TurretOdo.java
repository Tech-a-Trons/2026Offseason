//package org.firstinspires.ftc.teamcode.Reyansh.Subsystems;
//
//import com.pedropathing.geometry.PedroCoordinates;
//import com.pedropathing.geometry.Pose;
//
//import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
//
//import dev.nextftc.core.subsystems.Subsystem;
//
//public class TurretOdo implements Subsystem {
//
//
//    public static TurretOdo INSTANCE = new TurretOdo();
//    Pose cachedPose = null;
//    double x = 0;
//    double y = 0;
//    double heading = 0;
//    @Override
//    public void periodic() {
//
//
//        cachedPose = PedroComponent.follower().getPose();
//
//        x = cachedPose.getY();
//    }
//}
