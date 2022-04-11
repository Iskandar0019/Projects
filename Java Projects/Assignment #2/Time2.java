/**
 * This class represents a time in a day by the number of minutes that had passed since midnight.
 * @author Timor Tagiev.
 * @version 24 April, 2020.
 */

public class Time2
{
    private int _minFromMid; // Instance variables.

    private final int MIN_HOUR = 0, MAX_HOUR = 24, MIN_MINUTE = 0, MAX_MINUTE = 60; // constants.
    private final int MIN_DOUBLE_DIGIT_HOUR = 10, MIN_DOUBLE_DIGIT_MINUTE = 10;

    /**
     * Constructs a new Time2 object.
     * If any of the passed parameters our out of the valid range (0 - 23 for hours and 0 - 59 for minutes),
     * the invalid parameters are not used.
     * @param hour The hour in the day.
     * @param minute The minute in the day.
     */

    public Time2 (int hour, int minute) // Constructor.
    {
        if (hour < MIN_HOUR || hour >= MAX_HOUR)
        {
            hour = MIN_HOUR;
        }

        if (minute < MIN_MINUTE || minute >= MAX_MINUTE)
        {
            minute = MIN_MINUTE;
        }

        _minFromMid = (hour * MAX_MINUTE) + minute;
    }

    /**
     * Constructs a new copy of a Time2 object.
     * @param other The Time2 object to be copied.
     */

    public Time2 (Time2 other) // Copy constructor.
    {
        this._minFromMid = other.minFromMidnight ();
    }

    /**
     * Sets the hours of the time object in the form of minutes passed since midnight.
     * If the passed parameter is out of range (0 - 23), the time remains unaltered.
     * @param hour The hours to be set (a value in the range of 0 - 23).
     */

    public void setHour (int hour)
    {
        if ((hour >= MIN_HOUR) && (hour < MAX_HOUR))
        {
            this._minFromMid = (hour * MAX_MINUTE) + this.getMinute ();
        }
    }

    /**
     * Sets the minutes of the time object in the form of minutes passed since midnight.
     * If the passed parameter is out of range (0 - 59), the time remains unaltered.
     * @param minute The minutes to be set (a value in the range of 0 - 59).
     */

    public void setMinute (int minute)
    {
        if ((minute >= MIN_MINUTE) && (minute < MAX_MINUTE))
        {
            this._minFromMid = (this.getHour () * MAX_MINUTE) + minute;
        }
    }

    /**
     * Returns the hours component of the time (a value in the range of 0 - 23).
     * @return The hours component of the time.
     */

    public int getHour ()
    {
        return (this._minFromMid / MAX_MINUTE);
    }

    /**
     * Returns the minutes component of the time (a value in the range of 0 - 59).
     * @return The minutes component of the time.
     */

    public int getMinute ()
    {
        return (this._minFromMid % MAX_MINUTE);
    }

    /**
     * Returns the minutes that had passed between the time stored in the object and midnight.
     * @return The minutes that had passed since midnight.
     */

    public int minFromMidnight ()
    {
        return (this._minFromMid);
    }

    /**
     * Checks whether two time objects represent the same time in a day.
     * @param other The time to be compared.
     * @return True if both time objects represent the same time in a day. False otherwise.
     */

    public boolean equals (Time2 other)
    {
        return (this.difference (other) == MIN_MINUTE);
    }

    /**
     * Checks whether the invoking time object is before (relative to the day) the passed time object.
     * @param other The time to be compared.
     * @return True if the invoking time object is before (relative to the day) the passed time object. False otherwise.
     */

    public boolean before (Time2 other)
    {
        return (this.difference (other) < MIN_HOUR);
    }

    /**
     * Checks whether the invoking time object is after (relative to the day) the passed time object.
     * @param other The time to be compared.
     * @return True if the invoking time object is after (relative to the day) the passed time object. False otherwise.
     */

    public boolean after (Time2 other)
    {
        return (other.before (this));
    }

    /**
     * Calculates the difference in minutes between the invoking time object and the passed time object.
     * Assumes that the invoking time object indicates a later time (of the same day) than the passed time object.
     * @param other The time to be compared.
     * @return The difference in minutes between the invoking time object and the passed time object.
     */

    public int difference (Time2 other)
    {
       return (this.minFromMidnight () - other.minFromMidnight ());
    }

    /**
     * Adds the passed value of minutes to the invoking time object.
     * @param minutes The number of minutes to be added.
     * @return A new Time2 object after the passed minutes were added.
     */

    public Time2 addMinutes (int minutes) // This method is identical to addMinutes method of Time1 class except that it returns a Time2 object.
    {
        minutes %= (MAX_HOUR * MAX_MINUTE);

        if (minutes < MIN_HOUR)
        {
            minutes += (MAX_HOUR * MAX_MINUTE);
        }

        int newHour = ((this.minFromMidnight () + minutes) / MAX_MINUTE) % MAX_HOUR;
        int newMinute = (this.minFromMidnight () + minutes) % MAX_MINUTE;

        return (new Time2 (newHour,newMinute));
    }

    /**
     * Presents the time stored in the object.
     * @return A string representing the time of the day in the format: HH:MM.
     */

    public String toString () // This method is identical to toString method of Time1 class.
    {
       String hourString = (this.getHour () < MIN_DOUBLE_DIGIT_HOUR) ? ("0" + this.getHour ()) : ("" + this.getHour ());
       String minuteString = (this.getMinute () < MIN_DOUBLE_DIGIT_MINUTE) ? ("0" + this.getMinute ()) : ("" + this.getMinute ());

       return (hourString + ":" + minuteString);
    }
} // End of class Time2.