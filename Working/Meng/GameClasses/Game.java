package GameClasses;

import java.awt.*;
import java.awt.event.*;

//import jdk.jfr.Enabled;
import javax.swing.*;

import java.util.Timer;
import java.util.TimerTask;

import java.io.*;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class Game extends JFrame implements MouseListener, ActionListener {
	private JPanel leftPanel_Game, centerPanel_Game, rightPanel_Game;
	private Piece piecePanel[];
	private JLabel lblScores, lblPlayer1, lblPlayer2, lblPlayer3, lblPlayer4, lblPlayer1Score, lblPlayer2Score,
			lblPlayer3Score, lblPlayer4Score, thetime, pieceOutofBoard, piecesOverlap;
	static boolean pieceHasBeenClicked = false, BoardButtonHasBeenClicked = false, pieceHasBeenPlaced = true;
	private static Button btnBoard[][];
	private static Button btnBoardTemp[][];
	// private JScrollPane scrollPieces;
	private Timer timer;

	private static int row;
	private static int col;
	private int seconds;
	private int piecePanelY;
	private int scores[] = new int[4];

	public static Button pieceButtonThatHasBeenClicked;
	public Button boardButtonThatHasBeenClicked;
	public Button boardButtonMouseIsOver;
	public static Piece pieceThatHasBeenClicked = null;

	public static Player Players[] = new Player[5];
	public static Color pieceColors[] = new Color[4];

	private Player currentPlayer;
	private Color tempColor;

	private JButton btnSave, btnres, btnRotate, btnFlip;
	private int playersIndex, difficulty;

	public Game(int diff) {	
		difficulty = diff;
		setTitle("Blokus");
		setSize(835, 538);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// getContentPane().setLayout(null);

		this.row = 20;
		this.col = 20;

		// .......... Panels ..........
		leftPanel_Game = new JPanel();
		leftPanel_Game.setBackground(Color.WHITE);
		leftPanel_Game.setBounds(0, 0, 155, 500);
		getContentPane().add(leftPanel_Game);
		leftPanel_Game.setLayout(null);

		centerPanel_Game = new JPanel();
		centerPanel_Game.setBackground(Color.BLUE);
		centerPanel_Game.setBounds(155, 0, 500, 500);
		getContentPane().add(centerPanel_Game);
		centerPanel_Game.setLayout(new GridLayout(row, col));

		rightPanel_Game = new JPanel();
		rightPanel_Game.setBackground(Color.WHITE);
		rightPanel_Game.setBounds(655, 420, 180, 80);
		getContentPane().add(rightPanel_Game);
		rightPanel_Game.setLayout(null);

		for (int i = 0; i < 4; i++) {
			Players[i] = new Player(Home.getPlayerColor(i));
			System.out.println(Home.getPlayerColor(i).toString());
			Players[i].setBackground(Color.LIGHT_GRAY);
			Players[i].setVisible(false);
			Players[i].setBounds(655, 0, 180, 420);
			getContentPane().add(Players[i]);
		}

		JPanel player = new Player(Color.PINK);
		player.setBackground(Color.LIGHT_GRAY);
		player.setVisible(false);
		player.setBounds(655, 0, 180, 420);
		getContentPane().add(player);

		currentPlayer = Players[0];
		currentPlayer.setVisible(true);
		// .......... Panels ..........
		

		// .......... Left Panel ..........
		pieceOutofBoard = new JLabel("Piece out of board");
		pieceOutofBoard.setFont(new Font("Dyuthi", Font.BOLD, 15));
		pieceOutofBoard.setForeground(Color.RED);
		pieceOutofBoard.setBounds(10, 10, 200, 100);
		pieceOutofBoard.setVisible(false);
		leftPanel_Game.add(pieceOutofBoard); // pieceOutofBoard piecesOverlap

		piecesOverlap = new JLabel("Pieces overlap");
		piecesOverlap.setFont(new Font("Dyuthi", Font.BOLD, 15));
		piecesOverlap.setForeground(Color.RED);
		piecesOverlap.setBounds(10, 10, 200, 100);
		piecesOverlap.setVisible(false);
		leftPanel_Game.add(piecesOverlap);

		for (int i = 0; i < 4; i++) {
			scores[i] = 0;
		}

		lblScores = new JLabel("Scores");
		lblScores.setFont(new Font("Dyuthi", Font.BOLD, 20));
		lblScores.setBounds(45, 100, 75, 20);
		leftPanel_Game.add(lblScores);

		lblPlayer1 = new JLabel("Player 1:");
		lblPlayer1.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblPlayer1.setBounds(12, 135, 75, 15);
		leftPanel_Game.add(lblPlayer1);

		lblPlayer2 = new JLabel("Player 2:");
		lblPlayer2.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblPlayer2.setBounds(12, 165, 75, 15);
		leftPanel_Game.add(lblPlayer2);

		lblPlayer3 = new JLabel("Player 3:");
		lblPlayer3.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblPlayer3.setBounds(12, 195, 75, 15);
		leftPanel_Game.add(lblPlayer3);

		lblPlayer4 = new JLabel("Player 4:");
		lblPlayer4.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblPlayer4.setBounds(12, 225, 75, 15);
		leftPanel_Game.add(lblPlayer4);

		lblPlayer1Score = new JLabel(String.valueOf(scores[0]));
		lblPlayer1Score.setBounds(90, 135, 50, 15);
		leftPanel_Game.add(lblPlayer1Score);

		lblPlayer2Score = new JLabel(String.valueOf(scores[1]));
		lblPlayer2Score.setBounds(90, 165, 50, 15);
		leftPanel_Game.add(lblPlayer2Score);

		lblPlayer3Score = new JLabel(String.valueOf(scores[2]));
		lblPlayer3Score.setBounds(90, 195, 66, 15);
		leftPanel_Game.add(lblPlayer3Score);

		lblPlayer4Score = new JLabel(String.valueOf(scores[3]));
		lblPlayer4Score.setBounds(90, 225, 66, 15);
		leftPanel_Game.add(lblPlayer4Score);

		btnSave = new JButton();
		btnSave.setText("Save Game");
		btnSave.addActionListener(this);
		btnSave.setBackground(Color.YELLOW);
		btnSave.setBounds(27, 450, 100, 35);
		leftPanel_Game.add(btnSave);

		// .......... Left Panel ..........

		// .......... Center Panel ..........
		btnBoard = new Button[row][col];
		btnBoardTemp = new Button[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				btnBoard[i][j] = new Button();
				btnBoard[i][j].setXY(i, j);
				btnBoard[i][j].setBackground(Color.WHITE);
				btnBoard[i][j].addMouseListener(this);
				btnBoard[i][j].addActionListener(this);
				centerPanel_Game.add(btnBoard[i][j]);

				btnBoardTemp[i][j] = new Button();
				btnBoardTemp[i][j].setXY(i, j);
				btnBoardTemp[i][j].setBackground(Color.WHITE);

			}
		}

		// .......... Center Panel ..........

		// .......... Right Panel ..........
		btnRotate = new JButton("Rotate");
		btnRotate.setBounds(40, 15, 100, 25);
		btnRotate.setBackground(Color.ORANGE);
		btnRotate.addMouseListener(this);
		rightPanel_Game.add(btnRotate);

		btnFlip = new JButton("Flip");
		btnFlip.setBounds(40, 50, 100, 25);
		btnFlip.setBackground(Color.CYAN);
		btnFlip.addMouseListener(this);
		rightPanel_Game.add(btnFlip);
		// .......... Right Panel ..........

		Players[0].LegalBoardPositions.add(btnBoard[0][0]);
		Players[1].LegalBoardPositions.add(btnBoard[19][0]);
		Players[2].LegalBoardPositions.add(btnBoard[19][19]);
		Players[3].LegalBoardPositions.add(btnBoard[0][19]);

		if (difficulty == 0) {
			System.out.println("hi");
			resumeBoard();
			resumePlayer();
			resumePlayerIndex();
			resumePlayerScore();
		}
		setResizable(false);
		setVisible(true);
	}

	private void updateScore(int p, int s) {

		if (p == 0) {

			scores[p] += s;
			lblPlayer1Score.setText(String.valueOf(scores[0]));
		} else if (p == 1) {
			scores[p] += s;
			lblPlayer2Score.setText(String.valueOf(scores[1]));
		} else if (p == 2) {
			scores[p] += s;
			lblPlayer3Score.setText(String.valueOf(scores[2]));
		} else if (p == 3) {
			scores[p] += s;
			lblPlayer4Score.setText(String.valueOf(scores[3]));
		}
	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

		if (pieceThatHasBeenClicked != null & !pieceHasBeenPlaced) {

			Button brdButton = (Button) e.getSource();
			boardButtonMouseIsOver = brdButton;
			tempColor = brdButton.getBackground();

			int boardBtnrowPos = boardButtonMouseIsOver.x;
			int boardBtncolPos = boardButtonMouseIsOver.y;

			int pieceBtnrowPos = pieceButtonThatHasBeenClicked.x;
			int pieceBtncolPos = pieceButtonThatHasBeenClicked.y;

			Button[][] ArrayOfPieceButtons = pieceThatHasBeenClicked.pieceButtons;

			for (int i = 0; i < ArrayOfPieceButtons.length; i++) {
				int rowDist = i - pieceBtnrowPos;
				for (int j = 0; j < ArrayOfPieceButtons[0].length; j++) {
					int colDist = j - pieceBtncolPos;
					if (ArrayOfPieceButtons[i][j].isVisible()
							& !((boardBtnrowPos + rowDist) < 0 || boardBtnrowPos + rowDist >= btnBoard.length
									|| boardBtncolPos + colDist < 0 || boardBtncolPos + colDist >= btnBoard.length)) {

						btnBoard[boardBtnrowPos + rowDist][boardBtncolPos + colDist]
								.setBackground(ArrayOfPieceButtons[i][j].getBackground());
					}

				}

			}
		}
	}

	public void mouseExited(MouseEvent e) {

		if (pieceThatHasBeenClicked != null & !pieceHasBeenPlaced) {

			Button brdButton = (Button) e.getSource();
			boardButtonMouseIsOver = brdButton;

			int boardBtnrowPos = boardButtonMouseIsOver.x;
			int boardBtncolPos = boardButtonMouseIsOver.y;

			int pieceBtnrowPos = pieceButtonThatHasBeenClicked.x;
			int pieceBtncolPos = pieceButtonThatHasBeenClicked.y;

			Button[][] ArrayOfPieceButtons = pieceThatHasBeenClicked.pieceButtons;

			for (int i = 0; i < ArrayOfPieceButtons.length; i++) {
				int rowDist = i - pieceBtnrowPos;
				for (int j = 0; j < ArrayOfPieceButtons[0].length; j++) {
					int colDist = j - pieceBtncolPos;
					if (ArrayOfPieceButtons[i][j].isVisible()
							& !((boardBtnrowPos + rowDist) < 0 || boardBtnrowPos + rowDist >= btnBoard.length
									|| boardBtncolPos + colDist < 0 || boardBtncolPos + colDist >= btnBoard.length)) {

						btnBoard[boardBtnrowPos + rowDist][boardBtncolPos + colDist].setBackground(
								btnBoardTemp[boardBtnrowPos + rowDist][boardBtncolPos + colDist].getBackground());

					}

				}
			}
		}
	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {
		//
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnSave) {
			saveBoard();
			savePlayer();
			savePlayerIndex();
			savePlayerScores();
		}

		Button brdButton = (Button) e.getSource();
		BoardButtonHasBeenClicked = true;
		boardButtonThatHasBeenClicked = brdButton;
		System.out.println("Movepiece on board");

		if (pieceHasBeenClicked) {
			try {
				HandleInValidMove();
				MovePieceOnBoard();

			}

			catch (Exception ex) {
				JOptionPane.showMessageDialog(this, ex.getMessage(), "Illegal move", JOptionPane.INFORMATION_MESSAGE);
			}

		}

	}

	public static void SaveTempBoard() {

		for (int i = 0; i < col; i++) {
			for (int j = 0; j < row; j++) {
				btnBoardTemp[i][j].setBackground(btnBoard[i][j].getBackground());
			}
		}

	}

	public void HandleInValidMove() throws Exception {

		int boardBtnrowPos = boardButtonThatHasBeenClicked.x;
		int boardBtncolPos = boardButtonThatHasBeenClicked.y;

		int pieceBtnrowPos = pieceButtonThatHasBeenClicked.x;
		int pieceBtncolPos = pieceButtonThatHasBeenClicked.y;
		Boolean cornerTouching = false;
		Boolean sideTouching = false;
		Button[][] ArrayOfPieceButtons = pieceThatHasBeenClicked.pieceButtons;

		for (int i = 0; i < ArrayOfPieceButtons.length; i++) {
			int rowDist = i - pieceBtnrowPos;
			for (int j = 0; j < ArrayOfPieceButtons[0].length; j++) {
				int colDist = j - pieceBtncolPos;
				if (ArrayOfPieceButtons[i][j].isVisible()) {

					// checks piece is on board
					if ((boardBtnrowPos + rowDist) < 0 || boardBtnrowPos + rowDist >= btnBoard.length
							|| boardBtncolPos + colDist < 0 || boardBtncolPos + colDist >= btnBoard.length) {
						throw new Exception("Piece out of board.");
					}

					// checks piece overlap
					else if (btnBoardTemp[boardBtnrowPos + rowDist][boardBtncolPos + colDist]
							.getBackground() != Color.WHITE) {
						throw new Exception("Pieces overlap!");
					}

					// checks for correct corner colors for valid placement
					if ((boardBtnrowPos + rowDist + 1 < row && boardBtncolPos + colDist + 1 < col
							&& btnBoardTemp[boardBtnrowPos + rowDist + 1][boardBtncolPos + colDist + 1]
									.getBackground() == ArrayOfPieceButtons[i][j].getBackground()
							|| boardBtnrowPos + rowDist - 1 >= 0 && boardBtncolPos + colDist + 1 < col
									&& btnBoardTemp[boardBtnrowPos + rowDist - 1][boardBtncolPos + colDist + 1]
											.getBackground() == ArrayOfPieceButtons[i][j].getBackground()
							|| boardBtnrowPos + rowDist + 1 < row && boardBtncolPos + colDist - 1 >= 0
									&& btnBoardTemp[boardBtnrowPos + rowDist + 1][boardBtncolPos + colDist - 1]
											.getBackground() == ArrayOfPieceButtons[i][j].getBackground()
							|| boardBtnrowPos + rowDist - 1 >= 0 && boardBtncolPos + colDist - 1 >= 0
									&& btnBoardTemp[boardBtnrowPos + rowDist - 1][boardBtncolPos + colDist - 1]
											.getBackground() == ArrayOfPieceButtons[i][j].getBackground())) {
						cornerTouching = true;
					}

					// checks if piece is in starting corners
					if ((boardBtnrowPos + rowDist == 0 || boardBtnrowPos + rowDist == row - 1)
							&& (boardBtncolPos + colDist == 0 || boardBtncolPos + colDist == col - 1)) {
						cornerTouching = true;
					}

					// checks for color not being beside itself
					if ((boardBtnrowPos + rowDist + 1 < row
							&& btnBoardTemp[boardBtnrowPos + rowDist + 1][boardBtncolPos + colDist]
									.getBackground() == ArrayOfPieceButtons[i][j].getBackground()
							|| boardBtnrowPos + rowDist - 1 >= 0
									&& btnBoardTemp[boardBtnrowPos + rowDist - 1][boardBtncolPos + colDist]
											.getBackground() == ArrayOfPieceButtons[i][j].getBackground()
							|| boardBtncolPos + colDist + 1 < col
									&& btnBoardTemp[boardBtnrowPos + rowDist][boardBtncolPos + colDist + 1]
											.getBackground() == ArrayOfPieceButtons[i][j].getBackground()
							|| boardBtncolPos + colDist - 1 >= 0
									&& btnBoardTemp[boardBtnrowPos + rowDist][boardBtncolPos + colDist - 1]
											.getBackground() == ArrayOfPieceButtons[i][j].getBackground())) {
						sideTouching = true;
					}

				}

			}
		}
		if (!cornerTouching) {
			throw new Exception("Corner of Piece needs to touch other piece's corner!");
		}
		if (sideTouching) {
			throw new Exception("Piece can not touch side of other piece!");
		}

	}

	public void MovePieceOnBoard() { // PiecepieceThatHasBeenClicked, int coloredButtons[][]

		// if (pieceHasBeenClicked && BoardButtonHasBeenClicked) {
		int boardBtnrowPos = boardButtonThatHasBeenClicked.x;
		int boardBtncolPos = boardButtonThatHasBeenClicked.y;

		int pieceBtnrowPos = pieceButtonThatHasBeenClicked.x;
		int pieceBtncolPos = pieceButtonThatHasBeenClicked.y;
		int pieceScore = 0;

		System.out.println("Board button x:  " + boardBtnrowPos + ", Board button y: " + boardBtncolPos);
		System.out.println("Piece button x:  " + pieceBtnrowPos + ", Piece button y: " + pieceBtncolPos);

		Button[][] ArrayOfPieceButtons = pieceThatHasBeenClicked.pieceButtons;

		for (int i = 0; i < ArrayOfPieceButtons.length; i++) {
			int rowDist = i - pieceBtnrowPos;
			for (int j = 0; j < ArrayOfPieceButtons[0].length; j++) {
				int colDist = j - pieceBtncolPos;
				if (ArrayOfPieceButtons[i][j].isVisible()) {

					btnBoard[boardBtnrowPos + rowDist][boardBtncolPos + colDist]
							.setBackground(ArrayOfPieceButtons[i][j].getBackground());
					pieceScore += 1;

				}

			}
		}

		pieceOutofBoard.setVisible(false);
		pieceThatHasBeenClicked.setVisible(false);
		pieceHasBeenClicked = false;
		BoardButtonHasBeenClicked = false;
		pieceHasBeenPlaced = true;
		SaveTempBoard();
		updateScore(currentPlayersIndex(), pieceScore);

		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				changePlayer();

			}
		};

		Timer timer = new Timer();
		timer.schedule(task, 700);

	}

	public int currentPlayersIndex() {
		for (int i = 0; i < 4; i++) {
			if (Players[i].isVisible()) {
				playersIndex = i;
				return i;
			}
		}
		return -1;
	}

	public void changePlayer() {
		int currentPlayersIndex = currentPlayersIndex();
		Players[currentPlayersIndex].setVisible(false);
		Players[(currentPlayersIndex + 1) % 4].setVisible(true);
		currentPlayer = Players[(currentPlayersIndex + 1) % 4];
	};

	public void saveBoard() {

		try (FileWriter writer = new FileWriter("save.txt"); BufferedWriter gamesave = new BufferedWriter(writer)) {

			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
//            		String rep = ("java.awt.Color"); 
//            		String fullString = btnBoard[i][j].getBackground().toString();
//            		fullString = fullString.replaceAll(rep,"");
//            		gamesave.write(fullString + '\n');
					if (btnBoard[i][j].getBackground() == Home.getPlayerColor(0)) {
						gamesave.write("1" + '\n');
					}
					if (btnBoard[i][j].getBackground() == Home.getPlayerColor(1)) {
						gamesave.write("2" + '\n');
					}
					if (btnBoard[i][j].getBackground() == Home.getPlayerColor(2)) {
						gamesave.write("3" + '\n');
					}
					if (btnBoard[i][j].getBackground() == Home.getPlayerColor(3)) {
						gamesave.write("4" + '\n');

					}
					if (btnBoard[i][j].getBackground() == Color.WHITE) {
						gamesave.write("0" + '\n');
					}

				}
			}
			;
		} catch (IOException e) {
			System.err.println("There was a problem writing to file");
		}
	}

	public void savePlayer() {
		int cp = 0;
		int p = currentPlayersIndex();
		String str1 = Integer.toString(p);

		try (FileWriter writer = new FileWriter("save1.txt"); BufferedWriter gamesave = new BufferedWriter(writer)) {
			while (cp != 4) {
				for (int i = 0; i < 21; i++) {
					Boolean v = Players[cp].piecePanels[i].isVisible();
					gamesave.write(v.toString() + '\n');
				}
				cp += 1;
			}

		} catch (IOException e) {
			System.err.println("There was a problem writing to file");
		}
	}

	public void savePlayerIndex() {
		int p = playersIndex;
		String str1 = Integer.toString(p);

		try (FileWriter writer = new FileWriter("save2.txt"); BufferedWriter gamesave = new BufferedWriter(writer)) {
			gamesave.write(str1 + "");

		} catch (IOException e) {
			System.err.println("There was a problem writing to file");
		}
	}

	public void savePlayerScores() {

		try (FileWriter writer = new FileWriter("save3.txt"); BufferedWriter gamesave = new BufferedWriter(writer)) {
			int i = 0;
			while (i != 4) {
				int ascore = scores[i];
				String stringScore = String.valueOf(ascore);
				gamesave.write(stringScore + '\n');
				i += 1;
			}

		} catch (IOException e) {
			System.err.println("There was a problem writing to file");
		}
	}

	public void resumePlayerScore() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("save3.txt"));
			String aline = reader.readLine();
			int i = 0;
			while (i != 4) {
				Integer ps = Integer.valueOf(aline);
				updateScore(i, ps);
				aline = reader.readLine();
				i += 1;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void resumePlayerIndex() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("save2.txt"));
			String aline = reader.readLine();
			int result = Integer.parseInt(aline);
			currentPlayer.setVisible(false);
			currentPlayer = Players[result];
			currentPlayer.setVisible(true);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void resumePlayer() {
		int i = 0;
		int j = 0;
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("save1.txt"));
			String aline = reader.readLine();
			while (i != 4) {
				while (j != 21) {
					boolean b1 = Boolean.parseBoolean(aline);
					Players[i].piecePanels[j].setVisible(b1);
					aline = reader.readLine();
					j += 1;
				}
				j = 0;
				i += 1;

			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void resumeBoard() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("save.txt"));
			String aline = reader.readLine();
			int i = 0;
			int j = 0;
			while (j != col) {
				while (i != row) {
					String line = (aline);
//					String[] color = line.split(",");
//					String red = color[0].replace("[r=","");
//					String green = color[1].replace("g=","");
//					String blue = color[2].replace("b=","");
//					blue = blue.replace("]","");
//
//					Integer r1 = Integer.valueOf(red);
//					Integer g1 = Integer.valueOf(green);
//					Integer b1 = Integer.valueOf(blue);
//					
//					
//					btnBoard[j][i].setBackground(new Color(r1,g1,b1));
//					btnBoard[j][i].setVisible(true);
//					
//					if (r1 == 255 && g1 == 255 && b1 == 255) {
//						btnBoard[j][i].setBackground(Color.WHITE);
//						btnBoard[j][i].setVisible(true);
//					}
//					else if (r1 == 255 && g1 == 0 && b1 == 0) {
//						btnBoard[j][i].setBackground(Color.red);
//						btnBoard[j][i].setVisible(true);
//					}
//					else if (r1 == 255 && g1 == 255 && b1 == 0) {
//						btnBoard[j][i].setBackground(Color.YELLOW);
//						btnBoard[j][i].setVisible(true);
//					}
//					
//					else if (r1 == 0 && g1 == 255 && b1 == 0) {
//						btnBoard[j][i].setBackground(Color.GREEN);
//						btnBoard[j][i].setVisible(true);
//					}
//					
//					else if (r1 == 0 && g1 == 0 && b1 == 255) {
//						btnBoard[j][i].setBackground(Color.BLUE);
//						btnBoard[j][i].setVisible(true);
//					}
					Integer pr = Integer.valueOf(aline);
					if (pr == 1) {
						btnBoard[j][i].setBackground(Home.getPlayerColor(0));
					} else if (pr == 2) {
						btnBoard[j][i].setBackground(Home.getPlayerColor(1));
					} else if (pr == 3) {
						btnBoard[j][i].setBackground(Home.getPlayerColor(2));
					} else if (pr == 4) {
						btnBoard[j][i].setBackground(Home.getPlayerColor(3));
					} else if (pr == 0) {
						btnBoard[j][i].setBackground(Color.WHITE);
					}

					i += 1;
					aline = reader.readLine();
				}
				i = 0;
				j += 1;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static class IllegalMoveException extends Exception {
		public IllegalMoveException() {
			super();
		}

//	      public void HandleIllegalMoveException(String message)
//	      {
//	    	  JOptionPane.showMessageDialog(gridWindow, message, "Illegal move",  JOptionPane.INFORMATION_MESSAGE);
//	      }
	}

}



