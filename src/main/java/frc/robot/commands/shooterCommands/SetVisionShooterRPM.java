package frc.robot.commands.shooterCommands;

import frc.robot.subsystems.NetworkTables;
import frc.robot.subsystems.Shooter;

public class SetVisionShooterRPM extends SetShooterRPMBase {
    private NetworkTables m_nt;

    public SetVisionShooterRPM(Shooter shooter, NetworkTables nt) {
        super(shooter);

        m_nt = nt;
    }

    @Override
    public void execute() {
        double desiredRPM = m_nt.getDesiredRPM();

        m_shooter.setLeftMotorVoltage(
                m_ffLeft.calculate(m_shooter.getLeftRate())
                        + m_leftController.calculate(m_shooter.getLeftRPM(), desiredRPM));

        m_shooter.setRightMotorVoltage(
                m_ffLeft.calculate(m_shooter.getRightRate())
                        + m_leftController.calculate(m_shooter.getRightRPM(), desiredRPM));
    }

    @Override
    public boolean isFinished() {
        return m_leftController.atSetpoint() && m_rightController.atSetpoint();
    }
}
