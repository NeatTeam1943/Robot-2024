package frc.robot.commands.shooterCommands;

import frc.robot.subsystems.Shooter;

public class SetShooterRPM extends SetShooterRPMBase {
  public SetShooterRPM(Shooter shooter, double setpoint) {
    super(shooter, setpoint);
  }

  @Override
  public boolean isFinished() {
    return m_leftController.atSetpoint() && m_rightController.atSetpoint();
  }
}
