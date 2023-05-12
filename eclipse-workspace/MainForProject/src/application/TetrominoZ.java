package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TetrominoZ extends Tetromino {
	
	public static final int BLOCK_SIZE = Tetris.BLOCK_SIZE;
	public static int BOARD_WIDTH = Tetris.BOARD_WIDTH;
	public static int BOARD_HEIGHT = Tetris.BOARD_HEIGHT;

	TetrominoZ(Color color, Board gameBoard) {
		super(color, gameBoard);
		
		//Calculated spawn position
        super.getRectA().setX(BOARD_WIDTH / 2 - BLOCK_SIZE * 2);
        super.getRectB().setX(BOARD_WIDTH / 2 - BLOCK_SIZE);
        super.getRectC().setX(BOARD_WIDTH / 2 - BLOCK_SIZE);
        super.getRectD().setX(BOARD_WIDTH / 2);

        super.getRectA().setY(0);
        super.getRectB().setY(0);
        super.getRectC().setY(BLOCK_SIZE);
        super.getRectD().setY(BLOCK_SIZE);
	
	}

    public void rotate(Board gameBoard) {
    	Rectangle center = super.getRectB();
    	System.out.println(Rotation_Index);
        if (Rotation_Index == 0) {
        	
        	/*  a b
        	 *    c d
        	 *  
        	 *  to
        	 *  
        	 *    a
        	 *  c b
        	 *  d
        	 */ 
        	if((center.getY()-BLOCK_SIZE<0)||
     		       (gameBoard.getBoard((center.getX()/BLOCK_SIZE)+1, (center.getY()/BLOCK_SIZE))==1)||
     		       (gameBoard.getBoard((center.getX()/BLOCK_SIZE), (center.getY()/BLOCK_SIZE)+1)==1)||
     		       (gameBoard.getBoard((center.getX()/BLOCK_SIZE)-1, (center.getY()/BLOCK_SIZE)+1)==1)) 
     		        	{Rotation_Index--;}	
        	else {
            super.getRectA().setX(center.getX());
            super.getRectA().setY(center.getY() - BLOCK_SIZE);
            super.getRectC().setX(center.getX() - BLOCK_SIZE);
            super.getRectC().setY(center.getY());
            super.getRectD().setX(center.getX() - BLOCK_SIZE );
            super.getRectD().setY(center.getY()+BLOCK_SIZE);
        	}
        } else if (Rotation_Index == 1) {
        	/*    a
        	 *  c b
        	 *  d
        	 *  
        	 *  to
        	 *  
        	 *  a b
        	 *    c d
        	 *  
        	 */ 
        	if((super.getRectA().getY()+BLOCK_SIZE>BOARD_HEIGHT)||
             	   (gameBoard.getBoard((center.getX()/BLOCK_SIZE)-1, (center.getY()/BLOCK_SIZE))==1)||
             	   (gameBoard.getBoard((center.getX()/BLOCK_SIZE), (center.getY()/BLOCK_SIZE)+1)==1)||
             	   (gameBoard.getBoard((center.getX()/BLOCK_SIZE)+1, (center.getY()/BLOCK_SIZE)+1)==1)) 
             	{Rotation_Index--;}
        	else {
            super.getRectA().setX(center.getX() - BLOCK_SIZE);
            super.getRectA().setY(center.getY());
            super.getRectC().setX(center.getX());
            super.getRectC().setY(center.getY() + BLOCK_SIZE);
            super.getRectD().setX(center.getX() + BLOCK_SIZE);
            super.getRectD().setY(center.getY() + BLOCK_SIZE);
        	}
        }
        
        super.Rotation_Index = (super.Rotation_Index + 1 ) % 2;
    }



}