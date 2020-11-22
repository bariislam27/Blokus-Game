import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JButton;

public class pieceRotation
{
	public static void main(String[] args) 
	{
		new Rotation();
	}
}

	
class Rotation extends JFrame implements ActionListener, MouseListener
{
	private JPanel panel, smallPanel[];
	private JButton smallButton[][][], btnRotate, btnFlip;
	private int [][][] shapes;
	int currentShape[][][] = new int[1][7][7];
	int changedShape[][][] = new int[1][7][7];
	int currentIndex;
	
	public Rotation() 
	{
		setTitle("Rotation");
		setSize(420, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		panel = new JPanel();
		//panel.setBounds(0, 0, 590, 290);
		panel.setSize(240, 560);
		panel.setLayout(new GridLayout(7, 3));
		getContentPane().add(panel);
		
		btnRotate = new JButton("Rotate");
		btnRotate.setBounds(289, 150, 114, 25);
		btnRotate.addMouseListener(this);
		getContentPane().add(btnRotate);
		
		btnFlip = new JButton("Flip");
		btnFlip.setBounds(289, 319, 114, 25);
		btnFlip.addMouseListener(this);
		getContentPane().add(btnFlip);
		
		
		shapes = new Piece().getAllShapes();
		
		smallButton = new JButton[21][7][7];
		
		smallPanel = new JPanel[21];
		
		for (int i = 0; i < shapes.length; i++) {
			smallPanel[i] = new JPanel();
			smallPanel[i].setSize(50, 50);
			smallPanel[i].addMouseListener(this);
			smallPanel[i].setLayout(new GridLayout(7, 7));
			panel.add(smallPanel[i]);
			if (i % 2 == 0) {
				smallPanel[i].setBackground(Color.BLACK);
			}
			
			for (int j = 0; j < 7; j++) {
				for (int k = 0; k < 7; k++) {
					smallButton[i][j][k] = new JButton();
					smallPanel[i].add(smallButton[i][j][k]);
					smallButton[i][j][k].addActionListener(this);
					
					if (shapes[i][j][k]== 3) {
						smallButton[i][j][k].setBackground(Color.GREEN);
					} else {
						smallButton[i][j][k].setVisible(false);
					}
				}
			}
			
		}

		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				currentShape[0][i][j] = 0;
			}
		}
		setResizable(false);
		setVisible(true);
	
	}
	
	public void rotate () {
		// Rotating the shape
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				changedShape[0][j][6-i] = currentShape[0][i][j];
			}
		}
		
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				currentShape[0][i][j] = changedShape[0][i][j];
			}
		}
		
		for (int j = 0; j < 7; j++) 
		{
			for (int k = 0; k < 7; k++) 
			{
				smallButton[currentIndex][j][k].setVisible(true);
				if (currentShape[0][j][k]== 3) {
					smallButton[currentIndex][j][k].setBackground(Color.GREEN);
				} 
				else {
					smallButton[currentIndex][j][k].setVisible(false);
				}
			}
		}
		
	}
	
	public void flip ()
	{
		// Fliping the shape
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				changedShape[0][6-i][j] = currentShape[0][i][j];
			}
		}
		
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				currentShape[0][i][j] = changedShape[0][i][j];
			}
		}
		
		for (int j = 0; j < 7; j++) 
		{
			for (int k = 0; k < 7; k++) 
			{
				smallButton[currentIndex][j][k].setVisible(true);
				if (currentShape[0][j][k]== 3) {
					smallButton[currentIndex][j][k].setBackground(Color.GREEN);
				} 
				else {
					smallButton[currentIndex][j][k].setVisible(false);
				}
			}
		}
		
	}

	public void actionPerformed (ActionEvent e) {
	
	}
	
	public void mouseClicked (MouseEvent e) {
		for (int i = 0; i < 21; i++) 
		{
			if (e.getSource() == smallPanel[i]) 
			{
				currentIndex = i;
				for (int j = 0; j < 7; j++) 
				{
					for (int k = 0; k < 7; k++) {
						currentShape[0][j][k] = shapes[i][j][k];
					}
				}
			}
		}
		
		if (e.getSource() == btnRotate) 
		{
			rotate();
		}
		
		if (e.getSource() == btnFlip) 
		{
			flip();
		}
		
	
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
	
}