/**
 * This class represents a time in a day in a 24 hour format, indicated by its number of hours and number of minutes.
 * @author Timor Tagiev.
 * @version 24 April, 2020.
 */

public class Time1
{
    private int _hour, _minute; // Instance variables.

    private final int MIN_HOUR = 0, MAX_HOUR = 24, MIN_MINUTE = 0, MAX_MINUTE = 60; // constants.
    private final int MIN_DOUBLE_DIGIT_HOUR = 10, MIN_DOUBLE_DIGIT_MINUTE = 10;

    /**
     * Constructs a new Time1 object.
     * If any of the passed parameters our out of the valid range (0 - 23 for hours and 0 - 59 for minutes),
     * the respective time components are set to zero.
     * @param hour The hour in the day.
     * @param minute The minute in the day.
     */

    public Time1 (int hour, int minute) // Constructor.
    {
        this._hour = ((hour >= MIN_HOUR) && (hour < MAX_HOUR)) ? hour : MIN_HOUR;
        this._minute = ((minute >= MIN_MINUTE) && (minute < MAX_MINUTE)) ? minute : MIN_MINUTE;
    }

    /**
     * Constructs a new copy of a Time1 object.
     * @param other The Time1 object to be copied.
     */

    public Time1 (Time1 other) // Copy constructor.
    {
        this._hour = other.getHour ();
        this._minute = other.getMinute ();
    }

    /**
     * Sets the hour component of the time.
     * If the passed parameter is out of range (0 - 23), the hour component remains unaltered.
     * @param hour The hour to be set.
     */

    public void setHour (int hour)
    {
        if (hour >= MIN_HOUR && hour < MAX_HOUR)
        {
            this._hour = hour;
        }
    }

    /**
     * Sets the minute component of the time.
     * If the passed parameter is out of range (0 - 59), the minute component remains unaltered.
     * @param minute The minute to be set.
     */

    public void setMinute (int minute)
    {
        if (minute >= MIN_MINUTE && minute < MAX_MINUTE)
        {
            this._minute = minute;
        }
    }

    /**
     * Returns the hour component of the time.
     * @return The hour component.
     */

    public int getHour ()
    {
        return (this._hour);
    }

    /**
     * Returns the minute component of the time.
     * @return The minute component.
     */

    public int getMinute ()
    {
        return (this._minute);
    }

    /**
     * Calculates the minutes that had passed between the time stored in the object and midnight.
     * @return The minutes that had passed since midnight.
     */

    public int minFromMidnight ()
    {
        return ((MAX_MINUTE * this.getHour ()) + this.getMinute ());
    }

    /**
     * Checks whether two time objects represent the same time in a day.
     * @param other The time to be compared.
     * @return True if both time objects represent the same time in a day. False otherwise.
     */

    public boolean equals (Time1 other)
    {
        return (this.difference (other) == MIN_MINUTE);
    }

    /**
     * Checks whether the invoking time object is before (relative to the day) the passed time object.
     * @param other The time to be compared.
     * @return True if the invoking time object is before (relative to the day) the passed time object. False otherwise.
     */

    public boolean before (Time1 other)
    {
        return (this.difference (other) < MIN_MINUTE);
    }

    /**
     * Checks whether the invoking time object is after (relative to the day) the passed time object.
     * @param other The time to be compared.
     * @return True if the invoking time object is after (relative to the day) the passed time object. False otherwise.
     */

    public boolean after (Time1 other)
    {
        return (other.before (this));
    }

    /**
     * Calculates the difference in minutes between the invoking time object and the passed time object.
     * Assumes that the invoking time object indicates a later time (of the same day) than the passed time object.
     * @param other The time to be compared.
     * @return The difference in minutes between the invoking time object and the passed time object.
     */

    public int difference (Time1 other)
    {
        return (this.minFromMidnight () - other.minFromMidnight ());
    }

    /**
     * Adds the passed value of minutes to the invoking time object.
     * @param minutes The number of minutes to be added.
     * @return A new Time1 object after the passed minutes were added.
     */

    public Time1 addMinutes (int minutes)
    {
        minutes %= (MAX_HOUR * MAX_MINUTE); // Converts the passed value to a valid range of minutes passed from midnight.

        if (minutes < MIN_MINUTE)
        {
            minutes += (MAX_HOUR * MAX_MINUTE); // Handles the case of a negative passed value.
        }

        int newHour = ((this.minFromMidnight () + minutes) / MAX_MINUTE) % MAX_HOUR; // Extracts a valid hour value.
        int newMinute = (this.minFromMidnight () + minutes) % MAX_MINUTE; // Extracts a valid minute value.

        return (new Time1 (newHour,newMinute)); // Returns a new Time1 object.
    }

    /**
     * Presents the time stored in the object.
     * @return A string representing the time of the day in the format: HH:MM.
     */

    public String toString ()
    {
        String hourString = (this.getHour () < MIN_DOUBLE_DIGIT_HOUR) ? ("0" + this.getHour ()) : ("" + this.getHour ()); // Handles the case of a single digit hour.
        String minuteString = (this.getMinute () < MIN_DOUBLE_DIGIT_MINUTE) ? ("0" + this.getMinute ()) : ("" + this.getMinute ()); // Handles the case of a single digit minute.

        return (hourString + ":" + minuteString); // Returns a new string in the valid format.
    }
} // End of class Time1.