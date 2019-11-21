package TestClasses;
import static org.junit.jupiter.api.Assertions.*;

import javax.naming.spi.ResolveResult;

import org.junit.jupiter.api.Test;

class GameTest {

	@Test
	public void testCurrentPlayerIndex() {
		Game game = new Game(10);
		int result = game.currentPlayersIndex();
		assertEquals(0,result);		
	}

}
