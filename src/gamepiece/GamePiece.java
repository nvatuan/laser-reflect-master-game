package gamepiece;

import gameplay.LaserDirection;

public abstract class GamePiece {
	public static final int ORIENT_UP = 0, ORIENT_RIGHT = 1, ORIENT_DOWN = 2, ORIENT_LEFT = 3;
	public static final int ORIENT_COUNT = 4;
	
	public static final int PIECE_PROJECTOR = 0, PIECE_RECEIVER = 1, PIECE_MIRROR = 2, PIECE_WALL = 3, PIECE_EMPTY = 4;
	public static final int PIECE_COUNT = 5;
	
	int pieceID;
	boolean rotatable;
	int		orientation;
	GamePiece() {
		this.rotatable = false;
		this.orientation = GamePiece.ORIENT_UP;
		this.pieceID = GamePiece.PIECE_EMPTY;
	}
	GamePiece(int piece, boolean rot, int orient) {
		this.rotatable = rot;
		assert( (0 <= orient) && (orient <= GamePiece.ORIENT_COUNT) );
		this.orientation = orient;
		assert( (0 <= piece) && (piece <= GamePiece.PIECE_COUNT));
		this.pieceID = piece;
	}
	
	// -- getters
	int getPieceID() { return this.pieceID; }
	boolean getRotatable() { return this.rotatable; }
	int getOrientation() { return this.orientation; }
	
	// -- hash generate function
	public int getHash() {
		int hash = 0;
		int piece = this.getPieceID();
		int orient = this.getOrientation();
		boolean rot = this.getRotatable();
		
		// -- generate hash
		hash = piece;
		if (piece == GamePiece.PIECE_WALL || piece == GamePiece.PIECE_EMPTY) {
			return hash;
		} else {
			hash *= 10;
			hash += (rot ? 1 : 0);
			hash *= 10;
			hash += orient;
			return hash;
		}
	}
	
	// -- abstract methods
	abstract public LaserDirection bounce(LaserDirection arrived);
	abstract public boolean rotate();
}
