package ChessGame;

import ChessPiece.Piece;

public final class usableSquare extends Square{
		
		usableSquare(int position){
			super(position);
		}
		
		@Override
		public boolean isOccupied (){
			return false;
		}
		
		@Override
		public Piece getPiece (){
			return null;
		}
		
		@Override
		public String toString(){
			return "+  ";
			
		}
}
