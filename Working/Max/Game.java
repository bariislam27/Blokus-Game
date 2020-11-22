//package GameClasses;

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
	public static int listOfValidMoves[][] = new int[20][20];

	public static Button pieceButtonThatHasBeenClicked;
	public Button boardButtonThatHasBeenClicked;
	public Button boardButtonMouseIsOver;
	public static Piece pieceThatHasBeenClicked = null;

	public static Player Players[] = new Player[5];
	public static Color pieceColors[] = new Color[4];

	private static Player currentPlayer;
	private Color tempColor;
	public static Boolean showHints = true;
	private JButton btnSave, btnres, btnRotate, btnFlip;
	private int playersIndex, difficulty, resume;

	public Game(int res, boolean hints, int diff) {
		System.out.println(res + " " + hints + " " + diff);
		resume = res;
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
		btnRotate.addActionListener(this);
		rightPanel_Game.add(btnRotate);

		btnFlip = new JButton("Flip");
		btnFlip.setBounds(40, 50, 100, 25);
		btnFlip.setBackground(Color.CYAN);
		btnFlip.addActionListener(this);
		rightPanel_Game.add(btnFlip);
		// .......... Right Panel ..........

		Players[0].LegalBoardPositions.add(btnBoard[0][0]);
		Players[1].LegalBoardPositions.add(btnBoard[19][0]);
		Players[2].LegalBoardPositions.add(btnBoard[19][19]);
		Players[3].LegalBoardPositions.add(btnBoard[0][19]);

		if (resume == 0) {
			resumeBoard();
			resumePlayer();
			resumePlayerIndex();
			resumePlayerScore();
		}
		setResizable(false);
		setVisible(true);
	}

	// ------------------------------------Game Methods
	// Start---------------------------------------

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

	public static void SaveTempBoard() {

		for (int i = 0; i < col; i++) {
			for (int j = 0; j < row; j++) {
				btnBoardTemp[i][j].setBackground(btnBoard[i][j].getBackground());
			}
		}

	}

	public static void ShowHints() {
		int[][] tempValidMoves = GetValidMoves(pieceButtonThatHasBeenClicked, pieceThatHasBeenClicked);
		for (int x = 0; x < 20; x++) {
			for (int y = 0; y < 20; y++) {
				if (tempValidMoves[x][y] == 1) {
					PlaceHintOnBoard(x, y, pieceButtonThatHasBeenClicked);
				}
			}
		}

	}

	public static int[][] GetValidMoves(Button tempButton, Piece piece) {

		for (int x = 0; x < 20; x++) {
			for (int y = 0; y < 20; y++) {
				if (IsMoveValid(x, y, tempButton, piece)) {
					listOfValidMoves[x][y] = 1;
				} else {
					listOfValidMoves[x][y] = 0;
				}

			}

		}

		return listOfValidMoves;

	}

	// ------------------------------------Piece Moving and
	// Errors---------------------------------------

	public static void TurnHintsOn() {
		showHints = true;
	}

	public static void TurnHintsOff() {
		showHints = false;
	}

	public static void PlaceHintOnBoard(int x, int y, Button ClickedButton) {

		if (showHints == true) {

			int boardBtnrowPos = x;
			int boardBtncolPos = y;

			int pieceBtnrowPos = ClickedButton.x;
			int pieceBtncolPos = ClickedButton.y;

			Button[][] ArrayOfPieceButtons = pieceThatHasBeenClicked.pieceButtons;

			for (int i = 0; i < ArrayOfPieceButtons.length; i++) {
				int rowDist = i - pieceBtnrowPos;
				for (int j = 0; j < ArrayOfPieceButtons[0].length; j++) {
					int colDist = j - pieceBtncolPos;
					if (ArrayOfPieceButtons[i][j].isVisible()) {

						btnBoard[boardBtnrowPos + rowDist][boardBtncolPos + colDist].setBackground(Color.DARK_GRAY);

					}

				}
			}

		}
		SaveTempBoard();
	}

	public static Boolean IsMoveValid(int x, int y, Button testButton, Piece testPiece) {

		int boardBtnrowPos = x;
		int boardBtncolPos = y;

		int pieceBtnrowPos = testButton.x;
		int pieceBtncolPos = testButton.y;
		Boolean cornerTouching = false;
		Boolean sideTouching = false;
		Button[][] ArrayOfPieceButtons = testPiece.pieceButtons;

		for (int i = 0; i < ArrayOfPieceButtons.length; i++) {
			int rowDist = i - pieceBtnrowPos;
			for (int j = 0; j < ArrayOfPieceButtons[0].length; j++) {
				int colDist = j - pieceBtncolPos;
				if (ArrayOfPieceButtons[i][j].isVisible()) {

					// checks piece is on board
					if ((boardBtnrowPos + rowDist) < 0 || boardBtnrowPos + rowDist >= btnBoard.length
							|| boardBtncolPos + colDist < 0 || boardBtncolPos + colDist >= btnBoard.length) {
						return false;
					}

					// checks piece overlap
					else if (!((btnBoardTemp[boardBtnrowPos + rowDist][boardBtncolPos + colDist]
							.getBackground() == Color.WHITE)
							|| (btnBoardTemp[boardBtnrowPos + rowDist][boardBtncolPos + colDist]
									.getBackground() == Color.DARK_GRAY))) {
						return false;
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
		if (sideTouching) {
			return false;
		} else if (!cornerTouching) {
			return false;
		} else {
			return true;
		}
	}

	public void HandleInValidMove(Button clickedButton) throws Exception {

		int boardBtnrowPos = boardButtonThatHasBeenClicked.x;
		int boardBtncolPos = boardButtonThatHasBeenClicked.y;

		int pieceBtnrowPos = clickedButton.x;
		int pieceBtncolPos = clickedButton.y;
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
					else if (!((btnBoardTemp[boardBtnrowPos + rowDist][boardBtncolPos + colDist]
							.getBackground() == Color.WHITE)
							|| (btnBoardTemp[boardBtnrowPos + rowDist][boardBtncolPos + colDist]
									.getBackground() == Color.DARK_GRAY))) {
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
		if (sideTouching) {
			throw new Exception("Piece can not touch side of same color piece!");
		}
		if (!cornerTouching) {
			throw new Exception("Corner of Piece needs to touch other same color piece's corner!");
		}

	}

	public void MovePieceOnBoard() {

		int boardBtnrowPos = boardButtonThatHasBeenClicked.x;
		int boardBtncolPos = boardButtonThatHasBeenClicked.y;

		int pieceBtnrowPos = pieceButtonThatHasBeenClicked.x;
		int pieceBtncolPos = pieceButtonThatHasBeenClicked.y;
		int pieceScore = 0;

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

	// ------------------------------------Player
	// Information---------------------------------------

	public static int[][][] FindAllPlayerMoves(int i) {
		int[][][] validMoveList = new int[21][20][20];
		for (int p = 0; p < 21; p++) {
			Button tempButton = Players[i].GetPieceCenterButton(p);
			if (Players[i].GetPiece(p).isVisible()) {

				int[][] validMoves = GetValidMoves(tempButton, currentPlayer.GetPiece(p));
				for (int x = 0; x < row; x++) {
					for (int y = 0; y < col; y++) {
						validMoveList[p][x][y] = validMoves[x][y];
						if (validMoves[x][y] == 1) {
						}
					}
				}
			}

		}

		return validMoveList;
	}

	public static Boolean DoesAnyPlayerHaveMoves() {
		Boolean somePlayerCanMove = false;
		for (int p = 0; p < 4; p++) {
			if (DoesPlayerHaveMoves(p)) {
				somePlayerCanMove = true;
			}
		}
		return somePlayerCanMove;
	}

	public static Boolean DoesPlayerHaveMoves(int a) {
		int[][][] tempMoveList = FindAllPlayerMoves(a);
		int moveCount = 0;
		for (int p = 0; p < 21; p++) {
			for (int x = 0; x < row; x++) {
				for (int y = 0; y < col; y++) {
					if (tempMoveList[p][x][y] == 1) {
						moveCount += 1;
					}
				}
			}
		}
		System.out.println("player " + a + " has " + moveCount + " moves they can place");
		if (moveCount > 0) {
			return true;
		} else {
			return false;
		}
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
		if (!DoesAnyPlayerHaveMoves()) {
			RunWinScreen();
		}
		Players[currentPlayersIndex].setVisible(false);
		Players[(currentPlayersIndex + 1) % 4].setVisible(true);
		currentPlayer = Players[(currentPlayersIndex + 1) % 4];
		if (!DoesPlayerHaveMoves(currentPlayersIndex())) {
			changePlayer();
//			Players[currentPlayersIndex ].setVisible(false);
//			currentPlayer = Players[(currentPlayersIndex + 1) % 4];
//			Players[currentPlayersIndex ].setVisible(true);

		}
		CleanBoardOfHints();
	}

	// swaps dark grey buttons for white to clear hints
	public static void CleanBoardOfHints() {
		for (int x = 0; x < row; x++) {
			for (int y = 0; y < col; y++) {
				if (btnBoard[x][y].getBackground() == Color.DARK_GRAY) {
					btnBoard[x][y].setBackground(Color.WHITE);
				}
			}
		}
		SaveTempBoard();
	}

	// ------------------------------------Saving
	// Functionality---------------------------------------

	public void saveBoard() {

		try (FileWriter writer = new FileWriter("save.txt"); BufferedWriter gamesave = new BufferedWriter(writer)) {

			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
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

	// ------------------------------------Win
	// Screen---------------------------------------

	// Creates the congratulations screen at the end of a game and starts the game
	// again if the player wants.
	public void RunWinScreen() {
		int playerwin = 4;
		if ((scores[0] > scores[1]) && (scores[0] > scores[2]) && (scores[0] > scores[3])) {
			playerwin = 0;
		}
		if ((scores[1] > scores[0]) && (scores[1] > scores[2]) && (scores[1] > scores[3])) {
			playerwin = 1;
		}
		if ((scores[2] > scores[1]) && (scores[2] > scores[0]) && (scores[2] > scores[3])) {
			playerwin = 2;
		}
		if ((scores[3] > scores[0]) && (scores[3] > scores[0]) && (scores[3] > scores[0])) {
			playerwin = 3;
		}

		if (playerwin == 0) {
			JOptionPane.showMessageDialog(null, "Player 1 Wins!! Your Score Was: " + scores[0]);
			if (JOptionPane.showConfirmDialog(null, "Do you want to play again?", "Play Again",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				setVisible(false);
				// Driver again = new Driver();
				Driver.main(new String[0]);

			} else {
				System.exit(0);
			}
		}

		else if (playerwin == 1) {
			JOptionPane.showMessageDialog(null, "Player 2 Wins!! Your Score Was: " + scores[1]);
			if (JOptionPane.showConfirmDialog(null, "Do you want to play again?", "Play Again",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				setVisible(false);
				Driver.main(new String[0]);
			} else {
				System.exit(0);
			}
		}

		else if (playerwin == 2) {
			JOptionPane.showMessageDialog(null, "Player 3 Wins!! Your Score Was: " + scores[2]);
			if (JOptionPane.showConfirmDialog(null, "Do you want to play again?", "Play Again",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				setVisible(false);
				Driver.main(new String[0]);

			} else {
				System.exit(0);
			}
		}

		else if (playerwin == 3) {
			JOptionPane.showMessageDialog(null, "Player 4 Wins!! Your Score Was: " + scores[3]);
			if (JOptionPane.showConfirmDialog(null, "Do you want to play again?", "Play Again",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				setVisible(false);
				Driver.main(new String[0]);

			} else {
				System.exit(0);
			}
		} else if (playerwin == 4) {
			JOptionPane.showMessageDialog(null, "Tie Game!!");
			if (JOptionPane.showConfirmDialog(null, "Do you want to play again?", "Play Again",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				setVisible(false);
				Driver.main(new String[0]);

			} else {
				System.exit(0);
			}
		}

	}

	// ------------------------------------Action and Mouse
	// Events---------------------------------------

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
		if (e.getSource() == btnRotate) {
			currentPlayer.rotatePieces();
			CleanBoardOfHints();
			ShowHints();
		}
		if (e.getSource() == btnFlip) {
			currentPlayer.flipPieces();
			CleanBoardOfHints();
			ShowHints();
		}

		Button buttonClicked = (Button) e.getSource();
		BoardButtonHasBeenClicked = true;
		boardButtonThatHasBeenClicked = buttonClicked;
		System.out.println("Movepiece on board");

		if (pieceHasBeenClicked) {
			try {
				HandleInValidMove(pieceButtonThatHasBeenClicked);
				MovePieceOnBoard();

			}

			catch (Exception ex) {
				JOptionPane.showMessageDialog(this, ex.getMessage(), "Illegal move", JOptionPane.INFORMATION_MESSAGE);
			}

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
