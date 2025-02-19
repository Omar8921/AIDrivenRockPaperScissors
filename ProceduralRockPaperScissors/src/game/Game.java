package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Random;

public class Game {
	// create a random object to use it in generating some moves for the computer
	static Random random = new Random();

	public static void greet() {
		System.out.println("Welcome to rock paper scissors Game!");
	}

	// The flag is used to check if the user has entered an invalid value yet. If
	// the flag is false, it means that the user has not entered
	// any invalid value yet. If the flag is true, it means that the user has ented
	// an invalid value
	static void displayOptionsMenu(boolean flag) {
		if (!flag) {
			System.out.println("1. Start Game");
			System.out.println("2. Exit Game");
		} else {
			System.out.println("Please choose either 1 or 2");
			System.out.println("1. Start Game");
			System.out.println("2. Exit Game");
		}
	}

	// Choose a name for the computer bot, because it more fun :)
	static String setComputerName() {
		// create an array containing names that are used for the computer bot
		String[] names = new String[] { "Ahmad", "Leen", "Sami", "Hamza", "Reem", "Mohammad", "Adel", "Omar", "Eman",
				"Zeina" };
		return names[random.nextInt(names.length)];
	}

	public static void askForPlayerName() {
		System.out.println("Please enter your name (letters only): ");
	}

	public static void gameOver() {
		System.out.println("Game Over. Good Game!");
	}

	// This method takes three parameters, then computes the winner of the game
	public static String determineWinner(int playerWins, int computerWins) {
		if (playerWins > computerWins) {
			return "player";
		} else if (playerWins < computerWins) {
			return "computer";
		}
		return "draw";
	}

	static void displayRoundWinner(String winner, int roundNumber) {
		if (winner == "draw") {
			System.out.println("Draw");
		} else
			System.out.println("Winner of round #" + roundNumber + " is: " + winner);
	}

	public static void displayResults(int playerWins, String playerName, String computerName, int computerWins,
			int numOfRounds) {
		if (determineWinner(playerWins, computerWins) == "player") {
			System.out.println(playerName + " is the winner. " + playerName + " won " + playerWins + " out of "
					+ numOfRounds + " rounds.");
		} else if (determineWinner(playerWins, computerWins) == "computer") {
			System.out.println(computerName + " is the winner. " + computerName + " won " + computerWins + " out of "
					+ numOfRounds + " rounds.");
		} else {
			System.out.println("Draw");
			System.out.println(playerName + " won " + playerWins + " round(s).");
			System.out.println(computerName + " won " + computerWins + " round(s).");
		}
	}

	static void askForPlayerMove(String playerName) {
		System.out.println(playerName + ", choose your move (rock, paper, or scissors): ");
	}

	// Validates the name, if the name contains any character other than letters, it
	// returns false
	static boolean nameIsValid(String playerName) {
		if (playerName.matches("[a-zA-Z]+"))
			return true;
		return false;

	}

	// Generates the computer move based on the predicted player move
	static String getComputerMove(String playerLastMove, Map<String, Map<String, Integer>> transitionMatrix) {
		// call the predictPlayerMove method that returns the predicted move based on
		// the last move by the player
		String predictedMove = predictPlayerMove(playerLastMove, transitionMatrix);
		// if the predicted move is rock, computer's move is paper
		if (predictedMove == "rock")
			return "paper";
		else if (predictedMove == "paper")
			// if the predicted move is paper, computer's move is scissors
			return "scissors";
		// by default, return rock as computer's move, which is the only condition left
		return "rock";
	}

	static String predictPlayerMove(String playerLastMove, Map<String, Map<String, Integer>> transitionMatrix) {

		// if the player has not played any move yet (first round), the computer just
		// returns a random move
		if (playerLastMove == null) {
			return randomMove();
		}

		Map<String, Integer> nextMoves = transitionMatrix.get(playerLastMove);
		int total = nextMoves.values().stream().mapToInt(Integer::intValue).sum();

		if (total == 0)
			return randomMove();

		// declare and initialize variables to predict the next move, predicted move is
		// the value to return, max count is used to find the
		// highest possibility
		String predictedMove = null;
		int maxCount = -1;

		// this for each loop iterates over the number of moves played after a specific
		// move. Let's say that the last move was rock. This
		// loop will check how many rocks were played when the last move was rock, how
		// many papers were played when the last move was
		// rock, and how many scissors were played when the last move was rock. It
		// stores the highest count in the variable maxCount, and
		// stores the predicted move, based on maxCount, in a variable called
		// predictedMove
		for (Map.Entry<String, Integer> entry : nextMoves.entrySet()) {
			if (entry.getValue() > maxCount) {
				maxCount = entry.getValue();
				predictedMove = entry.getKey();
			}
		}

		// Let's say for example that you played 2 rocks, 2 papers, and 2 scissors, and
		// the computer wants to predict the next move. Here,
		// it will predict rock, because rock comes first in the entrySet, and this is
		// inaccurate. To solve this problem, this array list
		// will store the moves that share the same maxCount, then choose a random move
		// out of them
		ArrayList<String> possibleMoves = new ArrayList<>();

		// for each move, it will check if its value (count) is equal to the max count,
		// and add it to the array list if the condition is
		// true
		for (Map.Entry<String, Integer> entry : nextMoves.entrySet()) {
			if (entry.getValue() == maxCount) {
				possibleMoves.add(entry.getKey());
			}
		}

		// there will be one element in the array list at least, which is the element
		// with the maxCount. If there are any elements other
		// than this element, it will return a random element out of them
		if (possibleMoves.size() > 1)
			return possibleMoves.get(random.nextInt(possibleMoves.size()));

		// Returns the predicted move after checking for maxCount and there were no
		// elements sharing the same maxCount
		return predictedMove;
	}

	static void initializeTransitionMatrix(Map<String, Map<String, Integer>> transitionMatrix) {
		// initializes keys in the matrix, which are rock paper and scissors
		transitionMatrix.put("rock", new HashMap<>());
		transitionMatrix.put("paper", new HashMap<>());
		transitionMatrix.put("scissors", new HashMap<>());

		// initializes the transitions for each key. When initializing the matrix, there
		// will be no transition yet. So, the transition, for
		// example, from rock to paper, will be zero, the same applies to all other
		// possible transitions
		for (String key : transitionMatrix.keySet()) {
			transitionMatrix.get(key).put("rock", 0);
			transitionMatrix.get(key).put("paper", 0);
			transitionMatrix.get(key).put("scissors", 0);
		}
	}

	// this method returns a random move for the computer when it is called
	static String randomMove() {
		String[] moves = { "rock", "paper", "scissors" };
		return moves[random.nextInt(moves.length)];
	}

	static String decideRoundWinner(String playerMove, String computerMove, String playerName, String computerName) {
		// in each round, if the moves of the computer and player are the same, that
		// means it is a draw
		if (playerMove.equals(computerMove))
			return "draw";

		// these are the win conditions for the player, if the pair of moves of the
		// player and computer match any of these win conditions,
		// the player is the winner
		String[][] playerWinConditions = new String[][] { { "rock", "scissors" }, { "scissors", "paper" },
				{ "paper", "rock" } };
		String[] currentMoves = new String[] { playerMove, computerMove };

		// iterates over the win conditions, and checks if the current moves match any
		// win conditions
		for (String[] winCondition : playerWinConditions) {
			if (Arrays.equals(winCondition, currentMoves))
				return playerName;
		}

		// return the computer as a winner if there is no draw and no match in the
		// player win condition
		return computerName;

	}

	static void introduceComputer(String computerName) {
		System.out.println("Computer Bot Name: " + computerName);
	}

	static void displayRoundMoves(String playerMove, String computerMove) {
		// display the moves of the round so that the player knows what he played and
		// what the computer played.
		// substring is used to capitalize the first letter of the moves each player
		// chose
		// substring(0,1) returns a string that starts at the 0 index (inclusive), and
		// ends at the 1 index (exclusive), so basically it
		// returns the first letter of the move, then the letter is capitalized
		// substring(1) is used to return a string that starts at index 1, and continues
		// to the end of the string
		System.out.println("You chose: " + (playerMove.substring(0, 1).toUpperCase() + playerMove.substring(1)));
		System.out
				.println("Computer chose: " + (computerMove.substring(0, 1).toUpperCase() + computerMove.substring(1)));
	}

	public static void printSeparator() {
		System.out.println("==================================");
	}

	// this method is used after each round to update the transitions in the
	// transition matrix
	static Map<String, Map<String, Integer>> updateMatrix(String playerLastMove, String playerCurrentMove,
			Map<String, Map<String, Integer>> transitionMatrix) {
		// if this is not the first round:
		if (playerLastMove != null) {
			// create a transition map that has containing the transitions from the player
			// last move to the other moves
			Map<String, Integer> transitions = transitionMatrix.get(playerLastMove);
			// update the transition matrix by incrementing the count of the current move by
			// one
			transitions.put(playerCurrentMove, transitions.get(playerCurrentMove) + 1);
		}

		return transitionMatrix;
	}

	public static void main(String[] args) {

		String playerName;
		String computerName = setComputerName();
		String playerLastMove = null;
		Scanner scanner = new Scanner(System.in);

		int numOfRounds = 10;
		int roundNumber = 1;
		int playerWins = 0;
		int computerWins = 0;

		Map<String, Map<String, Integer>> transitionMatrix;
		transitionMatrix = new HashMap<>();
		initializeTransitionMatrix(transitionMatrix);

		greet();

		displayOptionsMenu(false);
		int choice = scanner.nextInt();

		while (true) {
			if (choice == 1)
				break;
			else if (choice == 2) {
				System.out.println("Have a nice day!");
				System.exit(0);
			} else {
				displayOptionsMenu(true);
				choice = scanner.nextInt();
			}
		}

		askForPlayerName();
		scanner.nextLine();
		playerName = scanner.nextLine();

		while (!nameIsValid(playerName)) {
			playerName = scanner.nextLine();
			askForPlayerName();
		}

		introduceComputer(computerName);

		while (roundNumber <= numOfRounds) {
			String playerMove = null;
			askForPlayerMove(playerName);

			// get the player move, and keep asking for the move until the user enters a
			// valid move
			while (true) {
				playerMove = scanner.next().toLowerCase();
				if (!playerMove.equals("rock") && !playerMove.equals("paper") && !playerMove.equals("scissors")) {
					askForPlayerMove(playerName);
					continue;
				}
				break;
			}

			String computerMove = getComputerMove(playerLastMove, transitionMatrix);
			String winner = decideRoundWinner(playerMove, computerMove, playerName, computerName);

			// increment the wins of either the player of the computer based on the winner
			if (winner == playerName)
				playerWins++;
			else if (winner == computerName)
				computerWins++;

			displayRoundMoves(playerMove, computerMove);
			displayRoundWinner(winner, roundNumber);
			printSeparator();
			transitionMatrix = updateMatrix(playerLastMove, playerMove, transitionMatrix);

			// update the last move of the next round to be the current move of the current
			// round
			playerLastMove = playerMove;
			roundNumber++;

		}

		displayResults(playerWins, playerName, computerName, computerWins, numOfRounds);
		gameOver();
		printSeparator();

	}

}
