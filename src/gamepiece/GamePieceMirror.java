package gamepiece;

import gameplay.LaserDirection;

public final class GamePieceMirror extends GamePiece {
	public GamePieceMirror() {
		super(GamePiece.PIECE_MIRROR, true, GamePiece.ORIENT_UP);
	}
	public GamePieceMirror(boolean rot, int orient) {
		super(GamePiece.PIECE_MIRROR, rot, orient);
	}
	public GamePieceMirror(GamePieceMirror ref) {
		super((GamePiece)ref);
	}
	// -- Override
	@Override
	public LaserDirection bounce(LaserDirection arrived) {
		switch(this.orientation) {
			case GamePiece.ORIENT_UP:
				if (arrived.isDown()) return new LaserDirection(LaserDirection.LaserRight);
				if (arrived.isLeft()) return new LaserDirection(LaserDirection.LaserUp);
				return new LaserDirection(LaserDirection.LaserLose);
			case GamePiece.ORIENT_RIGHT:
				if (arrived.isRight()) return new LaserDirection(LaserDirection.LaserDown);
				if (arrived.isUp()) return new LaserDirection(LaserDirection.LaserRight);
				return new LaserDirection(LaserDirection.LaserLose);
			case GamePiece.ORIENT_DOWN:
				if (arrived.isUp()) return new LaserDirection(LaserDirection.LaserLeft);
				if (arrived.isRight()) return new LaserDirection(LaserDirection.LaserDown);
				return new LaserDirection(LaserDirection.LaserLose);
			case GamePiece.ORIENT_LEFT:
				if (arrived.isRight()) return new LaserDirection(LaserDirection.LaserUp);
				if (arrived.isDown()) return new LaserDirection(LaserDirection.LaserLeft);
				return new LaserDirection(LaserDirection.LaserLose);
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
		return ((GamePiece) new GamePieceMirror(this));
	}
}