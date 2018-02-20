package Testing;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ChessGame.Board;



public class ProtossTest {
	@Test
	public void ProtossMoveToEmpty(){
		Board board = Board.initialBoard();
			
	
			
		board.makeMove(board,board.getSquare(21).getPiece(), 36);
			
		assertEquals(board.getSquare(36).getPiece().toString(), "S  ");
		
	}
}
