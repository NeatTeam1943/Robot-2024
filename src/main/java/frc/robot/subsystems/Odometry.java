// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Odometry extends SubsystemBase {
  /** Creates a new Odometry. */
  public Odometry() {}
  
  public void setPosition2D(Pose2d position){
    
  }

  public Pose2d getPosition2D(){

    return null;
  }

  public void setPosition3D(Pose3d position){
    
  }

  public Pose2d getPosition3D(){

    return null;
  }

  public void setHeading(double angle){

  }

  public double getHeading(){

    return 0;
  }

  public double distanceToAT(){

    return 0;
  }

  public double angleToAT(){
    
    return 0;
  }

  public double distanceToNote(){

    return 0;
  }

  public double angleToNote(){
    
    return 0;
  }

  public double velocity(){

    return 0;
  }

  public double acceleration(){

    return 0;
  }

  public double getDistanceTOF(){// time of flight sensor

    return 0;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
