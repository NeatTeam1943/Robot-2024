package frc.robot;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.BlinkinConstants;

public class LedDriver extends SubsystemBase {
  private Spark m_blinkin;

  private SendableChooser<Color> m_chooser;

  public LedDriver() {
    m_blinkin = new Spark(BlinkinConstants.kBlinkinPort);

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

    SmartDashboard.putData("Led Driver", this);
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
    DARK_GREEN(BlinkinConstants.kDarkGreen);

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
  }

  public void setColor(double color){
    m_blinkin.set(color);
  }

  @Override
  public void periodic() {  
    System.out.println(m_chooser.getSelected() + " " + m_chooser.getSelected().getColor());
    setColor(m_chooser.getSelected().getColor());
    // setColor(-0.59);
  }
}
