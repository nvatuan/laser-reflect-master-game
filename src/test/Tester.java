package test;

import java.util.Stack;

import datastruct.PairXY;
import gamepiece.*;
import gameplay.Level;

public class Tester {	
	
	public static void main(String[] args) {		
		GamePiece[][] M = new GamePiece[3][3];
		M[0][0] = new GamePieceProjector(GamePiece.ORIENT_DOWN);
		M[0][1] = new GamePieceWall();
		M[0][2] = new GamePieceReceiver(GamePiece.ORIENT_DOWN);
		
		M[1][0] = new GamePieceEmpty();
		M[1][1] = new GamePieceMirror(true, GamePiece.ORIENT_DOWN);
		M[1][2] = new GamePieceMirror(true, GamePiece.ORIENT_DOWN);
		
		M[2][0] = new GamePieceMirror(true, GamePiece.ORIENT_DOWN);
		M[2][1] = new GamePieceMirror(true, GamePiece.ORIENT_DOWN);
		M[2][2] = new GamePieceMirror(true, GamePiece.ORIENT_DOWN);
		
		Level lvl = new Level(M);
		lvl.setLevelDescription("Test 1");
		System.out.println(lvl.toString());
		
		System.out.println("Trying to solve the level..");
		boolean possible = lvl.hasSolution();
		System.out.println("Level is " + (possible ? "possible." : "impossible."));
		
		Stack<PairXY> mirror = lvl.getBouncedMirror();
		Stack<Integer> mirrorOrient = lvl.getBouncedMirrorOrientation();
		
		while (mirror.size() > 0) {
			int X = mirror.peek().X;
			int Y = mirror.peek().Y;
			int Orient = mirrorOrient.peek();
			//
			mirror.pop();
			mirrorOrient.pop();
			//
			while (lvl.map[X][Y].getOrientation() != Orient) lvl.map[X][Y].rotate();
		}
		System.out.println("Solution: ");
		System.out.println(lvl.toString());
	}
}
