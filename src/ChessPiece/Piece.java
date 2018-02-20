package ChessPiece;

import java.util.List;

import ChessGame.Board;
import ChessGame.Move;

public abstract class Piece {
	
	// a piece has its coordinate, color and its type (King, Knight, Queen etc.)
	int piecePosition ;
	PieceColor pieceColor; 
	Type pieceType;
	
	
	public int getPiecePosition(){
		return this.piecePosition;
	}
	
	public PieceColor getPieceColor(){
		return this.pieceColor;
	}
	
	// constructor
	protected Piece(int piecePosition, PieceColor pieceColor, Type pieceType){
		this.pieceColor = pieceColor;
		this.piecePosition = piecePosition;
		this.pieceType = pieceType;
	}
	// find out all the possible destination position
	public abstract List<Move> getAllPossiblePos (Board board);
	
	public abstract Type getPieceType (Piece piece);

	public void updatePiecePos(int destPos){
		this.piecePosition = destPos;
	}
	
}
	

