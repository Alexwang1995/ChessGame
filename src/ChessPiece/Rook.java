 package ChessPiece;

import java.util.ArrayList;
import java.util.List;

import ChessGame.Board;
import ChessGame.KillingMove;
import ChessGame.Move;
import ChessGame.Square;
import ChessGame.helperFunctions;
import ChessGame.nonKillingMove;

public class Rook extends Piece {

	public Rook(int piecePosition, PieceColor pieceColor, Type pieceType) {
		super(piecePosition, pieceColor, pieceType);
		// TODO Auto-generated constructor stub
	}
	
	private static int [] PossibleOffsetVector = null;
	private static int [] OtherOffsetVector = {1, -1, 8, -8};
	private static int [] FirstColPossibleVector = {1,8,-8};
	private static int [] EightColPossibleVector = {-1,8,-8};
	
	/**
	 * find all the possible moves that a rook could make from its current position
	 * @param board the board configuration
	 * @return PossiblePos a list of Move objects
	 */
	@Override
	public List<Move> getAllPossiblePos (Board board){
		int finalIndex;
		List<Move> possiblePos = new ArrayList<>();
		if(helperFunctions.InFirstColumn(this.piecePosition) == true){
			PossibleOffsetVector = FirstColPossibleVector;
		}
		if(helperFunctions.InEighthColumn(this.piecePosition) == true){
			PossibleOffsetVector = EightColPossibleVector;
		}
		if(helperFunctions.notFirstNEight(this.piecePosition) == true){
			PossibleOffsetVector = OtherOffsetVector;
		}
		
		for (int curIndex : PossibleOffsetVector){
			finalIndex = this.piecePosition;
			while(finalIndex < 64 && finalIndex >= 0){
				finalIndex = finalIndex + curIndex; 
				if(finalIndex < 64 && finalIndex >= 0){
					Square destSquare = board.getSquare(finalIndex);
					if(destSquare.isOccupied() == true){
						Piece destPiece = destSquare.getPiece();
						if(this.pieceColor != destPiece.pieceColor){
							possiblePos.add(new KillingMove(board, this, finalIndex, destPiece));
						}
						break;
					}
					else{
						possiblePos.add(new nonKillingMove(board, this, finalIndex));
					}
					
				}
				
			}
		}
		return possiblePos;
		
		
	}
	
	
	public Type getPieceType(Piece piece){
		return Type.ROOK;
		
	}
	
	public String toString(){
		return "R  ";
	}	
}
