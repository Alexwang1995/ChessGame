package ChessGame;

import java.util.HashMap;
import java.util.Map;

import ChessPiece.Piece;

public abstract class Square {
	int position; 
	

	private static Map<Integer, usableSquare> SquareCache = createUsableSquares();
	
	/**
	 * store some empty usable Square objects into the hashmap
	 * @return emptySquareMap a hashmap storing usable squares
	 */
	private static Map<Integer, usableSquare> createUsableSquares(){
		Map <Integer, usableSquare> emptySquareMap = new HashMap<>();
		for(int i = 0; i<= 63; i++) {
			emptySquareMap.put(i, new usableSquare (i));
		}
		return emptySquareMap;
	}
	
	public static Square createSquare (Piece piece, int piecePosition){
		if (piece == null){
			return SquareCache.get(piecePosition);
		}
		else{
			return new notusableSquare(piecePosition, piece);
		}
	}
	
	
	Square(int position) {
		this.position = position;
	}
	
	public abstract boolean isOccupied();
	
	public abstract Piece getPiece();
	
	public int getSquarePos(){
		return this.position;
	}

}	


