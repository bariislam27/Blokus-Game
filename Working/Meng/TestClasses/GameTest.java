package TestClasses;
import static org.junit.jupiter.api.Assertions.*;

import javax.naming.spi.ResolveResult;

import org.junit.jupiter.api.Test;

import GameClasses.Game;

class GameTest {

	@Test
	public void testCurrentPlayerIndex() {
		Game game = new Game(10);
		int result = game.currentPlayersIndex();
		assertEquals(0,result);		
	}
	
	@Test
	public void testupdateScorePlayer0() {
		Game game0 = new Game(10);
		int result0 = game0.updateScores(1,9);
		assertEquals(9, result0);
				
	}
	
	@Test
	public void testupdateScorePlayer1() {
		Game game1 = new Game(10);
		int result1 = game1.updateScores(1,10);
		assertEquals(10, result1);
				
	}
	
	@Test
	public void testupdateScorePlayer2() {
		Game game2 = new Game(10);
		int result2 = game2.updateScores(2,15);
		assertEquals(15, result2);			
	}
	
	@Test
	public void testupdateScorePlayer3() {
		Game game3 = new Game(10);
		int result3 = game3.updateScores(3,18);
		assertEquals(18, result3);			
	}

}
