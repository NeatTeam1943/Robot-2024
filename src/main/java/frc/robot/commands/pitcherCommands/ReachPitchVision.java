package frc.robot.commands.pitcherCommands;

import java.util.function.Supplier;

import frc.robot.subsystems.NetworkTables;
import frc.robot.subsystems.Pitcher;

/**
 * Sets the pitch of the shooting mechanism to the resulting vision requests.
 */
public class ReachPitchVision extends ReachPitchBase {
  private final NetworkTables m_nt;

  public ReachPitchVision(Pitcher pitcher, NetworkTables nt) {
    super(pitcher);

    m_nt = nt;
  }

  @Override
  public Supplier<Double> getSetpoint() {
    return () -> m_nt.getDesiredAngle(); // NOTE: We will get the targeted pitch from NT later this impl is just a
                                            // placeholder.
  }
}