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
		//assert(arrived == null);
		if(arrived != null) return LaserDirection.LaserLose;
		switch (this.orientation) {
			case GamePiece.ORIENT_UP:
				return LaserDirection.LaserUp;
			case GamePiece.ORIENT_RIGHT:
				return LaserDirection.LaserRight;
			case GamePiece.ORIENT_DOWN:
				return LaserDirection.LaserDown;
			case GamePiece.ORIENT_LEFT:
				return LaserDirection.LaserLeft;
			default:
				return LaserDirection.LaserLose;
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
	
	@Override
	public String toString() {
		String[] rep = {"P^", "P>", "Pv", "P<"};
		return rep[this.orientation];
	}
}
