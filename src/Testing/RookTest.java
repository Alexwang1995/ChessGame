package Testing;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ChessGame.Board;



public class RookTest {
	@Test
	public void RookMoveToEmpty(){
		Board board = Board.initialBoard();
		
		
		
		board.makeMove(board, board.getSquare(8).getPiece(), 24);//a7 pawn jump to a5
		board.makeMove(board, board.getSquare(0).getPiece(), 16);//a8 rook to a6
		board.makeMove(board, board.getSquare(16).getPiece(), 23);//a6 rook to h6
		
		assertEquals(board.getSquare(23).getPiece().toString(), "R  ");
	
	}
	
	@Test
	public void RookKillOther(){
		Board board = Board.initialBoard();
		
		
		
		board.makeMove(board, board.getSquare(8).getPiece(), 24);//a7 pawn jump to a5
		board.makeMove(board, board.getSquare(0).getPiece(), 16);//a8 rook to a6
		board.makeMove(board, board.getSquare(16).getPiece(), 23);//a6 rook to h6
		board.makeMove(board, board.getSquare(23).getPiece(), 55);//h6 rook attack h2 white pawn
		assertEquals(board.getSquare(55).getPiece().toString(),"R  ");
	}
}
