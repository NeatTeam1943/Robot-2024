package frc.robot.commands.pitcherCommands;

import java.util.function.Supplier;

import frc.robot.subsystems.Pitcher;

/**
 *  Sets the pitch of the shooting mechanism to a desired constant.
 */
public class ReachPitch extends ReachPitchBase {
    private final double m_desiredPitch;

    public ReachPitch(Pitcher pitcher, double desiredPitch) {
        super(pitcher);

        m_desiredPitch = desiredPitch;
    }

    @Override
    public Supplier<Double> getSetpoint() {
        return () -> m_desiredPitch;
    }
}