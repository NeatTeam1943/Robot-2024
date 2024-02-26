package frc.robot.commands.pitcherCommands;

import java.util.function.Supplier;

import edu.wpi.first.math.controller.BangBangController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Pitcher;

/**
 * Abstract base command for controlling the pitch of the shooting mechanism.
 */
public abstract class ReachPitchBase extends Command {
  private Pitcher m_pitcher;
  private BangBangController m_controller;

  private double m_currentPitch;

  /**
   * Abstract method to define how the setpoint is determined.
   *
   * @return The desired pitch angle.
   */
  public abstract Supplier<Double> getSetpoint();

  public ReachPitchBase(Pitcher pitcher) {
    m_pitcher = pitcher;
    m_controller = new BangBangController();

    addRequirements(m_pitcher);
  }

  @Override
  public void initialize() {}

  /**
   * Sets the motor speed based on the current pitch and the desired setpoint.
   */
  @Override
  public void execute() {
    m_currentPitch = m_pitcher.getAngleDegrees();

    double setpoint = getSetpoint().get();

    double speed = m_controller.calculate(m_currentPitch, setpoint);
    SmartDashboard.putNumber("Current power", speed);

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
    
    return m_controller.atSetpoint() || !m_pitcher.isInRange(m_currentPitch);
  }
}