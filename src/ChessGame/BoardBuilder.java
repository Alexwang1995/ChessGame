/**
 * BoardBuilder class, the builder for the board
 */
package ChessGame;

import java.util.HashMap;
import java.util.Map;

import ChessPiece.PieceColor;
import ChessPiece.Piece;

public class BoardBuilder {
	Map<Integer, Piece> boardInfo;
	PieceColor nextMoveColor;
	
	public BoardBuilder(){
		boardInfo = new HashMap<>();
	}
	
	/**
	 * store the piece into the hashmap
	 * @param piece
	 * @return the builder
	 */
	public BoardBuilder setPiece(Piece piece){
		boardInfo.put(piece.getPiecePosition(), piece);
		return this;
	}
	
	public BoardBuilder setNextMoveColor(PieceColor nextMoveColor){
		this.nextMoveColor = nextMoveColor;
		return this;
	}
	
	public Board buildBoard(){
		return new Board(this);
	}
	
}
