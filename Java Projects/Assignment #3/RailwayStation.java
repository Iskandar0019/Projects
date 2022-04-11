/**
 * This class represents a railway station's daily schedule.
 * @author Timor Tagiev
 * @version 09 May, 2020
 */

public class RailwayStation
{
    private final int TRAIN_NOT_FOUND_INDEX = -1, MIN_TRAINS = 0, MAX_TRAINS = 100; // Constants.

    private Train [] _station; // Instance variables.
    private int _noOfTrs;

    /**
     * Constructs a new RailwayStation object with room for 100 trains.
     * Initially there are no trains in the schedule.
     */

    public RailwayStation ()
    {
        _station = new Train [MAX_TRAINS];
        _noOfTrs = MIN_TRAINS;
    }

    /**
     * Adds a new train to the schedule.
     * @param train The train to be added. Adds the train to the first unoccupied index in the array.
     * @return True if the train was added successfully. False if the train is already present, the schedule is full,
     * or the passed parameter is a null reference.
     */

    public boolean addTrain (Train train)
    {
        boolean added = false;

        if ((train != null) && (this.getNoOfTrs () < MAX_TRAINS))
        {
            if ((this.isPresent (train) == TRAIN_NOT_FOUND_INDEX)) // Checks whether the train to ba added is already present.
            {
                _station [this.getNoOfTrs ()] = new Train (train); // Adds the train in the first available index.
                _noOfTrs++; // Updates the number of trains in the schedule.
                added = true;
            }
        }

        return (added);
    }

    /**
     * Removes a train from the schedule.
     * @param train The train to be removed.
     * @return True if the train was removed successfully. False if the train is not present, the schedule is empty,
     * or the passed parameter is a null reference.
     */

    public boolean removeTrain (Train train)
    {
        boolean removed = false;

        if ((train != null) && (this.isNotEmpty ()))
        {
            if (this.isPresent (train) != TRAIN_NOT_FOUND_INDEX) // Checks whether the train to be removed is present.
            {
                _station [this.isPresent (train)] = this.getTrain (this.getNoOfTrs () - 1); // Removes the train.
                _station [this.getNoOfTrs () - 1] = null; // Sets the last train (which was copied to the removed train's index) to null.
                _noOfTrs--; // Updates the number of trains.
                removed = true;
            }
        }

        return (removed);
    }

    /**
     * Finds the first train in the schedule to depart to the passed destination.
     * @param destination The destination to search for.
     * @return The first train in the schedule to depart to the passed destination.
     * Null if there no trains bound to the passed destination, the schedule is empty,
     * or the passed parameter is a null reference.
     */

    public Time1 firstDepartureToDestination (String destination)
    {
        Time1 firstDepartureTime = null; // The reference to be returned.

        if ((destination != null) && (this.isNotEmpty ()))
        {
            for (int i = 0; i < this.getNoOfTrs (); i++)
            {
                if ((this.getTrain (i).getDestination ()).equals (destination)) // Searches for the first train bound to the destination.
                {
                    if (firstDepartureTime == null)
                    {
                        firstDepartureTime = this.getTrain (i).getDeparture (); // First train to the bound destination was found.
                    }
                    else
                    {
                        if (this.getTrain (i).getDeparture ().before (firstDepartureTime))
                        {
                            firstDepartureTime = this.getTrain (i).getDeparture (); // Updates the first departure time to the destination.
                        }
                    }
                }
            }

            if (firstDepartureTime != null)
            {
                firstDepartureTime = new Time1 (firstDepartureTime); // Creates a new Time1 object if a train to the destination was found.
            }
        }

        return (firstDepartureTime);
    }

    /**
     * Checks how many trains are in full occupancy.
     * @return The number of full trains.
     */

    public int howManyFullTrains ()
    {
        int fullTrainsCounter = 0;

        for (int i = 0; i < this.getNoOfTrs (); i++)
        {
            if (this.getTrain (i).isFull ())
            {
                fullTrainsCounter++; // Counts the full trains.
            }
        }

        return (fullTrainsCounter);
    }

    /**
     * Finds the most popular destination in the schedule.
     * @return The most popular destination in the schedule. If there are several destinations that are equally
     * popular, the method returns the first one of them. Returns null if the schedule is empty.
     */

    public String mostPopularDestination ()
    {
        String mostPopularDestination = null; // The reference to be returned.

        int counter = 0, max = 0;

        if (this.isNotEmpty ())
        {
            mostPopularDestination = this.getTrain (0).getDestination (); // Sets an initial value.

            for (int i = 0; i < this.getNoOfTrs (); i++) // Compares each train's destination with the other trains.
            {
                for (int j = i; j < this.getNoOfTrs (); j++)
                {
                    if ((this.getTrain (i).getDestination ()).equals (this.getTrain (j).getDestination ()))
                    {
                        counter++; // A common destination was found.
                    }
                }

                if (counter > max)
                {
                    max = counter; // A more popular destination was found.
                    mostPopularDestination = this.getTrain (i).getDestination (); // Updates the most popular destination.
                }

                counter = 0;
            }
        }

        return (mostPopularDestination);
    }

    /**
     * Finds the train with the most expensive ticket in the schedule.
     * @return The train whose ticket is the most expensive. If several trains share the most expensive ticket,
     * returns the first one of them. Returns null if the schedule is empty.
     *
     */

    public Train mostExpensiveTicket ()
    {
        Train mostExpensiveTrain = null; // The reference to be returned.

        if (this.isNotEmpty ())
        {
            mostExpensiveTrain = this.getTrain (0); // Sets an initial value.

            for (int i = 1; i < this.getNoOfTrs (); i++)
            {
                if (mostExpensiveTrain.isCheaper (this.getTrain (i)))
                {
                    mostExpensiveTrain = this.getTrain (i); // A more expensive train was found.
                }
            }

            mostExpensiveTrain = new Train (mostExpensiveTrain); // Creates a new Train object to be returned.
        }

        return (mostExpensiveTrain);
    }

    /**
     * Finds the train with the longest journey time.
     * @return The train with the longest journey time. If several trains share the longest journey time,
     * returns the first one of them. Returns null if the schedule is empty.
     */

    public Train longestTrain ()
    {
        Train longestTrain = null; // The reference to be returned.

         if (this.isNotEmpty ())
         {
             longestTrain = this.getTrain (0); // Sets an initial value.

             for (int i = 1; i < this.getNoOfTrs (); i++)
             {
                 if (this.getTrain (i).getDuration () > longestTrain.getDuration ())
                 {
                     longestTrain = this.getTrain (i);// A train with a longer journey time was found.
                 }
             }

             longestTrain = new Train (longestTrain); // Creates a new Train object to be returned.
         }

        return (longestTrain);
    }

    /**
     * Presents the stations's daily schedule, indicating the destination, the departure time and occupancy.
     * @return The station's daily schedule.
     */

    public String toString ()
    {
        String schedule = "There are no trains today."; // The string to be returned.

        if (this.isNotEmpty ())
        {
            schedule = "The trains today are:\n";

            for (int i = 0; i < this.getNoOfTrs (); i++)
            {
                schedule += this.getTrain (i).toString () + "\n"; // Updates the schedule string.
            }
        }

        return (schedule);
    }

// ******************************* Private Methods ******************************* //

    private int getNoOfTrs () // Returns the number of trains in the station.
    {
        return (this._noOfTrs);
    }

    private Train getTrain (int i) // Returns the train in the passed index.
    {
        return (_station [i]);
    }

    private int isPresent (Train train) // Returns the index in the array of the passed train. -1 if the train is not present.
    {
        int index = TRAIN_NOT_FOUND_INDEX;

        for (int i = 0; i < this.getNoOfTrs (); i++)
        {
            if (train.equals (this.getTrain (i)))
            {
                index = i;
            }
        }

        return (index);
    }

    private boolean isNotEmpty () // Checks whether the schedule is not empty.
    {
        return (this.getNoOfTrs () > MIN_TRAINS);
    }
} // End of class RailwayStation.