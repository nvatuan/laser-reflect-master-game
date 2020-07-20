package gameplay;

import gamepiece.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

import datastruct.PairXY;

public class Level {
	// ===== constructors 
	
	public Level() {}
	// int, int
	public Level(int w, int h) {
		this.width = w; this.height = h;
		map = new GamePiece[h][w];
		
		for (int ih = 0; ih < height; ih ++)
			for (int iw = 0; iw < width; iw++)
				this.map[ih][iw] = new GamePieceEmpty();
	}
	// int, int, bool
	public Level(int w, int h, boolean ed) {
		this.width = w; this.height = h;
		map = new GamePiece[h][w];
		this.editable = ed;

		for (int ih = 0; ih < height; ih ++)
			for (int iw = 0; iw < width; iw++)
				this.map[ih][iw] = new GamePieceEmpty();
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
		this.map = new GamePiece[height][width];
		for (int ih = 0; ih < height; ih ++)
			for (int iw = 0; iw < width; iw++)
				this.map[ih][iw] = ref.map[ih][iw].clone();
	}
	
	// ====== general fields
	
	private  int width, height;
	//private  GamePiece[][] map;
	public GamePiece[][] map;
	
	public GamePiece[][] getMap() { return this.map; }
	private boolean editable = false;
	
	// please enforce these two to be equal
	private int projectorCount, receiverCount;
	
	private ArrayList<PairXY> projectorCoord = new ArrayList<PairXY>();
	private ArrayList<PairXY> receiverCoord = new ArrayList<PairXY>();
	
	private String levelDescription = "";
	
	// ====== general fields' getters and setters
	
	public int getWidth() { return this.width; }
	public int getHeight() { return this.height; }
	
	public boolean getEditable() { return this.editable; }
	
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

	private HashSet<String> visitedMirrors;
	// methods
	private boolean findSolution(LaserDirection cursor, boolean[][] locked, int X, int Y, boolean init, int depth, HashSet<String> reached) {
		if (init) {
			solvedMirror.add(new ArrayList<PairXY>());
			solvedMirrorOrientation.add(new ArrayList<Integer>());
			solvedMirrorSize++;
			visitedMirrors = new HashSet<String>();		
		}
		// -- check W/L conditions
		assert(!cursor.isStuck());
		System.out.println("@findSolution: X = " + X + " | Y = " + Y + " | depth = " + depth);
		System.out.println(cursor.toString());

		int newX = X;
		int newY = Y;
		
		while (true) {
			if (cursor.isWin()) {
				reached.add(new PairXY(newX, newY).toString());
				return true;
			}
			if (cursor.isLose()) return false;
			// increments
			newX = newX + cursor.getDx();
			newY = newY + cursor.getDy();
			
			// -- check borders
			if (newX < 0 || newX == this.height) return false;
			if (newY < 0 || newY == this.width) return false;
			
			if(this.map[newX][newY].getPieceID() == GamePiece.PIECE_MIRROR) break;
			cursor = this.map[newX][newY].bounce(cursor);
		}
		
		// -- get laser direction
		// - rotate mirrors....
		
		if ((this.map[newX][newY].getPieceID() == GamePiece.PIECE_MIRROR) && 
		   (locked[newX][newY] == false)) {
			PairXY p = new PairXY(newX, newY);
			if (visitedMirrors.contains(p.toString())) {
				System.out.println("Mirror " + p.toString() + " is visited.");
				return false;
			}
			visitedMirrors.add(p.toString());
			
			GamePieceMirror piece = new GamePieceMirror((GamePieceMirror)this.map[newX][newY]);
			for (int rt = 0; rt < 4; rt++) {
				piece.rotate();
				System.out.println("@rotating: X = " + newX + " | Y = " + newY + " | depth = " + depth);
				
				LaserDirection newCursor = piece.bounce(cursor);
				if (newCursor.isLose()) continue;
				// -- try
				if (findSolution(newCursor, locked, newX, newY, false, depth + 1, reached)) {
					//solvedMirror.get(solvedMirrorSize - 1).add(new PairXY(newX, newY));
					//solvedMirrorOrientation.get(solvedMirrorSize - 1).add(Integer.valueOf(piece.getOrientation()));
					//locked[newX][newY] = true;
					//return true; // comment this broke the above 2 lines
				}
			}
			
			visitedMirrors.remove(p.toString());
		} else {
			LaserDirection newCursor = this.map[newX][newY].bounce(cursor);
			//if (findSolution(newCursor, locked, newX, newY, false, depth + 1)) return true;
			findSolution(newCursor, locked, newX, newY, false, depth + 1, reached);
		}
		
		// -- no
		//return false;
		return false;
	}
	
	// -- count how may solutions this level has, zero if impossible.
	//public int countSolution() {
	private GamePiece[][] checkedMap = null;
	private boolean isSolved = false;
	
	private ArrayList<HashSet<String>> receiverReached;
	public boolean solve() {
		if (checkedMap == this.map) return isSolved;
		else checkedMap = this.map;
		//
		updateProjectorData();
		updateReceiverData();		
		// with countless times of testing, thesis stating and attempt proving,
		// i have come to accept that with this current rule for the game
		// there is only 1 solution.
		// One Projector can only hit One Receiver, at max, with any mirror-configuration.
		// And, there is only One Mirror configuration for a pair of Projector and Receiver. (meaning 1 solution).
		// edit1: this might be wrong..
		if (this.projectorCount < this.receiverCount) return false;
		
		receiverReached = new ArrayList<HashSet<String>>();
		
		boolean[][] locked = new boolean[height][width];
		for (int ih = 0; ih < height; ih ++)
			for (int iw = 0; iw < width; iw++)
				locked[ih][iw] = (!this.map[ih][iw].getRotatable());
		// -- resets something
		solvedMirror.clear();
		solvedMirrorOrientation.clear();
		solvedMirrorSize = 0;
	
		// runs
		int recvCount = this.receiverCount;
		for (int i = 0; i < this.projectorCount; i++) {
			int ih = projectorCoord.get(i).X;
			int iw = projectorCoord.get(i).Y;
			receiverReached.add(new HashSet<String>());
			
			LaserDirection cursor = this.map[ih][iw].bounce(null);
			
//			boolean possible = findSolution(cursor, locked, ih, iw, true, 0, receiverReached.get(i));
//			if (possible == true) {
//				recvCount--;
//			}
			findSolution(cursor, locked, ih, iw, true, 0, receiverReached.get(i));
		}
		// -- before multiple receivers
//		if (recvCount > 0) {
//			isSolved = false;
//		} else {
//			isSolved = true;
//		}		
//		return isSolved;
		
		// -- multiple receivers, no solution writing available..
		HashSet<String> combine = new HashSet<String>();
		for (int i = 0; i < this.projectorCount; i++) {
			Iterator<String> it = receiverReached.get(i).iterator();
		    while(it.hasNext()){
		       combine.add(it.next());
		    }
		}
//		Iterator<String> it = combine.iterator();
//		System.out.println("RECEIVERS SET: ");
//	    while(it.hasNext()){
//	       System.out.println(it.next());
//	    }
		
		if (combine.size() >= receiverCount) {
			isSolved = true;
		} else {
			isSolved = false;
		}
		return isSolved;
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
	// -- actual playing methods	
	public LaserDirection[][] laserMap = null;
	
	public boolean launchPlay() {
		if (this.solve() == false) {
			System.out.println("!!! This Level is impossible.");
			return false;
		}
		// -- 
		laserMap = new LaserDirection[height][width];
		for (int ih = 0; ih < height; ih++)
			for (int iw = 0; iw < width; iw++)
				laserMap[ih][iw] = LaserDirection.LaserLose;
		
		int status = Level.STATUS_ONGOING;
		while (status == Level.STATUS_ONGOING) {
			status = this.testSolution(laserMap);
			if (status == Level.STATUS_WIN) {
				break;
			}
			
			System.out.println("Laser =");
			for (int ih = 0; ih < height; ih++)
				for (int iw = 0; iw < width; iw++) {
					System.out.print(laserMap[ih][iw] + " ");
					if (iw + 1 == width) System.out.println("");
				}
			
			// -- debugging..
			System.out.println(this);
			System.out.print("<< ");
			Scanner sc = new Scanner(System.in);
			String line = sc.nextLine().trim();
			String[] inputs = line.split("\\s+");
			if (issueCommand(inputs)) break;
		}
		
		return true;
	}
	public boolean issueCommand(String[] inputs) {
		if (inputs[0].equals("EXIT")) {
			System.out.println("Exiting..");
			return true;
		}
		
		System.out.println("@Level @issueCommand: " + inputs[0] + "|" + inputs[1] + "|" + inputs[2]);
		try {
			int x = Integer.parseInt(inputs[1]);
			int y = Integer.parseInt(inputs[2]);
			
			if (inputs[0].equals("ROTATE")) {
				System.out.println("@issueCommand: rotating..");
				if (!this.map[x][y].rotate()) {
					// TODO: add unrotatable event handling here;
					System.out.println("Cannot rotate this piece.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
	
	public static final int STATUS_ONGOING = 0, STATUS_WIN = 1, STATUS_IMPOSSIBLE = -1;
	
	public int testSolution(LaserDirection[][] laser) {
		if (this.solve() == false) {
			System.out.println("!!! This Level is impossible.");
			return STATUS_IMPOSSIBLE;
		}
		int receiverLeft = this.receiverCount;
		for (int ip = 0; ip < this.projectorCount; ip++) {
			int ih = projectorCoord.get(ip).X;
			int iw = projectorCoord.get(ip).Y;
			
			LaserDirection cursor = this.map[ih][iw].bounce(null);
			while (true) {
				laser[ih][iw] = cursor;
				
				if (cursor.isLose()) break;
				if (cursor.isWin()) {
					receiverLeft--;
					break;
				}
				ih += cursor.getDx();
				iw += cursor.getDy();
				System.out.println("@testSol: " + ih + "|" + iw);
				
				if (ih < 0 || ih == this.height) break;
				if (iw < 0 || iw == this.width) break;
				cursor = this.map[ih][iw].bounce(cursor);
			}
		}
		
		System.out.println("@testSolution: receiverLeft = " + receiverLeft);
		return (receiverLeft > 0 ? Level.STATUS_ONGOING : Level.STATUS_WIN);
	}
	
	// -- overridden methods
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
