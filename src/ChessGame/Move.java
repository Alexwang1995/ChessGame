package ChessGame;

import ChessPiece.Piece;


public abstract class Move {

	Board board;
	Piece movingPiece;
	int destinationPos;
	
	Move(Board board, Piece movingPiece, int destinationPos){
		this.board = board;
		this.movingPiece = movingPiece;
		this.destinationPos = destinationPos;
		
	}
	
	/**
	 * execute the move that was made by the player
	 * @return myBoard the board configuration after the move was made
	 */
	public Board exucteMove (){
		int pieceCurPos = this.movingPiece.getPiecePosition();
		Board myBoard = this.board;
		this.movingPiece.updatePiecePos(destinationPos);
		

		
		myBoard.getGameBoard()[pieceCurPos] = new usableSquare(pieceCurPos);
		myBoard.getGameBoard()[this.destinationPos] = new notusableSquare(destinationPos, this.movingPiece);
		
		return myBoard;
	}
	
	public int getDestPos(){
		return this.destinationPos;
	}
	
	public Piece getMovePiece(){
		return this.movingPiece;
	}
	
	public Board getBoard (){
		return this.board;
	}
}
