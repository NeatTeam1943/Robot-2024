from scipy.optimize import minimize
import numpy as np

"""
This program calculates the optimal launch angle and initial velocity for a projectile to land
inside the target during the FRC 2024 season!

The program utilizes the scipy.optimize library to find the solutions to the projectile motion equations.

## Equations:
Δx = v0 * cos(θ) * t
Δy = v0 * sin(θ) * t - 0.5 * g * t^2

## Constants:
- Δx: Horizontal distance difference from the shooter to the target.
- Δy: Vertical height difference from the shooter to the target.
- g: Acceleration due to gravity (assumed constant): 9.8 m/s^2

## Variables:
- θ: Launch angle of the projectile.
- v0: Initial velocity of the projectile.

## Optimization Objective:
    - Minimize the absolute difference between the calculated and target vertical displacements.
"""


class ProjectileSolver:
    """
    ## A class for solving projectile motion problems to find optimal launch angle and initial velocity.
    ----
    Attributes:
    ----
        - initial_guess (list): Initial guess for launch angle (in radians) and initial velocity.
        - delta_x (float): Horizontal displacement to the target.
        - delta_y (float): Vertical displacement to the target.
        - g (float): Acceleration due to gravity.

    Methods:
    ----
        - __init__(self, delta_x, delta_y, g): Initialize the ProjectileSolver.
        - set_initial_guess(self, theta_guess, v0_guess): Set the initial guess for launch angle and initial velocity.
        - projectile_equation(self, params): Objective function representing the difference between calculated and target vertical displacements.
        - solve(self): Solve the projectile motion problem and print the optimal launch angle and initial velocity.
    """

    def __init__(self, g=9.8) -> None:
        """
        ## Initialize the ProjectileSolver.
        ----
        Args:
        ----
            - g (float): Acceleration due to gravity (default: 9.8 m/s^2).
        """
        self.g = g
        self.initial_guess = None

    def set_initial_guess(self, theta_guess: float, v0_guess: float) -> None:
        """
        ## Set the initial guess for launch angle and initial velocity.
        ----
        Args:
        ---- 
            - theta_guess (float): Initial guess for launch angle (in degrees).
            - v0_guess (float): Initial guess for initial velocity.

        More Info:
        ----
        - For the ease of calculation the function transfers the theta_guess into radians inside of itself.
        """
        self.initial_guess = [np.radians(theta_guess), v0_guess]
       
    def update_deltas(self, delta_x: float, delta_y: float) -> None:
        """
        ## Update the horizontal and vertical displacements to the target.
        ----
        Args:
        ----
            - delta_x (float): New horizontal displacement to the target.
            - delta_y (float): New vertical displacement to the target.
        """
        self.delta_x = delta_x
        self.delta_y = delta_y

    def projectile_equation(self, params: list, *args: tuple):
        """
        ## Objective function representing the difference between calculated and target vertical displacements.
        ----
        Args:
        ----
            params (list): Launch angle (in radians) and initial velocity.
            args (tuple): Additional arguments (delta_x, delta_y, g) for the objective function.

        Returns:
        ----
            float: Absolute difference between calculated and target vertical displacements.
        """
        theta, v0 = params
        delta_x, delta_y, g = args

        t = delta_x / (v0 * np.cos(theta))
        y = v0 * np.sin(theta) * t - 0.5 * g * t**2

        return np.abs(y - delta_y)

    def solve(self) -> tuple:
        """
        ## Solve the projectile motion problem and print the optimal launch angle and initial velocity.
        ----
        Raises:
        ----
            - ValueError: If initial guess is not set.

        Returns:
        ----
            - tuple: The optimal angle to set the shooter to and the optimal initial velocity to set the robot to.
        """
        if self.initial_guess is None:
            raise ValueError(
                "Initial guess not set. Use set_initial_guess method.")

        result = minimize(self.projectile_equation, self.initial_guess, args=(
            self.delta_x, self.delta_y, self.g))
        theta_optimal, v0_optimal = result.x

        return np.degrees(theta_optimal), v0_optimal
