package test;

import gamepiece.GamePiece;
import gamepiece.GamePieceEmpty;
import gamepiece.GamePieceMirror;
import gamepiece.GamePieceProjector;
import gamepiece.GamePieceReceiver;
import gamepiece.GamePieceWall;
import gameplay.Level;

public class Tests {
	public static Level Test0() {
		GamePiece[][] M = new GamePiece[2][2];
		M[0][0] = new GamePieceProjector(GamePiece.ORIENT_DOWN);
		M[0][1] = new GamePieceReceiver(GamePiece.ORIENT_DOWN);
		
		M[1][0] = new GamePieceMirror(GamePiece.ORIENT_RIGHT);
		M[1][1] = new GamePieceWall();
		
		Level lvl = new Level(M);
		lvl.setLevelDescription("TestImpossible");
		return lvl;
	}
	
	public static Level Test1() {
		GamePiece[][] M = new GamePiece[1][2];
		M[0][0] = new GamePieceProjector(GamePiece.ORIENT_RIGHT);
		M[0][1] = new GamePieceReceiver(GamePiece.ORIENT_RIGHT);
		
		Level lvl = new Level(M);
		lvl.setLevelDescription("Test 0");
		return lvl;
	}

	public static Level Test2() {
		GamePiece[][] M = new GamePiece[1][3];
		M[0][0] = new GamePieceProjector(GamePiece.ORIENT_RIGHT);
		M[0][1] = new GamePieceEmpty();
		M[0][2] = new GamePieceReceiver(GamePiece.ORIENT_RIGHT);
		
		Level lvl = new Level(M);
		lvl.setLevelDescription("Test 0_1");
		return lvl;
	}
	
	public static Level Test3() {
		GamePiece[][] M = new GamePiece[1][3];
		M[0][0] = new GamePieceProjector(GamePiece.ORIENT_RIGHT);
		M[0][1] = new GamePieceWall();
		M[0][2] = new GamePieceReceiver(GamePiece.ORIENT_RIGHT);
		
		Level lvl = new Level(M);
		lvl.setLevelDescription("Test 0_2");
		return lvl;
	}
	
	public static Level Test4() {
		GamePiece[][] M = new GamePiece[3][3];
		M[0][0] = new GamePieceProjector(GamePiece.ORIENT_DOWN);
		M[0][1] = new GamePieceWall();
		M[0][2] = new GamePieceReceiver(GamePiece.ORIENT_UP);
		
		M[1][0] = new GamePieceEmpty();
		M[1][1] = new GamePieceMirror(true, GamePiece.ORIENT_DOWN);
		M[1][2] = new GamePieceMirror(true, GamePiece.ORIENT_DOWN);
		
		M[2][0] = new GamePieceMirror(true, GamePiece.ORIENT_DOWN);
		M[2][1] = new GamePieceMirror(true, GamePiece.ORIENT_DOWN);
		M[2][2] = new GamePieceMirror(true, GamePiece.ORIENT_DOWN);
		
		Level lvl = new Level(M);
		lvl.setLevelDescription("Test 1");
		return lvl;
	}
	
	public static Level Test5() {
		GamePiece[][] M = new GamePiece[3][3];
		M[0][0] = new GamePieceProjector(GamePiece.ORIENT_RIGHT);
		M[0][1] = new GamePieceEmpty();
		M[0][2] = new GamePieceMirror(GamePiece.ORIENT_UP);
		
		M[1][0] = new GamePieceWall();
		M[1][1] = new GamePieceReceiver(GamePiece.ORIENT_LEFT);
		M[1][2] = new GamePieceMirror(GamePiece.ORIENT_DOWN);
		
		M[2][0] = new GamePieceReceiver(GamePiece.ORIENT_LEFT);
		M[2][1] = new GamePieceProjector(GamePiece.ORIENT_LEFT);
		M[2][2] = new GamePieceMirror(true, GamePiece.ORIENT_DOWN);
		
		Level lvl = new Level(M);
		lvl.setLevelDescription("Test 11");
		return lvl;
	}
	
	public static Level Test6() {
		GamePiece[][] M = new GamePiece[4][4];
		M[0][0] = new GamePieceProjector(GamePiece.ORIENT_DOWN);
		M[0][1] = new GamePieceMirror(true, GamePiece.ORIENT_DOWN);
		M[0][2] = new GamePieceMirror(true, GamePiece.ORIENT_DOWN);
		M[0][3] = new GamePieceMirror(true, GamePiece.ORIENT_DOWN);
		
		M[1][0] = new GamePieceMirror(true, GamePiece.ORIENT_DOWN);
		M[1][1] = new GamePieceMirror(true, GamePiece.ORIENT_DOWN);
		M[1][2] = new GamePieceMirror(true, GamePiece.ORIENT_DOWN);
		M[1][3] = new GamePieceMirror(true, GamePiece.ORIENT_DOWN);
		
		M[2][0] = new GamePieceMirror(true, GamePiece.ORIENT_DOWN);
		M[2][1] = new GamePieceMirror(true, GamePiece.ORIENT_DOWN);
		M[2][2] = new GamePieceMirror(true, GamePiece.ORIENT_DOWN);
		M[2][3] = new GamePieceMirror(true, GamePiece.ORIENT_DOWN);
		
		M[3][0] = new GamePieceReceiver(GamePiece.ORIENT_DOWN);
		M[3][1] = new GamePieceMirror(true, GamePiece.ORIENT_DOWN);
		M[3][2] = new GamePieceMirror(true, GamePiece.ORIENT_DOWN);
		M[3][3] = new GamePieceMirror(true, GamePiece.ORIENT_DOWN);
		
		Level lvl = new Level(M);
		lvl.setLevelDescription("Test 2");
		return lvl;
	}
}
