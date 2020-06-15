package gamepiece;

import gameplay.LaserDirection;

public final class GamePieceProjector extends GamePiece {
	public GamePieceProjector() {
		super(GamePiece.PIECE_PROJECTOR, false, GamePiece.ORIENT_UP);
	}
	public GamePieceProjector(int orient) {
		super(GamePiece.PIECE_PROJECTOR, false, orient);
	}
	
	public GamePieceProjector(GamePieceProjector ref) {
		super((GamePiece)ref);
	}
	// -- Override
	@Override
	public LaserDirection bounce(LaserDirection arrived) {
		switch (this.orientation) {
			case GamePiece.ORIENT_UP:
				return new LaserDirection(LaserDirection.LaserUp);
			case GamePiece.ORIENT_RIGHT:
				return new LaserDirection(LaserDirection.LaserRight);
			case GamePiece.ORIENT_DOWN:
				return new LaserDirection(LaserDirection.LaserDown);
			case GamePiece.ORIENT_LEFT:
				return new LaserDirection(LaserDirection.LaserLeft);
			default:
				return new LaserDirection(LaserDirection.LaserLose);
		}
	}
	
	@Override
	public boolean rotate() {
		if (this.rotatable) {
			this.orientation = (this.orientation + 1) % GamePiece.ORIENT_COUNT;
		}
		return this.rotatable;
	}
	@Override
	public GamePiece clone() {
		return ((GamePiece) new GamePieceProjector(this));
	}
}
