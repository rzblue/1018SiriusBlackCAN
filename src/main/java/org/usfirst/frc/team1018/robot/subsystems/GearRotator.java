package org.usfirst.frc.team1018.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team1018.lib.LidarLiteSensor;
import org.usfirst.frc.team1018.robot.RobotConfig;

/**
 * @author Ryan Blue
 */
public class GearRotator extends Subsystem {
    public RobotConfig.GearRotatorConfig CONFIG = RobotConfig.GEAR_ROTATOR_CONFIG;

    private static GearRotator instance;
    private boolean override;
    private boolean enabled;
    private GearStateMachine stateMachine = new GearStateMachine();
    private TalonSRX rotatorMotor = new TalonSRX(CONFIG.GEAR_ROTATOR_PWM);
    private DigitalInput banner = new DigitalInput(CONFIG.BANNER_DIO);
    private LidarLiteSensor lidar = new LidarLiteSensor(CONFIG.LIDAR_I2C);
    private Runnable stateMachineRunnable = new Runnable() {
        @Override
        public void run() {
            boolean spin = stateMachine.shouldRotateGear(isGearAligned(), getLidarDistance()) || override;
            if(spin) rotate();
            else stop();
        }
    };
    /**
     * Schedules repeated execution of state machine
     */
    private Notifier notifier;

    /**
     * Delay in seconds for the Notifier
     */
    private double period = 0.1;

    private GearRotator() {
        rotatorMotor.setInverted(true);
        notifier = new Notifier(stateMachineRunnable);
    }

    /**
     * Spins the motor
     */
    public void rotate() {
        rotatorMotor.set(1);
    }

    /**
     * Stops the motor
     */
    public void stop() {
        rotatorMotor.set(0);
    }

    /**
     * Returns true if the gear is properly aligned so that it may be placed on a peg.
     *
     * @return
     */
    public boolean isGearAligned() {
        return !banner.get();
    }

    /**
     * Returns the distance in 'inches' returned by the lidar sensor.
     *
     * @return
     */
    public double getLidarDistance() {
        return lidar.getDistance();
    }

    /**
     * When set to true, flipper motor spins constantly
     *
     * @param override Override the closed loop state machine
     */
    public void setOverride(boolean override) {
        this.override = override;
    }

    public void disable() {
        if(!enabled) return;
        notifier.stop();
        enabled = false;
    }

    public void enable() {
        if(enabled) return;
        stateMachine.reset();
        notifier.startPeriodic(period);
        enabled = true;
    }

    public static GearRotator getInstance() {
        if(instance == null) instance = new GearRotator();
        return instance;
    }

    public void outputToSmartDashboard() {
        SmartDashboard.putString("State: ", stateMachine.getState().toString());
        SmartDashboard.putNumber("LIDAR Distance: ", getLidarDistance());
        SmartDashboard.putBoolean("Banner: ", isGearAligned());
        SmartDashboard.putBoolean("Override: ", override);
    }

    public void initDefaultCommand() {
        // Set the default command, if any, for a subsystem here. Example:
        //    setDefaultCommand(new MySpecialCommand());
    }
}

/**
 * FSM that controls the GearRotator
 */
class GearStateMachine {
    private State state = State.WAITING;

    public boolean shouldRotateGear(boolean isGearAligned, double lidar) {
        return state.process(this, isGearAligned, lidar);
    }

    private void setState(State state) {
        this.state = state;
    }

    public void reset() {
        setState(State.WAITING);
    }

    public State getState() {
        return state;
    }

    public enum State implements IState {
        WAITING {
            public boolean process(GearStateMachine stateMachine, boolean gearAligned, double lidar) {
                if(lidar < 20) {
                    stateMachine.setState(State.SENSING);
                }
                return false;
            }
        },
        SENSING {
            public boolean process(GearStateMachine stateMachine, boolean gearAligned, double lidar) {
                stateMachine.setState(gearAligned && lidar < 20 ? GEAR_READY : CORRECTING);
                return false;
            }
        },
        CORRECTING {
            public boolean process(GearStateMachine stateMachine, boolean gearAligned, double lidar) {
                if(gearAligned) {
                    stateMachine.setState(SENSING);
                }
                return false;
            }
        },
        GEAR_READY {
            public boolean process(GearStateMachine stateMachine, boolean gearAligned, double lidar) {
                if(lidar > 25) stateMachine.setState(WAITING);
                return false;
            }
        }

    }

    /**
     * Interface to implement transition table for each state
     */
    interface IState {
        public boolean process(GearStateMachine stateMachine, boolean gearAligned, double lidar);
    }
}



