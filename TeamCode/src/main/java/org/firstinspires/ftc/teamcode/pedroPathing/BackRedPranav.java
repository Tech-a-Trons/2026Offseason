package org.firstinspires.ftc.teamcode.pedroPathing;
import com.pedropathing.paths.Path;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.telemetry.TelemetryManager;
import com.bylazar.telemetry.PanelsTelemetry;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.follower.Follower;
import com.pedropathing.paths.PathChain;
import com.pedropathing.geometry.Pose;

@Autonomous(name = "BackRedPranav", group = "Autonomous")
@Configurable // Panels
public class BackRedPranav extends OpMode {
    private TelemetryManager panelsTelemetry; // Panels Telemetry instance
    public Follower follower; // Pedro Pathing follower instance
    private int pathState; // Current autonomous path state (state machine)
    private Paths paths; // Paths defined in the Paths class
    private Timer pathTimer;
    private Timer opmodeTimer;
    public static Pose startPose = new Pose(88.10191082802545, 8.18343949044586, Math.toDegrees(0)); // Start Pose of our robot.

    @Override
    public void init() {
        panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();

        pathTimer = new Timer();
        opmodeTimer = new Timer();
        opmodeTimer.resetTimer();

        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(startPose);

        paths = new Paths(follower); // Build paths

        panelsTelemetry.debug("Status", "Initialized");
        panelsTelemetry.update(telemetry);
    }

    @Override
    public void loop() {
        follower.update(); // Update Pedro Pathing
        pathState = autonomousPathUpdate(); // Update autonomous state machine

        // Log values to Panels and Driver Station
        panelsTelemetry.debug("Path State", pathState);
        panelsTelemetry.debug("X", follower.getPose().getX());
        panelsTelemetry.debug("Y", follower.getPose().getY());
        panelsTelemetry.debug("Heading", follower.getPose().getHeading());
        panelsTelemetry.update(telemetry);
    }

    private static Path shootPreload;
    public static PathChain intakeSide;
    public static PathChain gotoshoot1;
    public static PathChain intake3rd;
    public static PathChain gotoshoot2;
    public static PathChain intakegate1;
    public static PathChain gotoshoot3;
    public static PathChain intakegate2;
    public static PathChain gotoshoot4;
    public static PathChain intakegate3;
    public static PathChain telesetgotoshoot5;

    public static class Paths {

        public Paths(Follower follower) {
            shootPreload = new Path(new BezierLine(startPose,startPose));
            shootPreload.setLinearHeadingInterpolation(startPose.getHeading(), startPose.getHeading());

            intakeSide = follower.pathBuilder()
                    .addPath(
                            new BezierLine(
                                    startPose,
                                    new Pose(135.804, 8.231)
                            )
                    )
                    .setConstantHeadingInterpolation(Math.toRadians(0))
                    .build();

            gotoshoot1 = follower.pathBuilder()
                    .addPath(
                            new BezierLine(
                                    new Pose(135.804, 8.231),
                                    new Pose(74.189, 15.654)
                            )
                    )
                    .setConstantHeadingInterpolation(Math.toRadians(0))
                    .build();

            intake3rd = follower.pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(74.189, 15.654),
                                    new Pose(91.846, 36.960),
                                    new Pose(126.125, 35.134)
                            )
                    )
                    .setConstantHeadingInterpolation(Math.toRadians(0))
                    .build();

            gotoshoot2 = follower.pathBuilder()
                    .addPath(
                            new BezierLine(
                                    new Pose(126.125, 35.134),
                                    new Pose(74.153, 15.586)
                            )
                    )
                    .setConstantHeadingInterpolation(Math.toRadians(0))
                    .build();

            intakegate1 = follower.pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(74.153, 15.586),
                                    new Pose(106.028, 7.762),
                                    new Pose(135.720, 8.140)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
                    .build();

            gotoshoot3 = follower.pathBuilder()
                    .addPath(
                            new BezierLine(
                                    new Pose(135.720, 8.140),
                                    new Pose(74.181, 15.628)
                            )
                    )
                    .setConstantHeadingInterpolation(Math.toRadians(0))
                    .build();

            intakegate2 = follower.pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(74.181, 15.628),
                                    new Pose(106.096, 7.450),
                                    new Pose(135.775, 8.190)
                            )
                    )
                    .setConstantHeadingInterpolation(Math.toRadians(0))
                    .build();

            gotoshoot4 = follower.pathBuilder()
                    .addPath(
                            new BezierLine(
                                    new Pose(135.775, 8.190),
                                    new Pose(74.306, 15.652)
                            )
                    )
                    .setConstantHeadingInterpolation(Math.toRadians(0))
                    .build();

            intakegate3 = follower.pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(74.306, 15.652),
                                    new Pose(106.041, 7.460),
                                    new Pose(135.800, 8.238)
                            )
                    )
                    .setConstantHeadingInterpolation(Math.toRadians(0))
                    .build();

            telesetgotoshoot5 = follower.pathBuilder()
                    .addPath(
                            new BezierLine(
                                    new Pose(135.800, 8.238),
                                    new Pose(74.247, 15.706)
                            )
                    )
                    .setConstantHeadingInterpolation(Math.toRadians(0))
                    .build();
        }
    }

    public int autonomousPathUpdate() {
        switch (pathState) {
            case 0:
                follower.followPath(shootPreload);
                setPathState(1);
                break;
            case 1:
                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the scorePose's position */
                if(pathTimer.getElapsedTime() > 0.2) {
                    /* Score Preload */

                    /* Since this is a pathChain, we can have Pedro hold the end point while we are grabbing the sample */
                    follower.followPath(intakeSide,true);
                    setPathState(2);
                }
                break;
            case 2:
                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the pickup1Pose's position */
                if(!follower.isBusy()) {
                    /* Grab Sample */

                    /* Since this is a pathChain, we can have Pedro hold the end point while we are scoring the sample */
                    follower.followPath(gotoshoot1,true);
                    setPathState(3);
                }
                break;
            case 3:
                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the scorePose's position */
                if(pathTimer.getElapsedTime() > 0.2) {
                    /* Score Sample */

                    /* Since this is a pathChain, we can have Pedro hold the end point while we are grabbing the sample */
                    follower.followPath(intake3rd,true);
                    setPathState(4);
                }
                break;
            case 4:
                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the pickup2Pose's position */
                if(!follower.isBusy()) {
                    /* Grab Sample */

                    /* Since this is a pathChain, we can have Pedro hold the end point while we are scoring the sample */
                    follower.followPath(gotoshoot2,true);
                    setPathState(5);
                }
                break;
            case 5:
                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the scorePose's position */
                if(pathTimer.getElapsedTime() > 0.2) {
                    /* Score Sample */

                    /* Since this is a pathChain, we can have Pedro hold the end point while we are grabbing the sample */
                    follower.followPath(intakegate1,true);
                    setPathState(6);
                }
                break;
            case 6:
                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the pickup3Pose's position */
                if(!follower.isBusy()) {
                    /* Grab Sample */

                    /* Since this is a pathChain, we can have Pedro hold the end point while we are scoring the sample */
                    follower.followPath(gotoshoot3, true);
                    setPathState(7);
                }
                break;
            case 7:
                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the scorePose's position */
                if(pathTimer.getElapsedTime() > 0.2) {
                    /* Set the state to a Case we won't use or define, so it just stops running an7 new paths */
                    follower.followPath(intakegate2, true);
                    setPathState(8);
                }
                break;
            case 8:
                if(!follower.isBusy()) {
                    /* Grab Sample */

                    /* Since this is a pathChain, we can have Pedro hold the end point while we are scoring the sample */
                    follower.followPath(gotoshoot4, true);
                    setPathState(9);
                }
                break;
            case 9:
                if(pathTimer.getElapsedTime() > 0.2) {
                    /* Set the state to a Case we won't use or define, so it just stops running an7 new paths */
                    follower.followPath(intakegate3, true);
                    setPathState(10);
                }
                break;
            case 10:
                if(!follower.isBusy()) {
                    /* Grab Sample */

                    /* Since this is a pathChain, we can have Pedro hold the end point while we are scoring the sample */
                    follower.followPath(telesetgotoshoot5, true);
                    setPathState(11);
                }
                break;
            case 11:
                if(pathTimer.getElapsedTime() > 0.2) {
                    setPathState(-1);
                }
                break;
        }
        return 0;
    }

    /** These change the states of the paths and actions. It will also reset the timers of the individual switches **/
    public void setPathState(int pState) {
        pathState = pState;
        pathTimer.resetTimer();
    }
}