package gameplay;

import gamepiece.*;

import java.util.ArrayList;

import datastruct.PairXY;

public class Level {
	// ===== constructors 
	
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
	
	// ====== general fields
	
	private  int width, height;
	//private  GamePiece[][] map;
	public GamePiece[][] map;
	
	// please enforce these two to be equal
	private int projectorCount, receiverCount;
	
	private ArrayList<PairXY> projectorCoord = new ArrayList<PairXY>();
	private ArrayList<PairXY> receiverCoord = new ArrayList<PairXY>();
	
	private boolean editable = false;
	
	private String levelDescription = "";
	
	// ====== general fields' getters and setters
	
	public int getWidth() { return this.width; }
	public int getHeight() { return this.height; }
	
	public int getProjectorCount() { return this.projectorCount; }
	public int getReceiverCount() { return this.receiverCount; }
	public ArrayList<PairXY> getProjectorPairXY() { return this.projectorCoord; } 
	public ArrayList<PairXY> getReceiverPairXY() { return this.receiverCoord; }
	
	public String getLevelDescription() { return this.levelDescription; }
	public void setLevelDescription(String msg) { this.levelDescription = msg; }
	
	// ===== instance methods
	
	// update projector's, receiver's count and coords
	private void updateProjectorData() {
		projectorCount = 0;
		projectorCoord.clear();
		// --
		for (int ih = 0; ih < height; ih++)
			for (int iw = 0; iw < width; iw++)
				if (map[ih][iw].getPieceID() == GamePiece.PIECE_PROJECTOR) {
					projectorCount++;
					projectorCoord.add(new PairXY(ih, iw));
				}
	}
	private void updateReceiverData() {
		receiverCount = 0;
		receiverCoord.clear();
		// --
		for (int ih = 0; ih < height; ih++)
			for (int iw = 0; iw < width; iw++)
				if (map[ih][iw].getPieceID() == GamePiece.PIECE_RECEIVER) {
					receiverCount++;
					receiverCoord.add(new PairXY(ih, iw));
				}
	}
	
	// -- solution debugging related
	private String solveDebug = null;
	public String getHasSolutionDebug() {
		if (solveDebug == null) solve();
		return solveDebug;
	}
	
	// -- DFS looking for solution
	// sol related fields
	private ArrayList<ArrayList<PairXY>> solvedMirror = new ArrayList<ArrayList<PairXY>>();
	private ArrayList<ArrayList<Integer>> solvedMirrorOrientation = new ArrayList<ArrayList<Integer>>();
	public int solvedMirrorSize = 0;
	
	public  ArrayList<PairXY> getsolvedMirror(int idx) { return this.solvedMirror.get(idx); }
	public  ArrayList<Integer> getsolvedMirrorOrientation(int idx) { return this.solvedMirrorOrientation.get(idx); }

	// methods
	private boolean[][] visited = new boolean[0][0];
	private boolean findSolution(LaserDirection cursor, boolean[][] locked, int X, int Y, boolean init) {
		if (init) {
			solvedMirror.add(new ArrayList<PairXY>());
			solvedMirrorOrientation.add(new ArrayList<Integer>());
			solvedMirrorSize++;
			visited = new boolean[height][width];
		}
		// -- check W/L conditions
		assert(!cursor.isStuck());
		//System.out.println("@findSolution: X = " + X + " | Y = " + Y);
		//System.out.println(cursor.toString());
		
		visited[X][Y] = true;
		if (cursor.isWin()) return true;
		if (cursor.isLose()) return false;
		
		// increments
		int newX = X + cursor.getDx();
		int newY = Y + cursor.getDy();
		
		// -- check borders
		if (newX < 0 || newX == this.height) return false;
		if (newY < 0 || newY == this.width) return false;
		if (visited[newX][newY]) return false;
		
		// -- get laser direction
		// - rotate mirrors....
		
		if ((this.map[newX][newY].getPieceID() == GamePiece.PIECE_MIRROR) && 
		   (locked[newX][newY] == false)) {
			GamePieceMirror piece = new GamePieceMirror((GamePieceMirror)this.map[newX][newY]);
			for (int rt = 0; rt < 4; rt++) {
				piece.rotate();
				LaserDirection newCursor = piece.bounce(cursor);
				// -- try
				if (findSolution(newCursor, locked, newX, newY, false)) {
					solvedMirror.get(solvedMirrorSize - 1).add(new PairXY(newX, newY));
					solvedMirrorOrientation.get(solvedMirrorSize - 1).add(Integer.valueOf(piece.getOrientation()));
					locked[newX][newY] = true;
					return true;
				}
			}
		} else {
			LaserDirection newCursor = this.map[newX][newY].bounce(cursor);
			if (findSolution(newCursor, locked, newX, newY, false)) return true;
		}
		
		// -- no
		return false;
	}
	
	// -- count how may solutions this level has, zero if impossible.
	//public int countSolution() {
	public boolean solve() {
		updateProjectorData();
		updateReceiverData();		
		// with countless times of testing, thesis stating and attempt proving,
		// i have come to accept that with this current rule for the game
		// there is only 1 solution.
		// One Projector can only hit One Receiver, at max, with any mirror-configuration.
		// And, there is only One Mirror configuration for a pair of Projector and Receiver. (meaning 1 solution).
		if (this.projectorCount > this.receiverCount) return false;
		
		boolean[][] locked = new boolean[height][width];
		for (int ih = 0; ih < height; ih ++)
			for (int iw = 0; iw < width; iw++)
				locked[ih][iw] = (!this.map[ih][iw].getRotatable());
		// -- resets something
		solvedMirror.clear();
		solvedMirrorOrientation.clear();
		solvedMirrorSize = 0;
	
		// runs
		for (int i = 0; i < this.projectorCount; i++) {
			int ih = projectorCoord.get(i).X;
			int iw = projectorCoord.get(i).Y;
			
			LaserDirection cursor = this.map[ih][iw].bounce(null);
			
			boolean possible = findSolution(cursor, locked, ih, iw, true);
			if (possible == false) return false;
		}
		return true;
	}
	
	// -- write solution to level
	public void writeSolutionToMap() {
		for (int i = 0; i < this.solvedMirrorSize; i++) {
			ArrayList<PairXY> mirror = this.getsolvedMirror(i);
			ArrayList<Integer> mirrorOrient = this.getsolvedMirrorOrientation(i);
			
			for (int mirr = 0; mirr < mirror.size(); mirr++) {
				int X = mirror.get(mirr).X;
				int Y = mirror.get(mirr).Y;
				int Orient = mirrorOrient.get(mirr);
				//
				while (this.map[X][Y].getOrientation() != Orient) this.map[X][Y].rotate();
			}
		}
	}
	
	public boolean launchEditor() {
		// -- TODO: Add editor
		return this.editable;
	}
	
	public boolean launchPlay() {
		if (this.solve() == false) return false;
		// -- TODO:
		
		return true;
	}
	
	// -- static methods
	public static Level generate() {
		return new Level();
	}
	
	// -- overriden methods
	@Override
	public String toString() {
		String prefix =   "Level Description: " + this.levelDescription + "\n"
							+ "Width = " + this.width + " | Height = " + this.height + "\n"
							+ "Map = {" + "\n";
		String surfix = "}";
		// --
		String rep = prefix;
		for (int ih = 0; ih < this.height; ih++) {
			for (int iw = 0; iw < this.width; iw++) {
				rep += this.map[ih][iw].toString();
				if (iw + 1 < this.width) rep += " ";
				else rep += "\n";
			}
			if (ih + 1 < this.height) rep += "\n";
		}
		rep += surfix;
		//
		return rep;
	}
}
