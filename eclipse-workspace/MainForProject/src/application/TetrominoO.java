package application;

import javafx.scene.paint.Color;

public class TetrominoO extends Tetromino {
	
	public static final int BLOCK_SIZE = Tetris.BLOCK_SIZE;
	public static int BOARD_WIDTH = Tetris.BOARD_WIDTH;
	public static int BOARD_HEIGHT = Tetris.BOARD_HEIGHT;

	TetrominoO(Color color, Board gameBoard) {
		super(color, gameBoard);
		
		super.getRectA().setX(BOARD_WIDTH / 2);
		super.getRectB().setX(BOARD_WIDTH / 2 - BLOCK_SIZE);
		super.getRectC().setY(BLOCK_SIZE);
		super.getRectC().setX(BOARD_WIDTH / 2);
		super.getRectC().setY(BLOCK_SIZE);
		super.getRectD().setX(BOARD_WIDTH / 2 - BLOCK_SIZE);
		super.getRectD().setY(BLOCK_SIZE);
		}

	@Override
	public void rotate(Board gameBoard) {
		super.Rotation_Index = (super.Rotation_Index + 1) % 4;
	}

}