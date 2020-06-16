package gamepiece;

import java.util.ArrayList;

import gameplay.LaserDirection;

public abstract class GamePiece {
	// -- Global variables
	public static final int ORIENT_UP = 0, ORIENT_RIGHT = 1, ORIENT_DOWN = 2, ORIENT_LEFT = 3;
	public static final int ORIENT_COUNT = 4;
	
	public static final String[] PIECE_NAME = new String[] {
		"Projector", "Receiver", "Mirror", "MirrorLocked", "Wall", "Empty"
	};
	
	public static final int PIECE_PROJECTOR = 0, PIECE_RECEIVER = 1, PIECE_MIRROR = 2,  PIECE_MIRROR_LOCKED = 3, PIECE_WALL = 4, PIECE_EMPTY = 5;
	public static final int PIECE_COUNT = 6;
	
	private static boolean _init = false; 
	private static ArrayList<GamePiece> ListOfUniqueGamePiece = null;
	private static int UniqueGamePieceCount = 0;
	
	public static ArrayList<GamePiece> getListOfUniqueGamePiece() {
		init();
		return GamePiece.ListOfUniqueGamePiece;
	}
	
	public static int getUniqueGamePieceCount() {
		init();
		return GamePiece.UniqueGamePieceCount;
	}
	
	public static void init() {
		if (!_init) {
			ListOfUniqueGamePiece = new ArrayList<GamePiece>();
			
			ListOfUniqueGamePiece.add(new GamePieceProjector());
			ListOfUniqueGamePiece.add(new GamePieceReceiver());
			ListOfUniqueGamePiece.add(new GamePieceMirror(true));
			ListOfUniqueGamePiece.add(new GamePieceMirror(false));
			ListOfUniqueGamePiece.add(new GamePieceWall());
			ListOfUniqueGamePiece.add(new GamePieceEmpty());
			
			UniqueGamePieceCount = ListOfUniqueGamePiece.size();
			_init = true;
		}
	}
	// -- members
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
	GamePiece(GamePiece ref) {
		this.pieceID = ref.pieceID;
		this.rotatable = ref.rotatable;
		this.orientation = ref.orientation;
	}
	
	// -- getters
	public int getPieceID() { return this.pieceID; }
	public boolean getRotatable() { return this.rotatable; }
	public int getOrientation() { return this.orientation; }
	
	// -- hash generate function
	public Integer getHash() {
		int hash = 0;
		int piece = this.getPieceID();
		int orient = this.getOrientation();
		boolean rot = this.getRotatable();
		
		// -- generate hash
		hash = piece;
		if (piece == GamePiece.PIECE_WALL || piece == GamePiece.PIECE_EMPTY) {
			return hash * -1;
		} else {
			hash *= 10;
			hash += (rot ? 1 : 0);
			hash *= 10;
			hash += orient;
			return hash;
		}
	}
	
	// -- methods
	public void rotateAnyway() {
		this.orientation = (this.orientation + 1) % GamePiece.ORIENT_COUNT;
	}
	
	public String name() {
		return GamePiece.PIECE_NAME[this.pieceID] + ", rotatable = " + this.rotatable; 
	}
	
	// -- static methods
	public static GamePiece nextGamePiece(int hash) {
		init();
		
		for (int i = 0; i < UniqueGamePieceCount; i++) {
			System.out.print(ListOfUniqueGamePiece.get(i).getHash() + " ");
			if (ListOfUniqueGamePiece.get(i).getHash() == hash) {
				int idx = (i + 1) % UniqueGamePieceCount;
				return GamePiece.ListOfUniqueGamePiece.get(idx).clone();
			}
		}
		return null;
	}
	
	// -- abstract methods
	abstract public LaserDirection bounce(LaserDirection arrived);
	abstract public boolean rotate();
	abstract public GamePiece clone();
}
