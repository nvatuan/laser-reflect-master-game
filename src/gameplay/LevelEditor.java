package gameplay;

import java.util.ArrayList;
//import java.util.HashMap;
import java.util.Scanner;

import gamepiece.*;

public class LevelEditor {
	private Level lvl;
	public LevelEditor(int w, int h) {
		lvl = new Level(w, h);
	}
	public LevelEditor(Level ref) {
		lvl = new Level(ref);
	}
	
	public boolean levelBoundCheck(int x, int y) {
		return ((0 <= x && x < lvl.getHeight()) && (0 <= y && y < lvl.getWidth()));
	}
	
	// ===== methods
	boolean _exit = false;
	public void launchEditor() {
		_init = false;
		_exit = false;
		gatheringGamePiece();
		//
		System.out.println("=============================================================");
		System.out.println("                 WELCOME TO THE LEVEL EDITOR                 ");
		System.out.println("=============================================================");
		while (!_exit) {
			System.out.println("The current map is = \n" + lvl.toString());
			System.out.print(this.getPromptMessage());
			this.parse(this.getLine());
		}
	}
	
	// --
	private boolean _init = false;
	private ArrayList<GamePiece> ListOfGamePiece;
	//private HashMap<Integer, GamePiece> hashToPiece;
	private int GamePieceCount = 0;
	public void gatheringGamePiece() {
		if (_init == false) {
			ListOfGamePiece = new ArrayList<GamePiece>();
			//hashToPiece = new HashMap<Integer, GamePiece>();
			
			ListOfGamePiece.add(new GamePieceProjector());
			ListOfGamePiece.add(new GamePieceReceiver());
			ListOfGamePiece.add(new GamePieceMirror(false));
			ListOfGamePiece.add(new GamePieceMirror(true));
			ListOfGamePiece.add(new GamePieceWall());
			ListOfGamePiece.add(new GamePieceEmpty());
			
			GamePieceCount = ListOfGamePiece.size();
			//for (int i = 0; i < GamePieceCount; i++)
			//	hashToPiece.put(i, ListOfGamePiece.get(i));
			
			_init = true;
		}
	}
	
	// -- parsing and such..
	
	public String getPromptMessage() {
		String msg = "";
		msg += ("PROMPT: -------------------------------------------------\n");
		msg += (">> 1. SET a tile X;Y to be something\n");
		msg += ("Syntax: SET [X] [Y] [GamePieceID]\n");
		for (int i = 0; i < GamePieceCount; i++) {
			msg += ("ID = " + i + " | GamePiece name = " + ListOfGamePiece.get(i).name() + "\n");
		}
		msg += (">> 2. ROTATE tile X;Y\n");
		msg += ("Syntax: ROTATE [X] [Y]\n");
		msg += (">> 3. CREATE a map with WIDTH, HEIGTH filled with empty tiles\n");
		msg += ("Syntax: CREATE [WIDTH] [HEIGHT]\n");
		msg += (">> 4. EXIT\n");
		msg += ("---------------------------------------------------------\n");
		msg += ("<< ");
		return msg;
	}
	
	private static Scanner sc = new Scanner(System.in);
	public String getLine() {
		return sc.nextLine();
	}
	
	public void parse(String input) {
		input = input.trim();

		if (input.equals("EXIT")) {
			System.out.println("Exiting..");
			this._exit = true;
			return;
		}
		
		String[] inputs = input.split("\\s+");
//		System.out.println("[" + input + "]");
//		System.out.println("@parsing: " + inputs[0] + "|" + inputs[1] + "|" + inputs[2]);

		int x = Integer.parseInt(inputs[1]);
		int y = Integer.parseInt(inputs[2]);
		
		if (inputs[0].equals("SET")) {
			System.out.println("setting..");
			int idx = Integer.parseInt(inputs[3]);	
			lvl.map[x][y] = ListOfGamePiece.get(idx);//.clone();
		} else
		if (inputs[0].equals("ROTATE")) {
			System.out.println("rotating..");
			lvl.map[x][y].rotateAnyway();
		} else
		if (inputs[0].equals("CREATE")) {
			System.out.println("creating..");
			lvl = new Level(x, y);
		}
	}
}
