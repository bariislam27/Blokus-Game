package GameClasses;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class Piece extends JPanel implements MouseListener {

	public static final int SHAPE_SIZE = 7;

	// Types of error during put the piece on the board.
	public static final String OFF_BOARD_ERROR = "Piece must be placed entirely on the board.";
	public static final String ADJACENCY_ERROR = "Pieces of the same color cannot share edges with one another.";
	public static final String OVERLAP_ERROR = "Pieces cannot overlap.";
	public static final String START_ERROR = "Starting peice must occupy the player's respective corner.";
	public static final String CORNER_ERROR = "Pieces must be connected to at least one other piece of the the same color by the corner.";

	public Button pieceButtons[][] = new Button[7][7];
	public int numOfColoredButtons = 0;

	public Piece() {

		setLayout(new GridLayout(7, 7));

		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				Button b = new Button();
				b.setVisible(true);
				b.addMouseListener(this);
				b.setXY(i, j);
				pieceButtons[i][j] = b;
				add(b);
				addMouseListener(this); // CHANGED THIS
			}

		}

	}

	// This function makes different shape of pieces.
	public static int[][][] getAllShapes() {
		int[][][] shapes = new int[21][SHAPE_SIZE][SHAPE_SIZE];
		int i = 0;

		// *
		// * * *
		// *
		shapes[i++] = new int[][] { { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 1, 2, 1, 0 }, { 0, 1, 2, 2, 3, 2, 0 },
				{ 0, 2, 3, 3, 3, 2, 0 }, { 0, 1, 2, 2, 3, 2, 0 }, { 0, 0, 0, 1, 2, 1, 0 }, { 0, 0, 0, 0, 0, 0, 0 } };

		// *
		// * *
		// * *
		shapes[i++] = new int[][] { { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 1, 2, 1, 0, 0 }, { 0, 0, 2, 3, 2, 1, 0 },
				{ 0, 1, 2, 3, 3, 2, 0 }, { 0, 2, 3, 3, 2, 1, 0 }, { 0, 1, 2, 2, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 } };

		// *
		// * * *
		// *
		shapes[i++] = new int[][] { { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 1, 2, 1, 0, 0 }, { 0, 1, 2, 3, 2, 1, 0 },
				{ 0, 2, 3, 3, 3, 2, 0 }, { 0, 1, 2, 3, 2, 1, 0 }, { 0, 0, 1, 2, 1, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 } };

		// *
		// * * * *
		shapes[i++] = new int[][] { { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 1, 2, 1, 0, 0 }, { 0, 1, 2, 3, 2, 2, 1 },
				{ 0, 2, 3, 3, 3, 3, 2 }, { 0, 1, 2, 2, 2, 2, 1 }, { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 } };

		// * * *
		// *
		// * * *
		shapes[i++] = new int[][] { { 0, 0, 0, 0, 0, 0, 0 }, { 0, 1, 2, 2, 1, 0, 0 }, { 0, 2, 3, 3, 2, 0, 0 },
				{ 0, 2, 3, 2, 1, 0, 0 }, { 0, 2, 3, 3, 2, 0, 0 }, { 0, 1, 2, 2, 1, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 } };

		// * *
		// *
		// * *
		shapes[i++] = new int[][] { { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 1, 2, 2, 1, 0 }, { 0, 0, 2, 3, 3, 2, 0 },
				{ 0, 1, 2, 3, 2, 1, 0 }, { 0, 2, 3, 3, 2, 0, 0 }, { 0, 1, 2, 2, 1, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 } };

		// * *
		// * *
		// *
		shapes[i++] = new int[][] { { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 2, 2, 1, 0 }, { 0, 1, 2, 3, 3, 2, 0 },
				{ 0, 2, 3, 3, 2, 1, 0 }, { 0, 2, 3, 2, 1, 0, 0 }, { 0, 1, 2, 1, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 } };

		// *
		// *
		// * * *
		shapes[i++] = new int[][] { { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 1, 2, 0, 0 }, { 0, 0, 1, 2, 3, 2, 0 },
				{ 0, 1, 2, 2, 3, 2, 0 }, { 0, 2, 3, 3, 3, 2, 0 }, { 0, 1, 2, 2, 2, 1, 0 }, { 0, 0, 0, 0, 0, 0, 0 } };

		// *
		// * *
		// * *
		shapes[i++] = new int[][] { { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 1, 2, 1, 0 }, { 0, 0, 1, 2, 3, 2, 0 },
				{ 0, 0, 2, 3, 3, 2, 0 }, { 0, 0, 2, 3, 3, 2, 0 }, { 0, 0, 1, 2, 2, 1, 0 }, { 0, 0, 0, 0, 0, 0, 0 } };

		// *
		// *
		// * *
		// *
		shapes[i++] = new int[][] { { 0, 0, 1, 2, 1, 0, 0 }, { 0, 0, 2, 3, 2, 0, 0 }, { 0, 1, 2, 3, 2, 0, 0 },
				{ 0, 2, 3, 3, 2, 0, 0 }, { 0, 2, 3, 2, 1, 0, 0 }, { 0, 1, 2, 1, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 } };

		// * * * *
		// *
		shapes[i++] = new int[][] { { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 }, { 0, 1, 2, 2, 2, 2, 1 },
				{ 0, 2, 3, 3, 3, 3, 2 }, { 0, 1, 2, 2, 2, 3, 2 }, { 0, 0, 0, 0, 1, 2, 1 }, { 0, 0, 0, 0, 0, 0, 0 } };

		// * * * * *
		shapes[i++] = new int[][] { { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 }, { 1, 2, 2, 2, 2, 2, 1 },
				{ 2, 3, 3, 3, 3, 3, 2 }, { 1, 2, 2, 2, 2, 2, 1 }, { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 } };

		// *
		// * *
		// *
		shapes[i++] = new int[][] { { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 1, 2, 1, 0 }, { 0, 0, 1, 2, 3, 2, 0 },
				{ 0, 0, 2, 3, 3, 2, 0 }, { 0, 0, 2, 3, 2, 1, 0 }, { 0, 0, 1, 2, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 } };

		// * *
		// * *
		shapes[i++] = new int[][] { { 0, 0, 0, 0, 0, 0, 0 }, { 0, 1, 2, 2, 1, 0, 0 }, { 0, 2, 3, 3, 2, 0, 0 },
				{ 0, 2, 3, 3, 2, 0, 0 }, { 0, 1, 2, 2, 1, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 } };

		// *
		// * * *
		shapes[i++] = new int[][] { { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 1, 2, 1, 0, 0 }, { 0, 1, 2, 3, 2, 1, 0 },
				{ 0, 2, 3, 3, 3, 2, 0 }, { 0, 1, 2, 2, 2, 1, 0 }, { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 } };

		// * * *
		// *
		shapes[i++] = new int[][] { { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 }, { 0, 1, 2, 2, 2, 1, 0 },
				{ 0, 2, 3, 3, 3, 2, 0 }, { 0, 1, 2, 2, 3, 2, 0 }, { 0, 0, 0, 1, 2, 1, 0 }, { 0, 0, 0, 0, 0, 0, 0 } };

		// * * * *
		shapes[i++] = new int[][] { { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 }, { 0, 1, 2, 2, 2, 2, 1 },
				{ 0, 2, 3, 3, 3, 3, 2 }, { 0, 1, 2, 2, 2, 2, 1 }, { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 } };

		// *
		// * *
		shapes[i++] = new int[][] { { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 1, 2, 1, 0 }, { 0, 0, 1, 2, 3, 2, 0 },
				{ 0, 0, 2, 3, 3, 2, 0 }, { 0, 0, 1, 2, 2, 1, 0 }, { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 } };

		// * * *
		shapes[i++] = new int[][] { { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 }, { 0, 1, 2, 2, 2, 1, 0 },
				{ 0, 2, 3, 3, 3, 2, 0 }, { 0, 1, 2, 2, 2, 1, 0 }, { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 } };

		// * *
		shapes[i++] = new int[][] { { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 1, 2, 2, 1, 0 },
				{ 0, 0, 2, 3, 3, 2, 0 }, { 0, 0, 1, 2, 2, 1, 0 }, { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 } };

		// *
		shapes[i++] = new int[][] { { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 1, 2, 1, 0, 0 },
				{ 0, 0, 2, 3, 2, 0, 0 }, { 0, 0, 1, 2, 1, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 } };
		return shapes;

	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if (e.getSource() instanceof JPanel) {
			System.out.println("small piece panel clicked");
			Game.pieceHasBeenClicked = false;
		}

		else {
			Button clickedButton = (Button) e.getSource();
			Game.pieceThatHasBeenClicked = this;
			Game.pieceHasBeenClicked = true;
			Game.pieceButtonThatHasBeenClicked = clickedButton;
			Game.pieceHasBeenPlaced = false;
			Game.SaveTempBoard();
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
