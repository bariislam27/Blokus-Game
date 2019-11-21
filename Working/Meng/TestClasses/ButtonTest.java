package TestClasses;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ButtonTest {

	@Test
	public void testgetX() {
		Button button = new Button();
		int result = button.getX();
		assertEquals(0,result);
	}
	
	@Test
	public void testgetY() {
		Button button = new Button();
		int result1 = button.getY();
		assertEquals(0,result1);
	}
}
