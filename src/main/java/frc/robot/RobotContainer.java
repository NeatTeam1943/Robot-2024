package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.HeadingCommands.ChangeMode;
import frc.robot.commands.driveTrainCommands.DriveRobot;
import frc.robot.commands.routines.InitializeIntakeMode;
import frc.robot.commands.routines.InitializeShooterMode;
import frc.robot.commands.routines.ShooterVision;
import frc.robot.commands.routines.TransportToShoot;
import frc.robot.commands.transportationCommands.IntakeNote;
import frc.robot.commands.transportationCommands.TransportNote;
import frc.robot.general.Odometry;
import frc.robot.general.RobotData;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Pitcher;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Transport;

import java.util.Map;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class RobotContainer {
  private final CommandXboxController m_driverController;

  private final DriveTrain m_drive;
  private final Pitcher m_pitcher;
  private final Shooter m_shooter;
  private final Intake m_intake;
  private final Transport m_transport; 

  private final SendableChooser<Command> m_autoChooser;
  private final Map<String, Command> m_commands;

  public RobotContainer() {
    m_driverController = new CommandXboxController(OperatorConstants.kDriverControllerPort);

    m_drive = new DriveTrain();
    m_pitcher = new Pitcher(); 
    m_shooter = new Shooter();
    m_intake = new Intake();
    m_transport = new Transport();

    m_autoChooser = AutoBuilder.buildAutoChooser();

    m_commands = Map.of(
      "Intake Note", new IntakeNote(m_intake, m_transport),
      "Transport Note", new TransportNote(m_transport),
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
    m_drive.setDefaultCommand(new DriveRobot(m_drive, m_driverController));

    m_driverController.a().onTrue(new RunCommand(() -> new Odometry(m_drive, RobotData.getInstance()).reset()));
    m_driverController.b().whileTrue(new IntakeNote(m_intake, m_transport));
  } 

  public Command getAutonomousCommand() {
    return m_autoChooser.getSelected();
  }
}
