package test;

import gamepiece.*;
import gameplay.Level;

public class Tester {	
	
	public static void main(String[] args) {		
		GamePiece[][] M = new GamePiece[3][3];
		M[0][0] = new GamePieceProjector(GamePiece.ORIENT_DOWN);
		M[0][1] = new GamePieceWall();
		M[0][2] = new GamePieceReceiver(GamePiece.ORIENT_DOWN);
		
		M[1][0] = new GamePieceEmpty();
		M[1][1] = new GamePieceMirror(true, GamePiece.ORIENT_RIGHT);
		M[1][2] = new GamePieceMirror(true, GamePiece.ORIENT_LEFT);
		
		M[2][0] = new GamePieceMirror(true, GamePiece.ORIENT_UP);
		M[2][1] = new GamePieceMirror(true, GamePiece.ORIENT_LEFT);
		M[2][2] = new GamePieceMirror(true, GamePiece.ORIENT_DOWN);
		
		Level lvl = new Level(M);
		System.out.println(lvl.toString());
	}
}
