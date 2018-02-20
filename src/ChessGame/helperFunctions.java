/**
 * contains all the helper functions
 */
package ChessGame;

public class helperFunctions {
	//check if piece's position is in first column
		public static boolean InFirstColumn (int position){
			if(position == 0 || position == 8 || position == 16 || position == 24 || position == 32 || position == 40 || position == 48 || position == 56){
				return true;
			}
			else return false;
		}
		
		public static boolean InSecondColumn (int position){
			if(position == 1 || position == 9 || position == 17 || position == 25 || position == 33 || position == 41 || position == 49 || position == 57){
				return true;
			}
			else return false;
		}
		
		public static boolean InSeventhColumn (int position){
			if(position == 6 || position == 14 || position == 22 || position == 30 || position == 38 || position == 46 || position == 54 || position == 62){
				return true;
			}
			else return false;
		}
		
		public static boolean InEighthColumn (int position){
			if(position == 7 || position == 15 || position == 23 || position == 31 || position == 39 || position == 47 || position == 55 || position == 63){
				return true;
			}
			else return false;
		}
		
		public static boolean notInThoseFour (int position){
			return (!(InFirstColumn(position) || InSecondColumn(position) || InSeventhColumn(position) || InEighthColumn(position)));
		}
		
		public static boolean notFirstNEight (int position){
			return (! (InFirstColumn(position) || InEighthColumn(position)));
		}
		
		
		public static boolean InSecondRow(int position){
			if(position == 8 ||position == 9 || position == 10 || position == 11 || position == 12 || position == 13 || position == 14 || position == 15 ){
				return true;
			}
			else return false;
		}
		
		public static boolean InSeventhRow(int position){
			if(position == 48 ||position == 49 || position == 50 || position == 51 || position == 52 || position == 53 || position == 54 || position == 55 ){
				return true;
			}
			else return false;
		}
		public static void printBoard(Board board){
			String result = "";
			for (int i = 0; i < 64; i++){
				result = result + board.getSquare(i).toString();
				if((i+1) % 8 == 0){
					result = result + "\n";
				}
			}
			System.out.println(result);
		}
}
