package frc.robot.commands.pitcherCommands;

import java.util.function.Supplier;

import frc.robot.Limelight;
import frc.robot.subsystems.Pitcher;

/**
 * Sets the pitch of the shooting mechanism to the resulting vision requests.
 */
public class ReachPitchVision extends ReachPitchBase {
  private Pitcher m_pitcher;

  public ReachPitchVision(Pitcher pitcher) {
    super(pitcher);

    m_pitcher = pitcher;
  }

  @Override
  public Supplier<Double> getSetpoint() {
    return () -> m_pitcher.getDesiredAngle();
  }

  @Override
  public boolean isFinished(){
    // return !Limelight.hasTarget();
    return false;
  }
}