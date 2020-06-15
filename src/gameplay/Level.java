package gameplay;

import gamepiece.*;
import datastruct.PairXY;

public class Level {
	// -- constructors 
	public Level() {}
	// int, int
	public Level(int w, int h) {
		this.width = w; this.height = h;
		map = new GamePieceEmpty[w][h];
	}
	// int, int, bool
	public Level(int w, int h, boolean ed) {
		this.width = w; this.height = h;
		map = new GamePieceEmpty[w][h];
		this.editable = ed;
	}
	
	// map
	public Level(GamePiece[][] m) {
		this.width = m[0].length; this.height = m.length;
		this.map = m;
	}
	// map, bool
	public Level(GamePiece[][] m, boolean ed) {
		this.width = m[0].length; this.height = m.length;
		this.map = m;
		this.editable = ed;
	}
	
	// copy constructor
	public Level(Level ref) {
		this.width = ref.width; this.height = ref.height;
		this.map = new GamePiece[width][height];
		for (int ih = 0; ih < height; ih ++)
			for (int iw = 0; iw < width; iw++)
				this.map[ih][iw] = ref.map[ih][iw].clone();
	}
	
	// -- fields
	private  int width, height;
	private  GamePiece[][] map;
	
	// please enforce these two to be equal
	private int projectorCount, receiverCount;
	
	private PairXY[] projectorCoord;
	private PairXY[] receiverCoord;
	
	private boolean editable = false;
	// -- getters
	public int getWidth() { return this.width; }
	public int getHeight() { return this.height; }
	
	public int getProjectorCount() { return this.projectorCount; }
	public int getReceiverCount() { return this.receiverCount; }
	public PairXY[] getProjectorPairXY() { return this.projectorCoord; } 
	public PairXY[] getReceiverPairXY() { return this.receiverCoord; }
	
	
	
	// -- instance methods
	public boolean isValid() {
		
		return true;
	}
	
	public boolean launchEditor() {
		// -- TODO: Add editor
		return this.editable;
	}
	
	public boolean launchPlay() {
		if (this.isValid() == false) return false;
		// -- TODO:
		
		return true;
	}
	
	
	// -- static methods
	public static Level generate() {
		return new Level();
	}
}
