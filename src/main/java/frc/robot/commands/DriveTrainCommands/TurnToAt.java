package frc.robot.commands.driveTrainCommands;

import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.RobotOdometry;
import frc.robot.vision.*;

public class TurnToAt extends TurnToAngleBase {
    private Limelight m_camera;

    public TurnToAt(DriveTrain drive, RobotOdometry odometry, Limelight camera) {
        super(drive, odometry);

        m_camera = camera;
    }

  @Override
  protected double getTargetAngle() {
    return -(m_camera.getTargetYaw() / 2);
  }
}
