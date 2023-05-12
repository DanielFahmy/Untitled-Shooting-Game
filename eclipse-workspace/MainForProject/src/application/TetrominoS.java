package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TetrominoS extends Tetromino {
	
	public static final int BLOCK_SIZE = Tetris.BLOCK_SIZE;
	public static int BOARD_WIDTH = Tetris.BOARD_WIDTH;
	public static int BOARD_HEIGHT = Tetris.BOARD_HEIGHT;

	TetrominoS(Color color, Board gameBoard) {
		super(color, gameBoard);
		
		super.getRectA().setX(BOARD_WIDTH / 2);
		super.getRectB().setX(BOARD_WIDTH / 2);
		super.getRectB().setX(BOARD_WIDTH / 2 + BLOCK_SIZE);
		super.getRectC().setY(BLOCK_SIZE);
		super.getRectC().setX(BOARD_WIDTH / 2);
		super.getRectC().setY(BLOCK_SIZE);
		super.getRectD().setX(BOARD_WIDTH / 2 - BLOCK_SIZE);
		super.getRectD().setY(BLOCK_SIZE);
	
	}

	@Override
	public void rotate(Board gameBoard) {
		// TODO Auto-generated method stub
		System.out.println(Rotation_Index);
		Rectangle center = super.getRectA();
		if (Rotation_Index == 0) {
			/*  
        	 *   a b
        	 * d c
        	 *  
        	 *  to
        	 *  
        	 *   b
        	 *   a c
        	 *     d
        	 *  
        	 */ 
			if((super.getRectA().getY()-BLOCK_SIZE<0)||
		       (gameBoard.getBoard((center.getX()/BLOCK_SIZE), (center.getY()/BLOCK_SIZE)-1)==1)||
		       (gameBoard.getBoard((center.getX()/BLOCK_SIZE), (center.getY()/BLOCK_SIZE)+1)==1)||
		       (gameBoard.getBoard((center.getX()/BLOCK_SIZE)+1, (center.getY()/BLOCK_SIZE)+1)==1)) 
		        	{Rotation_Index--;}	
			else {
            super.getRectB().setX(center.getX());
            super.getRectB().setY(center.getY() - BLOCK_SIZE);
            super.getRectC().setX(center.getX() + BLOCK_SIZE);
            super.getRectC().setY(center.getY());
            super.getRectD().setX(center.getX() + BLOCK_SIZE );
            super.getRectD().setY(center.getY() + BLOCK_SIZE);
			}
			
        } else if (Rotation_Index == 1) {
        	/*  b
        	 *  a c
        	 *    d
        	 *  
        	 *  to
        	 *  
        	 *    a b
        	 *  d c
        	 *  
        	 */ 
        	if((super.getRectA().getX()+BLOCK_SIZE>BOARD_WIDTH)||
        	   (gameBoard.getBoard((center.getX()/BLOCK_SIZE)+1, (center.getY()/BLOCK_SIZE))==1)||
        	   (gameBoard.getBoard((center.getX()/BLOCK_SIZE), (center.getY()/BLOCK_SIZE)+1)==1)||
        	   (gameBoard.getBoard((center.getX()/BLOCK_SIZE)-1, (center.getY()/BLOCK_SIZE)+1)==1)) 
        	{Rotation_Index--;}
        	else {
            super.getRectB().setX(center.getX() + BLOCK_SIZE);
            super.getRectB().setY(center.getY());
            super.getRectC().setX(center.getX());
            super.getRectC().setY(center.getY() + BLOCK_SIZE);
            super.getRectD().setX(center.getX() - BLOCK_SIZE );
            super.getRectD().setY(center.getY() + BLOCK_SIZE);
        	}
        	        	
        	
        }
		super.Rotation_Index = (super.Rotation_Index + 1 ) % 2;
	}

}