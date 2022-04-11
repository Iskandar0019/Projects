import javax.swing.*;

public class Main
{
    public static void main (String [] args)
    {
        int playAgain;

        String userGuess;
        Message message = new Message (); // The message object to control the output to the user.

        message.setIntroduction ();
        playAgain = JOptionPane.showConfirmDialog (null, message.getContent (), message.getTitle (), JOptionPane.YES_NO_OPTION);

        while (playAgain == JOptionPane.YES_OPTION) // Checks whether the user wishes to play again.
        {
            Game game = new Game (); // A new instance of Bulls and Cows is created.
            String guessList = "Guess History:\n"; // The list of the user's previous guesses.

            message.setGameStart ();
            userGuess = JOptionPane.showInputDialog (null, message.getContent (), message.getTitle (), JOptionPane.PLAIN_MESSAGE); // Awaits input.

            while ((userGuess != null) && !game.getHit ()) // Checks if the user did not choose to exit the game or guessed the secret number.
            {
                while ((userGuess != null) && !game.checkInput (userGuess)) // Checks whether the input is valid.
                {
                    message.setInvalidInput ();
                    userGuess = JOptionPane.showInputDialog (null, message.getContent (), message.getTitle (), JOptionPane.ERROR_MESSAGE); // A guess is entered.
                }

                if (userGuess == null) // Checks if the user did not choose to exit the game.
                {
                    break;
                }

                guessList += game.checkGuess (userGuess); // The list of previous guesses is updated.
                message.setTitle (game.getNumOfGuesses () + 1);

                if (!game.getHit ()) // The user did not guess the secret number.
                {
                    userGuess = JOptionPane.showInputDialog (null, guessList, message.getTitle (), JOptionPane.PLAIN_MESSAGE);
                }
            }

            if (game.getHit ()) // The user guessed the secret number.
            {
                message.setYouWon (guessList,game.getNumOfGuesses ()); // The list of guesses is presented and the user is offered to play again.
            }
            else
            {
                message.setPlayAgain (); // Offers to play again  if the user chose to exit the current game.
            }
            playAgain = JOptionPane.showConfirmDialog (null, message.getContent (), message.getTitle (), JOptionPane.YES_NO_OPTION);
        }

        message.setBye (); // A goodbye message.
        JOptionPane.showMessageDialog (null, message.getContent (), message.getTitle (), JOptionPane.PLAIN_MESSAGE);
    }
}

// End of class Main.
