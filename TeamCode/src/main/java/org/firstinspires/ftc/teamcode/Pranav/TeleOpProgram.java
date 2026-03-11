package org.firstinspires.ftc.teamcode.Pranav;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.ftc.Gamepads;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;
import dev.nextftc.hardware.driving.MecanumDriverControlled;
import dev.nextftc.hardware.impl.MotorEx;

@TeleOp(name = "NextFTC TeleOp Program Java")
public class TeleOpProgram extends NextFTCOpMode {
    public TeleOpProgram() {
        addComponents(
                new SubsystemComponent(Intake.INSTANCE, SimpleTurret.INSTANCE),
                BulkReadComponent.INSTANCE,
                BindingsComponent.INSTANCE
        );
    }

    // change the names and directions to suit your robot
    private final MotorEx frontLeftMotor = new MotorEx("fl").reversed();
    private final MotorEx frontRightMotor = new MotorEx("fr");
    private final MotorEx backLeftMotor = new MotorEx("bl").reversed();
    private final MotorEx backRightMotor = new MotorEx("br");

    @Override
    public void onStartButtonPressed() {
        Command driverControlled = new MecanumDriverControlled(
                frontLeftMotor,
                frontRightMotor,
                backLeftMotor,
                backRightMotor,
                Gamepads.gamepad1().leftStickY().negate(),
                Gamepads.gamepad1().leftStickX(),
                Gamepads.gamepad1().rightStickX()
        );
        driverControlled.schedule();

        Gamepads.gamepad1().rightBumper().whenBecomesTrue(Intake.INSTANCE.into);
        Gamepads.gamepad1().y().whenBecomesTrue(Intake.INSTANCE.out);
        Gamepads.gamepad1().leftBumper().whenBecomesTrue(Intake.INSTANCE.off);

        Gamepads.gamepad1().a().whenBecomesTrue(SimpleTurret.INSTANCE.left1.and(SimpleTurret.INSTANCE.right1));
        Gamepads.gamepad1().b().whenBecomesTrue(SimpleTurret.INSTANCE.left2.and(SimpleTurret.INSTANCE.right2));
    }
}
