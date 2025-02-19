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
