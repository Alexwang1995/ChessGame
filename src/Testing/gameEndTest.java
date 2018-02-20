package Testing;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ChessGame.Board;



public class gameEndTest {
	@Test
	public void TestGameEnd(){
		Board board = Board.initialBoard();
		
		
		board.makeMove(board, board.getSquare(1).getPiece(), 18);//b8 kinght to c6
		board.makeMove(board, board.getSquare(18).getPiece(), 35);// c6 to d4
		board.makeMove(board, board.getSquare(35).getPiece(), 45);//d4 to f3
		board.makeMove(board, board.getSquare(45).getPiece(), 60);//kill white king
		
		assertEquals(board.getSquare(60).getPiece().toString(), "N  ");
		assertEquals(board.blackWin, true);
	}
}
