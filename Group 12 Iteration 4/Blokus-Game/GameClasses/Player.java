package GameClasses;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.*;


public class Player extends JPanel {
	
	Color color;
	Piece[] piecePanels = new Piece[21];
	public ArrayList<Button> LegalBoardPositions = new ArrayList<>();
	public boolean isFirstMove = true;
	
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
	    
        int[][][] shapes = new Piece().getAllShapes();
		
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
	
	public boolean getFirstMove(){
		return isFirstMove;
	}
    

}


	

