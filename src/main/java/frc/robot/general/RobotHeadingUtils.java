package frc.robot.general;

/** Implements the Heading class as a Singleton, ensuring only one instance exists. */
public class RobotHeadingUtils {
  private static RobotHeadingUtils m_instance;
  private RobotHeading m_currentHeading;

  private RobotHeadingUtils() {
    m_currentHeading = RobotHeading.INTAKE;
  }

  /**
   * Gets the single instance of Heading. Thread-safe implementation.
   *
   * @return The single instance of Heading.
   */
  public static synchronized RobotHeadingUtils getInstance() {
    if (m_instance == null) {
      m_instance = new RobotHeadingUtils();
    }

    return m_instance;
  }

  /**
   * Sets the current robot heading.
   *
   * @param heading The new robot heading.
   */
  public void setRobotHeading(RobotHeading heading) {
    // Consider validating the input for robustness
    if (heading != null) {
      m_currentHeading = heading;
    }
  }

  /**
   * Gets the current robot heading.
   *
   * @return The current robot heading.
   */
  public RobotHeading getRobotHeading() {
    return m_currentHeading; // Return a copy, not the reference
  }

  /** Toggles the robot heading between INTAKE and SHOOTER. */
  public void toggleHeading() {
    m_currentHeading =
        (m_currentHeading == RobotHeading.INTAKE) ? RobotHeading.SHOOTER : RobotHeading.INTAKE;
  }

  public boolean sameAs(RobotHeading otherHeading) {
    return m_currentHeading == otherHeading;
  }

  public boolean isIntakeMode() {
    return m_currentHeading == RobotHeading.INTAKE;
  }

  public boolean isShooterMode() {
    return m_currentHeading == RobotHeading.SHOOTER;
  }
}
