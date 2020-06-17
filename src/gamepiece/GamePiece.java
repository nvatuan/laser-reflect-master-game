package gamepiece;

import java.util.ArrayList;
import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Set;

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
	
	private static HashMap<Integer, GamePiece> hashToPiece = new HashMap<Integer, GamePiece>();
	public static HashMap<Integer, GamePiece> getHashToPiece() {
		init();
		return hashToPiece;
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
			// --
			for (int idx = 0; idx < UniqueGamePieceCount; idx++) {
				GamePiece p = ListOfUniqueGamePiece.get(idx);
				for (int ir = 0; ir < 4; ir++) {
					GamePiece piece = p.clone();
					hashToPiece.put(piece.getHash(), piece);
					piece.rotateAnyway();
					p = piece;
				}
			}			
			
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
//		Set<Integer> s = hashToPiece.keySet();
//		Iterator<Integer> it = s.iterator();
//		
//		int[] hsh = new int[s.size()];
//		int idx = 0;
//		while (it.hasNext()) {
//			hsh[idx] = it.next();
//			idx++;
//		}
//		
//		for (int i = 0; i < hsh.length; i++) {
//			if (hsh[i] == hash) {
//				int ii = (i + 1) % hsh.length;
//				return hashToPiece.get(hsh[ii]).clone();
//			}
//		}
		for (int i = 0; i < UniqueGamePieceCount; i++) {
			if (hash == ListOfUniqueGamePiece.get(i).getHash()) {
				return ListOfUniqueGamePiece.get((i + 1) % UniqueGamePieceCount).clone();
			}
		}
		return ListOfUniqueGamePiece.get(0).clone();
	}
	
	// -- abstract methods
	abstract public LaserDirection bounce(LaserDirection arrived);
	abstract public boolean rotate();
	abstract public GamePiece clone();
}
