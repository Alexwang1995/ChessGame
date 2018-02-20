package ChessPiece;

import java.util.ArrayList;
import java.util.List;

import ChessGame.Board;
import ChessGame.KillingMove;
import ChessGame.Move;
import ChessGame.Square;
import ChessGame.helperFunctions;
import ChessGame.nonKillingMove;

public class Terran extends Piece {

	//moves like king, but could only attack pawn
	public Terran(int piecePosition, PieceColor pieceColor, Type pieceType) {
		super(piecePosition, pieceColor, pieceType);
		// TODO Auto-generated constructor stub
	}
	
	private static int[] OtherPossibleOffset = {1,-1,7,-7,8,-8,9,-9};
	private static int[] FirstColPossibleOffset = {1,-7,8,-8,9};
	private static int[] EighthColPossibleOffset = {-1,7,8,-8,-9};
	private static int[] PossibleOffsetList = null;
	
	/**
	 * find all the possible moves that a terran could make from its current position
	 * @param board the board configuration
	 * @return PossiblePos a list of Move objects
	 */
	@Override
	public List<Move> getAllPossiblePos(Board board) {
		// TODO Auto-generated method stub
		int finalIndex;
		List<Move> possiblePos = new ArrayList<>();
		
		if(helperFunctions. InFirstColumn(this.piecePosition) == true){
			PossibleOffsetList = FirstColPossibleOffset; 
		}
		
		if(helperFunctions.InEighthColumn(this.piecePosition) == true){
			PossibleOffsetList = EighthColPossibleOffset;
		}
		
		if(helperFunctions.notFirstNEight(this.piecePosition) == true){
			PossibleOffsetList = OtherPossibleOffset;
		}
	
		for (int curIndex : PossibleOffsetList){
			finalIndex = this.piecePosition + curIndex;
			if(finalIndex < 64 && finalIndex >= 0){
				Square destSquare = board.getSquare(finalIndex);
				// if some piece is already on the destination square
				if(destSquare.isOccupied() == true && destSquare.getPiece().pieceType == Type.PAWN){
					//get that piece
					Piece destPiece = destSquare.getPiece();
					possiblePos.add(new KillingMove (board, this, finalIndex, destPiece));
					
					

				}
				else{
					possiblePos.add(new nonKillingMove(board, this, finalIndex));
				}
			}
		}
		
		return possiblePos;
	}
	
	
	@Override
	public Type getPieceType(Piece piece) {
		// TODO Auto-generated method stub
		return Type.TERRAN;
	}
	
	public String toString(){
		return "T  ";
	}
	
}
