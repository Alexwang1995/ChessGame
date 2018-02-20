package ChessPiece;

import java.util.ArrayList;
import java.util.List;

import ChessGame.Board;
import ChessGame.KillingMove;
import ChessGame.Move;
import ChessGame.Square;
import ChessGame.helperFunctions;
import ChessGame.nonKillingMove;

public class Protoss extends Piece {
	//every move is a jump, and move over other pieces
	public Protoss(int piecePosition, PieceColor pieceColor, Type pieceType) {
		super(piecePosition, pieceColor, pieceType);
		// TODO Auto-generated constructor stub
	}
	private static int[] OtherPossibleOffset = {2,-2,16,-16};
	private static int[] SecondColPossibleOffset = {2,16,-16};
	private static int[] FirstColPossibleOffset = {2,16,-16};
	private static int[] SeventhColPossibleOffset = {-2,16,-16};
	private static int[] EighthColPossibleOffset = {-2,16,-16};
	private static int[] PossibleOffsetList = null;
	
	
	/**
	 * find all the possible moves that a protoss could make from its current position
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
			finalIndex = this.piecePosition + curIndex;
			if(finalIndex < 64 && finalIndex >= 0){
				Square destSquare = board.getSquare(finalIndex);
				// if some piece is already on the destination square
				if(destSquare.isOccupied()){
					//get that piece
					Piece destPiece = destSquare.getPiece();
					//Destination piece is opponent's, then is a possible move.
					if (this.pieceColor != destPiece.pieceColor){
						//add it to the list
						possiblePos.add(new KillingMove (board, this, finalIndex, destPiece));
					}	

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
		return Type.PROTOSS;
	}
	
	public String toString(){
		return "S  ";
	}
}
