package org.usfirst.frc.team1018.robot.commands.brakes;

import edu.wpi.first.wpilibj.command.InstantCommand;
import org.usfirst.frc.team1018.robot.subsystems.Brakes;

/**
 * @author Ryan Blue
 */
public class BrakesCommand extends InstantCommand {
    private Brakes brakes = Brakes.getInstance();
    private boolean brakesDown;

    public BrakesCommand(boolean brakesDown) {
        requires(brakes);
        this.brakesDown = brakesDown;
    }

    @Override
    protected void execute() {
        brakes.setDown(brakesDown);
    }


}
