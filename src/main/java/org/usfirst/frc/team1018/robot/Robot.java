package org.usfirst.frc.team1018.robot;

import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jaci.pathfinder.Waypoint;
import org.usfirst.frc.team1018.robot.commands.auto.PathfinderAuto;
import org.usfirst.frc.team1018.robot.pathfinder.PathfinderWaypoints;
import org.usfirst.frc.team1018.robot.subsystems.*;

/**
 * @author Ryan Blue
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    public static OI oi;
    private static Drivetrain drivetrain;
    private static Climber climber;
    private static Brakes brakes;
    private static Paddles paddles;
    private static GearRotator gearRotator;
    Command autonomousCommand;
    SendableChooser<Waypoint[]> chooser = new SendableChooser<>();

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
        new Thread(() -> {
            UsbCamera camera = new UsbCamera("CoprocessorCamera", 0);
            // Set the resolution for our camera, since this is over USB
            camera.setResolution(320, 240);
            // This stores our reference to our mjpeg server for streaming the input image
            MjpegServer inputStream = new MjpegServer("MJPEG Server", 1185);
            inputStream.setSource(camera);
        }).start();
        chooser.addObject("Blue Right Peg", PathfinderWaypoints.BLUE_RIGHT);
        chooser.addObject("Blue Left Peg", PathfinderWaypoints.BLUE_LEFT);
        SmartDashboard.putData("Autonomous:", chooser);
        drivetrain = Drivetrain.getInstance();
        climber = Climber.getInstance();
        brakes = Brakes.getInstance();
        paddles = Paddles.getInstance();
        gearRotator = GearRotator.getInstance();
        oi = OI.getInstance();
    }

    /**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
     * the robot is disabled.
     */
    @Override
    public void disabledInit() {
        outputAllToSmartDashboard();
        gearRotator.disable();
    }

    @Override
    public void disabledPeriodic() {
        SmartDashboard.putNumber("Yaw", drivetrain.getYaw());
        Scheduler.getInstance().run();
    }

    /**
     * This autonomous (along with the chooser code above) shows how to select
     * between different autonomous modes using the dashboard. The sendable
     * chooser code works with the Java SmartDashboard. If you prefer the
     * LabVIEW Dashboard, remove all of the chooser code and uncomment the
     * getString code to get the auto name from the text box below the Gyro
     * <p>
     * You can add additional auto modes by adding additional commands to the
     * chooser code above (like the commented example) or additional comparisons
     * to the switch structure below with additional strings & commands.
     */
    @Override
    public void autonomousInit() {
        autonomousCommand = new PathfinderAuto(chooser.getSelected());

        // schedule the autonomous command (example)
        if(autonomousCommand != null)
            autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    @Override
    public void autonomousPeriodic() {
        outputAllToSmartDashboard();
        gearRotator.disable();
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        gearRotator.enable();
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if(autonomousCommand != null)
            autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic() {
        outputAllToSmartDashboard();
        drivetrain.mecanumDrive(oi.getX(), oi.getY(), oi.getTurn());
        Scheduler.getInstance().run();
    }

    /**
     * This function is called periodically during test mode
     */
    @Override
    public void testPeriodic() {
        outputAllToSmartDashboard();
        LiveWindow.run();
    }

    public void outputAllToSmartDashboard() {
        drivetrain.outputToSmartDashboard();
        climber.outputToSmartDashboard();
        brakes.outputToSmartDashboard();
        paddles.outputToSmartDashboard();
        gearRotator.outputToSmartDashboard();

    }
}
