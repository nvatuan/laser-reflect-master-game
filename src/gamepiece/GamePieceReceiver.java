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
		boolean flag = false;
		flag |= (arrived.isUp() && (this.getOrientation() == GamePiece.ORIENT_UP));
		flag |= (arrived.isRight() && (this.getOrientation() == GamePiece.ORIENT_RIGHT));
		flag |= (arrived.isDown() && (this.getOrientation() == GamePiece.ORIENT_DOWN));
		flag |= (arrived.isLeft() && (this.getOrientation() == GamePiece.ORIENT_LEFT));
		if (flag) return new LaserDirection(LaserDirection.LaserWin);
		return new LaserDirection(LaserDirection.LaserLose);
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
