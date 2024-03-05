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

  protected boolean m_shouldFinish;
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
    m_currentPitch = m_pitcher.getCurrentAngleDegrees();
    
    double setpoint = getSetpoint().get();
    
    m_error = m_currentPitch - setpoint;
    
    double speed = m_error < 0 ? -0.8 : 0.8;
    
    SmartDashboard.putNumber("error but abs", Math.abs(m_error));
    SmartDashboard.putNumber("Current power", speed);
    SmartDashboard.putNumber("ERROR TOF!!!!11!!1!11!", m_error);
    
    m_pitcher.setAngleMotorsSpeed(speed);
    
    m_shouldFinish = Math.abs(m_error) <= 0.005;
  }
  
  @Override
  public void end(boolean interrupted) {
    m_pitcher.setAngleMotorsSpeed(0);
    System.out.println("finished!!1!11!!!!1");
  }

  /**
   * Checks if the desired pitch has been reached or if it's out of range.
   */
  @Override
  public boolean isFinished() {
    return m_shouldFinish;
  }
}