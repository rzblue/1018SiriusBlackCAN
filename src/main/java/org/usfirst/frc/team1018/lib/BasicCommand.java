package org.usfirst.frc.team1018.lib;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Ryan Blue
 * <p>
 * Command to do a basic action without feedback (spinning a motor until a joystick is released)
 */
public abstract class BasicCommand extends Command {

    // Called just before this Command runs the first time
    protected final void initialize() {
        runOnce();
    }

    /**
     * Use this command to start your actuator(s)
     */
    protected abstract void runOnce();

    // Called repeatedly when this Command is scheduled to run
    protected final void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected final boolean isFinished() {
        return false;
    }

    /**
     * Finishes the command
     * <p>
     * Use this method to stop any actuators associated with the command.
     */
    protected abstract void end();

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected final void interrupted() {
        end();
    }
}
