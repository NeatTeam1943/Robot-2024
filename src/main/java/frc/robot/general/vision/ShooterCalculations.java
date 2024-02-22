import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.optim.InitialGuess;
import org.apache.commons.math3.optim.MaxEval;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import org.apache.commons.math3.optim.nonlinear.scalar.ObjectiveFunction;
import org.apache.commons.math3.optim.nonlinear.scalar.noderiv.PowellOptimizer;

public class ShooterCalculations {
    public static ShooterCalculations m_instance;

    public static void main(String[] args) {
        double deltaX = 3;
        double deltaY = 3.5;
        double g = 9.8;

        MultivariateFunction objective = point -> calculateError(point, deltaX, deltaY, g);
        PointValuePair solution = findSolution(objective);

        double v0 = solution.getPoint()[0];
        double theta = solution.getPoint()[1];

        printResults(v0, theta);
    }

    private static double calculateError(double[] point, double deltaX, double deltaY, double g) {
        double v0 = point[0];
        double theta = point[1];

        double t = deltaX / (v0 * Math.cos(theta));
        double y = v0 * Math.sin(theta) * t - 0.5 * g * t * t;

        double errorX = deltaX - v0 * Math.cos(theta) * t;
        double errorY = deltaY - y;

        return errorX * errorX + errorY * errorY;
    }

    private static PointValuePair findSolution(MultivariateFunction objective) {
        PowellOptimizer optimizer = new PowellOptimizer(1e-10, 1e-10);

        double[] initialGuess = { 10, Math.toRadians(55) };
        PointValuePair solution = optimizer.optimize(
                new MaxEval(1000),
                new ObjectiveFunction(objective),
                GoalType.MINIMIZE,
                new InitialGuess(initialGuess));

        return solution;
    }

    private static void printResults(double v0, double theta) {
        System.out.println("Initial velocity (v0): " + v0);
        System.out.println("Launch angle (theta): " + Math.toDegrees(theta));
    }
}