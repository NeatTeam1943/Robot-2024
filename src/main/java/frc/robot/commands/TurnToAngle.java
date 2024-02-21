package frc.robot.commands;

import frc.robot.commands.driveTrainCommands.TurnToAngleBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.RobotOdometry;

public class TurnToAngle extends TurnToAngleBase {
  private double m_targetAngle;

  public TurnToAngle(DriveTrain drive, RobotOdometry odometry, double targetAngle) {
    super(drive, odometry);

    m_targetAngle = (targetAngle / 2);
  }

  @Override
  protected double getTargetAngle() {
    return m_targetAngle;
  }
}
