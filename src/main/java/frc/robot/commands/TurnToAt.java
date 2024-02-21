package frc.robot.commands;

import frc.robot.Limelight;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.RobotOdometry;

public class TurnToAt extends TurnToAngleBase {
    public TurnToAt(DriveTrain drive, RobotOdometry odometry) {
        super(drive, odometry);
    }

  @Override
  protected double getTargetAngle() {
    return -(Limelight.getTargetYaw() / 2);
  }
}
