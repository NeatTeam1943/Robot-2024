package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos.DriveIntake;
import frc.robot.commands.HeadingCommands.ChangeMode;
import frc.robot.commands.routines.ShooterVision;
import frc.robot.commands.routines.TransportToShoot;
import frc.robot.commands.routines.automatic.InitializeIntakeMode;
import frc.robot.commands.routines.automatic.InitializeShooterMode;
import frc.robot.commands.transportationCommands.IntakeNote;
import frc.robot.commands.transportationCommands.TransportNote;
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
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.driveTrainCommands.TurnToAngle;

public class RobotContainer {
  private final CommandXboxController m_driverController;
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

  public RobotContainer() {
    m_driverController = new CommandXboxController(OperatorConstants.kDriverControllerPort);
    m_odometry = RobotOdometry.getInstance();
    m_drive = new DriveTrain();

    m_pitcher = new Pitcher(); 
    m_shooter = new Shooter();
    m_intake = new Intake();
    m_transport = new Transport();

    m_transportToShoot = new TransportToShoot(m_pitcher, m_shooter, m_transport);
    m_intakeNote = new IntakeNote(m_intake, m_transport);
    m_autoChooser = new SendableChooser<>();

    m_commands = Map.of(
      "AMP", new TransportNote(m_transport),
      "AMPTWO", new SequentialCommandGroup(new TransportNote(m_transport),
                                              new DriveIntake(m_drive,
                                              m_intakeNote,
                                              "AmpSpeakerToNote")),
      "Init Shooter Mode", new InitializeShooterMode(m_pitcher, m_shooter),
      "Init Intake Mode", new InitializeIntakeMode(m_pitcher, m_shooter),
      "Shooter Vision Mode", new ShooterVision(m_pitcher, m_shooter, null),
      "Transport Note To Shoot", new TransportToShoot(m_pitcher, m_shooter, m_transport, null),
      "Change To Intake Mode", ChangeMode.IntakeMode(),
      "Change To Shooter Mode", ChangeMode.ShooterMode()
    );

    NamedCommands.registerCommands(m_commands);
    
    SmartDashboard.putData("Auto Chooser", m_autoChooser);

    configureBindings();
  }

  private void configureBindings() {
    m_driverController.y().onTrue(new TurnToAngle(m_drive, 90));

    m_driverController.a().onTrue(new IntakeNote(m_intake, m_transport));

    m_driverController.b().whileTrue(new RunCommand(() -> m_pitcher.setAngleMotorsSpeed(-1)));
    m_driverController.b().whileFalse(new RunCommand(() -> m_pitcher.setAngleMotorsSpeed(1)));

    m_driverController.x().onTrue(new TransportNote(m_transport));

    m_driverController.leftTrigger().whileTrue(new RunCommand(() -> m_shooter.setShooterMotorsSpeed(0.7)));

    m_drive.setDefaultCommand(new RunCommand(() -> m_drive.driveArcade(m_driverController), m_drive));
  }

  public Command getAutonomousCommand() {
    return m_autoChooser.getSelected();
  }
}
