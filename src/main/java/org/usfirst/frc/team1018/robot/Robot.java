package org.usfirst.frc.team1018.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jaci.pathfinder.Waypoint;
import org.usfirst.frc.team1018.lib.util.Utils;
import org.usfirst.frc.team1018.robot.commands.auto.PathfinderAuto;
import org.usfirst.frc.team1018.robot.pathfinder.PathfinderWaypoints;
import org.usfirst.frc.team1018.robot.subsystems.*;
import org.usfirst.frc.team1018.lib.vision.VisionNetworkTable;

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
    PowerDistributionPanel pdp = new PowerDistributionPanel();
    Command autonomousCommand;
    SendableChooser<Waypoint[]> chooser = new SendableChooser<>();

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
        /*new Thread(() -> {
            UsbCamera camera = new UsbCamera("CoprocessorCamera", 0);
            // Set the resolution for our camera, since this is over USB
            camera.setResolution(160, 120);
            //camera.setResolution(480, 260);
            camera.setBrightness(53);
            camera.getProperty("contrast").set(100);
            camera.getProperty("saturation").set(100);
            camera.setWhiteBalanceAuto();
            camera.getProperty("gain").set(0);
            camera.getProperty("sharpness").set(51);
            camera.setExposureManual(1);
            // This stores our reference to our mjpeg server for streaming the input image
            MjpegServer inputStream = new MjpegServer("MJPEG Server", 1185);
            inputStream.setSource(camera);
        }).start();*/
        chooser.addObject("Blue Right Peg", PathfinderWaypoints.BLUE_RIGHT);
        chooser.addObject("Blue Left Peg", PathfinderWaypoints.BLUE_LEFT);
        SmartDashboard.putData("Autonomous:", chooser);
        drivetrain = Drivetrain.getInstance();
        climber = Climber.getInstance();
        brakes = Brakes.getInstance();
        paddles = Paddles.getInstance();
        gearRotator = GearRotator.getInstance();
        oi = OI.getInstance();
        drivetrain.resetGyro();
        //while(!VisionNetworkTable.getInstance().getStatus()) Timer.delay(0.5);
    }

    /**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
     * the robot is disabled.
     */
    @Override
    public void disabledInit() {
        gearRotator.disable();
    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void robotPeriodic() {
        outputAllToSmartDashboard();
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
        if(chooser.getSelected() != null) {

            autonomousCommand = new PathfinderAuto(chooser.getSelected());
        }

        // schedule the autonomous command (example)
        if(autonomousCommand != null)
            autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    @Override
    public void autonomousPeriodic() {
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
        if(oi.getAlignButton()) {
            drivetrain.driveSimpleArcade(Utils.limit(-oi.getY(), 0.5), 0.5 * VisionNetworkTable.getInstance().getTargetAimingCenterX());
        } else {
            drivetrain.mecanumDrive(oi.getX(), oi.getY(), oi.getTurn());
        }
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
        SmartDashboard.putNumber("Upper Climber Current: ", pdp.getCurrent(12));
        SmartDashboard.putNumber("Lower Climber Current: ", pdp.getCurrent(3));
    }
}
