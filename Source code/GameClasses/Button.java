package GameClasses;
import javax.swing.*;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.*;

public class Button extends JButton implements ActionListener {
    
	public int x, y;
    
	public Button() {
		super();
		setBackground(Color.WHITE);
		
	}	
	
	
	public void setXY(int x, int y) {
		
		this.x = x;
		this.y = y;
		
	}
	
	public int getXCoor() {
		return x;
	}

	public void setXCoor(int x) {
		this.x = x;
	}

	public int getYCoor() {
		return y;
	}

	public void setYCoor(int y) {
		this.y = y;
	}
	

	

	public void actionPerformed(ActionEvent e) {
		
		
		
	}

	
}
