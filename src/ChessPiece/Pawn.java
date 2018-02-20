package ChessPiece;

import java.util.ArrayList;
import java.util.List;

import ChessGame.Board;
import ChessGame.KillingMove;
import ChessGame.Move;
import ChessGame.helperFunctions;
import ChessGame.nonKillingMove;

public class Pawn extends Piece {
	
	Boolean couldJump; //if the pawn is in its first move, it could jump
	public Pawn(int piecePosition, PieceColor pieceColor, Type pieceType) {
		super(piecePosition, pieceColor, pieceType);
		// TODO Auto-generated constructor stub
	
	}

	private static int [] PossibleOffsetVector = {8,16,7,9};
	
	
	/**
	 * find all the possible moves that a pawn could make from its current position
	 * @param board the board configuration
	 * @return PossiblePos a list of Move objects
	 */
	@Override
	public List<Move> getAllPossiblePos(Board board) {
		// TODO Auto-generated method stub
		int finalIndex = 0;
		List<Move> possiblePos = new ArrayList<>();
	
		for (int curIndex : PossibleOffsetVector){
			if(this.pieceColor == PieceColor.WHITE){
				finalIndex = (-1) * curIndex + this.piecePosition;
			}
			if(this.pieceColor == PieceColor.BLACK){
				finalIndex = curIndex + this.piecePosition;
			}
			
			if(finalIndex < 64 && finalIndex >= 0){
				//non-killing pawn move
				if(board.getSquare(finalIndex).isOccupied() == false && curIndex == 8){
					possiblePos.add(new nonKillingMove(board, this, finalIndex));
				}
				//pawn jump condition is satisfied
				else if (curIndex == 16 && 
						(this.pieceColor == PieceColor.BLACK && helperFunctions.InSecondRow(this.piecePosition)) ||
						(this.pieceColor == PieceColor.WHITE && helperFunctions.InSeventhRow(this.piecePosition))){
					// FirstJumpIndex is the first position right above the current position.
					int FirstJumpIndex = 0;
					if (this.pieceColor == PieceColor.WHITE){
						FirstJumpIndex = this.piecePosition - 8;
					}
					if (this.pieceColor == PieceColor.BLACK){
						FirstJumpIndex = this.piecePosition + 8;
					}
					if(board.getSquare(FirstJumpIndex).isOccupied() == false && board.getSquare(finalIndex).isOccupied() == false){
						possiblePos.add(new nonKillingMove(board, this, finalIndex));
					}
				}
				//pawn kills
				//if white pawn in first column, it cannot attack x-9, when its in eighth column, it cannot attack x-7
				//black pawn x+9, x+7	
				else if(curIndex == 9 && 
						(helperFunctions.InFirstColumn(this.piecePosition) == false && this.pieceColor == PieceColor.WHITE)|| // if it's white and not in first column
						(helperFunctions.InEighthColumn(this.piecePosition) == false && this.pieceColor == PieceColor.BLACK)){//if it's black and not in eighth column
					
					if(board.getSquare(finalIndex).isOccupied() == true){
						Piece destPiece = board.getSquare(finalIndex).getPiece();
						
						if(destPiece.pieceColor != this.pieceColor){
							possiblePos.add(new KillingMove(board, this, finalIndex, destPiece));
						}
					}
				}
				else if(curIndex == 7 && 
						(helperFunctions.InEighthColumn(this.piecePosition) == false && this.pieceColor == PieceColor.WHITE)|| // if it's white and not in eighth column
						(helperFunctions.InFirstColumn(this.piecePosition) == false && this.pieceColor == PieceColor.BLACK)){//if it's black and not in first column
					
					if(board.getSquare(finalIndex).isOccupied() == true){
						
						Piece destPiece = board.getSquare(finalIndex).getPiece();
						if(destPiece.pieceColor != this.pieceColor){
							possiblePos.add(new KillingMove(board, this, finalIndex, destPiece));
						}
					}
				}
				
			}
		}
		return possiblePos;
	}

	@Override
	public Type getPieceType(Piece piece) {
		// TODO Auto-generated method stub
		return Type.PAWN;
	}
	
	
	public String toString(){
		return "P  ";
	}
}
