package frc.robot.commands.DriveTrainCommands;

import frc.robot.Vision.*;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.RobotOdometry;

public class TurnToAt extends TurnToAngleBase {
    private Limelight m_camera;

    public TurnToAt(DriveTrain drive, RobotOdometry odometry, Limelight camera) {
        super(drive, odometry);

        m_camera = camera;
    }

  @Override
  protected double getTargetAngle() {
    return -(m_camera.getTargetYaw() / 2) + m_odometry.getHeading();
  }
}
