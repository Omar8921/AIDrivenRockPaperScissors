package gameComponents;

public class Menu {

	public void greet() {
		System.out.println("Welcome to rock paper scissors Game!");
	}

	public void displayOptionsMenu(boolean flag) {
		if (!flag) {
			System.out.println("1. Start Game");
			System.out.println("2. Exit Game");
		} else {
			System.out.println("Please choose either 1 or 2");
			System.out.println("1. Start Game");
			System.out.println("2. Exit Game");
		}
	}

	public void askForPlayerName() {
		System.out.println("Please enter your name (letters only): ");
	}

	public void gameOver() {
		System.out.println("Game Over. Good Game!");
	}

	public void displayRoundWinner(String winner, int roundNumber) {
		System.out.println("Winner of round #" + roundNumber + " is: " + winner);
	}

	// display the final results of the game.
	public void displayResults(int playerWins, String playerName, String computerName, int computerWins,
			int numOfRounds) {
		if (Logic.determineWinner(playerWins, computerWins, numOfRounds) == "player") {
			System.out.println(playerName + " is the winner. " + playerName + " won " + playerWins + " out of "
					+ numOfRounds + " rounds.");
		} else if (Logic.determineWinner(playerWins, computerWins, numOfRounds) == "computer") {
			System.out.println(computerName + " is the winner. " + computerName + " won " + computerWins + " out of "
					+ numOfRounds + " rounds.");
		} else {
			System.out.println("Draw");
			System.out.println(playerName + " won " + playerWins + " round(s).");
			System.out.println(computerName + " won " + computerWins + " round(s).");
		}
	}

	public void askForPlayerMove(String playerName) {
		System.out.println(playerName + ", choose your move (rock, paper, or scissors): ");
	}

	public void introduceComputer(String computerName) {
		System.out.println("Computer Bot Name: " + computerName);
	}

	// display the moves of the round so that the player knows what he played and
	// what the computer played.
	// substring is used to capitalize the first letter of the moves each player
	// chose
	// substring(0,1) returns a string that starts at the 0 index (inclusive), and
	// ends at the 1 index (exclusive), so basically it
	// returns the first letter of the move, then the letter is capitalized
	// substring(1) is used to return a string that starts at index 1, and continues
	// to the end of the string
	public void displayRoundMoves(String playerMove, String computerMove) {
		System.out.println("You chose: " + (playerMove.substring(0, 1).toUpperCase() + playerMove.substring(1)));
		System.out
				.println("Computer chose: " + (computerMove.substring(0, 1).toUpperCase() + computerMove.substring(1)));
	}

	public void printSeparator() {
		System.out.println("==================================");
	}

	public void goodBye() {
		System.out.println("Have a nice day!");
	}

}
