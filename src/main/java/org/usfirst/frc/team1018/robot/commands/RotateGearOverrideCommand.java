package org.usfirst.frc.team1018.robot.commands;

import org.usfirst.frc.team1018.lib.BasicCommand;
import org.usfirst.frc.team1018.robot.subsystems.GearRotator;

/**
 * @author Ryan Blue
 */
public class RotateGearOverrideCommand extends BasicCommand {
    private GearRotator gearRotator = GearRotator.getInstance();

    public RotateGearOverrideCommand() {
        requires(gearRotator);
    }

    protected void runOnce() {
        gearRotator.setOverride(true);
    }

    protected void end() {
        gearRotator.setOverride(false);
    }
}
