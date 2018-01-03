package org.usfirst.frc.team1018.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;
import org.usfirst.frc.team1018.robot.subsystems.Drivetrain;

/**
 * @author Ryan Blue
 * <p>
 * A Pathfinder based autonomous mode, configurable by passing a array of waypoints
 *
 * FIXME: tune
 * TODO: after paths are tuned in, pregenerate paths either on program start or save to file
 */
public class PathfinderAuto extends Command {
    /**
     * The trajectory to follow
     */
    private Trajectory trajectory;
    /**
     *
     * Modifier for driving on a "tank" drive;
     */
    private TankModifier modifier;
    /**
     * Tracks and updates outputs based on encoder feedback and trajectory.
     */
    private EncoderFollower left, right;

    private Drivetrain drivetrain = Drivetrain.getInstance();

    /**
     * Creates a new PathfinderAuto
     * @param waypoints
     */
    public PathfinderAuto(Waypoint[] waypoints) {
        requires(drivetrain);
        trajectory = Pathfinder.generate(waypoints, drivetrain.CONFIG.TRAJECTORY_CFG);
        modifier = new TankModifier(trajectory).modify(drivetrain.CONFIG.WHEELBASE_WIDTH_M_CFG);
        left = new EncoderFollower(modifier.getLeftTrajectory());
        right = new EncoderFollower(modifier.getRightTrajectory());
    }

    protected void initialize() {
        drivetrain.resetEncoders();
        left.configureEncoder(drivetrain.getLeftEncoderTicks(), drivetrain.CONFIG.ENCODER_TICKS_PER_REV_CFG, drivetrain.CONFIG.WHEEL_DIAMETER_M_CFG);
        right.configureEncoder(drivetrain.getRightEncoderTicks(), drivetrain.CONFIG.ENCODER_TICKS_PER_REV_CFG, drivetrain.CONFIG.WHEEL_DIAMETER_M_CFG);
        left.configurePIDVA(1.0, 0.0, 0.0, 1 / drivetrain.CONFIG.TRAJECTORY_CFG.max_velocity, 0);
        right.configurePIDVA(1.0, 0.0, 0.0, 1 / drivetrain.CONFIG.TRAJECTORY_CFG.max_velocity, 0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double l = left.calculate(drivetrain.getLeftEncoderTicks());
        double r = right.calculate(drivetrain.getRightEncoderTicks());

        double gyroHeading = drivetrain.getYaw();
        double desiredHeading = Pathfinder.r2d(left.getHeading());

        double angleDifference = Pathfinder.boundHalfDegrees(desiredHeading - gyroHeading);

        double turn = 0.8 * (-1.0 / 80.0) * angleDifference;

        drivetrain.setLeftRightMotors(l - turn, r + turn);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return left.isFinished() && right.isFinished();
    }

    // Called once after isFinished() returns true
    protected void end() {

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
