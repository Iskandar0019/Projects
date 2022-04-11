/* This program computes the area and perimeter of a trapezoid provided the left point
   coordinates of the bases and their lengths. */

import java.util.Scanner;

public class Trapezoid
{
    public static void main (String [] args)
    {
        Scanner scanner = new Scanner (System.in); // Objects.

        int x1, x2, y1, y2, base1, base2; // Variables.
        double height, side1, side2, area, perimeter;

        System.out.println ("Please enter the left point coordinates of the base followed by its length:"); // Input.
        x1 = scanner.nextInt ();
        y1 = scanner.nextInt ();
        base1 = scanner.nextInt ();

        System.out.println ("Please enter the left point coordinates of the other base followed by its length:");
        x2 = scanner.nextInt ();
        y2 = scanner.nextInt ();
        base2 = scanner.nextInt ();

        /* Avoiding loss of information in the following calculations.
           The variables height, side1 and side2 were declared as floating - point variables.
           The expression on the right hand side of the first equation is converted to floating point value via
           assignment conversion.
           Math.pow and Math.sqrt return floating point values by definition.*/

        height = Math.abs (y2 - y1); // Length of the height.
        side1 = Math.sqrt (Math.pow ((x2 - x1),2) + Math.pow ((y2 - y1),2)); // Length of the first side.
        side2 = Math.sqrt (Math.pow (((x2 + base2) - (x1 + base1)),2) + Math.pow ((y2 - y1),2)); // Length of the second side.

        area = (height * (base1 + base2)) / 2; // Calculating the results.
        perimeter = base1 + base2 + side1 + side2;

        System.out.println ("The area of the trapezoid is " + area); // Output results.
        System.out.println ("The perimeter of the trapezoid is " + perimeter);
    } // End of method main.
} // End of class Trapezoid.