package gamepiece;

import gameplay.LaserDirection;

public final class GamePieceReceiver extends GamePiece {
	public GamePieceReceiver() {
		super(GamePiece.PIECE_RECEIVER, false, GamePiece.ORIENT_UP);
	}
	public GamePieceReceiver(int orient) {
		super(GamePiece.PIECE_RECEIVER, false, orient);
	}
	
	public GamePieceReceiver(GamePieceReceiver ref) {
		super ((GamePiece)ref);
	}
	// -- Override
	@Override
	public LaserDirection bounce(LaserDirection arrived) {
		return new LaserDirection(LaserDirection.LaserWin);
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
		return ((GamePiece) new GamePieceReceiver(this));
	}
	
	@Override
	public String toString() {
		String[] rep = {"R^", "R>", "Rv", "R<"};
		return rep[this.orientation];
	}
}
