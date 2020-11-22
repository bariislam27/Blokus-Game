package TestClasses;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.Test;

import GameClasses.Home;

class HomeTest {

	@Test
	public void testgetCPU() {
		Home home = new Home();
		int result = home.getIntCPU();
		assertEquals(0, result);

	}

	@Test
	public void testgetHuman() {
		Home home = new Home();
		int result1 = home.getIntHuman();
		assertEquals(0, result1);
	}

	@Test
	public void testPlayerColor() {
		Home home = new Home();
		Color result2 = home.getPlayerColor(2);
		assertEquals(new Color(255, 255, 0), result2);
	}
	
}
