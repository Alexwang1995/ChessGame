package ChessPiece;

import java.util.ArrayList;
import java.util.List;

import ChessGame.Board;
import ChessGame.KillingMove;
import ChessGame.Move;
import ChessGame.Square;
import ChessGame.helperFunctions;
import ChessGame.nonKillingMove;

public class Knight extends Piece {

	public Knight(int piecePosition, PieceColor pieceColor, Type pieceType) {
		super(piecePosition, pieceColor, pieceType);
		// TODO Auto-generated constructor stub
	}
	//suppose the Knight is at position x, then all the possible destinations are x¡À6, x¡À10, x¡À15, x¡À17
	private static int[] OtherPossibleOffset = {6,-6,10,-10,15,-15,17,-17};
	private static int[] FirstColPossibleOffset = {-6,10,-15,17};
	private static int[] SecondColPossibleOffset = {-6,10,15,-15,17,-17};
	private static int[] SeventhColPossibleOffset = {6,-10,15,-15,17,-17};
	private static int[] EighthColPossibleOffset = {6,-10,15,-17};
	private static int[] PossibleOffsetList = null;
	
	
	/**
	 * find all the possible moves that a knight could make from its current position
	 * @param board the board configuration
	 * @return PossiblePos a list of Move objects
	 */
	@Override
	public List<Move> getAllPossiblePos (Board board){
		int finalIndex;
		List<Move> possiblePos = new ArrayList<>();
		if(helperFunctions. InFirstColumn(this.piecePosition) == true){
			PossibleOffsetList = FirstColPossibleOffset; 
		}
		
		if(helperFunctions.InSecondColumn(this.piecePosition) == true){
			PossibleOffsetList = SecondColPossibleOffset;
		}
		
		if(helperFunctions.InSeventhColumn(this.piecePosition) == true){
			PossibleOffsetList = SeventhColPossibleOffset;
		}
		
		if(helperFunctions.InEighthColumn(this.piecePosition) == true){
			PossibleOffsetList = EighthColPossibleOffset;
		}
		
		if(helperFunctions.notInThoseFour(this.piecePosition) == true){
			PossibleOffsetList = OtherPossibleOffset;
		}
		for (int curIndex : PossibleOffsetList){
			finalIndex = curIndex + this.piecePosition;
			
			if(finalIndex < 64 && finalIndex >= 0){
				Square destSquare = board.getSquare(finalIndex);
				
				// if some piece is already on the destination square
				if(destSquare.isOccupied() == true){
					//get that piece
					Piece destPiece = destSquare.getPiece();
					//Destination piece is opponent's, then is a possible move.
					if (this.pieceColor != destPiece.pieceColor){
						//add it to the list
						possiblePos.add(new KillingMove (board, this, finalIndex, destPiece));
					}	
				}
				
				// no piece on the destination square
				else{
					possiblePos.add(new nonKillingMove(board, this, finalIndex));
				}
			}
		}
		return possiblePos;
		
	}
	
	@Override
	public Type getPieceType (Piece piece){
		return Type.KNIGHT;
	}
	
	
	
	
	public String toString(){
		return "N  ";
	}
	
	
	
}
