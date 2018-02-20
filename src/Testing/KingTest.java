package Testing;


import static org.junit.Assert.assertEquals;

import org.junit.Test;



import ChessGame.Board;



public class KingTest {
	//check whether king could move to empty space
	@Test
	public void KingMoveToEmpty(){
		Board board = Board.initialBoard();
	
		
		
		board.makeMove(board,board.getSquare(11).getPiece(), 27);
		board.makeMove(board,board.getSquare(12).getPiece(), 28);
		board.makeMove(board,board.getSquare(13).getPiece(), 29);
		board.makeMove(board,board.getSquare(4).getPiece() , 11);
		assertEquals(board.getSquare(11).getPiece().toString(), "K  ");
		
		
		String result = "";
		for (int i = 0; i < 64; i++){
			result = result + board.getSquare(i).toString();
			if((i+1) % 8 == 0){
				result = result + "\n";
			}
		}
		System.out.println(result);
	}
	
	//check whether king could kill other pieces.
	@Test
	public void KingKillOther(){
		Board board = Board.initialBoard();

		
		board.makeMove(board,board.getSquare(11).getPiece(), 27);
		board.makeMove(board,board.getSquare(12).getPiece(), 28);
		board.makeMove(board,board.getSquare(13).getPiece(), 29);
		board.makeMove(board,board.getSquare(4).getPiece() , 11);
		board.makeMove(board, board.getSquare(50).getPiece(), 34);
		board.makeMove(board, board.getSquare(34).getPiece(), 26);
		board.makeMove(board, board.getSquare(26).getPiece(), 18);
		board.makeMove(board,board.getSquare(11).getPiece() , 18);
		assertEquals(board.getSquare(18).getPiece().toString(), "K  ");
		
		String result = "";
		for (int i = 0; i < 64; i++){
			result = result + board.getSquare(i).toString();
			if((i+1) % 8 == 0){
				result = result + "\n";
			}
		}
		System.out.println(result);
		
	}
	
	@Test
	public void MoveOutOfBound(){
		Board board = Board.initialBoard();
		
		
		//test, let black king move to -1
		board.makeMove(board, board.getSquare(0).getPiece(), -1);
		assertEquals(board.getSquare(0).getPiece().toString(),"R  ");
		assertEquals(board.cantMove,true);
	}
	
	
	@Test
	public void AlreadyHadPiece(){
		Board board = Board.initialBoard();
		
		
		//test, let black king move from 4 to 12 which already has a black pawn.
		board.makeMove(board, board.getSquare(4).getPiece(), 12);
		assertEquals(board.getSquare(4).getPiece().toString(), "K  ");
		assertEquals(board.cantMove, true);
	}
}
