package frc.robot.commands.shooterCommands;

import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.RobotOdometry;
import frc.robot.subsystems.Shooter;

/**
 * Sets desired RPM for the shooter while not in shooting range.
 */
public class SetShooterToDefaultRPM extends SetShooterRPM {
  private RobotOdometry m_robotData;

  public SetShooterToDefaultRPM(Shooter shooter) {
    super(shooter, ShooterConstants.kDefaultRPM);

    m_robotData = RobotOdometry.getInstance();
  }

  @Override
  public boolean isFinished() {
    return m_robotData.isSpeakerVisible() || m_robotData.isAMPVisible();
  }
}
