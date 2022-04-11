/**
 * The Game class represents the logics of the game of Bulls and Cows.
 */

import java.util.ArrayList;
import java.util.Collections;

public class Game
{
    private boolean hit = false; // A boolean field triggered when the secret number is guessed.
    private final int Num_Of_Digits = 4;
    private int numOfGuesses; // Counts the number of the guesses entered by the user.

    private final String secretNumber;

    /**
     * Constructs a new Bull and Cows Game with the secret number randomly generated.
     * The number of guesses of the Game is initialized to 0.
     */

    public Game ()
    {
        this.numOfGuesses = 0;
        this.secretNumber = this.generateRandomNumber ();
    }

    /**
     * Signals whether the user guessed the secret number.
     * @return True if the user guessed the secret number. False otherwise.
     */

    public boolean getHit ()
    {
        return (this.hit);
    }

    /**
     * Returns the number of guesses entered by the user during the game.
     * @return The number of guesses entered by the user during the game.
     */

    public int getNumOfGuesses ()
    {
        return (this.numOfGuesses);
    }

    /**
     * Returns the secret number.
     * @return The secret number.
     */

    public String getSecretNumber ()
    {
        return (this.secretNumber);
    }

    /**
     * Checks whether the user's input is valid.
     * @param input The user's input.
     * @return True if the input is composed of 4 unique digits. False otherwise.
     */

    public boolean checkInput (String input)
    {
        boolean validInput = true;

        if ((input == null) || (input.length () != Num_Of_Digits))
        {
            validInput = false; // Input is of incorrect length.
        }
        else
        {
            for (int i = 0; i < Num_Of_Digits; i++)
            {
                if ((input.charAt (i) < '0') || (input.charAt (i) > '9'))
                {
                    validInput = false; // Input is not fully composed of digits.
                    break;
                }
            }

            for (int i = 0; i < (Num_Of_Digits - 1); i++)
            {
                for (int j = (i + 1); j < Num_Of_Digits; j++)
                {
                    if (input.charAt (i) == input.charAt (j)) //
                    {
                        validInput = false; // Input is not fully composed of unique digits.
                        break;
                    }
                }
            }
        }

        return (validInput);
    }

    /**
     * Checks if the guess entered by the user is in fact the secret number.
     * @param guess The user's guess.
     * @return The number of the guess followed by the result.
     */

    public String checkGuess (String guess)
    {
        String result;

        this.numOfGuesses++;

        if (this.getSecretNumber ().equals (guess))
        {
            this.hit = true; // The user guessed the secret number.
            result = "Guess #" + this.getNumOfGuesses () + ": " + guess + "     Bulls - 4 Cows - 4" + "\n";
        }
        else
        {
            int bulls = 0, cows = 0;

            for (int i = 0; i < Num_Of_Digits; i++) // Counts the number of bulls and cows of the guess.
            {
                for (int j = 0; j < Num_Of_Digits; j++)
                {
                    if (this.getSecretNumber ().charAt (i) == guess.charAt (j))
                    {
                        if (i == j)
                        {
                            bulls++;
                        }
                        else
                        {
                            cows++;
                        }
                    }
                }
            }

            result = "Guess #" + this.getNumOfGuesses () + ": " + guess + "     Bulls - " + bulls + " Cows - " + cows + "\n";
        }

        return (result);
    }

    /* -------------------- Private Methods -------------------- */

    /* Generates a String composed of 4 unique digits which represents the secret number.
       Implements the ArrayList and Collections classes to generate the secret number.
       In order to generate the random number, an ArrayList of all digits is created,
       shuffled, and the first 4 digits are extracted.
     */

    private String generateRandomNumber ()
    {
        String randomNumber;

        ArrayList <Integer> digits = new ArrayList <> ();

        for (int i = 0; i <= 9; i++)
        {
            digits.add (i);
        }

        Collections.shuffle (digits);

        randomNumber = "" + digits.get (0) + digits.get (1) + digits.get (2) + digits.get (3);

        return (randomNumber);
    }
}
// End of class Game.