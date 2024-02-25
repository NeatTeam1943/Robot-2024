package frc.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.BlinkinConstants;

public class LedDriver extends SubsystemBase {
  private Spark m_blinkin;
  private Timer m_time;

  public LedDriver() {
    m_blinkin = new Spark(BlinkinConstants.kBlinkinPort);

    m_time = new Timer();

    m_time.start();

  }

  @Override
  public void periodic() {
    double current = ((int) m_time.get() % 3 == 0) ? -0.15 : 0.67;
      m_blinkin.set(current);
  }
}
