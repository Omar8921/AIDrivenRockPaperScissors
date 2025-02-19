package gameComponents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Computer extends Player {
	private Map<String, Map<String, Integer>> transitionMatrix;
	private Random random;

	public Computer() {
		random = new Random();

		transitionMatrix = new HashMap<>();
		transitionMatrix.put("rock", new HashMap<>());
		transitionMatrix.put("paper", new HashMap<>());
		transitionMatrix.put("scissors", new HashMap<>());

		for (String key : transitionMatrix.keySet()) {
			transitionMatrix.get(key).put("rock", 0);
			transitionMatrix.get(key).put("paper", 0);
			transitionMatrix.get(key).put("scissors", 0);

		}
		// create an array containing names that are used for the computer bot
		String[] names = new String[] { "Ahmad", "Leen", "Sami", "Hamza", "Reem", "Mohammad", "Adel", "Omar", "Eman",
				"Zeina" };
		setName(names[random.nextInt(names.length)]);
	}

	public Map<String, Map<String, Integer>> getTransitionMatrix() {
		return transitionMatrix;
	}

	public void setTransitionMatrix(Map<String, Map<String, Integer>> transitionMatrix) {
		this.transitionMatrix = transitionMatrix;
	}

	// this method is used after each round to update the transitions in the
	// transition matrix
	public Map<String, Map<String, Integer>> updateMatrix(String playerLastMove, String playerCurrentMove,
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

	public String predictPlayerMove(String lastMove) {
		// if the player has not played any move yet (first round), the computer just
		// returns a random move
		if (lastMove == null) {
			return randomMove();
		}

		Map<String, Integer> nextMoves = transitionMatrix.get(lastMove);
		int total = nextMoves.values().stream().mapToInt(Integer::intValue).sum();

		if (total == 0) {
			// If no transitions recorded, return a random choice
			return randomMove();
		}

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

	// Generates the computer move based on the predicted player move
	public String getComputerMove(String playerLastMove) {
		// call the predictPlayerMove method that returns the predicted move based on
		// the last move by the player
		String predictedPlayerMove = predictPlayerMove(playerLastMove);
		// if the predicted move is rock, computer's move is paper
		if (predictedPlayerMove == "rock")
			return "paper";
		// if the predicted move is paper, computer's move is scissors
		else if (predictedPlayerMove == "paper")
			return "scissors";
		// by default, return rock as computer's move, which is the only condition left
		return "rock";
	}

	// this method returns a random move for the computer when it is called
	private String randomMove() {
		String[] moves = { "rock", "paper", "scissors" };
		return moves[random.nextInt(moves.length)];
	}

	public void setName(String name) {
		this.name = name;
	}

}
