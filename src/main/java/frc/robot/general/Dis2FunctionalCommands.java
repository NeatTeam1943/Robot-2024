package frc.robot.general;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Transport;

public class Dis2FunctionalCommands {
    private static Shooter m_shooter;
    private static Transport m_transport;
    private static Intake m_intake;

    public Dis2FunctionalCommands(Shooter shooter, Transport transport, Intake intake) {
        m_shooter = shooter;
        m_transport = transport;
        m_intake = intake;
    }

    public ParallelCommandGroup transportIntake(double intakeSpeed, double transportSpeed) {
        return new ParallelCommandGroup(
                new InstantCommand(() -> m_intake.setMotorSpeed(intakeSpeed), m_intake),
                new InstantCommand(() -> m_transport.setBeltsSpeed(transportSpeed), m_transport));
    }

    public InstantCommand intake(double intakeSpeed) {
        return new InstantCommand(() -> m_intake.setMotorSpeed(intakeSpeed), m_intake);
    }

    public InstantCommand shoot(double shooterSpeed){
        return new InstantCommand(() -> m_shooter.setShooterMotorsSpeed(shooterSpeed), m_shooter);
    }
}