/**
 * GUI for the chess game
 */
package ChessGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


import ChessGame.Board;
import ChessGame.Square;
import ChessGame.helperFunctions;
import ChessPiece.Piece;
import ChessPiece.PieceColor;



public class Frame {
	
	private JFrame frame;
	private BoardPanel boardPanel;
	private Dimension frameDimension = new Dimension (800,800);
	private Board board;
	private Board preBoard;
	private Square sourceSquare;
	private Piece movingPiece;
	private Square destinationSquare;
	private int originPos;
	
	
	
	public Frame (){
		
		this.frame = new JFrame("Minghan's ChessGame");
		this.frame.setSize(frameDimension);
		this.frame.setLayout(new BorderLayout());
		JMenuBar menuBar = createMenu();
		this.frame.setJMenuBar(menuBar);
		this.board = Board.initialBoard();
		System.out.println(board.getGameBoard());
		this.preBoard = Board.initialBoard();
		System.out.println(preBoard.getGameBoard());
		this.boardPanel = new BoardPanel();
		this.frame.add(boardPanel, BorderLayout.CENTER);
		this.frame.setVisible(true);
		
		
	}
	
	
	/**
	 * create the menu
	 * @return a JManuBar
	 */
	
	private JMenuBar createMenu() {
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(createGameLoopMenu());
		return menuBar;
	}
	
	/**
	 * adding the restart function and undo function
	 * @return
	 */
	private JMenu createGameLoopMenu(){
		JMenu gameLoop = new JMenu("GameLoop");
		
		
		JMenuItem restart = new JMenuItem("Restart");
		restart.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				board = Board.initialBoard();
				boardPanel.createBoard(board);
				board.checkMate = false;
				
			}
		});
		gameLoop.add(restart);	
		
		
		
		JMenuItem custom = new JMenuItem("Custom Game");
		custom.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				board = Board.initialCustomBoard();
				helperFunctions.printBoard(board);
				boardPanel.createBoard(board);
				board.checkMate = false;
				
			}
		});
		gameLoop.add(custom);
		
		
		JMenuItem undo = new JMenuItem("Undo");
		undo.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				board = preBoard;
				System.out.println(preBoard.currentTurn);
				boardPanel.createBoard(board);
			}
		});
		gameLoop.add(undo);
		
		return gameLoop;
	}

	/**
	 * BoardPanel class
	 * @author AlexWang
	 *
	 */
	private class BoardPanel extends JPanel{
		List<SquarePanel> SquareOnBoard;
		
		BoardPanel(){
			super(new GridLayout(8,8));
			removeAll();
			this.SquareOnBoard = new ArrayList<>();
			for(int i = 0; i < 64;i++){
				SquarePanel squarePanel = new SquarePanel(i);
				SquareOnBoard.add(squarePanel);
				add(squarePanel);
			}
			
			
			setPreferredSize(new Dimension(700,700));
			validate();
		}
		
		public void createBoard(Board board){
			removeAll();
			for(SquarePanel squarePanel: SquareOnBoard){
				squarePanel.drawSquare(board);
				add(squarePanel);
			}
			validate();
			repaint();
		}
		
	}
	
	/**
	 * SquarePanel class
	 * @author AlexWang
	 *
	 */
	private class SquarePanel extends JPanel{
		
		private int SquarePosition;
		
		SquarePanel( int SquarePosition){
			super(new GridBagLayout());
			this.SquarePosition = SquarePosition;
			setPreferredSize(new Dimension(10,10));
			giveColor();
			giveImage(board);
			
			addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
					if(SwingUtilities.isLeftMouseButton(arg0)){
						if(sourceSquare == null){
							sourceSquare = board.getSquare(SquarePosition);
							movingPiece = sourceSquare.getPiece();
							originPos = movingPiece.getPiecePosition();
							drawSelected(board);
							//System.out.println(movingPiece.toString());
							if(movingPiece == null){
								sourceSquare = null;
							}
						}else{
							destinationSquare = board.getSquare(SquarePosition);
							
							preBoard = board.boardCopy();
							
							System.out.println(preBoard.getGameBoard());
							System.out.println(board.getGameBoard());
							board.makeMove(board, sourceSquare.getPiece(), destinationSquare.getSquarePos());
							System.out.println(preBoard.getGameBoard());
							helperFunctions.printBoard(board);
							helperFunctions.printBoard(preBoard);
							//System.out.println(board.currentTurn);
							//System.out.println("moving from " + originPos + "to " + destinationSquare.getSquarePos());
							drawSquare(board);
							boardPanel.SquareOnBoard.get(originPos).drawEmpty();
							if(board.cantMove == true){
								boardPanel.SquareOnBoard.get(originPos).drawSquare(board);
							}
							
							//helperFunctions.printBoard(board);
							sourceSquare = null;
							destinationSquare = null;
							movingPiece = null;
						}
						
							
					}else if(SwingUtilities.isRightMouseButton(arg0)){
						sourceSquare = null;
						destinationSquare = null;
						movingPiece = null;
						drawSquare(board);
					}
					
					
				}

				@Override
				public void mouseEntered(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mousePressed(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
			});
			
			validate();
		}
		public void drawSquare(Board board) {
			// TODO Auto-generated method stub
			removeAll();
			giveColor();
			giveImage(board);
			validate();
			repaint();
		}
		
		
		public void drawSelected(Board board) {
			// TODO Auto-generated method stub
			removeAll();
			giveColor();
			giveImageSelected(board);
			validate();
			repaint();
		}
		
		/**
		 * draw the square, only color but no picture
		 */
		public void drawEmpty(){
			removeAll();
			giveColor();
			validate();
			repaint();
		}
		/**
		 * a helper function that checks whether the key is contained inside the array
		 * @param array 
		 * @param key
		 * @return
		 */
		private boolean contains( int[] array,  int key) {
		    for ( int i : array) {
		        if (i == key) {
		            return true;
		        }
		    }
		    return false;
		}
		
		/**
		 * set the background color 
		 */
		private void giveColor(){
			int [] BlackPosition = {1,3,5,7,8,10,12,14,17,19,21,23,24,26,28,30,33,35,37,39,40,42,44,46,49,51,53,55,56,58,60,62};
			int [] WhitePosition = {0,2,4,6,9,11,13,15,16,18,20,22,25,27,29,31,32,34,36,38,41,43,45,47,48,50,52,54,57,59,61,63};
			
		
			if(contains(BlackPosition, SquarePosition)){
				setBackground(Color.pink);
				
			}
			if(contains(WhitePosition, SquarePosition)){
				setBackground(Color.white);
			}
		}
		
		
		/**
		 * give the SquarePanel an image
		 * @param board
		 */
		private void giveImage(Board board){
			
			if(board.getSquare(this.SquarePosition).isOccupied()){
				try{
					BufferedImage image = ImageIO.read(new File("images/"+board.getSquare(this.SquarePosition).getPiece().getPieceColor().toString()+board.getSquare(this.SquarePosition).getPiece().getPieceType(board.getSquare(this.SquarePosition).getPiece()).toString()+".gif"));
					add(new JLabel(new ImageIcon(image)));
				}catch(IOException e){
					e.printStackTrace();
				}
				
				
			}
		}
		
		private void giveImageSelected(Board board){
			
			if(board.getSquare(this.SquarePosition).isOccupied()){
				try{
					BufferedImage image = ImageIO.read(new File("images/"+board.getSquare(this.SquarePosition).getPiece().getPieceColor().toString()+board.getSquare(this.SquarePosition).getPiece().getPieceType(board.getSquare(this.SquarePosition).getPiece()).toString()+"Selected"+".gif"));
					add(new JLabel(new ImageIcon(image)));
				}catch(IOException e){
					e.printStackTrace();
				}
				
				
			}
		}
		
		
	}
}
