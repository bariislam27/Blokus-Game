import javax.swing.*;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.*;

public class Button extends JButton implements ActionListener {
    
	public int x, y;
    
	public Button() {
		super();
		setBackground(Color.WHITE);
		//setVisible(true);
		
//
//	    try {
//	            // Set System L&F
//	        UIManager.setLookAndFeel(
//	            UIManager.getSystemLookAndFeelClassName());
//	    } 
//	    catch (UnsupportedLookAndFeelException e) {
//	       // handle exception
//	    }
//	    catch (ClassNotFoundException e) {
//	       // handle exception
//	    }
//	    catch (InstantiationException e) {
//	       // handle exception
//	    }
//	    catch (IllegalAccessException e) {
//	       // handle exception
//	    }
//
//	    new Button(); //Create and show the GUI.
//	}
//		
	}	
	
	
	public void setXY(int x, int y) {
		
		this.x = x;
		this.y = y;
		
	}
	
	public int getX() {
		
		return this.x ;
		
		
	}
	

	public void actionPerformed(ActionEvent e) {
		
		
		
	}

	
}
