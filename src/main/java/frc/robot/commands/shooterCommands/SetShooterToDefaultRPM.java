package frc.robot.commands.shooterCommands;

import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.RobotOdometry;
import frc.robot.subsystems.Shooter;

/**
 * Sets desired RPM for the shooter while not in shooting range.
 */
public class SetShooterToDefaultRPM extends SetShooterRPM {
  private RobotOdometry m_odometry;

  public SetShooterToDefaultRPM(Shooter shooter, RobotOdometry odometry) {
    super(shooter, ShooterConstants.kDefaultRPM);

    m_odometry = odometry;
  }

  @Override
  public boolean isFinished() {
    return m_odometry.isSpeakerVisible() || m_odometry.isAMPVisible();
  }
}
