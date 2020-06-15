package gameplay;

public class GamePieceWall extends GamePiece {
	public GamePieceWall() {
		super(GamePiece.PIECE_WALL, false, GamePiece.ORIENT_UP);
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
}
