package frc.robot.commands.shooterCommands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.Shooter;

/**
 * Abstract command for setting and maintaining a desired RPM for the shooter motors.
 */
public abstract class SetShooterRPMBase extends Command {
  protected Shooter m_shooter;

  protected PIDController m_leftController;
  protected PIDController m_rightController;
  
  protected SimpleMotorFeedforward m_ff;

  protected double m_setpoint;

  public SetShooterRPMBase(Shooter shooter, double setpoint) {
    m_setpoint = setpoint;
    m_shooter = shooter;

    m_leftController = new PIDController(ShooterConstants.kP, ShooterConstants.kI, ShooterConstants.kD);
    m_rightController = new PIDController(ShooterConstants.kP, ShooterConstants.kI, ShooterConstants.kD);

    m_ff = new SimpleMotorFeedforward(ShooterConstants.kS, ShooterConstants.kV);

    addRequirements(shooter);
  }

  @Override
  public void initialize() {
    m_leftController.setTolerance(ShooterConstants.kLeftControllerThreshold);
    m_rightController.setTolerance(ShooterConstants.kRightControllerThreshold);

    m_leftController.setSetpoint(m_setpoint);
    m_rightController.setSetpoint(m_setpoint);
  }

  @Override
  public void execute() {
    m_shooter.setLeftMotorVoltage(
      m_ff.calculate(m_shooter.getLeftRate()) + m_leftController.calculate(m_shooter.getLeftRPM()));

    m_shooter.setRightMotorVoltage(
      m_ff.calculate(m_shooter.getRightRate()) + m_leftController.calculate(m_shooter.getRightRPM()));
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
