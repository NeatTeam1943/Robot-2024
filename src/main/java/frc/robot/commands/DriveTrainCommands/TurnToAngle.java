package frc.robot.commands.DriveTrainCommands;

import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.RobotOdometry;

public class TurnToAngle extends TurnToAngleBase {
  private double m_targetAngle;

  public TurnToAngle(DriveTrain drive, RobotOdometry odometry, double targetAngle) {
    super(drive, odometry);

    m_targetAngle = (targetAngle / 2) + m_odometry.getHeading();
  }

  @Override
  protected double getTargetAngle() {
    return m_targetAngle;
  }
}
