package frc.robot.commands.pitcherCommands;

import java.util.function.Supplier;

import frc.robot.subsystems.Pitcher;
import frc.robot.subsystems.RobotOdometry;

/**
 * Sets the pitch of the shooting mechanism to the resulting vision requests.
 */
public class ReachPitchVision extends ReachPitchBase {
  private final RobotOdometry m_odometry;

  public ReachPitchVision(Pitcher pitcher, RobotOdometry odometry) {
    super(pitcher);

    m_odometry = odometry;
  }

  @Override
  public Supplier<Double> getSetpoint() {
    return () -> m_odometry.getPitchToAT(); // NOTE: We will get the targeted pitch from NT later this impl is just a
                                            // placeholder.
  }
}