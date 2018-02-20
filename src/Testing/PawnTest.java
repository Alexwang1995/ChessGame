package Testing;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ChessGame.Board;
import ChessPiece.PieceColor;


public class PawnTest {
	//check whether pawn could move to empty space
	@Test
	public void PawnMoveToEmpty(){
		Board board = Board.initialBoard();
		
		
		board.makeMove(board, board.getSquare(8).getPiece(), 16);//black a7 p to a 6
		assertEquals(board.getSquare(16).getPiece().toString(),"P  ");
		
		board.makeMove(board, board.getSquare(9).getPiece(), 25);//b7 p to b5, pawn jump
		assertEquals(board.getSquare(25).getPiece().toString(),"P  ");
	}
	
	@Test
	public void PawnAttack(){
		Board board = Board.initialBoard();
		
		
		board.makeMove(board, board.getSquare(8).getPiece(), 24);//a7 p jump to a5
		board.makeMove(board, board.getSquare(49).getPiece(), 33);//b2 white p jump to b4
		board.makeMove(board, board.getSquare(24).getPiece(), 33);//a5 p attack b4 p
		
		assertEquals(board.getSquare(33).getPiece().getPieceColor(), PieceColor.BLACK);//b4 should now be black pawn
		assertEquals(board.getSquare(33).getPiece().toString(),"P  ");
	}
	
	
}
