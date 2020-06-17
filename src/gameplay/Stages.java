package gameplay;

import java.util.Random;

import gamepiece.GamePiece;
import gamepiece.GamePieceEmpty;
import gamepiece.GamePieceMirror;
import gamepiece.GamePieceProjector;
import gamepiece.GamePieceReceiver;
import gamepiece.GamePieceWall;

public class Stages {
	public static Random rnd = new Random();
	
	public static Level Stage1() {
		GamePiece[][] M = new GamePiece[5][5];
		M[0][0] = new GamePieceWall();
		M[0][1] = new GamePieceWall();
		M[0][2] = new GamePieceWall();
		M[0][3] = new GamePieceEmpty();
		M[0][4] = new GamePieceWall();
		
		M[1][0] = new GamePieceWall();
		M[1][1] = new GamePieceProjector(GamePiece.ORIENT_RIGHT);
		M[1][2] = new GamePieceEmpty();
		M[1][3] = new GamePieceMirror(true, GamePiece.ORIENT_LEFT);
		M[1][4] = new GamePieceWall();
		
		M[2][0] = new GamePieceWall();
		M[2][1] = new GamePieceEmpty();
		M[2][2] = new GamePieceEmpty();
		M[2][3] = new GamePieceEmpty();
		M[2][4] = new GamePieceWall();
		
		M[3][0] = new GamePieceWall();
		M[3][1] = new GamePieceEmpty();
		M[3][2] = new GamePieceEmpty();
		M[3][3] = new GamePieceReceiver( GamePiece.ORIENT_DOWN);
		M[3][4] = new GamePieceWall();
		
		M[4][0] = new GamePieceWall();
		M[4][1] = new GamePieceWall();
		M[4][2] = new GamePieceWall();
		M[4][3] = new GamePieceWall();
		M[4][4] = new GamePieceWall();
		
		Level lvl = new Level(M);
		lvl.setLevelDescription("Welcome to LaserReflectMaster v0.9");
		return lvl;
	}

	public static Level Stage2() {
		GamePiece[][] M = new GamePiece[5][5];
		M[0][0] = new GamePieceEmpty();
		M[0][1] = new GamePieceMirror(GamePiece.ORIENT_LEFT);
		M[0][2] = new GamePieceMirror(GamePiece.ORIENT_LEFT);
		M[0][3] = new GamePieceMirror(GamePiece.ORIENT_LEFT);
		M[0][4] = new GamePieceMirror(GamePiece.ORIENT_LEFT);
		
		M[1][0] = new GamePieceEmpty();
		M[1][1] = new GamePieceEmpty();
		M[1][2] = new GamePieceMirror(GamePiece.ORIENT_LEFT);
		M[1][3] = new GamePieceMirror(GamePiece.ORIENT_LEFT);
		M[1][4] = new GamePieceEmpty();
		
		M[2][0] = new GamePieceProjector(GamePiece.ORIENT_RIGHT);
		M[2][1] = new GamePieceMirror(GamePiece.ORIENT_LEFT);
		M[2][2] = new GamePieceEmpty();
		M[2][3] = new GamePieceEmpty();
		M[2][4] = new GamePieceEmpty();
		
		M[3][0] = new GamePieceEmpty();
		M[3][1] = new GamePieceEmpty();
		M[3][2] = new GamePieceEmpty();
		M[3][3] = new GamePieceEmpty();
		M[3][4] = new GamePieceEmpty();
		
		M[4][0] = new GamePieceEmpty();
		M[4][1] = new GamePieceMirror(GamePiece.ORIENT_LEFT);
		M[4][2] = new GamePieceEmpty();
		M[4][3] = new GamePieceEmpty();
		M[4][4] = new GamePieceReceiver(GamePiece.ORIENT_DOWN);
		
		Level lvl = new Level(M);
		lvl.setLevelDescription("Welcome to LaserReflectMaster v0.9");
		return lvl;
	}
	public static Level Stage3() {
		GamePiece[][] M = new GamePiece[6][6];
		M[0][0] = new GamePieceMirror(GamePiece.ORIENT_LEFT);
		M[0][1] = new GamePieceEmpty();
		M[0][2] = new GamePieceEmpty();
		M[0][3] = new GamePieceMirror(GamePiece.ORIENT_LEFT);
		M[0][4] = new GamePieceMirror(false, GamePiece.ORIENT_RIGHT);
		M[0][5] = new GamePieceMirror(GamePiece.ORIENT_UP);;
		
		M[1][0] = new GamePieceMirror(GamePiece.ORIENT_DOWN);
		M[1][1] = new GamePieceEmpty();
		M[1][2] = new GamePieceEmpty();
		M[1][3] = new GamePieceEmpty();
		M[1][4] = new GamePieceEmpty();
		M[1][5] = new GamePieceMirror(GamePiece.ORIENT_RIGHT);
		
		M[2][0] = new GamePieceEmpty();
		M[2][1] = new GamePieceWall();
		M[2][2] = new GamePieceProjector(GamePiece.ORIENT_LEFT);
		M[2][3] = new GamePieceProjector(GamePiece.ORIENT_UP);
		M[2][4] = new GamePieceEmpty();
		M[2][5] = new GamePieceEmpty();
		
		M[3][0] = new GamePieceEmpty();
		M[3][1] = new GamePieceEmpty();
		M[3][2] = new GamePieceProjector(GamePiece.ORIENT_DOWN);
		M[3][3] = new GamePieceProjector(GamePiece.ORIENT_RIGHT);
		M[3][4] = new GamePieceReceiver(GamePiece.ORIENT_DOWN);
		M[3][5] = new GamePieceEmpty();
		
		M[4][0] = new GamePieceMirror(GamePiece.ORIENT_RIGHT);
		M[4][1] = new GamePieceEmpty();
		M[4][2] = new GamePieceMirror(GamePiece.ORIENT_LEFT);
		M[4][3] = new GamePieceEmpty();
		M[4][4] = new GamePieceEmpty();
		M[4][5] = new GamePieceEmpty();
		
		M[5][0] = new GamePieceMirror(GamePiece.ORIENT_RIGHT);
		M[5][1] = new GamePieceEmpty();
		M[5][2] = new GamePieceWall();
		M[5][3] = new GamePieceWall();
		M[5][4] = new GamePieceEmpty();
		M[5][5] = new GamePieceMirror(GamePiece.ORIENT_UP);
		
		Level lvl = new Level(M);
		lvl.setLevelDescription("Multiple Projectors");
		return lvl;
	}

	public static Level Stage4() {
		GamePiece[][] M = new GamePiece[7][7];
		for (int i = 0; i < 7; i++)
			for (int j = 0; j < 7; j++)
				M[i][j] = new GamePieceEmpty();
		// --
		M[5][0] = new GamePieceProjector(GamePiece.ORIENT_UP);
		M[6][1] = new GamePieceProjector(GamePiece.ORIENT_RIGHT);
		
		M[0][5] = new GamePieceReceiver(GamePiece.ORIENT_RIGHT);
		M[1][6] = new GamePieceReceiver(GamePiece.ORIENT_UP);
		
		
		int[] r = new int[] {
			0, 1, 1, 2, 2, 3, 3, 3, 3, 4, 4, 6, 6, 6, 6, 6
		};
		int[] c = new int[] {
			4, 1, 5, 0, 2, 0, 1, 3, 4, 0, 4, 2, 3, 4, 5, 6
		};
		
		int mir = r.length;
		for (int i = 0; i < mir; i++)
			M[r[i]][c[i]] = new GamePieceMirror(rnd.nextInt(GamePiece.ORIENT_COUNT));
		
		M[0][0] = new GamePieceMirror(false, GamePiece.ORIENT_RIGHT);
		M[5][1] = new GamePieceMirror(false, GamePiece.ORIENT_UP);
		
		M[2][3] = new GamePieceWall();
		M[6][0] = new GamePieceWall();
		M[0][6] = new GamePieceWall();
		Level lvl = new Level(M);
		lvl.setLevelDescription("Mirror Hazard");
		return lvl;
	}

	public static Level Stage5() {
		GamePiece[][] M = new GamePiece[8][8];
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				M[i][j] = new GamePieceMirror(rnd.nextInt(GamePiece.ORIENT_COUNT));
		// --
		M[0][0] = new GamePieceProjector(GamePiece.ORIENT_RIGHT);
		M[7][7] = new GamePieceProjector(GamePiece.ORIENT_LEFT);
		
		M[0][7] = new GamePieceReceiver(GamePiece.ORIENT_RIGHT);
		M[7][0] = new GamePieceReceiver(GamePiece.ORIENT_LEFT);
		
		int[] r = new int[] {
			0,0,1,1,1,2,2,2,2,2,3,3,3,3,4,4,4,4,5,5,5,5,6,6,6,6,7,7
		};
		int[] c = new int[] {
			1,2,1,3,6,0,2,3,5,6,1,2,5,6,4,5,6,7,3,4,5,7,1,2,3,5,3,4
		};
		int n = r.length;
		for (int i = 0; i < n; i++)
			M[r[i]][c[i]] = new GamePieceEmpty();
		
		
		Level lvl = new Level(M);
		lvl.setLevelDescription("The closer the better");
		return lvl;
	}
}
