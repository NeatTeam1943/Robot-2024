package frc.robot;

import frc.robot.Constants.BlinkinConstants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.LedDriver.Color;
import frc.robot.Limelight.Target;
import frc.robot.commands.HeadingCommands.ChangeMode;
import frc.robot.commands.routines.ShooterVision;
import frc.robot.commands.routines.TransportToShoot;
import frc.robot.commands.routines.automatic.InitializeIntakeMode;
import frc.robot.commands.routines.automatic.InitializeShooterMode;
import frc.robot.commands.transportationCommands.IntakeNote;
import frc.robot.commands.transportationCommands.TransportNote;
import frc.robot.general.RobotHeading;
import frc.robot.general.RobotHeadingUtils;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Pitcher;
import frc.robot.subsystems.RobotOdometry;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Transport;

import java.util.Map;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;

import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.event.BooleanEvent;
import edu.wpi.first.wpilibj.event.EventLoop;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
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

  private final SendableChooser<Command> m_autoChooser;
  private final Map<String, Command> m_commands;

  private final EventLoop m_ledLoop = new EventLoop();

  private final BooleanEvent m_driveIntakeModeEvent;
  private final BooleanEvent m_driveShooterModeEvent;

  private final BooleanEvent m_validShootingDistanceEvent;
  private final BooleanEvent m_goodPitcherAngle;

  private final BooleanEvent m_noteVisibleEvent;

  private final BooleanEvent m_lowBatteryEvent;

  private LedDriver m_blinkin = new LedDriver();

  public RobotContainer() {
    m_driverController = new CommandXboxController(OperatorConstants.kDriverControllerPort);
    m_odometry = RobotOdometry.getInstance();
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
        "Change To Shooter Mode", ChangeMode.ShooterMode());

    NamedCommands.registerCommands(m_commands);

    SmartDashboard.putData("Auto Chooser", m_autoChooser);

    m_driveIntakeModeEvent = new BooleanEvent(m_ledLoop, () -> RobotHeadingUtils.getInstance().getRobotHeading() == RobotHeading.INTAKE).debounce(0.02);
    m_driveShooterModeEvent = new BooleanEvent(m_ledLoop, () -> RobotHeadingUtils.getInstance().getRobotHeading() == RobotHeading.SHOOTER).debounce(0.02);

    m_validShootingDistanceEvent = new BooleanEvent(m_ledLoop, () -> Limelight.getDistanceFrom(Target.SPEAKER) < 2.3).debounce(0.02);
    m_goodPitcherAngle = new BooleanEvent(m_ledLoop, () -> m_pitcher.hasReachedPitch()).debounce(0.02);

    m_noteVisibleEvent = new BooleanEvent(m_ledLoop, () -> m_transport.isNoteVisible()).debounce(0.02);

    m_lowBatteryEvent = new BooleanEvent(m_ledLoop, () -> RobotController.getBatteryVoltage() < 12).debounce(0.02);

    m_driveIntakeModeEvent.ifHigh(() -> m_blinkin.setColor(Color.AQUA));
    m_driveShooterModeEvent.ifHigh(() -> m_blinkin.setColor(Color.HOT_PINK));
    
    m_validShootingDistanceEvent.ifHigh(() -> m_blinkin.setColor(Color.GREEN));
    m_goodPitcherAngle.ifHigh(() -> m_blinkin.setColor(Color.YELLOW));
  
    m_noteVisibleEvent.ifHigh(() -> m_blinkin.setColor(Color.LIME));

    m_lowBatteryEvent.ifHigh(() -> m_blinkin.setColor(Color.WHITE));

    configureBindings();
  }

  private void configureBindings() {
    m_driverController.a().onTrue(new TurnToAngle(m_drive, 90));
    m_driverController.b().whileTrue(new RunCommand(() -> m_odometry.setHeading(0)));

    m_drive.setDefaultCommand(new RunCommand(() -> m_drive.driveArcade(m_driverController), m_drive));
  }

  public Command getAutonomousCommand() {
    return m_autoChooser.getSelected();
  }

  public EventLoop getLedLoop() {
    return m_ledLoop;
  }

  public LedDriver getBlinkin() {
    return m_blinkin;
  }
}
