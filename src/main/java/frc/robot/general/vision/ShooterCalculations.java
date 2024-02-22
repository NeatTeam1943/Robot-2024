package frc.robot.general.vision;

import frc.robot.general.RobotData;

public class ShooterCalculations {
    public static ShooterCalculations m_instance;

    private ShooterCalculations() {}
    
    public static ShooterCalculations getInstance() {
        if (m_instance == null) {
            m_instance = new ShooterCalculations();
        }
        return m_instance;
    }

}
