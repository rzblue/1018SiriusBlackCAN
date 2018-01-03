package org.usfirst.frc.team1018.robot.subsystems;

import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team1018.robot.RobotConfig;

/**
 * @author Ryan Blue
 */
public class Climber extends Subsystem {
    private static Climber instance;
    public RobotConfig.ClimberConfig CONFIG = RobotConfig.CLIMBER_CONFIG;
    private TalonSRX lowerClimber = new TalonSRX(CONFIG.CLIMBER_LOWER_PWM);
    private TalonSRX upperClimber = new TalonSRX(CONFIG.CLIMBER_UPPER_PWM);
    private State state = State.OFF;

    private Climber() {
        lowerClimber.setInverted(true);
    }

    public static Climber getInstance() {
        if(instance == null) instance = new Climber();
        return instance;
    }

    public void setState(State state) {
        this.state = state;
        lowerClimber.set(state.value);
        upperClimber.set(state.value);
    }

    public void outputToSmartDashboard() {
        SmartDashboard.putString("Climber State: ", state.toString());
    }

    public void initDefaultCommand() {
        //No default command
    }

    public enum State {

        CLIMB_UP(1),
        CLIMB_DOWN(-1),
        OFF(0);

        public double value;

        State(double value) {
            this.value = value;
        }
    }
}

