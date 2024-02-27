package frc.robot.commands.shooterCommands;

import frc.robot.subsystems.Shooter;

/**
 * Sets desired RPM for the shooter
 */
public class SetShooterRPM extends SetShooterRPMBase {
  private double m_setpoint;

  public SetShooterRPM(Shooter shooter, double setpoint) {
    super(shooter);

    m_setpoint = setpoint;
  }

  @Override
  public void initialize() {
    m_leftController.setSetpoint(m_setpoint);
    m_rightController.setSetpoint(m_setpoint);
  }

  public void end(boolean interrupted) {
    m_shooter.setLeftShooterMotorSpeed(0);
    m_shooter.setRightShooterMotorSpeed(0);
  }

  @Override
  public boolean isFinished() {
    // return m_leftController.atSetpoint() && m_rightController.atSetpoint();
    return false;
  }
}
