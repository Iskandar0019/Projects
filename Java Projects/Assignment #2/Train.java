/**
 * This class represents a passenger train.
 * @author Timor Tagiev.
 * @version 24 April, 2020.
 */

public class Train
{
    private String _destination; // Instance variables (objects).
    private Time1 _departure;

    private int _duration, _passengers, _seats, _price; // Instance variables (primitive data types).

    private final int MIN_DURATION = 0, MIN_PASSENGERS = 0, MIN_SEATS = 0, MIN_PRICE = 0; // Constants.

    /**
     * Constructs a new Train object. If any of the integer passed parameters are less than zero,
     * the attributes are set to zero. Also, if the number of passengers exceeds the number of the
     * seats on the train, the number of passengers is set to maximum occupancy.
     * @param destination The destination of the train.
     * @param hour The hour component of the train's departure time.
     * @param minute The minute component of the train's departure time.
     * @param duration The duration of the train's journey.
     * @param passengers The number of passengers on the train.
     * @param seats The number of seats in the train.
     * @param price The price of the traveling ticket.
     */

    public Train (String destination, int hour, int minute, int duration, int passengers, int seats, int price) // Constructor.
    {
        this._departure = new Time1 (hour,minute); // Avoids aliasing.
        this._destination = destination;
        this._duration = Math.max (duration,MIN_DURATION);
        this._seats = Math.max (seats,MIN_SEATS);
        this._price = Math.max (price,MIN_PRICE);
        this._passengers = Math.min(Math.max (passengers,MIN_PASSENGERS),seats);
    }

    /**
     * Construct a new copy of a Train object.
     * @param other The Train object to be copied.
     */

    public Train (Train other) // Copy constructor.
    {
        this._departure = new Time1 (other.getDeparture ()); // Avoids aliasing.
        this._destination = other.getDestination ();
        this._duration = other.getDuration ();
        this._seats = other.getSeats ();
        this._price = other.getPrice ();
        this._passengers = other.getPassengers ();
    }

    /**
     * Sets the train's departure time.
     * @param time1 The train's departure time to be set.
     */

    public void setDeparture (Time1 time1)
    {
        this._departure = new Time1 (time1); // Avoids aliasing.
    }

    /**
     * Sets the train's destination
     * @param destination The train's destination to be set.
     */

    public void setDestination (String destination)
    {
        this._destination = destination;
    }

    /**
     * Sets the duration of the train's journey. If the passed parameter is less than zero,
     * the duration of the journey remains unaltered.
     * @param duration The duration of the train's journey to be set in minutes.
     */

    public void setDuration (int duration)
    {
        if (duration >= MIN_DURATION)
        {
            this._duration = duration;
        }
    }

    /**
     * Sets the number of the passengers on the train. If the passed parameter is less than zero,
     * the number of passengers remains unaltered. Also, if the number of passengers exceeds the number of the
     * seats on the train, the number of passengers is set to maximum occupancy.
     * @param passengers The number of the passengers to be set.
     */

    public void setPassengers (int passengers)
    {
        if (passengers >= MIN_PASSENGERS)
        {
            this._passengers = Math.min (passengers,this.getSeats ());
        }
    }

    /**
     * Sets the number of seats on the train.If the passed parameter is less than zero,
     * or less than the number of passengers on the train, the number of seats remains unaltered.
     * @param seats The number of seats to be set.
     */

    public void setSeats (int seats)
    {
        if ((seats >= MIN_SEATS) && (seats > this.getPassengers ()))
        {
            this._seats = seats;
        }
    }

    /**
     * Sets the price of the traveling ticket. If the passed parameter is less than zero,
     * the price remains unaltered.
     * @param price The price of the traveling ticket to be set.
     */

    public void setPrice (int price)
    {
        if (price >= MIN_PRICE)
        {
            this._price = price;
        }
    }

    /**
     * Returns the train's departure time.
     * @return The train's departure time.
     */

    public Time1 getDeparture ()
    {
        return (this._departure);
    }

    /**
     * Returns the train's destination.
     * @return The train's destination.
     */

    public String getDestination ()
    {
        return (this._destination);
    }

    /**
     * Returns the duration of the train's journey.
     * @return The duration of the train's journey in minutes.
     */

    public int getDuration ()
    {
        return (this._duration);
    }

    /**
     * Returns the number of passengers on the train.
     * @return The number of passengers on the train.
     */

    public int getPassengers ()
    {
        return (this._passengers);
    }

    /**
     * Returns the price of the traveling ticket.
     * @return The price of the traveling ticket.
     */

    public int getPrice ()
    {
        return (this._price);
    }

    /**
     * Returns the number of seats on the train.
     * @return The number of seats on the train.
     */

    public int getSeats ()
    {
        return (this._seats);
    }

    /**
     * Checks whether two trains have the same destination, number of seats and depart at the same time.
     * @param other The train to be compared.
     * @return True if both trains the same destination, number of seats and depart at the same time. False otherwise.
     */

    public boolean equals (Train other)
    {
        return ((this.getDestination ().equals (other.getDestination ())) &&
                (this.getSeats () == other.getSeats ()) && (this.getDeparture ().equals (other.getDeparture ())));
    }

    /**
     * Calculates the train's arrival time in its destination.
     * @return The train's arrival time in its destination.
     */

    public Time1 getArrivalTime ()
    {
        return (this.getDeparture ().addMinutes (this.getDuration ()));
    }

    /**
     * Adds passengers to the train only if there are enough available seats for all the passengers to be added.
     * Assumes the parameter passed is a positive number.
     * @param passengers The number of passengers to be added.
     * @return True if the passengers were successfully added. False otherwise.
     */

    public boolean addPassengers (int passengers)
    {
        boolean passengersAdded = false;

        if (this.getPassengers () + passengers <= this.getSeats ())
        {
            this.setPassengers (this.getPassengers () + passengers);

            passengersAdded = true;
        }

        return (passengersAdded);
    }

    /**
     * Checks whether the train is full (i.e all seats are taken).
     * @return True if the train is full. False otherwise.
     */

    public boolean isFull ()
    {
        return (this.getPassengers () == this.getSeats ());
    }

    /**
     * Checks whether the invoking object's ticket price is cheaper than the passed object's price.
     * @param other The train which ticket price is to be compared.
     * @return True if the invoking object's ticket price is cheaper than the passed object's price. False otherwise.
     */

    public boolean isCheaper (Train other)
    {
        return (this.getPrice () < other.getPrice ());
    }

    /**
     * Calculates the total price of tickets on the train.
     * @return The total price of tickets on the train.
     */

    public int totalPrice ()
    {
        return (this.getPassengers () * this.getPrice ());
    }

    /**
     * Checks whether the invoking object's arrival time is earlier than the passed object's arrival time.
     * @param other The other train whose time of arrival is to be compared.
     * @return True if the invoking object's arrival time is earlier than the passed object's arrival time. False otherwise.
     */

    public boolean arrivesEarlier (Train other)
    {
        return (this.getArrivalTime ().before (other.getArrivalTime ()));
    }

    /**
     * Presents the details of the train, indicating whether it is full or not.
     * @return A string representing the details of the train in the format:
     * "Train to _destination_ departs at _departure_. Train is _full/not full_."
     */

    public String toString ()
    {
        String trainDetails = "Train to " + this.getDestination () + " departs at " + this.getDeparture () + ". ";

        trainDetails += (this.isFull ()) ? "Train is full." : "Train is not full.";

        return (trainDetails);
    }
} // End of class Train.