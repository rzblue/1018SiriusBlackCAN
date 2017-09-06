package org.usfirst.frc.team1018.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team1018.lib.LidarLite;
import org.usfirst.frc.team1018.robot.RobotConfig;

/**
 * @author Ryan Blue
 */
public class GearRotator extends Subsystem {
    private static GearRotator instance;
    boolean override;
    boolean enabled;
    GearStateMachine stateMachine = new GearStateMachine();
    private TalonSRX rotatorMotor = new TalonSRX(RobotConfig.GearRotatorConfig.GEAR_ROTATOR_PWM);
    private DigitalInput banner = new DigitalInput(RobotConfig.GearRotatorConfig.BANNER_DIO);
    private LidarLite lidar = new LidarLite(RobotConfig.GearRotatorConfig.LIDAR_I2C);
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

    public static GearRotator getInstance() {
        if(instance == null) instance = new GearRotator();
        return instance;
    }

    /**
     * Spins the motor
     */
    public void rotate() {
        rotatorMotor.set(1);
    }

    public boolean isGearAligned() {
        return !banner.get();
    }

    public double getLidarDistance() {
        return lidar.getDistanceInches();
    }

    /**
     * Stops the motor
     */
    public void stop() {
        rotatorMotor.set(0);
    }

    public void initDefaultCommand() {
        // Set the default command, if any, for a subsystem here. Example:
        //    setDefaultCommand(new MySpecialCommand());
    }

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
}

/**
 * FSM that controls the GearRotator
 */
class GearStateMachine {
    private State state = State.WAITING;

    public boolean shouldRotateGear(boolean isGearAligned, double lidar) {
        return state.process(this, isGearAligned, lidar);
    }

    public void reset() {
        setState(State.WAITING);
    }

    private void setState(State state) {
        this.state = state;
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
                return true;
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



