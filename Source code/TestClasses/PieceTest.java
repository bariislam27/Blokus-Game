package TestClasses;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.migrationsupport.rules.adapter.ExpectedExceptionAdapter;

import GameClasses.Piece;

class PieceTest {

	@Test
	public void testgetAllShapes() {
		Piece piece = new Piece();
		int result = piece.getAllShapes()[0][0][0];
		assertEquals(0, result);
	}
	
	
	@Test
	public void testgetAllShapes1() {
		Piece piece = new Piece();
		int result1 = piece.getAllShapes()[0][1][0];
		assertEquals(0, result1);
	}
	
	@Test
	public void testgetAllShapes2() {
		Piece piece = new Piece();
		int result2 = piece.getAllShapes()[1][1][1];
		assertEquals(0, result2);
	}
}
