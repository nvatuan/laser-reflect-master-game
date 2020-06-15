package gamepiece;

import gameplay.LaserDirection;

public class GamePieceWall extends GamePiece {
	public GamePieceWall() {
		super(GamePiece.PIECE_WALL, false, GamePiece.ORIENT_UP);
	}
	
	public GamePieceWall(GamePieceWall ref) {
		super((GamePiece)ref);
	}
	// -- Override
	@Override
	public LaserDirection bounce(LaserDirection arrived) {
		return new LaserDirection(LaserDirection.LaserLose);
	}
	
	@Override
	public boolean rotate() {
		return false;
	}
	
	@Override
	public GamePiece clone() {
		return ((GamePiece) new GamePieceWall(this));
	}
}
