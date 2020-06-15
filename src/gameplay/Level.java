package gameplay;

import gamepiece.GamePiece;
import datastruct.PairXY;

public class Level {
	// -- fields
	private  int width, height;
	private  GamePiece[][] map;
	
	// please enforce these two to be equal
	private int projectorCount, receiverCount;
	
	private PairXY[] projectorCoord;
	private PairXY[] receiverCoord;
	
	// -- getters
	public int getWidth() { return this.width; }
	public int getHeight() { return this.height; }
	
	public int getProjectorCount() { return this.projectorCount; }
	public int getReceiverCount() { return this.receiverCount; }
	public PairXY[] getProjectorPairXY() { return this.projectorCoord; } 
	public PairXY[] getReceiverPairXY() { return this.receiverCoord; }
	
	// -- methods
	public boolean isValid() {
		return true;
	}
}
