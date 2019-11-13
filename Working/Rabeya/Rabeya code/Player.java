import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.*;


public class Player extends JPanel {
	
	Color color;
	Piece[] piecePanels = new Piece[21];
	
	public Player(Color color){
		this.color = color;

	    for (int i = 0; i < 21; i++) {
	    	piecePanels[i] = new Piece();
	    	piecePanels[i].setBackground(Color.LIGHT_GRAY);
	    	setLayout(new GridLayout(11, 2));
			add(piecePanels[i]);	
		}
	    
        int[][][] shapes = new Piece().getAllShapes();
		
		for (int i = 0; i < 21; i++) {
			
			for (int j = 0; j < 7; j++) {
				for (int k = 0; k < 7; k++) {
					
					if (shapes [i][j][k] == 3) { 
						piecePanels[i].pieceButtons[j][k].setBackground(this.color);
						//piecePanels[i].numOfColoredButtons += 1;
					}
					else {
					piecePanels[i].pieceButtons[j][k].setBackground(Color.BLACK);
					piecePanels[i].pieceButtons[j][k].setBorderPainted(false);
						
					}
				}
			}	
			
		}
	}
    

}


	

