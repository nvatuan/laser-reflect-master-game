package gameplay;

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
	public void launchEditor() {
		System.out.println("=============================================================");
		System.out.println("                 WELCOME TO THE LEVEL EDITOR                 ");
		System.out.println("=============================================================");
		while (true) {
			System.out.println("The current map is = \n" + lvl.toString());
			System.out.print(this.getPromptMessage());
			if (this.parse(this.splitInput(sc.nextLine()))) break;
		}
	}
	
	// -- parsing and such..
	
	public String getPromptMessage() {
		String msg = "";
		msg += ("PROMPT: -------------------------------------------------\n");
		msg += (">> 1. SET a tile X;Y to be something\n");
		msg += ("Syntax: SET [X] [Y] [GamePieceID]\n");
		for (int i = 0; i < GamePiece.getUniqueGamePieceCount(); i++) {
			msg += ("ID = " + i + " | GamePiece name = " + GamePiece.getListOfUniqueGamePiece().get(i).name() + "\n");
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
	
	private static final int SPLIT_SIZE = 4;
	public String[] splitInput (String input) {
		input = input.trim();
		String[] inputs = input.split("\\s+");
		
		String[] splitted = new String[SPLIT_SIZE];
		for (int i = 0; i < SPLIT_SIZE; i++)
			splitted[i] = (i < inputs.length ? inputs[i] : null);
		
		return splitted;
	}
	
	public boolean parse(String[] inputs) {
		if (inputs[0].equals("EXIT")) {
			System.out.println("Exiting..");
			return true;
		}
		
		System.out.println("@LevelEditor @parsing: " + inputs[0] + "|" + inputs[1] + "|" + inputs[2]);
		try {
			int x = Integer.parseInt(inputs[1]);
			int y = Integer.parseInt(inputs[2]);
			
			if (inputs[0].equals("SET")) {
				System.out.println("setting..");
				int idx = Integer.parseInt(inputs[3]);	
				lvl.map[x][y] = GamePiece.getListOfUniqueGamePiece().get(idx);//.clone();
			} else
			if (inputs[0].equals("ROTATE")) {
				System.out.println("rotating..");
				lvl.map[x][y].rotateAnyway();
			} else
			if (inputs[0].equals("CREATE")) {
				System.out.println("creating..");
				lvl = new Level(x, y);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//
		return false;
	}
}
