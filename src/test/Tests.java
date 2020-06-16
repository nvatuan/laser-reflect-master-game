package test;

import java.util.Random;

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
		GamePiece[][] M = new GamePiece[5][5];
		M[0][0] = new GamePieceProjector(GamePiece.ORIENT_DOWN);
		M[0][1] = new GamePieceEmpty();
		M[0][2] = new GamePieceEmpty();
		M[0][3] = new GamePieceMirror(true, GamePiece.ORIENT_DOWN);
		M[0][4] = new GamePieceEmpty();
		
		M[1][0] = new GamePieceEmpty();
		M[1][1] = new GamePieceEmpty();
		M[1][2] = new GamePieceMirror(true, GamePiece.ORIENT_DOWN);
		M[1][3] = new GamePieceEmpty();
		M[1][4] = new GamePieceEmpty();
		
		M[2][0] = new GamePieceEmpty();
		M[2][1] = new GamePieceMirror(true, GamePiece.ORIENT_DOWN);
		M[2][2] = new GamePieceEmpty();
		M[2][3] = new GamePieceEmpty();
		M[2][4] = new GamePieceMirror(true, GamePiece.ORIENT_RIGHT);
		
		M[3][0] = new GamePieceReceiver(GamePiece.ORIENT_DOWN);
		M[3][1] = new GamePieceEmpty();
		M[3][2] = new GamePieceEmpty();
		M[3][3] = new GamePieceMirror(true, GamePiece.ORIENT_DOWN);
		M[3][4] = new GamePieceEmpty();
		
		M[4][0] = new GamePieceMirror(true, GamePiece.ORIENT_DOWN);
		M[4][1] = new GamePieceEmpty();
		M[4][2] = new GamePieceEmpty();
		M[4][3] = new GamePieceMirror(true, GamePiece.ORIENT_DOWN);
		M[4][4] = new GamePieceEmpty();
		
		Level lvl = new Level(M);
		lvl.setLevelDescription("Test 2");
		return lvl;
	}
	
	public static Level TestGuiDebug() {
		GamePiece[][] M = new GamePiece[5][4];
		M[0][0] = new GamePieceProjector(GamePiece.ORIENT_UP);
		M[0][1] = new GamePieceProjector(GamePiece.ORIENT_RIGHT);
		M[0][2] = new GamePieceProjector(GamePiece.ORIENT_DOWN);
		M[0][3] = new GamePieceProjector(GamePiece.ORIENT_LEFT);
		
		M[1][0] = new GamePieceReceiver(GamePiece.ORIENT_UP);
		M[1][1] = new GamePieceReceiver(GamePiece.ORIENT_RIGHT);
		M[1][2] = new GamePieceReceiver(GamePiece.ORIENT_DOWN);
		M[1][3] = new GamePieceReceiver(GamePiece.ORIENT_LEFT);
		
		M[2][0] = new GamePieceMirror(true, GamePiece.ORIENT_UP);
		M[2][1] = new GamePieceMirror(true, GamePiece.ORIENT_RIGHT);
		M[2][2] = new GamePieceMirror(true, GamePiece.ORIENT_DOWN);
		M[2][3] = new GamePieceMirror(true, GamePiece.ORIENT_LEFT);
				
		M[3][0] = new GamePieceMirror(false, GamePiece.ORIENT_UP);
		M[3][1] = new GamePieceMirror(false, GamePiece.ORIENT_RIGHT);
		M[3][2] = new GamePieceMirror(false, GamePiece.ORIENT_DOWN);
		M[3][3] = new GamePieceMirror(false, GamePiece.ORIENT_LEFT);
		
		M[4][0] = new GamePieceEmpty();
		M[4][1] = new GamePieceWall();
		M[4][2] = new GamePieceEmpty();
		M[4][3] = new GamePieceWall();
		
		Level lvl = new Level(M);
		lvl.setLevelDescription("Test Debug Gui");
		return lvl;
	}
	
	public static Level TestRand() {
		int size = 4;
		GamePiece[][] M = new GamePiece[size][size];
		Random rnd = new Random();
		
		for (int ih = 0; ih < size; ih++)
			for (int iw = 0; iw < size; iw++) {
				int idx = rnd.nextInt(GamePiece.getUniqueGamePieceCount());
				M[ih][iw] = GamePiece.getListOfUniqueGamePiece().get(idx);
			}
		Level lvl = new Level(M);
		lvl.setLevelDescription("Random");
		return lvl;
	}
}
