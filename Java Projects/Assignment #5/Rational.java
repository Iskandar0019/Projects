/**
 * The Rational class represents a rational number in the form of a fraction.
 * @author Timor Tagiev
 * @version November 6, 2020
 */

public class Rational
{
    private int numerator, denominator;

    /**
     * Constructs a new Rational number with the specified parameters.
     * @param numerator The numerator of the Rational number.
     * @param denominator The denominator of the Rational number. If the denominator is 0,
     *                    the Rational number is initialized to 0.
     */

    public Rational (int numerator, int denominator)
    {
        if (denominator > 0)
        {
            this.numerator = numerator;
            this.denominator = denominator;
        }
        else if (denominator < 0) // Makes only the numerator signed.
        {
            this.numerator = numerator * (-1);
            this.denominator = denominator * (-1);
        }
        else // Initializes the Rational number to 0. The denominator value is insignificant.
        {
            this.numerator = 0;
            this.denominator = 1;
        }
    }

    /**
     * Returns the numerator of the Rational number.
     * @return The numerator of the Rational number.
     */

    public int getNumerator ()
    {
        return (this.numerator);
    }

    /**
     * Returns the denominator of the Rational number.
     * @return The denominator of the Rational number.
     */

    public int getDenominator ()
    {
        return (this.denominator);
    }

    /**
     * Presents the Rational number in the form of a/b.
     * @return A String representing the Rational number in the form of a/b.
     */

    public String toString ()
    {
        String result;

        if (this.getNumerator () % this.getDenominator () == 0) // The Rational number is an integer.
        {
            result = (this.getNumerator () / this.getDenominator ()) + "";
        }
        else
        {
            result = this.getNumerator () + "/" + this.getDenominator ();
        }

        return (result);
    }

    /**
     * Checks whether the invoking Rational number is greater than the passed Rational number.
     * @param other The Rational number to be compared to.
     * @return True if the invoking Rational number is greater than the passed Rational number. False otherwise.
     */

    public boolean greaterThan (Rational other) // Compares according to the formula provided in the assignment.
    {
        return ((other != null) &&
                (this.getNumerator () * other.getDenominator ()) > (other.getNumerator () * this.getDenominator ()));
    }

    /**
     * Checks whether the invoking Rational number is equal to the passed Rational number.
     * @param other The Rational number to be compared to.
     * @return True if the invoking Rational number is equal to the passed Rational number.
     *         False if the invoking Rational number is not equal to the passed Rational number
     *         or if the passed parameter is not an instance of the Rational class.
     */

    public boolean equals (Object other) // Compares according to the formula provided in the assignment.
    {
            return (other instanceof Rational &&
                    ((this.getNumerator () * ((Rational) other).getDenominator ()) ==
                    (((Rational) other).getNumerator () * this.getDenominator ())));
    }

    /**
     * Adds the passed Rational number to the invoking Rational number.
     * @param other The Rational number to be added to the invoking Rational number.
     * @return The sum of the invoking Rational number and the passed Rational number.
     *         Null if the passed Rational number is null.
     */

    public Rational plus (Rational other) // Calculates according to the formula provided in the assignment.
    {
        Rational sum = null;

        if (other != null)
        {
            sum = new Rational (this.getNumerator () * other.getDenominator () +
                                this.getDenominator () * other.getNumerator (),
                                this.getDenominator () * other.getDenominator ());
        }

        return (sum);
    }

    /**
     * Subtracts the passed Rational number from the invoking Rational number.
     * @param other The Rational number to be subtracted from the invoking Rational number.
     * @return The difference between the invoking Rational number and the passed Rational number.
     *         Null if the passed Rational number is null.
     */

    public Rational minus (Rational other) // Calculates according to the formula provided in the assignment.
    {
        Rational difference = null;

        if (other != null)
        {
            other.numerator *= -1;

            difference = this.plus (other);
        }

        return (difference);
    }

    /**
     * Multiplies the invoking Rational number by the passed Rational number.
     * @param other The Rational number to be multiplied by the invoking Rational number.
     * @return The product of the the invoking Rational number multiplied by the passed Rational number.
     *         Null if the passed Rational number is null.
     */

    public Rational multiply (Rational other) // Calculates according to the formula provided in the assignment.
    {
        Rational product = null;

        if (other != null)
        {
            product = new Rational (this.getNumerator () * other.getNumerator (),
                                    this.getDenominator () * other.getDenominator ());
        }

        return (product);
    }

    /**
     * Reduces the invoking Rational number to its reduced fraction.
     * @return The Rational number in its reduced fraction.
     */

    public Rational reduce ()
    {
        int gcd = this.gcd (this.getNumerator (), this.getDenominator ()); // Finds the greatest common divisor.

        return (new Rational (this.getNumerator () / gcd, this.getDenominator () / gcd));
    }

    /*----------> Private Methods <----------*/

    private int gcd (int num1, int num2) // Euclid's recursive algorithm for determining the greatest common divisor.
    {
        if (num2 == 0)
        {
            return (num1);
        }
        else
        {
            return (gcd (num2, (num1 % num2)));
        }
    }
}

// End of class Rational.