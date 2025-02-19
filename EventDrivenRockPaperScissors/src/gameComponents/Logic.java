package gameComponents;

public class Logic {
		
		public static String winner(int playerWins, int computerWins, String playerName, String computerName) {
			if(playerWins > computerWins)
				return (playerName + " is the winner. " + playerName + " won " + playerWins + " round(s) of out 10");
			else if(computerWins > playerWins)
				return (computerName + " is the winner. " + computerName + " won " + computerWins + " round(s) of out 10");
			return ("There is no winner. Both" + playerName + " and " + computerName + " got " + playerWins +  " wins");
		}
		

	}

