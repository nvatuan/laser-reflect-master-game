package gamepiece;

import gameplay.LaserDirection;

public class GamePieceEmpty extends GamePiece {
	public GamePieceEmpty() {
		super(GamePiece.PIECE_EMPTY, false, GamePiece.ORIENT_UP);
	}
	
	public GamePieceEmpty(GamePieceEmpty ref) {
		super((GamePiece)ref);
	}
	
	// -- Override
	@Override
	public LaserDirection bounce(LaserDirection arrived) {
		return new LaserDirection(arrived);
	}
	
	@Override
	public boolean rotate() {
		return false;
	}
	
	@Override
	public GamePiece clone() {
		return ((GamePiece) new GamePieceEmpty(this));
	}
	
	@Override
	public String toString() {
		return "  ";
	}
}