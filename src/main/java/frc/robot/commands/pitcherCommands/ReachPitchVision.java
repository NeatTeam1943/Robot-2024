package frc.robot.commands.pitcherCommands;

import java.util.function.Supplier;

import frc.robot.subsystems.NetworkTables;
import frc.robot.subsystems.Pitcher;

/**
 * Sets the pitch of the shooting mechanism to the resulting vision requests.
 */
public class ReachPitchVision extends ReachPitchBase {
  private final NetworkTables m_nt;
  private final Pitcher m_pitcher;

  public ReachPitchVision(Pitcher pitcher, NetworkTables nt) {
    super(pitcher);

    m_pitcher = pitcher;
    m_nt = nt;
  }

  @Override
  public void initialize() {
    m_pitcher.setReachedPitch(false);
  }

  @Override
  public void end(boolean interrupted) {
    m_pitcher.setReachedPitch(true);    
  }

  @Override
  public Supplier<Double> getSetpoint() {
    return () -> m_nt.getDesiredAngle(); // NOTE: We will get the targeted pitch from NT later this impl is just a
                                            // placeholder.
  }
}