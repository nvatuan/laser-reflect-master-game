package test;

import gameplay.Level;
import gameplay.LevelEditor;
import gameplay.Stages;

public class Tester {

	public static void main(String[] args) {
		Level lvl = Stages.Stage5();
		lvl.launchPlay();
	}
	
	public static void testLevelEditor() {
		Level lvl = Tests.Test3();
		LevelEditor editor = new LevelEditor(lvl);
		
		editor.launchEditor();
	}
	
	public static void LevelSolvableTesting() {
		Level[] tests = new Level[] { Tests.Test0(), Tests.Test1(), Tests.Test2() }; 
		
		for (Level lvl : tests) {
			System.out.println(lvl.toString());
			
			System.out.println("Trying to solve the level..");
			boolean possible = lvl.solve();
			System.out.println("Level is " + (possible ? "possible." : "impossible."));
			
			lvl.writeSolutionToMap();
			
			System.out.println("Solution: ");
			System.out.println(lvl.toString());
		}
	}
	
	
	
}
