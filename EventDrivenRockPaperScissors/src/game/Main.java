package game;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gameComponents.Computer;
import gameComponents.Player;
import javax.swing.*;
import gameComponents.Logic;

public class Main {
	static int roundNumber = 1;

	public static void main(String[] args) {
		Player player = new Player();
		Computer computer = new Computer();		
		
		JFrame gameWindow = new JFrame();
		gameWindow.setSize(800, 600);
		gameWindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		gameWindow.setVisible(false);
		gameWindow.getContentPane().setBackground(Color.decode("#96ceb3"));

		JLabel chooseMoveLabel = new JLabel();
		chooseMoveLabel.setBounds(500, 50, 400, 150);
		Font font = chooseMoveLabel.getFont();
		chooseMoveLabel.setFont(font.deriveFont(Font.BOLD | Font.ITALIC, 14f));
		gameWindow.add(chooseMoveLabel);

		gameWindow.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				chooseMoveLabel.setText(player.getName() + ", choose your move");
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		JLabel computerMoveLabel = new JLabel();
		computerMoveLabel.setBounds(400, 200, 300, 100);
		gameWindow.add(computerMoveLabel);

		JLabel playerMoveLabel = new JLabel();
		playerMoveLabel.setBounds(400, 300, 300, 100);
		gameWindow.add(playerMoveLabel);

		JLabel roundResultLabel = new JLabel();
		roundResultLabel.setBounds(400, 400, 300, 100);
		gameWindow.add(roundResultLabel);

		JButton rockButton = new JButton("Rock");
		rockButton.setBounds(100, 80, 100, 100);
		gameWindow.add(rockButton);
		rockButton.setBackground(Color.decode("#ffeeae"));
		rockButton.setForeground(Color.decode("#fe6f69"));
		font = rockButton.getFont();
		rockButton.setFont(font.deriveFont(Font.BOLD, 14f));

		rockButton.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				rockButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		rockButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (roundNumber == 10) {
					gameWindow.hide();
					JOptionPane.showConfirmDialog(null, Logic.winner(player.getWins(), computer.getWins(), player.getName(), computer.getName()) , "Congrajulations!", JOptionPane.PLAIN_MESSAGE);
					System.exit(0);
				}

			}
		});

		rockButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				computer.setCurrentMove(computer.getComputerMove(player.getLastMove()));
				player.setCurrentMove("rock");
				computerMoveLabel.setText(computer.getName() + " chose: " + computer.getCurrentMove());
				playerMoveLabel.setText("You chose: " + player.getCurrentMove());

				if (computer.getCurrentMove() == "rock") 
					roundResultLabel.setText("Draw");
				else if (computer.getCurrentMove() == "paper") {
					roundResultLabel.setText(computer.getName() + " won round # " + roundNumber);
					computer.incrementWins();
				}
				else if (computer.getCurrentMove() == "scissors") {
					roundResultLabel.setText(player.getName() + " won round # " + roundNumber);
					player.incrementWins();
				}

				computer.setTransitionMatrix(computer.updateMatrix(player.getLastMove(), player.getCurrentMove(),
						computer.getTransitionMatrix()));
				player.setLastMove(player.getCurrentMove());

				roundNumber += 1;
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				rockButton.setBackground(Color.decode("#ffeeae"));

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				rockButton.setBackground(Color.decode("#fce48e"));

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		JButton paperButton = new JButton("Paper");
		paperButton.setBounds(100, 200, 100, 100);
		gameWindow.add(paperButton);
		paperButton.setBackground(Color.decode("#ffeeae"));
		paperButton.setForeground(Color.decode("#fe6f69"));
		font = paperButton.getFont();
		paperButton.setFont(font.deriveFont(Font.BOLD, 14f));

		paperButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (roundNumber == 10) {
					gameWindow.hide();
					JOptionPane.showConfirmDialog(null, Logic.winner(player.getWins(), computer.getWins(), player.getName(), computer.getName()) , "Congrajulations!", JOptionPane.PLAIN_MESSAGE);
					System.exit(0);
				}

			}
		});

		paperButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				computer.setCurrentMove(computer.getComputerMove(player.getLastMove()));
				player.setCurrentMove("paper");

				computerMoveLabel.setText(computer.getName() + " chose: " + computer.getCurrentMove());
				playerMoveLabel.setText("You chose: " + player.getCurrentMove());

				if (computer.getCurrentMove() == "paper")
					roundResultLabel.setText("Draw");
				else if (computer.getCurrentMove() == "scissors"){
					roundResultLabel.setText(computer.getName() + " won round # " + roundNumber);
					computer.incrementWins();
				}
				else if (computer.getCurrentMove() == "rock"){
					roundResultLabel.setText(player.getName() + " won round # " + roundNumber);
					player.incrementWins();
				}

				computer.setTransitionMatrix(computer.updateMatrix(player.getLastMove(), player.getCurrentMove(),
						computer.getTransitionMatrix()));

				player.setLastMove(player.getCurrentMove());

				roundNumber += 1;

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				paperButton.setBackground(Color.decode("#ffeeae"));

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				paperButton.setBackground(Color.decode("#fce48e"));

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		paperButton.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				paperButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		JButton scissorsButton = new JButton("Scissors");
		scissorsButton.setBounds(100, 320, 100, 100);
		scissorsButton.setBackground(Color.decode("#ffeeae"));
		scissorsButton.setForeground(Color.decode("#fe6f69"));
		font = scissorsButton.getFont();
		scissorsButton.setFont(font.deriveFont(Font.BOLD, 14f));

		gameWindow.add(scissorsButton);

		scissorsButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (roundNumber == 10) {
					gameWindow.hide();
					JOptionPane.showConfirmDialog(null, Logic.winner(player.getWins(), computer.getWins(), player.getName(), computer.getName()) , "Congrajulations!", JOptionPane.PLAIN_MESSAGE);
					System.exit(0);
				}

			}
		});

		scissorsButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				computer.setCurrentMove(computer.getComputerMove(player.getLastMove()));
				player.setCurrentMove("scissors");
				computerMoveLabel.setText(computer.getName() + " chose: " + computer.getCurrentMove());
				playerMoveLabel.setText("You chose: " + player.getCurrentMove());

				if (computer.getCurrentMove() == "scissors")
					roundResultLabel.setText("Draw");
				else if (computer.getCurrentMove() == "rock"){
					roundResultLabel.setText(computer.getName() + " won round # " + roundNumber);
					computer.incrementWins();
				}
				else if (computer.getCurrentMove() == "paper"){
					roundResultLabel.setText(computer.getName() + " won round # " + roundNumber);
					computer.incrementWins();
				}

				computer.setTransitionMatrix(computer.updateMatrix(player.getLastMove(), player.getCurrentMove(),
						computer.getTransitionMatrix()));
				player.setLastMove(player.getCurrentMove());

				roundNumber += 1;

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				scissorsButton.setBackground(Color.decode("#ffeeae"));

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				scissorsButton.setBackground(Color.decode("#fce48e"));

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		scissorsButton.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				scissorsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		gameWindow.setLayout(null);

		JFrame playerNameWindow = new JFrame();
		playerNameWindow.setSize(800, 400);
		playerNameWindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		playerNameWindow.getContentPane().setBackground(Color.decode("#96ceb3"));

		JLabel enterNameLabel = new JLabel("Please enter your name");
		font = enterNameLabel.getFont();
		enterNameLabel.setFont(font.deriveFont(Font.ITALIC | Font.BOLD, 18f));
		enterNameLabel.setBounds(25, 30, 250, 100);

		JLabel errorLabel = new JLabel("Please only use letters in your name, and no spaces");
		errorLabel.setBounds(400, 100, 320, 100);
		errorLabel.setForeground(Color.decode("#fe6f69"));
		font = errorLabel.getFont();
		errorLabel.setFont(font.deriveFont(Font.ITALIC | Font.BOLD, 13f));
		playerNameWindow.add(errorLabel);
		JTextField nameInput = new JTextField();
		nameInput.setBounds(25, 150, 250, 30);

		nameInput.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (nameInput.getText().matches("[a-zA-Z]+")) {
					errorLabel.setVisible(false);
				} else {
					errorLabel.setVisible(true);
				}

				if (e.getKeyCode() == KeyEvent.VK_ENTER && !errorLabel.isVisible()) {
					player.setName(nameInput.getText());
					playerNameWindow.setVisible(false);
					gameWindow.setVisible(true);
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
		JButton submitNameButton = new JButton("Submit");
		submitNameButton.setBounds(280, 150, 100, 30);
		submitNameButton.setBackground(Color.decode("#ffeeae"));
		submitNameButton.setForeground(Color.decode("#fe6f69"));

		playerNameWindow.add(enterNameLabel);
		playerNameWindow.add(nameInput);
		playerNameWindow.add(submitNameButton);
		playerNameWindow.setLayout(null);
		submitNameButton.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				submitNameButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		submitNameButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				if (errorLabel.isVisible() == false) {
					player.setName(nameInput.getText());
					playerNameWindow.setVisible(false);
					gameWindow.setVisible(true);
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				submitNameButton.setBackground(Color.decode("#ffeeae"));

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				submitNameButton.setBackground(Color.decode("#fce48e"));

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		JFrame mainWindow = new JFrame("Rock Paper Scissors");
		mainWindow.setVisible(true);
		mainWindow.setSize(800, 600);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.getContentPane().setBackground(Color.decode("#96ceb3"));

		JLabel startLabel = new JLabel("Welcome to Rock Paper Scissors Game!");
		startLabel.setBounds(250, 100, 500, 50);
		mainWindow.add(startLabel);

		font = startLabel.getFont();
		startLabel.setFont(font.deriveFont(Font.BOLD, 20f));

		JButton startGameButton = new JButton("Start Game");
		mainWindow.add(startGameButton);
		startGameButton.setBounds(200, 300, 150, 100);
		startGameButton.setBackground(Color.decode("#ffeeae"));
		startGameButton.setForeground(Color.decode("#fe6f69"));

		font = startGameButton.getFont();
		startGameButton.setFont(font.deriveFont(Font.ITALIC | Font.BOLD, 18f));

		startGameButton.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				startGameButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		startGameButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				playerNameWindow.setVisible(true);
			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {
				startGameButton.setBackground(Color.decode("#ffeeae"));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				startGameButton.setBackground(Color.decode("#fce48e"));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		JButton exitGameButton = new JButton("Exit Button");
		mainWindow.add(exitGameButton);
		exitGameButton.setBounds(400, 300, 150, 100);
		font = exitGameButton.getFont();
		exitGameButton.setFont(font.deriveFont(Font.ITALIC | Font.BOLD, 18f));
		exitGameButton.setBackground(Color.decode("#ffeeae"));
		exitGameButton.setForeground(Color.decode("#fe6f69"));

		exitGameButton.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				exitGameButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		exitGameButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				System.exit(0);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				exitGameButton.setBackground(Color.decode("#ffeeae"));

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				exitGameButton.setBackground(Color.decode("#fce48e"));

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		mainWindow.setLayout(null);

	}

}
