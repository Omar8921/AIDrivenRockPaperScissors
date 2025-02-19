package gameComponents;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

public class Logic {

	private static Scanner scanner;
	private static Player player;
	private static Computer computer;
	private static Menu menu;
	private static int roundNumber;
	private static int numOfRounds;

	public Logic() {
		scanner = new Scanner(System.in);
		player = new Player();
		computer = new Computer();
		menu = new Menu();
		roundNumber = 1;
		numOfRounds = 10;
	}

	// This method takes three parameters, then computes the winner of the game

	public static String determineWinner(int playerWins, int computerWins, int numOfRounds) {
		if (playerWins > computerWins) {
			return "player";
		} else if (playerWins < computerWins) {
			return "computer";
		}
		return "draw";
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

	public static void startGame() {
		menu.greet();
		menu.displayOptionsMenu(false);

		int choice = scanner.nextInt();

		while (true) {
			if (choice == 1)
				break;
			else if (choice == 2) {
				menu.goodBye();
				System.exit(0);
			} else {
				menu.displayOptionsMenu(true);
				choice = scanner.nextInt();
			}
		}

		menu.askForPlayerName();
		String playerName = scanner.next();
		player.setName(playerName);

		while (!player.validateName(playerName)) {
			menu.askForPlayerName();
			playerName = scanner.next();
			player.setName(playerName);
		}

		scanner.nextLine();

		while (roundNumber <= numOfRounds) {
			menu.askForPlayerMove(player.getName());
			while (true) {
				String playerMove = scanner.next().toLowerCase();
				// get the player move, and keep asking for the move until the user enters a
				// valid move
				if (playerMove.equals("rock") || playerMove.equals("paper") || playerMove.equals("scissors")) {
					player.setCurrentMove(playerMove);
					break;
				}
				menu.askForPlayerMove(playerName);
			}

			String computerMove = computer.getComputerMove(player.getLastMove());
			computer.setCurrentMove(computerMove);

			String winner = decideRoundWinner(player.getCurrentMove(), computerMove, player.getName(),
					computer.getName());

			// increment the wins of either the player of the computer based on the winner
			if (winner == player.getName())
				player.incrementWins();
			else if (winner == computer.getName())
				computer.incrementWins();

			menu.displayRoundMoves(player.getCurrentMove(), computer.getCurrentMove());
			menu.displayRoundWinner(winner, roundNumber);
			menu.printSeparator();

			Map<String, Map<String, Integer>> transMatrix = computer.updateMatrix(player.getLastMove(),
					player.getCurrentMove(), computer.getTransitionMatrix());
			computer.setTransitionMatrix(transMatrix);

			// update the last move of the next round to be the current move of the current
			// round
			player.setLastMove(player.getCurrentMove());
			roundNumber++;

		}

		menu.displayResults(player.getWins(), player.getName(), computer.getName(), computer.getWins(), numOfRounds);
		menu.gameOver();

		menu.printSeparator();

	}
}
