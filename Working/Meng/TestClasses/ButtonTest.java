package TestClasses;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import GameClasses.Button;

class ButtonTest {

	@Test
	public void testgetXCoor() {
		Button button = new Button();
		int result = button.getXCoor();
		assertEquals("The initialX should be:" ,0,result,1);
	}
	
	@Test
	public void testgetYCoor() {
		Button button = new Button();
		int result1 = button.getYCoor();
		assertEquals("The initialY should be:" ,0,result1,1);
	}
}
