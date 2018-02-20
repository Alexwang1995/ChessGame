package Testing;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ChessGame.Board;



public class QueenTest {
	@Test
	public void QueenMoveToEmpty(){
		Board board = Board.initialBoard();
		
		
		
		board.makeMove(board, board.getSquare(12).getPiece(),20); //b p e7 to e6
		board.makeMove(board, board.getSquare(3).getPiece(), 21);//b q d8 to f6
		board.makeMove(board, board.getSquare(21).getPiece(), 29);//f6 to f5
		
		assertEquals(board.getSquare(29).getPiece().toString(),"Q  ");
	}
}
