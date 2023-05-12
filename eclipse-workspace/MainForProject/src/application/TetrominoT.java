package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TetrominoT extends Tetromino {
	
	public static final int BLOCK_SIZE = Tetris.BLOCK_SIZE;
	public static int BOARD_WIDTH = Tetris.BOARD_WIDTH;
	public static int BOARD_HEIGHT = Tetris.BOARD_HEIGHT;

	TetrominoT(Color color, Board gameBoard) {
		super(color, gameBoard);
		
		//Calculated spawn location
		super.getRectA().setX(BOARD_WIDTH / 2);
		super.getRectB().setX(BOARD_WIDTH / 2);
		super.getRectC().setX(BOARD_WIDTH / 2 + BLOCK_SIZE);
		super.getRectD().setX(BOARD_WIDTH / 2 - BLOCK_SIZE);

		super.getRectB().setY(BLOCK_SIZE);
		super.getRectC().setY(BLOCK_SIZE);
		super.getRectD().setY(BLOCK_SIZE);
		
	}

    public void rotate(Board gameBoard) {
        // We can rotate a T-shaped block by rotating the 3 rectangles
        // around the center rectangle, which is RectB in this case.
        Rectangle center = super.getRectB();
        if((center.getX()+BLOCK_SIZE>BOARD_WIDTH)||
           (center.getX()-BLOCK_SIZE<0)||
           (center.getY()+BLOCK_SIZE>BOARD_HEIGHT)||
           (center.getY()-BLOCK_SIZE<0)||
           (gameBoard.getBoard((center.getX()/BLOCK_SIZE)+1, (center.getY()/BLOCK_SIZE))==1)||
           (gameBoard.getBoard((center.getX()/BLOCK_SIZE)-1, (center.getY()/BLOCK_SIZE))==1)||
           (gameBoard.getBoard((center.getX()/BLOCK_SIZE), (center.getY()/BLOCK_SIZE)+1)==1)||
           (gameBoard.getBoard((center.getX()/BLOCK_SIZE), (center.getY()/BLOCK_SIZE)-1)==1)) {
        	Rotation_Index--;
        }
        else {
        for (Rectangle r : super.getPoints()) {
            if (r != center) {
                // Calculate the new position of the rectangle after rotation
                double xDiff = r.getX() - center.getX();
                double yDiff = r.getY() - center.getY();
                r.setX(center.getX() - yDiff);
                r.setY(center.getY() + xDiff);
            }
        }
        }
	    super.Rotation_Index = (super.Rotation_Index + 1) % 4;

    }


}