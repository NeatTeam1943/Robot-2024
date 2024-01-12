package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
  public Shooter() {}

  public void setMotorSpeed(double speed){

  }

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
