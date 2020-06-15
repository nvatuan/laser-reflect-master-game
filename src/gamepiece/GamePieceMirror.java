package gamepiece;

import gameplay.LaserDirection;

public final class GamePieceMirror extends GamePiece {
	public GamePieceMirror() {
		super(GamePiece.PIECE_MIRROR, true, GamePiece.ORIENT_UP);
	}
	public GamePieceMirror(int orient) {
		super(GamePiece.PIECE_MIRROR, true, orient);
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
		LaserDirection next = LaserDirection.LaserLose;
		switch(this.orientation) {
			case GamePiece.ORIENT_UP:
				if (arrived.isDown()) next = LaserDirection.LaserRight;
				if (arrived.isLeft()) next = LaserDirection.LaserUp;
				break;
			case GamePiece.ORIENT_RIGHT:
				if (arrived.isLeft()) next = LaserDirection.LaserDown;
				if (arrived.isUp()) next = LaserDirection.LaserRight;
				break;
			case GamePiece.ORIENT_DOWN:
				if (arrived.isUp()) next = LaserDirection.LaserLeft;
				if (arrived.isRight()) next = LaserDirection.LaserDown;
				break;
			case GamePiece.ORIENT_LEFT:
				if (arrived.isRight()) next = LaserDirection.LaserUp;
				if (arrived.isDown()) next = LaserDirection.LaserLeft;
				break;
			default:
				break;
		}
		return next;
	}
	
	// -- Overriden methods
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
	
	@Override
	public String toString() {
		String[] rep = {"|\\", "|/", "\\|", "/|"};
		return rep[this.orientation];
	}
}