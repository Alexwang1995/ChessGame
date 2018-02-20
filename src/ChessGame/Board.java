/**
 * Board class, creating the board for the chess game
 */
package ChessGame;



import java.util.ArrayList;
import java.util.List;

import ChessPiece.Bishop;
import ChessPiece.PieceColor;
import ChessPiece.King;
import ChessPiece.Knight;
import ChessPiece.Pawn;
import ChessPiece.Piece;
import ChessPiece.Protoss;
import ChessPiece.Queen;
import ChessPiece.Rook;
import ChessPiece.Terran;
import ChessPiece.Type;


public class Board {

	private Square[] gameBoard;
	
	
	public boolean checkMate;
	public boolean cantMove;
	public boolean whiteWin;
	public boolean blackWin;
	public PieceColor currentTurn;
	public static int BlackScore = 0;
	public static int WhiteScore = 0;
	public int kingPos;
	
	public Board(BoardBuilder builder){
		gameBoard = createBoard(builder); 
		currentTurn = PieceColor.WHITE;
		checkMate = false;
		cantMove = false;
		whiteWin = false;
		blackWin = false;
	}
	
	public Board boardCopy(){
		Board result = Board.initialBoard();
		for (int i = 0; i < 64; i++){
			result.gameBoard[i] = this.gameBoard[i];
		}
		result.currentTurn = this.currentTurn;
		result.blackWin = this.blackWin;
		result.whiteWin = this.whiteWin;
		result.checkMate = this.checkMate;
		result.cantMove = this.cantMove;
		
		return result;
	}

	
	/**
	 * call the builder and create the board
	 * @param builder  the board builder
	 * @return an array of squares
	 */

	private static Square[] createBoard(BoardBuilder builder){
		Square[] mySquares = new Square[64];
		for (int i = 0; i < 64; i++){
			mySquares[i] = Square.createSquare(builder.boardInfo.get(i), i);
		}
		return mySquares;
	}
	
	public Square getSquare(int finalIndex) {
		// TODO Auto-generated method stub
		return gameBoard[finalIndex];
	}
	
	/**
	 * initialize the builder, set up the pieces and build the board
	 * @return the board being build
	 */
	public static Board initialBoard(){
		//create a builder
		BoardBuilder builder = new BoardBuilder(); 
		//change the "settings" in the builder
		
		builder.setPiece(new Rook(56,PieceColor.WHITE,Type.ROOK));
		builder.setPiece(new Knight(57,PieceColor.WHITE,Type.KNIGHT));
		builder.setPiece(new Bishop(58,PieceColor.WHITE,Type.BISHOP));
		builder.setPiece(new Queen(59,PieceColor.WHITE,Type.QUEEN));
		builder.setPiece(new King(60,PieceColor.WHITE,Type.KING));
		builder.setPiece(new Bishop(61,PieceColor.WHITE,Type.BISHOP));
		builder.setPiece(new Knight(62,PieceColor.WHITE,Type.KNIGHT));
		builder.setPiece(new Rook(63,PieceColor.WHITE,Type.ROOK));
		builder.setPiece(new Pawn(48,PieceColor.WHITE,Type.PAWN));
		builder.setPiece(new Pawn(49,PieceColor.WHITE,Type.PAWN));
		builder.setPiece(new Pawn(50,PieceColor.WHITE,Type.PAWN));
		builder.setPiece(new Pawn(51,PieceColor.WHITE,Type.PAWN));
		builder.setPiece(new Pawn(52,PieceColor.WHITE,Type.PAWN));
		builder.setPiece(new Pawn(53,PieceColor.WHITE,Type.PAWN));
		builder.setPiece(new Pawn(54,PieceColor.WHITE,Type.PAWN));
		builder.setPiece(new Pawn(55,PieceColor.WHITE,Type.PAWN));
		
		
		
		
		builder.setPiece(new Rook(0,PieceColor.BLACK,Type.ROOK));
		builder.setPiece(new Knight(1,PieceColor.BLACK,Type.KNIGHT));
		builder.setPiece(new Bishop(2,PieceColor.BLACK,Type.BISHOP));
		builder.setPiece(new Queen(3,PieceColor.BLACK,Type.QUEEN));
		builder.setPiece(new King(4,PieceColor.BLACK,Type.KING));
		builder.setPiece(new Bishop(5,PieceColor.BLACK,Type.BISHOP));
		builder.setPiece(new Knight(6,PieceColor.BLACK,Type.KNIGHT));
		builder.setPiece(new Rook(7,PieceColor.BLACK,Type.ROOK));
		builder.setPiece(new Pawn(8,PieceColor.BLACK,Type.PAWN));
		builder.setPiece(new Pawn(9,PieceColor.BLACK,Type.PAWN));
		builder.setPiece(new Pawn(10,PieceColor.BLACK,Type.PAWN));
		builder.setPiece(new Pawn(11,PieceColor.BLACK,Type.PAWN));
		builder.setPiece(new Pawn(12,PieceColor.BLACK,Type.PAWN));
		builder.setPiece(new Pawn(13,PieceColor.BLACK,Type.PAWN));
		builder.setPiece(new Pawn(14,PieceColor.BLACK,Type.PAWN));
		builder.setPiece(new Pawn(15,PieceColor.BLACK,Type.PAWN));
		
		return builder.buildBoard();
	}
	
	public Square[] getGameBoard(){
		return this.gameBoard;
	}
	
	public void makeMove (Board board, Piece movingPiece, int destPosition){
		if(currentTurn != movingPiece.getPieceColor()){
			cantMove = true;
			System.out.println("This is Not your turn!");
		}else{	
			if(!(destPosition < 64 && destPosition >= 0)){
				cantMove = true;
				return;
			}
		
		
			Square destSquare = board.getSquare(destPosition);
			Move playerMove = null;
		
			if(destSquare.isOccupied()){
				Piece destPiece = destSquare.getPiece();
				//game end condition
				if(destPiece.getPieceType(destPiece) == Type.KING && movingPiece.getPieceColor() != destPiece.getPieceColor()){
					//opponent's king being attacked
					if(movingPiece.getPieceColor() == PieceColor.BLACK){
						blackWin = true;
					}
					else{
						whiteWin = true;
					}
			
				}
			
				if(destPiece.getPieceColor() == movingPiece.getPieceColor()){
				
					cantMove = true; //you already have another piece on that spot
					return;
			
				}
				else{
				
					playerMove = new KillingMove(board, movingPiece, destPosition, destPiece);
				}
			}
			else{
				playerMove = new nonKillingMove(board, movingPiece, destPosition);
			}
			List<Move> curPossibleMove = movingPiece.getAllPossiblePos(board);
				
				List<Integer> PossiblePos = new ArrayList<>(); 
		
				for(Move i : curPossibleMove){
					
					PossiblePos.add(i.getDestPos());
				
				}
				
				if(PossiblePos.contains(playerMove.getDestPos())){
				
					playerMove.exucteMove();
					
					List<Integer> AllPossiblePos = new ArrayList<>();
					List<Integer> KingPossiblePos = new ArrayList<>();
					
					for(int i = 0; i < 64; i++){
						Square curS = board.getSquare(i);
						if(curS.isOccupied()){
							Piece curP = curS.getPiece();
							if(curP.getPieceColor() == movingPiece.getPieceColor()){
								List<Move> curMoveList = curP.getAllPossiblePos(board);
								
								for(Move j : curMoveList){
									AllPossiblePos.add(j.getDestPos());
									
									
								}
							}
						}
					}
					System.out.println("\n\n\n\n");
					for(int i = 0; i < 64; i++){
						Square findKingS = board.getSquare(i);
						if(findKingS.isOccupied()){
							Piece findKingP = findKingS.getPiece();
							if(findKingP.getPieceType(findKingP) == Type.KING && findKingP.getPieceColor() != movingPiece.getPieceColor()){
								kingPos = findKingP.getPiecePosition();
								System.out.println(findKingP.getPiecePosition());
								List<Move> findKingList = findKingP.getAllPossiblePos(board);
								
								for(Move k : findKingList){
									KingPossiblePos.add(k.getDestPos());
									
								}
								
							}
						}
						
					}
					if(KingPossiblePos.isEmpty() && AllPossiblePos.contains(kingPos)){
						
						checkMate = true;
						System.out.println("Check Mate!");
						if(movingPiece.getPieceColor() == PieceColor.BLACK){
							BlackScore ++;
						}else{
							WhiteScore ++;
						}
						System.out.println("Current Score: Black " + BlackScore + "        White " + WhiteScore);
					}
					
					
					
					if(AllPossiblePos.containsAll(KingPossiblePos) && KingPossiblePos.isEmpty() == false){
						checkMate = true;
						System.out.println("Check Mate!");
						if(movingPiece.getPieceColor() == PieceColor.BLACK){
							BlackScore ++;
						}else{
							WhiteScore ++;
						}
						System.out.println("Current Score: Black " + BlackScore + "        White " + WhiteScore);
					}
					
					
					if(movingPiece.getPieceColor() == PieceColor.BLACK){
						currentTurn = PieceColor.WHITE;
					}
					else{
						currentTurn = PieceColor.BLACK;
					}
				}
				else{
					currentTurn = movingPiece.getPieceColor();
					cantMove = true;
					System.out.println("Invalid Move!");
				}
			
			}
		}

	public static Board initialCustomBoard() {
		// TODO Auto-generated method stub
		//create a builder
				BoardBuilder builder = new BoardBuilder(); 
				//change the "settings" in the builder
				
				builder.setPiece(new Rook(56,PieceColor.WHITE,Type.ROOK));
				builder.setPiece(new Knight(57,PieceColor.WHITE,Type.KNIGHT));
				builder.setPiece(new Bishop(58,PieceColor.WHITE,Type.BISHOP));
				builder.setPiece(new Queen(59,PieceColor.WHITE,Type.QUEEN));
				builder.setPiece(new King(60,PieceColor.WHITE,Type.KING));
				builder.setPiece(new Bishop(61,PieceColor.WHITE,Type.BISHOP));
				builder.setPiece(new Knight(62,PieceColor.WHITE,Type.KNIGHT));
				builder.setPiece(new Rook(63,PieceColor.WHITE,Type.ROOK));
				builder.setPiece(new Pawn(48,PieceColor.WHITE,Type.PAWN));
				builder.setPiece(new Pawn(49,PieceColor.WHITE,Type.PAWN));
				builder.setPiece(new Pawn(50,PieceColor.WHITE,Type.PAWN));
				builder.setPiece(new Pawn(51,PieceColor.WHITE,Type.PAWN));
				builder.setPiece(new Pawn(52,PieceColor.WHITE,Type.PAWN));
				builder.setPiece(new Pawn(53,PieceColor.WHITE,Type.PAWN));
				builder.setPiece(new Pawn(54,PieceColor.WHITE,Type.PAWN));
				builder.setPiece(new Pawn(55,PieceColor.WHITE,Type.PAWN));
				builder.setPiece(new Protoss(41,PieceColor.WHITE,Type.PROTOSS));
				builder.setPiece(new Terran(46, PieceColor.WHITE,Type.TERRAN));
				
				
				builder.setPiece(new Rook(0,PieceColor.BLACK,Type.ROOK));
				builder.setPiece(new Knight(1,PieceColor.BLACK,Type.KNIGHT));
				builder.setPiece(new Bishop(2,PieceColor.BLACK,Type.BISHOP));
				builder.setPiece(new Queen(3,PieceColor.BLACK,Type.QUEEN));
				builder.setPiece(new King(4,PieceColor.BLACK,Type.KING));
				builder.setPiece(new Bishop(5,PieceColor.BLACK,Type.BISHOP));
				builder.setPiece(new Knight(6,PieceColor.BLACK,Type.KNIGHT));
				builder.setPiece(new Rook(7,PieceColor.BLACK,Type.ROOK));
				builder.setPiece(new Pawn(8,PieceColor.BLACK,Type.PAWN));
				builder.setPiece(new Pawn(9,PieceColor.BLACK,Type.PAWN));
				builder.setPiece(new Pawn(10,PieceColor.BLACK,Type.PAWN));
				builder.setPiece(new Pawn(11,PieceColor.BLACK,Type.PAWN));
				builder.setPiece(new Pawn(12,PieceColor.BLACK,Type.PAWN));
				builder.setPiece(new Pawn(13,PieceColor.BLACK,Type.PAWN));
				builder.setPiece(new Pawn(14,PieceColor.BLACK,Type.PAWN));
				builder.setPiece(new Pawn(15,PieceColor.BLACK,Type.PAWN));
				builder.setPiece(new Protoss(17,PieceColor.BLACK,Type.PROTOSS));
				builder.setPiece(new Terran(22, PieceColor.BLACK,Type.TERRAN));
				
				
				return builder.buildBoard();
		
		
	}
	
}
