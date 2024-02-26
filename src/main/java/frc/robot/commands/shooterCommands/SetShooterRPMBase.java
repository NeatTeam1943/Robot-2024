package frc.robot.commands.shooterCommands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.Shooter;

/**
 * Abstract command for setting and maintaining a desired RPM for the shooter
 * motors.
 */
public abstract class SetShooterRPMBase extends Command {
  protected Shooter m_shooter;

  protected PIDController m_leftController;
  protected PIDController m_rightController;

  protected SimpleMotorFeedforward m_ffLeft;
  protected SimpleMotorFeedforward m_ffRight;

  public SetShooterRPMBase(Shooter shooter) {
    m_shooter = shooter;

    m_leftController = new PIDController(ShooterConstants.kLeftP, ShooterConstants.kLeftI, ShooterConstants.kLeftD);
    m_rightController = new PIDController(ShooterConstants.kRightP, ShooterConstants.kRightI, ShooterConstants.kRightD);

    m_ffLeft = new SimpleMotorFeedforward(ShooterConstants.kLeftS, ShooterConstants.kLeftV, ShooterConstants.kLeftA);
    m_ffRight = new SimpleMotorFeedforward(ShooterConstants.kRightS, ShooterConstants.kRightV, ShooterConstants.kRightA);

    m_leftController.setTolerance(ShooterConstants.kLeftControllerThreshold);
    m_rightController.setTolerance(ShooterConstants.kRightControllerThreshold);

    addRequirements(shooter);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    m_shooter.setLeftMotorVoltage(
        m_ffLeft.calculate(m_shooter.getLeftRPM()) + m_leftController.calculate(m_shooter.getLeftRPM()));

    m_shooter.setRightMotorVoltage(
        m_ffRight.calculate(m_shooter.getRightRPM()) + m_rightController.calculate(m_shooter.getRightRPM()));
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
