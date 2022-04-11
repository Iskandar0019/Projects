/* This program converts a car's petrol consumption from miles/gallon to litres/100 Km */

import java.util.Scanner;

public class Petrol
{
    public static void main (String [] args)
    {
        Scanner scanner = new Scanner (System.in); // Objects.

        final int MAX = 100;
        final double KM_PER_MILE = 1.609, LITRES_PER_GALLON = 3.785; // Constants.

        double milesPerGallon, beforeRounding, afterRounding; // Variables.

        System.out.println ("Please enter the car's petrol consumption measured in miles/gallon:"); // Read input.
        milesPerGallon = scanner.nextDouble ();

        beforeRounding = (MAX / milesPerGallon) * (LITRES_PER_GALLON / KM_PER_MILE); // Calculating the result.

        afterRounding = Math.round (beforeRounding * MAX) / (MAX * 1.0); // Rounding the result to 2 decimal places.

        System.out.println ("The car's petrol consumption converted to litres/100km is:"); // Output result.
        System.out.println (afterRounding);
    } // End of method main.
} // End of class Petrol.