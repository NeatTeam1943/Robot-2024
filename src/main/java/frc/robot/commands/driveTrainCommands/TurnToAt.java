package frc.robot.commands.driveTrainCommands;

import frc.robot.Limelight;
import frc.robot.subsystems.DriveTrain;

public class TurnToAt extends TurnToAngleBase {
    public TurnToAt(DriveTrain drive) {
        super(drive);
    }

  @Override
  protected double getTargetAngle() {
    return -(Limelight.getTargetYaw());
  }

  @Override
  public boolean isFinished() {
    return shouldStop() || Math.abs(getTargetAngle()) < 6;
  }
}
