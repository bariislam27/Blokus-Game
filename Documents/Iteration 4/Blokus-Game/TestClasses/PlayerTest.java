package TestClasses;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.Test;

import GameClasses.Player;

class PlayerTest {

	@Test
	public void testisFirstMove() {
		Player player = new Player(Color.RED);
		boolean flag = player.getFirstMove();
		assertEquals(true, flag);
	}

}
