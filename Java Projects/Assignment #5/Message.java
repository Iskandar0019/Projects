/**
 * The Message class represents output messages to the user during the game.
 */

public class Message
{
    private String title, content;

    /**
     * Constructs a new Message with the fields initialized to empty strings.
     */

    public Message ()
    {
        this.title = "";
        this.content = "";
    }

    /**
     * Sets the title of the Message.
     * @param guessNumber The current guess number entered by the user.
     */

    public void setTitle (int guessNumber)
    {
        this.title = "Guess #" + guessNumber;
    }

    /**
     * Returns the title of the Message.
     * @return The title of the Message.
     */

    public String getTitle ()
    {
        return (this.title);
    }

    /**
     * Returns the content of the Message.
     * @return The content of the Message.
     */

    public String getContent ()
    {
        return (this.content);
    }

    /**
     * Sets the Message to the introductory message when the game is run.
     */

    public void setIntroduction ()
    {
        this.title = "Bulls and Cows";
        this.content = "Welcome to Bulls and Cows!\n\n" +
                       "I will be thinking of a secret 4 digit number composed of unique digits (0 - 9).\n" +
                       "Your task is to guess that number.\n\n" +
                       "The rules are as follows:\n" +
                       "- Guessing a digit of the secret number will grant you a Cow hit.\n" +
                       "- Guessing a digit in its exact position within the secret number will grant you a Bull hit.\n\n" +
                       "Your goal is to guess the secret number by earning 4 Bull hits -\n" +
                       "which will in turn grant you a Full Hit.\n\n" +
                       "Would you like to play?\n\n" +
                       "*Note: No bulls or cows were harmed during the production of this game.";
    }

    /**
     * Sets the Message when play begins.
     */

    public void setGameStart ()
    {
        this.title = "Guess #1";
        this.content = "Hmmmmm... I thought of a secret number.\n\n" +

                       "Please enter your guess: ";
    }

    /**
     * Sets the Message when an invalid input has been entered by the user.
     */

    public void setInvalidInput ()
    {
        this.title = "Invalid Input";
        this.content = "Please enter a 4 digit number composed of unique digits.";
    }

    /**
     * Sets the Message if the game was won.
     * @param guessList A list of the user's previous guesses.
     * @param numOfGuesses The total amount of guesses entered by the user when the game was one.
     */

    public void setYouWon (String guessList, int numOfGuesses)
    {
        this.title = "You Won!";
        this.content = "Congratulations! You guessed the secret number in " + numOfGuesses + " attempt";

        this.content += (numOfGuesses == 1) ? ".\n\n" : "s.\n\n"; //Updates the message according to
                                                                  // the number of attempts.
        this.content += guessList + "\n";
        this.content += "Would you like to play again?";
    }

    /**
     * Sets the Message to offer the user to play the game again.
     */

    public void setPlayAgain ()
    {
        this.title = "Bulls and Cows";
        this.content = "Would you like to play again?";
    }

    /**
     * Sets the message when the user decides to exit the game.
     */

    public void setBye ()
    {
        this.title = "Bye!";
        this.content = "Thanks for playing!";
    }
}

// End of class Message.