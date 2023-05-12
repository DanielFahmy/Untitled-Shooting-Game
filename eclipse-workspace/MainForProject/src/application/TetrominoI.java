package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TetrominoI extends Tetromino {
	
	public static final int BLOCK_SIZE = Tetris.BLOCK_SIZE;
	public static int BOARD_WIDTH = Tetris.BOARD_WIDTH;
	public static int BOARD_HEIGHT = Tetris.BOARD_HEIGHT;

	TetrominoI(Color color, Board Board) {
		super(color, Board);
		//  abcd
		//calculated spawn position
		super.getRectA().setX(BOARD_WIDTH / 2 - BLOCK_SIZE * 2);
		super.getRectB().setX(BOARD_WIDTH / 2 - BLOCK_SIZE);
		super.getRectC().setX(BOARD_WIDTH / 2);
		super.getRectD().setX(BOARD_WIDTH / 2 + BLOCK_SIZE);

  	}

	@Override
	public void rotate(Board gameBoard) {
	    Rectangle center = super.getRectB(); // get the center block, which is the second block
	 System.out.println(super.Rotation_Index);
	    switch (super.Rotation_Index) {
	        case 0:
	        	if((center.getX()+2*BLOCK_SIZE>BOARD_WIDTH)||
	        		center.getX()-BLOCK_SIZE<0||
	        		gameBoard.getBoard((center.getX()/BLOCK_SIZE)+2, (center.getY()/BLOCK_SIZE))==1||
	        		gameBoard.getBoard((center.getX()/BLOCK_SIZE)-1, (center.getY()/BLOCK_SIZE))==1)
	        	{super.Rotation_Index--;}
	        	else {
	            // current orientation: vertical, rotate to horizontal
	            super.getRectA().setX(center.getX() - BLOCK_SIZE);
	            super.getRectA().setY(center.getY());
	            super.getRectC().setX(center.getX() + BLOCK_SIZE);
	            super.getRectC().setY(center.getY());
	            super.getRectD().setX(center.getX() + 2 * BLOCK_SIZE);
	            super.getRectD().setY(center.getY());
	        	}
	            break;
	        case 1:
	        	if(center.getY()+2*BLOCK_SIZE>BOARD_HEIGHT||
	        	   center.getY()-BLOCK_SIZE<0||
	        	   gameBoard.getBoard(center.getX()/BLOCK_SIZE, (center.getY()/BLOCK_SIZE)+2)==1||
	        	   gameBoard.getBoard(center.getX()/BLOCK_SIZE, (center.getY()/BLOCK_SIZE)+1)==1)
	        	{super.Rotation_Index--;}
	        	else {
	            // current orientation: horizontal, rotate to vertical
	            super.getRectA().setX(center.getX());
	            super.getRectA().setY(center.getY() - BLOCK_SIZE);
	            super.getRectC().setX(center.getX());	
	            super.getRectC().setY(center.getY() + BLOCK_SIZE);
	            super.getRectD().setX(center.getX());
	            super.getRectD().setY(center.getY() + 2 * BLOCK_SIZE);}
	            break;
	        case 2:
	            // current orientation: vertical, rotate to horizontal
	        	if(center.getX()-(2*BLOCK_SIZE)<0||
	        	   center.getX()+1*BLOCK_SIZE>BOARD_WIDTH||
	 	           gameBoard.getBoard((center.getX()/BLOCK_SIZE)-1, (center.getY()/BLOCK_SIZE))==1||
	 	           gameBoard.getBoard((center.getX()/BLOCK_SIZE)-2, (center.getY()/BLOCK_SIZE))==1||
	 	           gameBoard.getBoard((center.getX()/BLOCK_SIZE)+1, (center.getY()/BLOCK_SIZE))==1)
	 	        	{super.Rotation_Index--;}
	        	else {
	            super.getRectA().setX(center.getX() + BLOCK_SIZE);
	            super.getRectA().setY(center.getY());
	            super.getRectC().setX(center.getX() - BLOCK_SIZE);
	            super.getRectC().setY(center.getY());
	            super.getRectD().setX(center.getX() - 2 * BLOCK_SIZE);
	            super.getRectD().setY(center.getY());
	        	}
	            break;
	        case 3:
	            // current orientation: horizontal, rotate to vertical
	        	if((center.getY()-2*BLOCK_SIZE<0)||
	        		center.getY()+BLOCK_SIZE>BOARD_HEIGHT||
	        		gameBoard.getBoard((center.getX()/BLOCK_SIZE), (center.getY()/BLOCK_SIZE)+1)==1) {
	        		super.Rotation_Index--;
	        	}
	        	else {
	            super.getRectA().setX(center.getX());
	            super.getRectA().setY(center.getY() + BLOCK_SIZE);
	            super.getRectC().setX(center.getX());
	            super.getRectC().setY(center.getY() - BLOCK_SIZE);
	            super.getRectD().setX(center.getX());
	            super.getRectD().setY(center.getY() - 2 * BLOCK_SIZE);
	        	}
	            break;
	        	
	    }
	    
	    super.Rotation_Index = ((Rotation_Index + 1) % 4); // update the rotation index
	}

}