import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class Game extends JFrame implements ActionListener
{
	private JPanel leftPanel_Game, centerPanel_Game, rightPanel_Game;
	private JLabel lblScores, lblPlayer1, lblPlayer2, lblPlayer3, lblPlayer4,
		lblPlayer1Score, lblPlayer2Score, lblPlayer3Score, lblPlayer4Score;
	private JButton btnBoard[][];
	private JPanel piecePanels[]  = new JPanel[21];
	private JScrollPane scrollPane;

	private int row = 20;
	private int col = 20;
	
	
	public Game()
	{
		setTitle("Blokus");
		setSize(800, 529);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		

		
		//.......... Panels ..........
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
		rightPanel_Game.setBackground(Color.LIGHT_GRAY);
		rightPanel_Game.setBounds(655, 0, 170, 500);
		getContentPane().add(rightPanel_Game);
		rightPanel_Game.setLayout(null);
		//.......... Panels ..........
		
		
		//.......... Left Panel ..........
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
		//.......... Left Panel ..........
		

		
		//.......... Center Panel ..........
		btnBoard = new JButton[row][col];
        for (int i = 0; i < row; i++) {
        	for (int j = 0; j < col; j++) {
                btnBoard[i][j] = new JButton();
                btnBoard[i][j].addActionListener(this);
            centerPanel_Game.add(btnBoard[i][j]);
            btnBoard[i][j].setBackground(Color.white);
	        }
	    }
		//.......... Center Panel .......... 
        
        
		
		//.......... Right Panel ..........	


//        System.out.println("Initial for loop Working!");
        int b = 0;
       
		for (int m = 0; m < 21; m++) {
			JPanel piecePanel= new JPanel();
			piecePanel.setBounds(0, b, 170, 100);
			rightPanel_Game.add(piecePanel);
			piecePanel.setLayout(new GridLayout(7,7));
			piecePanel.setBackground( Color.LIGHT_GRAY );
			piecePanels[m] = piecePanel;
			b += 100;
	    }
		
		for(JPanel piecePnl: piecePanels) {
			
		}
		
		int[][][] shapes = new Piece().getAllShapes();
		
		for (int i = 0; i < 21; i++) {
			for (int j = 0; j < 7; j++) {
				for (int k = 0; k < 7; k++) {
					JButton button = new JButton();
					if (shapes [i][j][k] == 3) { button.setBackground(Color.RED); }
					else {button.setVisible(false);}
					piecePanels[i].add(button);
					
				}
			}
		}
		
		
    		

		
		setResizable(false);
		setVisible(true);
	}	
	
	
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		System.out.println("" + ob);
	}
}