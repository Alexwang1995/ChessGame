package ChessGame;

import ChessPiece.Piece;

public class KillingMove extends Move {
	
	Piece killedPiece;
	
	public KillingMove(Board board, Piece movingPiece, int destinationPos, Piece killedPiece) {
		super(board, movingPiece, destinationPos);
		// TODO Auto-generated constructor stub
		this.killedPiece = killedPiece;
	}

}
