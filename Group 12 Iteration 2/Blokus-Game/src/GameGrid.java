import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

public class GameGrid extends JFrame
{
	private JPanel leftPanel_GameGrid, centerPanel_GameGrid, rightPanel_GameGrid;
	private JLabel lblScores, lblPlayer1, lblPlayer2, lblPlayer3, lblPlayer4,
		lblPlayer1Score, lblPlayer2Score, lblPlayer3Score, lblPlayer4Score;
	private JButton but[][];
	private String text;
	private int themenumber, row, col;
	
	public GameGrid()
	{
		setTitle("Blokus");
		setBounds(300, 150, 800, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		row = 20;
		col = 20;
		
		//.......... Panels ..........
		leftPanel_GameGrid = new JPanel();
		leftPanel_GameGrid.setBackground(Color.WHITE);
		leftPanel_GameGrid.setBounds(0, 0, 155, 470);
		getContentPane().add(leftPanel_GameGrid);
		leftPanel_GameGrid.setLayout(null);
		
		centerPanel_GameGrid = new JPanel();
		centerPanel_GameGrid.setBackground(Color.GRAY);
		centerPanel_GameGrid.setBounds(155, 0, 490, 470);
		getContentPane().add(centerPanel_GameGrid);
		centerPanel_GameGrid.setLayout(new GridLayout(row,col));	
		
		rightPanel_GameGrid = new JPanel();
		rightPanel_GameGrid.setBackground(Color.LIGHT_GRAY);
		rightPanel_GameGrid.setBounds(645, 0, 155, 470);
		getContentPane().add(rightPanel_GameGrid);
		rightPanel_GameGrid.setLayout(null);
		//.......... Panels ..........
		
		
		//.......... Left Panel ..........
		lblScores = new JLabel("Scores");
		lblScores.setFont(new Font("Dyuthi", Font.BOLD, 20));
		lblScores.setBounds(45, 100, 65, 20);
		leftPanel_GameGrid.add(lblScores);
		
		lblPlayer1 = new JLabel("Player 1:");
		lblPlayer1.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblPlayer1.setBounds(12, 135, 75, 15);
		leftPanel_GameGrid.add(lblPlayer1);
		
		lblPlayer2 = new JLabel("Player 2:");
		lblPlayer2.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblPlayer2.setBounds(12, 165, 75, 15);
		leftPanel_GameGrid.add(lblPlayer2);
		
		lblPlayer3 = new JLabel("Player 3:");
		lblPlayer3.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblPlayer3.setBounds(12, 195, 75, 15);
		leftPanel_GameGrid.add(lblPlayer3);
		
		lblPlayer4 = new JLabel("Player 4:");
		lblPlayer4.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblPlayer4.setBounds(12, 225, 75, 15);
		leftPanel_GameGrid.add(lblPlayer4);
		
		lblPlayer1Score = new JLabel("00");
		lblPlayer1Score.setBounds(90, 135, 50, 15);
		leftPanel_GameGrid.add(lblPlayer1Score);
		
		lblPlayer2Score = new JLabel("00");
		lblPlayer2Score.setBounds(90, 165, 50, 15);
		leftPanel_GameGrid.add(lblPlayer2Score);
		
		lblPlayer3Score = new JLabel("00");
		lblPlayer3Score.setBounds(90, 195, 66, 15);
		leftPanel_GameGrid.add(lblPlayer3Score);
		
		lblPlayer4Score = new JLabel("00");
		lblPlayer4Score.setBounds(90, 225, 66, 15);
		leftPanel_GameGrid.add(lblPlayer4Score);
		//.......... Left Panel ..........
		
		
		//.......... Center Panel ..........
		but = new JButton[row][col];
        for (int i = 0; i < row; i++) {
        	for (int j = 0; j < col; j++) {
                but[i][j] = new JButton();
                
                text += String.format("[%d,%d]", i, j);
            String cord = String.format("[%d,%d]", i, j);
            //but[i][j].setText(cord);
            centerPanel_GameGrid.add(but[i][j]);
            but[i][j].setBackground(Color.white);
	        }
	    }
        //.......... Center Panel ..........
		
		
		//.......... Right Panel ..........
		// Write your code here
		//.......... Right Panel ..........
		
		
		
		setVisible(true);
	}
}

