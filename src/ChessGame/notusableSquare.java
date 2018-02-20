package ChessGame;

import ChessPiece.PieceColor;
import ChessPiece.Piece;

public final class notusableSquare extends Square {
	
	Piece pieceOnBoard;
	
	notusableSquare(int position, Piece pieceOnBoard){
		super(position);
		this.pieceOnBoard = pieceOnBoard;
	}
	
	@Override
	public boolean isOccupied (){
		return true;
	}
	@Override
	public Piece getPiece (){
		return this.pieceOnBoard;
	}
	@Override
	public String toString(){
		Piece piece = getPiece();
		PieceColor color = piece.getPieceColor();
		if(color == PieceColor.BLACK){
			 return piece.toString().toUpperCase(); // black piece upper case
		}
		else{
			return piece.toString().toLowerCase();// white piece lower case
		}
	}
}
