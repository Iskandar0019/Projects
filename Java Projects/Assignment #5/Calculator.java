import java.util.Scanner;

public class Calculator
{
    public static void main (String [] args)
    {
        Scanner scanner = new Scanner (System.in);
        Rational num1, num2;

        int numerator, denominator;

        System.out.println ("Please enter the numerator of the first rational number:");
        numerator = scanner.nextInt ();

        System.out.println ("Please enter the denominator of the first rational number:");

        while ((denominator = scanner.nextInt ()) == 0) // Invalid denominator.
        {
            System.out.println ("The denominator cannot be a 0 value. Please enter a non 0 value:");
        }

        num1 = (new Rational (numerator, denominator));

        System.out.println ("Please enter the numerator of the second rational number:");
        numerator = scanner.nextInt ();

        System.out.println ("Please enter the denominator of the second rational number:");

        while ((denominator = scanner.nextInt ()) == 0) // Invalid denominator.
        {
            System.out.println ("The denominator cannot be a 0 value. Please enter a non 0 value:");
        }

        num2 = (new Rational (numerator, denominator));

        /* Outputs the Results of Comparison and Calculation */

        System.out.println ();
        System.out.println ("Comparison Results:");
        System.out.println ();

        if (num1.greaterThan (num2))
        {
            System.out.println (num1 + " is greater than " + num2 + ".");
        }
        else
        {
            System.out.println (num1 + " is not greater than " + num2 + ".");
        }

        if (num1.equals (num2))
        {
            System.out.println (num1 + " is equal to " + num2 + ".");
        }
        else
        {
            System.out.println (num1 + " is not equal to " + num2 + ".");
        }

        System.out.println ();
        System.out.println ("Calculation Results:");
        System.out.println ();

        System.out.println (num1 + " + " + num2 + " = " + (num1.plus (num2)).reduce ());
        System.out.println (num1 + " - " + num2 + " = " + (num1.minus (num2)).reduce ());
        System.out.println (num1 + " * " + num2 + " = " + (num1.multiply (num2)).reduce ());
    }

    // End of method main.
}

// End of class Calculator.