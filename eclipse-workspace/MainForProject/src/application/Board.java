package application;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

//The Board class has several jobs including:
//Creating the 2d matrix grid
//Tracks the location of all existing pieces
//Detect collision with the borders or with another block
//Check and clear lines

public class Board {
	
	static final int NUM_ROW = 24;
	static final int NUM_COL = 12;
	
	//individual block size as it appears on the screen
    static final int BLOCK_SIZE = 25;
    static final int BOARD_WIDTH = BLOCK_SIZE * NUM_COL;
    static final int BOARD_HEIGHT = BLOCK_SIZE * NUM_ROW;
 
	public static final int CELL_SIZE = Tetris.CELL_SIZE;

	private int row;
	private int col;
	private int score;
	private int[][] Board;
	private boolean gameOver;
	private ArrayList<Rectangle> Points_Collection;
	private Pane boardPane;
	
	/**
	 * Constructor that takes in parameters 
	 * @param NUM_ROW
	 * @param NUM_COL
	 */
	Board(int NUM_ROW, int NUM_COL){
		row = NUM_ROW;
		col = NUM_COL;
		
		Points_Collection = new ArrayList<Rectangle>();
		this.Board = new int[row][col];
		setScore(0);
		setGameOver(false);
		
		   
        for(int row = 0; row < NUM_ROW; row++) {
        	for(int col = 0; col < NUM_COL; col++) {
        		Board[row][col] = 0;
        	}
        	
        }
	}
	
	/* PlaceShape works in tandem with setBoard()
	 * The board should only place a block when it is at the bottom of the board
	 * or if the block directly under it is another block
	 * 
	 * The console will mark the position of the placed block
	 * 1 = occupied
	 * 0 = unoccupied 
	 * @param tetromino
	 * @return
	 */
	public void gameOver() {
		
	}
	public int getBoard(double x, double y) {
        return Board[(int) y][(int) x];
    }
	public boolean placeShape(Tetromino tetromino) {
		
		if(tetromino == null)
			return false;
		
	for(Rectangle rect : tetromino.getPoints()) {
		 
	    Points_Collection.add(rect);

	}
		setBoard();
		
	    return true;
	}
	
	/*
	 * After the piece has been placed on the grid,
	 * The location will be marked as occupied with a 1
	 */
	public void setBoard() {
		
		for(Rectangle rect : Points_Collection) {
				int converted_X = (int) (rect.getX()/Tetris.BLOCK_SIZE);
				int converted_Y = (int) (rect.getY()/Tetris.BLOCK_SIZE);
				
				Board[converted_Y][converted_X] = 1;
			
		}
		System.out.println("In setBoard************************************");
	    PrintBoard();
	    System.out.println("************************************");
		
	}
	
	/*
	 * Check if next shape will spawn on top another piece on the board
	 * 
	 */
	public void checkGameOver() {
		//for(Rectangle blocks : Points_Collection) {
		//	if(blocks.getY()>=BOARD_HEIGHT-BLOCK_SIZE) {
			//	System.out.println("GAME OVER!!!");
			//	gameOver=true;
			//}
		//}
	}

	/*
	 * Checks if an entire row is filled up 
	 * if the row is filled up, clear the row by setting the entire row to 0 and shifting everything down 
	 */
	public int clearFullRows(Group blocksGroup) {
	    int numRowsCleared = 0;

	    for (int row = 0; row < Tetris.NUM_ROW; row++) {
	        int numNonZeroCells = 0;

	        for (int col = 0; col < Tetris.NUM_COL; col++) {
	            if (Board[row][col] != 0) {
	                numNonZeroCells++;
	            }
	        }

	        if (numNonZeroCells == Tetris.NUM_COL) {
		        System.out.println("Row is full: " + row);

	            // clear the row by setting to 0
	            for (int col = 0; col < Tetris.NUM_COL; col++) {
	                Board[row][col] = 0;
	                // Remove the corresponding Rectangle object from the blocksGroup
	                Rectangle rect = getRectangleAt(row, col, blocksGroup);
	                Points_Collection.remove(rect);
	                if (rect != null) {
	                    blocksGroup.getChildren().remove(rect);
	                } else {
	                    System.out.println("Rectangle is null");
	                }
	            }

	            // Move all rows above this row down by one
	            for (int r = row - 1; r >= 0; r--) {
	                for (int col = 0; col < Tetris.NUM_COL; col++) {
	                    Board[r+1][col] = Board[r][col];
	                    Board[r][col] = 0;

	                    // Move the corresponding Rectangle object down by one cell
	                    Rectangle rect = getRectangleAt(r, col, blocksGroup);
	                    if (rect != null) {
	                        rect.setY(rect.getY() + Tetris.BLOCK_SIZE);
	                    }
	                }
	            }

	            numRowsCleared++;
	        }
	    }

	    setScore(getScore() + 100 * numRowsCleared);
	    System.out.println("In clear rows************************************");
	    PrintBoard();
	    System.out.println("************************************");
	    return numRowsCleared;
	}

	private Rectangle getRectangleAt(int row, int col, Group blocksGroup) {
	    for (Node node : blocksGroup.getChildren()) {
	    	
	    	if(node instanceof Rectangle && ((Rectangle) node).getX()/BLOCK_SIZE == col && ((Rectangle) node).getY()/BLOCK_SIZE == row) {
	    		
	    		return (Rectangle) node;
	    	}
	    	
	    }
	    
	    return null;
	}

	/*
	 * Checks if the piece movement is legal
	 * returns true if the operation is allowed 
	 */
	public boolean isInBounds(Rectangle newRect) {

	    int convertedX = (int) (newRect.getX() / Tetris.BLOCK_SIZE);
	    int convertedY = (int) (newRect.getY() / Tetris.BLOCK_SIZE);

	    // Check if block is outside the grid
	    if (convertedX < 0 || convertedX >= Tetris.NUM_COL || convertedY >= Tetris.NUM_ROW) {
	        return false;
	    }

	    // Check if block collides with existing block on board
	    if (Board[convertedY][convertedX] == 1) {
	        return false;
	    }
	    
	    return true;
	}

	
	/*
	 * The PrintBoard() function will print the current board state to the console
	 * if there is a block at the location
	 */
	public void PrintBoard() {
		System.out.println(col + "x" + row);
				
		for(int i = 0; i < Tetris.NUM_ROW; i++) {
			for(int j = 0; j < Tetris.NUM_COL; j++) {
				if(Board[i][j] == 1) {
					System.out.print("X ");
				}else {
					System.out.print("O ");
				}
				
			}
			
			System.out.println();
		}
	}
	
	/*
	 * Creates a Pane object to be painted onto the scene
	 */
	public Pane createBoardPane() {
	    boardPane = new Pane();
	    
	    for (int currentRow = 0; currentRow < row; currentRow++) {
	        for (int currentCol = 0; currentCol < col; currentCol++) {
	            Rectangle rect = new Rectangle(currentCol * CELL_SIZE, currentRow * CELL_SIZE, CELL_SIZE, CELL_SIZE);
	            rect.setFill(Color.TRANSPARENT);
	            rect.setStroke(Color.BLACK);
	            boardPane.getChildren().add(rect);
	        }
	    }
	    return boardPane;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Node getBoardPane() {
		return boardPane;
	}
	
}