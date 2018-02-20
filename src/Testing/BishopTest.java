package Testing;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ChessGame.Board;



public class BishopTest {
	//check whether bishop could move to empty space
	@Test
	public void BishopMoveToEmpty(){
		Board board = Board.initialBoard();
	
		
	
		board.makeMove(board, board.getSquare(11).getPiece(), 19);
		board.makeMove(board, board.getSquare(2).getPiece(), 11);
		assertEquals(board.getSquare(11).getPiece().toString(), "B  ");
		
	}
	
	@Test
	public void BishopKillOther(){
		Board board = Board.initialBoard();
		
		
		
		board.makeMove(board, board.getSquare(55).getPiece(), 47);//h2 p to h3
		board.makeMove(board, board.getSquare(11).getPiece(), 19);//d7 p to d6
		board.makeMove(board, board.getSquare(2).getPiece(), 47);//c8 b to h3 eat white pawn
		
		assertEquals(board.getSquare(47).getPiece().toString(), "B  ");
	}
	
	@Test
	public void MoveOutOfBound(){
		Board board = Board.initialBoard();
		
		
		
		board.makeMove(board, board.getSquare(2).getPiece(), -1);
		assertEquals(board.getSquare(2).getPiece().toString(),"B  ");
		assertEquals(board.cantMove,true);
	}
	
	
	@Test
	public void AlreadyHadPiece(){
		Board board = Board.initialBoard();
		
		
		
		board.makeMove(board, board.getSquare(2).getPiece(), 11);
		assertEquals(board.getSquare(2).getPiece().toString(), "B  ");
		assertEquals(board.cantMove, true);
	}
	
}
