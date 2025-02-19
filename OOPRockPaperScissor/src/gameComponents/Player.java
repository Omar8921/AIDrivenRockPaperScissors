package gameComponents;

public class Player {
	protected String name;
	protected String currentMove;
	protected String lastMove;
	protected int wins;

	public Player() {
		name = null;
		currentMove = null;
		lastMove = null;
		wins = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean validateName(String name) {
		return name.matches("[a-zA-Z]+");
	}

	public boolean validateName(String name, int minLength, int maxLength) {
		return name.matches("[a-zA-Z]+") && name.length() >= minLength && name.length() <= maxLength;
	}

	public void incrementWins() {
		wins++;
	}

	public String getLastMove() {
		return lastMove;
	}

	public void setLastMove(String currentMove) {
		this.lastMove = currentMove;
	}

	public String getCurrentMove() {
		return currentMove;
	}

	public int getWins() {
		return wins;
	}

	public void setCurrentMove(String currentMove) {
		this.currentMove = currentMove;
	}
}
