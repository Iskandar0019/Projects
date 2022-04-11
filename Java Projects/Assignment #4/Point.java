/**
 * This class represents a point in a Cartesian plane.
 * @author Timor Tagiev
 * @version 27 June, 2020
 */

public class Point
{
    private double _x, _y; // Instance variables.

    /**
     * Constructs a new Point object.
     * @param x x coordinate of the point.
     * @param y y coordinate of the point.
     */

    public Point (double x, double y)
    {
        this._x = x;
        this._y = y;
    }

    /**
     * Constructs a new copy of a Point object.
     * @param other The Point object to be copied.
     */

    public Point (Point other)
    {
        if (other != null)
        {
            this._x = other.getX ();
            this._y = other.getY ();
        }
    }

    /**
     * X coordinate getter.
     * @return X coordinate of a Point object.
     */

    public double getX ()
    {
        return (this._x);
    }

    /**
     * Y coordinate getter.
     * @return Y coordinate of a Point object.
     */

    public double getY ()
    {
        return (this._y);
    }

    /**
     * X coordinate setter.
     * @param x X coordinate to be set.
     */

    public void setX (double x)
    {
        this._x = x;
    }

    /**
     * Y coordinate setter.
     * @param y Y coordinate to be set.
     */

    public void setY (double y)
    {
        this._y = y;
    }

    /**
     * String representation of a Point object.
     * @return A String in the format of (x,y).
     */

    public String toString ()
    {
        return ("(" + this.getX () + "," + this.getY () + ")");
    }

    /**
     * Compares two points for equality.
     * @param other The point to be compared.
     * @return True if both the X and Y coordinates of the points are equal. False otherwise.
     */

    public boolean equals (Point other)
    {
        return ((other != null) && (this.getX () == other.getX ()) && (this.getY () == other.getY ()));
    }

    /**
     * Determines whether the invoking Point object is above the passed Point object.
     * @param other The point to be compared.
     * @return True if the invoking point is above the passed point. False otherwise.
     */

    public boolean isAbove (Point other)
    {
        return ((other != null) && (this.getY () > other.getY ()));
    }

    /**
     * Determines whether the invoking Point object is under the passed Point object.
     * @param other The point to be compared.
     * @return True if the invoking point is under the passed point. False otherwise.
     */

    public boolean isUnder (Point other)
    {
        return (other.isAbove (this));
    }

    /**
     * Determines whether the invoking Point object is left of the passed Point object.
     * @param other The point to be compared.
     * @return True if the invoking point is left of the passed point. False otherwise.
     */

    public boolean isLeft (Point other)
    {
        return ((other != null) && (this.getX () < other.getX ()));
    }

    /**
     * Determines whether the invoking Point object is right of the passed Point object.
     * @param other The point to be compared.
     * @return True if the invoking point is right of the passed point. False otherwise.
     */

    public boolean isRight (Point other)
    {
        return (other.isLeft (this));
    }

    /**
     * Calculates the distance between two Point objects.
     * @param other The point to which the distance is calculated.
     * @return The distance between the invoking point and the passed point.
     */

    public double distance (Point other)
    {
        return (Math.sqrt ((Math.pow (this.getX () - other.getX (), 2) + (Math.pow (this.getY () - other.getY (), 2)))));
    }

    /**
     * Moves the Point object.
     * @param dx The x distance (both positive and negative) to be moved.
     * @param dy The y distance (both positive and negative) to be moved.
     */

    public void move (double dx, double dy)
    {
        this.setX (this.getX () + dx);
        this.setY (this.getY () + dy);
    }
} // End of class Point.
