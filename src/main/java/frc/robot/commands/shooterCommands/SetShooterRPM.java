package frc.robot.commands.shooterCommands;

import frc.robot.subsystems.Shooter;

/**
 * Sets desired RPM for the shooter
 */
public class SetShooterRPM extends SetShooterRPMBase {
  public SetShooterRPM(Shooter shooter, double setpoint) {
    super(shooter, setpoint);
  }

  @Override
  public boolean isFinished() {
    return m_leftController.atSetpoint() && m_rightController.atSetpoint();
  }
}
