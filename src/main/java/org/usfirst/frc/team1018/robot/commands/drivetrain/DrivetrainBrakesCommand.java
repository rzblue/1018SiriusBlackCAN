package org.usfirst.frc.team1018.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import org.usfirst.frc.team1018.robot.subsystems.Drivetrain;

/**
 * @author Ryan Blue
 * <p>
 * Sets the drivetrain's brake mode
 * @see Drivetrain
 */
public class DrivetrainBrakesCommand extends InstantCommand {
    Drivetrain drivetrain = Drivetrain.getInstance();
    boolean brake;

    /**
     * Creates a new DrivetrainBrakesCommand
     *
     * @param brake set the brakes or not
     */
    public DrivetrainBrakesCommand(boolean brake) {
        requires(drivetrain);
        this.brake = brake;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        drivetrain.enableBrakeMode(brake);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {}

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished() returns true
    protected void end() {}

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
