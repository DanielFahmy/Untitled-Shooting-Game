package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TetrominoL extends Tetromino {
	
	public static final int BLOCK_SIZE = Tetris.BLOCK_SIZE;
	public static int BOARD_WIDTH = Tetris.BOARD_WIDTH;
	public static int BOARD_HEIGHT = Tetris.BOARD_HEIGHT;

	TetrominoL(Color color, Board gameBoard) {
		super(color, gameBoard);
		//			a
		//			b
		//			c d
		super.getRectA().setX(BOARD_WIDTH / 2);
		super.getRectB().setX(BOARD_WIDTH / 2);
		super.getRectB().setY(BLOCK_SIZE);
		super.getRectC().setX(BOARD_WIDTH / 2);
		super.getRectC().setY(2 * BLOCK_SIZE);
		super.getRectD().setX(BOARD_WIDTH / 2 + BLOCK_SIZE);
		super.getRectD().setY(2 * BLOCK_SIZE);	}

	@Override
	public void rotate(Board gameBoard) {
		Rectangle center = super.getRectB(); // get the center block, which is the second block
		 switch(super.Rotation_Index) {
		 
		 case 0:
			 /* a 
			  * b
			  * c d
			  *
			  * to
			  * 
			  * c b a
			  * d 
			  */
			 if((center.getX()-BLOCK_SIZE<0)||
				(gameBoard.getBoard((center.getX()/BLOCK_SIZE)+1, (center.getY()/BLOCK_SIZE))==1)||
				(gameBoard.getBoard((center.getX()/BLOCK_SIZE)-1, (center.getY()/BLOCK_SIZE)+1)==1)||
				(gameBoard.getBoard((center.getX()/BLOCK_SIZE)-1, (center.getY()/BLOCK_SIZE))==1))
			 	{Rotation_Index--;}
			 else {
			 super.getRectA().setX(center.getX() + BLOCK_SIZE);
			 super.getRectA().setY(center.getY());
			 super.getRectC().setX(center.getX() - BLOCK_SIZE);
			 super.getRectC().setY(center.getY());
			 super.getRectD().setX(center.getX() -  BLOCK_SIZE);
			 super.getRectD().setY(center.getY() + BLOCK_SIZE);
			 }
			 break;
			 
		 case 1:
			 /* c b a 
			  * d
			  * 
			  * to
			  * 
			  * d c
			  *   b
			  *   a
			  */
			 if((center.getY()+BLOCK_SIZE>BOARD_HEIGHT)||
				(center.getY()-BLOCK_SIZE<0)||
				(gameBoard.getBoard((center.getX()/BLOCK_SIZE), (center.getY()/BLOCK_SIZE)+1)==1)||
				(gameBoard.getBoard((center.getX()/BLOCK_SIZE)-1, (center.getY()/BLOCK_SIZE)-1)==1)||
				(gameBoard.getBoard((center.getX()/BLOCK_SIZE), (center.getY()/BLOCK_SIZE)-1)==1))
				{Rotation_Index--;}
			 else {
			 super.getRectA().setX(center.getX());
			 super.getRectA().setY(center.getY() + BLOCK_SIZE);
			 super.getRectC().setX(center.getX());
			 super.getRectC().setY(center.getY() - BLOCK_SIZE);
			 super.getRectD().setX(center.getX() -  BLOCK_SIZE);
			 super.getRectD().setY(center.getY() - BLOCK_SIZE);
			 }
			 break;
			 
		 case 2:
			 /* d c
			  *   b
			  *   a
			  *   
			  * to
			  * 
			  * 	d
			  * a b c
			  *   
			  */
			 if((center.getX()+BLOCK_SIZE>BOARD_WIDTH)||
				(gameBoard.getBoard((center.getX()/BLOCK_SIZE)+1, (center.getY()/BLOCK_SIZE))==1)||
				(gameBoard.getBoard((center.getX()/BLOCK_SIZE)+1, (center.getY()/BLOCK_SIZE)+1)==1)||
				(gameBoard.getBoard((center.getX()/BLOCK_SIZE)-1, (center.getY()/BLOCK_SIZE))==1))
			 	{Rotation_Index--;}
			 else {
			 super.getRectA().setX(center.getX() - BLOCK_SIZE);
			 super.getRectA().setY(center.getY());
			 super.getRectC().setX(center.getX() + BLOCK_SIZE);
			 super.getRectC().setY(center.getY());
			 super.getRectD().setX(center.getX() + BLOCK_SIZE);
			 super.getRectD().setY(center.getY() - BLOCK_SIZE);
			 }
			 break;
			 
		 case 3:
			 /* 	d
			  * a b c
			  *
			  * to
			  * 
			  * a 	
			  * b 
			  * c d  
			  */
			 if((center.getY()+BLOCK_SIZE>BOARD_HEIGHT)||
				(gameBoard.getBoard((center.getX()/BLOCK_SIZE), (center.getY()/BLOCK_SIZE)+1)==1)||
				(gameBoard.getBoard((center.getX()/BLOCK_SIZE)+1, (center.getY()/BLOCK_SIZE)+1)==1)||
				(gameBoard.getBoard((center.getX()/BLOCK_SIZE), (center.getY()/BLOCK_SIZE)-1)==1))
				{Rotation_Index--;}
			 else {
			 super.getRectA().setX(center.getX());
			 super.getRectA().setY(center.getY() - BLOCK_SIZE);
			 super.getRectC().setX(center.getX());
			 super.getRectC().setY(center.getY() + BLOCK_SIZE);
			 super.getRectD().setX(center.getX() + BLOCK_SIZE);
			 super.getRectD().setY(center.getY() + BLOCK_SIZE);
			 }
			 break;
			 
		 }
		 super.Rotation_Index = ((Rotation_Index + 1) % 4);
	}

}