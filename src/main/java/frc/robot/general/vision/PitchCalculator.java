package frc.robot.general.vision;

import frc.robot.Constants.MeasurementConstants;
import frc.robot.Constants.ShooterConstants;

/**
 * The ShooterCalculator class provides methods to calculate the optimal
 * shooting angle
 * for a projectile given the desired target location and shooting constants.
 * 
 * @Equations
 *            Δx = v0 * cos(θ) * t
 *            Δy = v0 * sin(θ) * t - 0.5 * g * t^2 .
 */
public class PitchCalculator {
    private double m_deltaX;
    private double m_deltaY;


    /**
     * Constructs a PitchCalculator.
     * 
     * @param deltaY The vertical distance to the target (in meters).
     */
    public PitchCalculator(double deltaY) {
        m_deltaY = deltaY;
    }

    /**
     * Updates the target location parameters.
     *
     * @param deltaX The horizontal distance to the target (in meters).
     * @param deltaY The vertical distance to the target (in meters).
     */
    public void update(double deltaX, double deltaY) {
        m_deltaX = deltaX;
        m_deltaY = deltaY;
    }

    /**
     * Updates the deltaX from the target.
     *
     * @param deltaX The horizontal distance to the target (in meters).
     */
    public void update(double deltaX) {
        m_deltaX = deltaX;
    }

    /**
     * Solves for the optimal shooting angle using an iterative approach.
     * The method uses an iterative algorithm to find the shooting angle that
     * minimizes
     * the absolute difference between the calculated and desired vertical positions
     * of the projectile.
     * The iterative method involves adjusting the shooting angle in both directions
     * and updating
     * based on the best-performing angle until convergence or the maximum number of
     * iterations is reached.
     *
     * @param initialGuess  The initial guess for the shooting angle in degrees.
     * @param stepSize      The step size for each iteration in degrees.
     * @param maxIterations The maximum number of iterations to perform.
     * @return The optimal shooting angle in degrees.
     */
    public double solve(double initialGuess, double stepSize, int maxIterations) {
        double currentTheta = initialGuess;
        double bestTheta = currentTheta;
        double bestDifference = calculateDifference(currentTheta);

        for (int i = 0; i < maxIterations; i++) {
            double newTheta = currentTheta + stepSize;
            double newDifference = calculateDifference(newTheta);

            if (newDifference < bestDifference) {
                bestTheta = newTheta;
                bestDifference = newDifference;
            } else {
                newTheta = currentTheta - stepSize;
                newDifference = calculateDifference(newTheta);

                if (newDifference < bestDifference) {
                    bestTheta = newTheta;
                    bestDifference = newDifference;
                }
            }

            currentTheta = bestTheta;
        }

        return bestTheta;
    }

    /**
     * Calculates the absolute difference between the calculated and desired
     * vertical positions of the projectile.
     * The calculation involves determining the time of flight and the vertical
     * position of the projectile
     * based on the given shooting angle and initial velocity.
     *
     * @param theta The shooting angle in degrees.
     * @return The absolute difference between the calculated and desired vertical
     *         positions.
     */
    private double calculateDifference(double theta) {
        double time = m_deltaX / (Math.cos(Math.toRadians(theta)) * getInitialVelocity(ShooterConstants.kMaxRPS));
        double calculatedY = getFlightHeight(theta, time);

        return Math.abs(calculatedY - m_deltaY);
    }

    /**
     * Calculates the initial velocity of the projectile based on the given
     * rotational speed.
     *
     * @param rps The rotational speed of the shooting wheel in revolutions per
     *            second.
     * @return The initial velocity of the projectile.
     */
    private double getInitialVelocity(double rps) {
        return 2 * Math.PI * ShooterConstants.kShootingWheelRadiusMeters * rps;
    }

    /**
     * Calculates the vertical position of the projectile at a given time.
     * The calculation involves considering the projectile motion equation.
     *
     * @param theta The shooting angle in degrees.
     * @param time  The time of flight of the projectile.
     * @return The vertical position of the projectile at the given time.
     */
    private double getFlightHeight(double theta, double time) {
        return Math.sin(Math.toRadians(theta)) * time * getInitialVelocity(ShooterConstants.kMaxRPS)
                - 0.5 * MeasurementConstants.kG * (time * time);
    }

    /**
     * Here just for test purpuses.
     * Gleb don't kill me please.
     */
    public static void main(String[] args) {
        double deltaX = 2; // meters
        double deltaY = 2.5; // meters

        PitchCalculator solver = new PitchCalculator(deltaY);
        solver.update(deltaX, deltaY);

        double initialGuess = 36.0; // degrees
        double stepSize = 0.05; // degrees
        int maxIterations = 3000;

        double solution = solver.solve(initialGuess, stepSize, maxIterations);
        System.out.println("Test Case: Optimal angle (degrees): " + solution);
    }
}
