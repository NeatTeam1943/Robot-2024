package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

/*
 * The Shooter subsystem controls the robot's shooting mechanism.
 */
public class Shooter extends SubsystemBase {
  /*
   * Constructs the Shooter subsystem with motor controllers.
   */
  public Shooter() {}

  /*
   * Sets the speed of the motors for initiating the shooting mechanism.
   */
  public void setMotorSpeed(double speed){

  }

  /*
   * Sets the pitch angle of the shooting mechanism.
   */
  public void setAngle(double angle){

  }

  /*
   * returns if the shooter angle got the limit of motion
   */
  public boolean isAtLimit(){
    return false;
  }

  /*
   * Returns the RPM of the motor.
   */
  public double getRPM() {

    return 0;
  }
}
