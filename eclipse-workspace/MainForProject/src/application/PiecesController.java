package application;

import javafx.scene.shape.Rectangle;

//Controls the pieces
//Rotations
//Handles player input
//Stores offset data
public class PiecesController {
	static final int NUM_ROW = Tetris.NUM_ROW;
	static final int NUM_COL = Tetris.NUM_COL;
    static final int BLOCK_SIZE = Tetris.BLOCK_SIZE;
    static final int BOARD_WIDTH = Tetris.BOARD_WIDTH;
    static final int BOARD_HEIGHT = Tetris.BOARD_HEIGHT;
    static final int CELL_SIZE = Tetris.CELL_SIZE;
    
    /*
     * Rotates the current block
     */
    boolean Rotate_Clockwise(Tetromino tetromino, Board gameBoard) {
        if (tetromino == null) {
            return false;
        }
        
        
        tetromino.rotate(gameBoard);
        
        return true;
       
    }

    /*
     * Move shape down
     */
    public boolean Move_Down(Tetromino Tetromino, Board gameBoard) {
        
    	if(Tetromino == null) {
    		return false;
    	}
    	
        boolean canMoveDown = true;
        
        for(Rectangle rect : Tetromino.getPoints()) {
            int newY = (int) (rect.getY() + BLOCK_SIZE);
            
            Rectangle newPos = new Rectangle(rect.getX(), newY, BLOCK_SIZE, BLOCK_SIZE);
            
            if (!gameBoard.isInBounds(newPos)) {
                canMoveDown = false;
                break;
            }
        }

        if (canMoveDown) {
        	
            for (Rectangle rect : Tetromino.getPoints()) {
                int newY = (int) (rect.getY() + BLOCK_SIZE);
                rect.setY(newY);
            }
            
            return true;
        }else {
    		return false;
        }
        
    }


    /*
     *Move shape left 
     */
    boolean Move_Left(Tetromino Tetromino, Board gameBoard){
    	
    	/*
    	 * Create copy of the location of the current Tetromino
    	 * Perform movement operations
    	 * Check if its legal
    	 * if legal, set the current Tetromino to the updated location
    	 * if not legal, current Tetromino remains the same position
    	 */
    	
    	int newX;
    	
    	if(Tetromino == null) {
    		return false;
    	}
    	
        boolean canMoveLeft = true;
        
        for(Rectangle rect : Tetromino.getPoints()) {
            newX = (int) (rect.getX() - BLOCK_SIZE);
            
            Rectangle newPos = new Rectangle(newX, rect.getY(), BLOCK_SIZE, BLOCK_SIZE);
            
            if (!gameBoard.isInBounds(newPos)) {
            	canMoveLeft = false;
                break;
            }
        }

        if (canMoveLeft) {
        	
            for (Rectangle rect : Tetromino.getPoints()) {
                newX = (int) (rect.getX() - BLOCK_SIZE);
                rect.setX(newX);
            }
            
            return true;
        }else {
    		return false;
        }
    }
    
    /*
     * Move shape right
     */
    boolean Move_Right(Tetromino Tetromino, Board gameBoard){
    	
    	int newX;
    	
    	if(Tetromino == null) {
    		return false;
    	}
    	
        boolean canMoveRight = true;
        
        for(Rectangle rect : Tetromino.getPoints()) {
            newX = (int) (rect.getX() + BLOCK_SIZE);
            
            Rectangle newPos = new Rectangle(newX, rect.getY(), BLOCK_SIZE, BLOCK_SIZE);
            
            if (!gameBoard.isInBounds(newPos)) {
            	canMoveRight = false;
                break;
            }
        }

        if (canMoveRight) {
        	
            for (Rectangle rect : Tetromino.getPoints()) {
                newX = (int) (rect.getX() + BLOCK_SIZE);
                rect.setX(newX);
            }
            
            return true;
        }else {
    		return false;
        }

    }

}