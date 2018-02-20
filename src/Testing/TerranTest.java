package Testing;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ChessGame.Board;



public class TerranTest {
	@Test
	public void TerranKill(){
		Board board = Board.initialBoard();
		
	
		
		board.makeMove(board,board.getSquare(17).getPiece(), 24);
		board.makeMove(board,board.getSquare(24).getPiece(), 32);
		board.makeMove(board,board.getSquare(32).getPiece(), 40);
		board.makeMove(board,board.getSquare(40).getPiece(), 48);
		
		assertEquals(board.getSquare(48).getPiece().toString(), "T  ");
	}
}
