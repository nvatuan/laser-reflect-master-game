package gameplay;

public class GamePieceEmpty extends GamePiece {
	public GamePieceEmpty() {
		super(GamePiece.PIECE_EMPTY, false, GamePiece.ORIENT_UP);
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
}