//package GameClasses;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.*;


public class Player extends JPanel {
	
	Color color;
	Piece[] piecePanels = new Piece[21];
	public ArrayList<Button> LegalBoardPositions = new ArrayList<>();
	public boolean isFirstMove = true;
	private int changedShape[][][] = new int[21][7][7];
	public int shapes[][][] = new int[21][7][7];
	
	public Player(Color pieceColor){
		this.color = pieceColor;
	    for (int i = 0; i < 21; i++) {
	    	piecePanels[i] = new Piece();
	    	if (i % 2 == 0) {
	    		piecePanels[i].setBackground(Color.LIGHT_GRAY);
			} else {
				piecePanels[i].setBackground(Color.GRAY);
			}
	    	
	    	setLayout(new GridLayout(7, 3));
			add(piecePanels[i]);	
		}
	    
         new Piece();
		shapes = Piece.getAllShapes();
		
		for (int i = 0; i < 21; i++) {
			
			for (int j = 0; j < 7; j++) {
				for (int k = 0; k < 7; k++) {
					
					if (shapes [i][j][k] == 3) { 
						piecePanels[i].pieceButtons[j][k].setBackground(pieceColor);
					} else {
						piecePanels[i].pieceButtons[j][k].setVisible(false);	
						
					}
				}
			}	
			
		}
	}
	public Button GetPieceCenterButton(int p) {
		return piecePanels[p].pieceButtons[3][3];
				
				
	}
	public Piece GetPiece(int p) {
		return piecePanels[p];
				
				
	}

	// Function to rotate all input shapes
	public void rotatePieces () {
		for(int p = 0; p < 21; p++) {
			
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				changedShape[p][j][6-i] = shapes[p][i][j];
			}
		}
		
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				shapes[p][i][j] = changedShape[p][i][j];
			}
		}
		
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				
				if (shapes [p][i][j] == 3) { 
					piecePanels[p].pieceButtons[i][j].setBackground(color);
					piecePanels[p].pieceButtons[i][j].setVisible(true);
				} else {
					piecePanels[p].pieceButtons[i][j].setVisible(false);	
					
				}
		}
		}
		}                         
		
	}
	// Flips all the shapes
	public void flipPieces() {
		
		for(int p = 0; p < 21; p++) {
		
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				changedShape[p][6 - i][j] = shapes[p][i][j];
			}
		}

		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				shapes[p][i][j] = changedShape[p][i][j];
			}
		}

		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				
				if (shapes [p][i][j] == 3) { 
					piecePanels[p].pieceButtons[i][j].setBackground(color);
					piecePanels[p].pieceButtons[i][j].setVisible(true);
				} else {
					piecePanels[p].pieceButtons[i][j].setVisible(false);	
					
				}
		}
		}		}
	}

}


	

