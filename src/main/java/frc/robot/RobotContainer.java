package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos.DriveIntake;
import frc.robot.commands.Autos.normalAuto.DriveDistance;
import frc.robot.commands.HeadingCommands.ChangeMode;
import frc.robot.commands.routines.ShooterVision;
import frc.robot.commands.routines.TransportToShoot;
import frc.robot.commands.routines.automatic.InitializeIntakeMode;
import frc.robot.commands.routines.automatic.InitializeShooterMode;
import frc.robot.commands.routines.dis2.Shoot;
import frc.robot.commands.transportationCommands.IntakeNote;
import frc.robot.commands.transportationCommands.TransportNote;
import frc.robot.general.Dis2FunctionalCommands;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Pitcher;
import frc.robot.subsystems.RobotOdometry;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Transport;

import java.util.Map;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.driveTrainCommands.InvertDrive;
import frc.robot.commands.pitcherCommands.ReachPitch;

public class RobotContainer {
  private final CommandXboxController m_driverController;
  private final CommandXboxController m_mechanismController;

  private final RobotOdometry m_odometry;

  private final DriveTrain m_drive;
  private final Pitcher m_pitcher;
  private final Shooter m_shooter;
  private final Intake m_intake;
  private final Transport m_transport;

  private final TransportToShoot m_transportToShoot;
  private final IntakeNote m_intakeNote;

  private final SendableChooser<Command> m_autoChooser;
  private final Map<String, Command> m_commands;

  private final Dis2FunctionalCommands m_dis2Commands;

  public RobotContainer() {
    m_driverController = new CommandXboxController(OperatorConstants.kDriverControllerPort);
    m_mechanismController = new CommandXboxController(OperatorConstants.kMechanismController);

    m_odometry = RobotOdometry.getInstance();
    m_drive = new DriveTrain();

    m_pitcher = new Pitcher();
    m_shooter = new Shooter();
    m_intake = new Intake();
    m_transport = new Transport();

    m_transportToShoot = new TransportToShoot(m_pitcher, m_shooter, m_transport, null);
    m_intakeNote = new IntakeNote(m_intake, m_transport);
    m_autoChooser = new SendableChooser<>();

    m_dis2Commands = new Dis2FunctionalCommands(m_shooter, m_transport, m_intake);
    m_intake.setDefaultCommand(m_dis2Commands.intake(0));
    m_transport.setDefaultCommand(m_dis2Commands.transportIntake(0,0));

    m_commands = Map.of(
        "Shoot Note", new SequentialCommandGroup(new InitializeShooterMode(m_pitcher, m_shooter, false),new Shoot(m_dis2Commands, false)),
        "Intake Note", new SequentialCommandGroup(m_dis2Commands.intake(0.6),m_dis2Commands.transportIntake(0, 0)),
        "Init Shooter Mode", new InitializeShooterMode(m_pitcher, m_shooter, false),
        "Init Intake Mode", new InitializeIntakeMode(m_pitcher, m_shooter),
        "Shooter Vision Mode", new ShooterVision(m_pitcher, m_shooter, null),
        "Transport Note To Shoot", new TransportToShoot(m_pitcher, m_shooter, m_transport, null),
        "Change To Intake Mode", ChangeMode.IntakeMode(),
        "Change To Shooter Mode", ChangeMode.ShooterMode());

    NamedCommands.registerCommands(m_commands);


    SmartDashboard.putData("Auto Chooser", m_autoChooser);

    configureBindings();
  }

  private void configureBindings() {
    // m_driverController.y().whileTrue(new ParallelCommandGroup(new RunCommand(()
    // -> m_intake.setMotorSpeed(0.05), m_intake), new RunCommand(() ->
    // m_transport.setBeltsSpeed(-0.7), m_transport)));
    // m_driverController.y().whileFalse(new ParallelCommandGroup(new RunCommand(()
    // -> m_intake.setMotorSpeed(0), m_intake), new RunCommand(() ->
    // m_transport.setBeltsSpeed(0), m_transport)));
    // m_driverController.y().whileFalse(new RunCommand(() ->
    // m_transport.setBeltsSpeed(0), m_transport));

    m_mechanismController.x().onTrue(new InitializeShooterMode(m_pitcher, m_shooter, false));
    m_mechanismController.y().onTrue(m_dis2Commands.transportIntake(0.05, -0.7));
    m_mechanismController.start().onTrue(new InitializeIntakeMode(m_pitcher, m_shooter));

    m_mechanismController.a().whileTrue(new RunCommand(() -> m_intake.setMotorSpeed(-1), m_intake));
    // m_mechanismController.a().whileTrue(new IntakeNote(m_intake, m_transport));
    m_mechanismController.a().whileFalse(new RunCommand(() -> m_intake.setMotorSpeed(0), m_intake));
    
    m_mechanismController.povLeft().whileTrue(m_dis2Commands.transportIntake(0.05, -0.7));

    m_mechanismController.leftBumper().onTrue(new Shoot(m_dis2Commands, true));
    m_mechanismController.rightBumper().onTrue(new Shoot(m_dis2Commands, false));

    m_mechanismController.povDown().whileTrue(new RunCommand(() -> m_pitcher.setAngleMotorsSpeed(1), m_pitcher));
    m_mechanismController.povUp().whileTrue(new RunCommand(() -> m_pitcher.setAngleMotorsSpeed(-1), m_pitcher));

    m_driverController.back().onTrue(new InvertDrive(m_drive));
    m_mechanismController.start().onTrue(m_dis2Commands.intake(1)); // Outtake

    m_pitcher.setDefaultCommand(new RunCommand(() -> m_pitcher.setAngleMotorsSpeed(0), m_pitcher));
    m_drive.setDefaultCommand(new RunCommand(() -> m_drive.driveArcade(m_driverController), m_drive));
  }

  public Pitcher getPitcher() {
    return m_pitcher;
  }

  public Command getAutonomousCommand() {
    return new SequentialCommandGroup(
        new InitializeShooterMode(m_pitcher, m_shooter, false),
        new Shoot(m_dis2Commands, false),
        new InitializeIntakeMode(m_pitcher, m_shooter),
        new ParallelDeadlineGroup(new DriveDistance(m_drive, 1, 0.5), m_dis2Commands.intake(-1)),
        m_dis2Commands.transportIntake(0, 0);
        new DriveDistance(m_drive, 1, 0.5),
        new Shoot(m_dis2Commands, false));
  }
}
