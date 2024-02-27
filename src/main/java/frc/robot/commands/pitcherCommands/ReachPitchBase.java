package frc.robot.commands.pitcherCommands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Pitcher;

/**
 * Abstract base command for controlling the pitch of the shooting mechanism.
 */
public abstract class ReachPitchBase extends Command {
  private Pitcher m_pitcher;

  private double m_currentPitch;
  private double m_error;

  /**
   * Abstract method to define how the setpoint is determined.
   *
   * @return The desired pitch angle.
   */
  public abstract Supplier<Double> getSetpoint();

  public ReachPitchBase(Pitcher pitcher) {
    m_pitcher = pitcher;

    addRequirements(m_pitcher);
  }

  @Override
  public void initialize() {
  }

  /**
   * Sets the motor speed based on the current pitch and the desired setpoint.
   */
  @Override
  public void execute() {
    m_currentPitch = m_pitcher.getAngleDegrees();

    double setpoint = getSetpoint().get();

    m_error = m_currentPitch - setpoint;

    double speed = m_error < 0 ? -.9 : .9;

    SmartDashboard.putNumber("Current power", speed);
    SmartDashboard.putNumber("ERROR", m_error);

    m_pitcher.setAngleMotorsSpeed(speed);
  }

  @Override
  public void end(boolean interrupted) {
    m_pitcher.setAngleMotorsSpeed(0);
  }

  /**
   * Checks if the desired pitch has been reached or if it's out of range.
   */
  @Override
  public boolean isFinished() {
    boolean shouldFinish = Math.abs(m_error) <= 0.2;
    SmartDashboard.putBoolean("FINISH?", shouldFinish);

    return shouldFinish;
  }
}