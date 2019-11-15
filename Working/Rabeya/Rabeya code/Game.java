import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

import jdk.jfr.Enabled;

import javax.swing.JLabel;

import java.util.Timer;
import java.util.TimerTask;

public class Game extends JFrame implements MouseListener, ActionListener
{
	private JPanel leftPanel_Game, centerPanel_Game, rightPanel_Game, rightPanel2_Game;
	private Piece piecePanel[];
	private JLabel lblScores, lblPlayer1, lblPlayer2, lblPlayer3, lblPlayer4,
		lblPlayer1Score, lblPlayer2Score, lblPlayer3Score, lblPlayer4Score, thetime, pieceOutofBoard, piecesOverlap;
	static boolean  pieceHasBeenClicked = false, BoardButtonHasBeenClicked=false;;
	private static Button btnBoard[][];
	//private JScrollPane scrollPieces;
	private Timer timer;
	
	private int row, col, seconds, piecePanelY;
	//private static int boardButtonX;
	//private static int boardButtonY;
	// Piece pieceThatHasBeenClicked;
	
	
	public static Button pieceButtonThatHasBeenClicked;
	public Button boardButtonThatHasBeenClicked;
	public static Piece pieceThatHasBeenClicked = null;
	
	public static Player Players[] =new Player[5];
	public static Color pieceColors[] =new Color[4];
	

	private Player currentPlayer ;

	public Game(int sec)
	{
//		
		
		setTitle("Blokus");
		setSize(810, 529);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//getContentPane().setLayout(null);
		
		this.row = 20;
     	this.col = 20;
		
		//.......... Panels ..........
		leftPanel_Game = new JPanel();
		leftPanel_Game.setBackground(Color.WHITE);
		leftPanel_Game.setBounds(0, 0, 155, 500);
		//getContentPane().add(leftPanel_Game);
		leftPanel_Game.setLayout(null);
		
		centerPanel_Game = new JPanel();
		centerPanel_Game.setBackground(Color.BLUE);
		centerPanel_Game.setBounds(155, 0, 500, 500);
		getContentPane().add(centerPanel_Game);
		centerPanel_Game.setLayout(new GridLayout(row, col));	
		
		getContentPane().add(leftPanel_Game);
		getContentPane().add(centerPanel_Game);
		
		pieceColors[0] = Color.red ;
        pieceColors[1] = Color.YELLOW  ;
        pieceColors[2] = Color.GREEN ;
        pieceColors[3] = Color.BLUE;
        
        for (int i = 0; i <4; i++) {
        	Players[i] = new Player(pieceColors[i]);
        	Players[i].setBackground(Color.LIGHT_GRAY);
        	Players[i].setVisible(false);
        	Players[i].setBounds(655, 0, 155, 500);
    		getContentPane().add(Players[i]);
        }
        
        
        
        


        
        
       JPanel player = new Player(Color.PINK);
       player.setBackground(Color.LIGHT_GRAY);
       player.setVisible(false);
       player.setBounds(655, 0, 155, 500);
	   getContentPane().add(player);
        
	   currentPlayer = Players[0];
	   Players[0].setVisible(true);
        
        
        
        
        
		//rightPanel_Game = new JPanel();
//		rightPanel_Game.setBackground(Color.LIGHT_GRAY);
//		rightPanel_Game.setBounds(655, 0, 155, 500);
//		getContentPane().add(rightPanel_Game);
		
		//.......... Panels ..........
		
		
		//.......... Left Panel ..........
		pieceOutofBoard = new JLabel("Piece out of board");
		pieceOutofBoard.setFont(new Font("Dyuthi", Font.BOLD, 15));
		pieceOutofBoard.setForeground(Color.RED);
		pieceOutofBoard.setBounds(10, 10, 200, 100);
		pieceOutofBoard.setVisible(false);
		leftPanel_Game.add(pieceOutofBoard); // pieceOutofBoard    piecesOverlap
		
		piecesOverlap = new JLabel("Pieces overlap");
		piecesOverlap.setFont(new Font("Dyuthi", Font.BOLD, 15));
		piecesOverlap.setForeground(Color.RED);
		piecesOverlap.setBounds(10, 10, 200, 100);
		piecesOverlap.setVisible(false);
		leftPanel_Game.add(piecesOverlap);
		
		lblScores = new JLabel("Scores");
		lblScores.setFont(new Font("Dyuthi", Font.BOLD, 20));
		lblScores.setBounds(45, 100, 65, 20);
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
		
		lblPlayer1Score = new JLabel("00");
		lblPlayer1Score.setBounds(90, 135, 50, 15);
		leftPanel_Game.add(lblPlayer1Score);
		
		lblPlayer2Score = new JLabel("00");
		lblPlayer2Score.setBounds(90, 165, 50, 15);
		leftPanel_Game.add(lblPlayer2Score);
		
		lblPlayer3Score = new JLabel("00");
		lblPlayer3Score.setBounds(90, 195, 66, 15);
		leftPanel_Game.add(lblPlayer3Score);
		
		lblPlayer4Score = new JLabel("00");
		lblPlayer4Score.setBounds(90, 225, 66, 15);
		leftPanel_Game.add(lblPlayer4Score);
		
		
		
		seconds = sec;
	    thetime = new JLabel();
        thetime.setFont(new Font("Courier", Font.PLAIN, 20));
        timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
            	if (seconds == 0) {
            		thetime.setText("");
            	}
                if (seconds >= 1){
                    thetime.setText("" + seconds--);
                   }
                   else{
                       cancel();
                   }
               }
           };

         timer.schedule(task,0,1000);
         leftPanel_Game.add(thetime);
         thetime.setBounds(20, 10, 60, 60);
		//.......... Left Panel ..........
		

		
		//.......... Center Panel ..........
		btnBoard = new Button[row][col];
        for (int i = 0; i < row; i++) {
        	for (int j = 0; j < col; j++) {
                btnBoard[i][j] = new Button();
                btnBoard[i][j].setXY(i, j);
                //btnBoard[i][j].addMouseListener(this);
                btnBoard[i][j].addActionListener(this);
                centerPanel_Game.add(btnBoard[i][j]);
            
	        }
	    }
        
//        btnBoard[0][19].setBackground(Color.GREEN);
//        btnBoard[19][0].setBackground(Color.YELLOW);
//        btnBoard[19][19].setBackground(Color.BLUE);
		//.......... Center Panel ..........
        

        
		
	//.......... Right Panel ..........	

		
		//.......... Right Panel ..........
		
        
//        pieceColors[0] = Color.red ;
//        pieceColors[1] = Color.YELLOW  ;
//        pieceColors[2] = Color.GREEN ;
//        pieceColors[3] = Color.BLUE;
      
     
      Players[0].LegalBoardPositions.add(btnBoard[0][0]);
      Players[1].LegalBoardPositions.add(btnBoard[19][0]);
      Players[2].LegalBoardPositions.add(btnBoard[19][19]);
      Players[3].LegalBoardPositions.add(btnBoard[0][19]);
      
//      if (currentPlayer.LegalBoardPositions.contains(btnBoard[0][0]) ) {
//    	  System.out.println("Player 0 passed first test");
//    	  
//      }
//      if (!currentPlayer.LegalBoardPositions.contains(btnBoard[0][19]) ) {
//    	  System.out.println("Player 1 passed second test");
//    	  
//      }
        
		setResizable(false);
		setVisible(true);
	}	
	
	
	
	
	
	public void mouseClicked (MouseEvent e) {
		
	}
	
	
	
	public void mouseEntered(MouseEvent e) {
		//
	}  
	
    public void mouseExited(MouseEvent e) {
    	//
    }
    
    public void mousePressed(MouseEvent e) {
    	//
    }
    
    public void mouseReleased(MouseEvent e) {
    	//
    }

	
	public void actionPerformed(ActionEvent e) {
		Button brdButton = (Button) e.getSource();
		BoardButtonHasBeenClicked = true;
		boardButtonThatHasBeenClicked = brdButton;
		System.out.println("Movepiece on board");
		
		if (pieceHasBeenClicked) {
			try {
				HandleInValidMove( );
				MovePieceOnBoard( );
				
			}
			
//			catch(IllegalMoveException ex) {  
//				JOptionPane.showMessageDialog(this, "Pieces overlap", "Illegal move",  JOptionPane.INFORMATION_MESSAGE);
//				
//			}  
//			
//			catch(IndexOutOfBoundsException ex) {  
//				JOptionPane.showMessageDialog(this, "piece out of board", "Illegal move",  JOptionPane.INFORMATION_MESSAGE);
//				
//			}  
			
			
			catch(Exception ex){
				JOptionPane.showMessageDialog(this, ex.getMessage(), "Illegal move",  JOptionPane.INFORMATION_MESSAGE);
		}
			
		}
//		Button b1 = new Button();
//		if (! ( currentPlayer.LegalBoardPositions.contains(b1)) ) {
//			System.out.println("test passed");
//		}

    } 
	
	public  void PieceOutOfBoard(Piece piece ){
		
	}
	
	public void  HandleInValidMove( ) throws Exception{
		
//      if (!currentPlayer.LegalBoardPositions.contains(boardButtonThatHasBeenClicked) ) {
//    	  throw new Exception("Piece must be placed on the first tile of the respective player");
//  	  
//    }
		
			int boardBtnrowPos =     boardButtonThatHasBeenClicked.x ;
			int boardBtncolPos =  boardButtonThatHasBeenClicked.y ;
	
			
			int pieceBtnrowPos = pieceButtonThatHasBeenClicked.x ;
			int pieceBtncolPos = pieceButtonThatHasBeenClicked.y ;

			Button[][] ArrayOfPieceButtons = pieceThatHasBeenClicked.pieceButtons;
			

			for (int i = 0; i < ArrayOfPieceButtons.length; i++) {
				int rowDist = i - pieceBtnrowPos ;                   
				for (int j = 0; j <ArrayOfPieceButtons[0].length; j++) {
					int colDist = j - pieceBtncolPos ;	
					if( ArrayOfPieceButtons[i][j].isVisible()  ) {
						
						if( (boardBtnrowPos+rowDist) <0 || boardBtnrowPos+rowDist>= btnBoard.length || boardBtncolPos+colDist<0	|| boardBtncolPos+colDist>=btnBoard.length )
							          { throw new Exception("Piece out of board");}
						else if(btnBoard[boardBtnrowPos+rowDist][boardBtncolPos+colDist].getBackground() != Color.WHITE) {
							throw new Exception("Pieces overlap!");
						}
							
						
	
					}
					
				}
			}

	}
	
	
	

	public  void MovePieceOnBoard( ){ //PiecepieceThatHasBeenClicked, int coloredButtons[][]
		
		//if (pieceHasBeenClicked && BoardButtonHasBeenClicked) {
			int boardBtnrowPos =     boardButtonThatHasBeenClicked.x ;
			int boardBtncolPos =  boardButtonThatHasBeenClicked.y ;
	
			
			int pieceBtnrowPos = pieceButtonThatHasBeenClicked.x ;
			int pieceBtncolPos = pieceButtonThatHasBeenClicked.y ;
			
			
			System.out.println("Board button x:  " + boardBtnrowPos + ", Board button y: " + boardBtncolPos);
			System.out.println("Piece button x:  " + pieceBtnrowPos + ", Piece button y: " + pieceBtncolPos);
			
			Button[][] ArrayOfPieceButtons = pieceThatHasBeenClicked.pieceButtons;
			

			for (int i = 0; i < ArrayOfPieceButtons.length; i++) {
				int rowDist = i - pieceBtnrowPos ;                   
				for (int j = 0; j <ArrayOfPieceButtons[0].length; j++) {
					int colDist = j - pieceBtncolPos ;	
					if( ArrayOfPieceButtons[i][j].isVisible()  ) {
						
						btnBoard[boardBtnrowPos+rowDist][boardBtncolPos+colDist].setBackground(ArrayOfPieceButtons[i][j].getBackground());
							
						
						
					}
					
				}
			}
			
		//}
		pieceOutofBoard.setVisible(false);
		pieceThatHasBeenClicked.setVisible(false);
		pieceHasBeenClicked = false;
		BoardButtonHasBeenClicked = false;
		
	      
	        TimerTask task = new TimerTask()  {
	        	

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
			if ( Players[i].isVisible() ) {
				return i;
			}
		 }
		 return -1;
	}
	
	public void changePlayer() {
		int currentPlayersIndex = currentPlayersIndex();
		Players[currentPlayersIndex].setVisible(false);
		Players[ (currentPlayersIndex+1) % 4 ].setVisible(true);
		currentPlayer = Players[ (currentPlayersIndex+1) % 4 ];
	};
		


	   public static class IllegalMoveException extends Exception
	   {
	      public IllegalMoveException()
	      {
	         super();
	      }
	      
//	      public void HandleIllegalMoveException(String message)
//	      {
//	    	  JOptionPane.showMessageDialog(gridWindow, message, "Illegal move",  JOptionPane.INFORMATION_MESSAGE);
//	      }
	   }
	
}



