package TestClasses;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.migrationsupport.rules.adapter.ExpectedExceptionAdapter;

class PieceTest {

	@Test
	public void testgetAllShapes() {
		Piece piece = new Piece();
		int result = piece.getAllShapes()[0][0][0];
		assertEquals(0,result);
	}
}
