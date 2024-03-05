package frc.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.BlinkinConstants;

public class LedDriver extends SubsystemBase {
  private Spark m_blinkin;

  private Timer m_timer;
  private Timer m_normalRoutineTimer;

  private SendableChooser<Color> m_chooser;

  public LedDriver() {
    m_blinkin = new Spark(BlinkinConstants.kBlinkinPort);

    m_timer = new Timer();
    m_normalRoutineTimer = new Timer();

    m_normalRoutineTimer.start();
    
    m_chooser = new SendableChooser<>();

    m_chooser.setDefaultOption("Orange", Color.ORANGE);
    m_chooser.addOption("Blue", Color.BLUE);
    m_chooser.addOption("Green", Color.GREEN);
    m_chooser.addOption("Rainbow", Color.RAINBOW);
    m_chooser.addOption("Lime", Color.LIME);
    m_chooser.addOption("Red", Color.RED);
    m_chooser.addOption("White", Color.WHITE);
    m_chooser.addOption("Violet", Color.VIOLET);
    m_chooser.addOption("Dark Green", Color.DARK_GREEN);
    m_chooser.addOption("Hot Pink", Color.HOT_PINK);

    SmartDashboard.putData("Color", m_chooser);
  }
  
  public enum Color {
    ORANGE(BlinkinConstants.kOrange),
    BLUE(BlinkinConstants.kBlue),
    GREEN(BlinkinConstants.kGreen),
    RAINBOW(BlinkinConstants.kRainBow),
    LIME(BlinkinConstants.kLime),
    RED(BlinkinConstants.kRed),
    WHITE(BlinkinConstants.kWhite),
    VIOLET(BlinkinConstants.kViolet),
    DARK_GREEN(BlinkinConstants.kDarkGreen),
    HOT_PINK(BlinkinConstants.kHotPink),
    AQUA(BlinkinConstants.kAqua),
    YELLOW(BlinkinConstants.kYellow);

    private double m_color;

    private Color(double color){
      m_color = color;
    }

    public double getColor(){
      return m_color;
    }
  }

  public void setColor(Color color){
    m_blinkin.set(color.getColor());
    m_timer.restart();
  }

  public void setColor(double color){
    m_blinkin.set(color);
    m_timer.restart();
  }

  public void normalRoutine() {
    if (m_timer.get() > 2) {
      m_blinkin.set(((int) m_normalRoutineTimer.get() % 2 == 0 ? Color.ORANGE.getColor() : Color.BLUE.getColor()));
    }
  }

  @Override
  public void periodic() {
    // setColor(m_chooser.getSelected());
  }
}
