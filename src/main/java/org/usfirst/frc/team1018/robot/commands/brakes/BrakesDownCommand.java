package org.usfirst.frc.team1018.robot.commands.brakes;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import org.usfirst.frc.team1018.lib.BasicCommand;
import org.usfirst.frc.team1018.robot.subsystems.Brakes;

/**
 * @author Ryan Blue
 */
public class BrakesDownCommand extends Command {
    private Brakes brakes = Brakes.getInstance();

    public BrakesDownCommand() {
        requires(brakes);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        brakes.set();
    }

    // Called repeatedly when this Command is scheduled to run

    protected void execute() {}
    // Called once after isFinished() returns true

    protected boolean isFinished() {
        return false;
    }

    protected void end() {}

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }

}
