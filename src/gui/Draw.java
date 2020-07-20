package gui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import gamepiece.*;
import gameplay.LaserDirection;

public class Draw {
	
	private static boolean initImportGamePieceImage = false;
	private static HashMap<Integer, ImageIcon> pieceIcon = new HashMap<Integer, ImageIcon>();
	
	public static ImageIcon Empty, MirrorDown, MirrorDownLocked, MirrorLeft, MirrorLeftLocked, MirrorRight, MirrorRightLocked, MirrorUp, MirrorUpLocked, Projector, 
		ProjectorDown, ProjectorLeft, ProjectorRight, ProjectorUp, Receiver, ReceiverDown, ReceiverLeft, ReceiverRight, ReceiverUp, Wall;
	
	public static void importGamePieceImage() {	
		if (initImportGamePieceImage == false) {
			try {
				Empty = new ImageIcon(MainBody.class.getResource("/piece/Empty.png"));
				MirrorDown = new ImageIcon(Draw.class.getResource("/piece/MirrorDown.png"));
				MirrorDownLocked = new ImageIcon(Draw.class.getResource("/piece/MirrorDownLocked.png"));
				MirrorLeft = new ImageIcon(Draw.class.getResource("/piece/MirrorLeft.png"));
				MirrorLeftLocked = new ImageIcon(Draw.class.getResource("/piece/MirrorLeftLocked.png"));
				MirrorRight = new ImageIcon(Draw.class.getResource("/piece/MirrorRight.png"));
				MirrorRightLocked = new ImageIcon(Draw.class.getResource("/piece/MirrorRightLocked.png"));
				MirrorUp = new ImageIcon(Draw.class.getResource("/piece/MirrorUp.png"));
				MirrorUpLocked = new ImageIcon(Draw.class.getResource("/piece/MirrorUpLocked.png"));
				Projector = new ImageIcon(Draw.class.getResource("/piece/Projector.png"));
				ProjectorDown = new ImageIcon(Draw.class.getResource("/piece/ProjectorDown.png"));
				ProjectorLeft = new ImageIcon(Draw.class.getResource("/piece/ProjectorLeft.png"));
				ProjectorRight = new ImageIcon(Draw.class.getResource("/piece/ProjectorRight.png"));
				ProjectorUp = new ImageIcon(Draw.class.getResource("/piece/ProjectorUp.png"));
				Receiver = new ImageIcon(Draw.class.getResource("/piece/Receiver.png"));
				ReceiverDown = new ImageIcon(Draw.class.getResource("/piece/ReceiverDown.png"));
				ReceiverLeft = new ImageIcon(Draw.class.getResource("/piece/ReceiverLeft.png"));
				ReceiverRight = new ImageIcon(Draw.class.getResource("/piece/ReceiverRight.png"));
				ReceiverUp = new ImageIcon(Draw.class.getResource("/piece/ReceiverUp.png"));
				Wall = new ImageIcon(Draw.class.getResource("/piece/Wall.png"));
				
				// -- hash
				GamePieceProjector prjU = new GamePieceProjector(GamePiece.ORIENT_UP);
				GamePieceProjector prjR = new GamePieceProjector(GamePiece.ORIENT_RIGHT);
				GamePieceProjector prjD = new GamePieceProjector(GamePiece.ORIENT_DOWN);
				GamePieceProjector prjL = new GamePieceProjector(GamePiece.ORIENT_LEFT);
				pieceIcon.put(prjU.getHash(), ProjectorUp);
				pieceIcon.put(prjR.getHash(), ProjectorRight);
				pieceIcon.put(prjD.getHash(), ProjectorDown);
				pieceIcon.put(prjL.getHash(), ProjectorLeft);
				
				
				GamePieceReceiver rcvU = new GamePieceReceiver(GamePiece.ORIENT_UP); 
				GamePieceReceiver rcvR = new GamePieceReceiver(GamePiece.ORIENT_RIGHT);
				GamePieceReceiver rcvD = new GamePieceReceiver(GamePiece.ORIENT_DOWN);
				GamePieceReceiver rcvL = new GamePieceReceiver(GamePiece.ORIENT_LEFT);
				pieceIcon.put(rcvU.getHash(), ReceiverUp);
				pieceIcon.put(rcvR.getHash(), ReceiverRight);
				pieceIcon.put(rcvD.getHash(), ReceiverDown);
				pieceIcon.put(rcvL.getHash(), ReceiverLeft);
				
				
				GamePieceMirror mirU = new GamePieceMirror(true, GamePiece.ORIENT_UP); 
				GamePieceMirror mirR = new GamePieceMirror(true, GamePiece.ORIENT_RIGHT);
				GamePieceMirror mirD = new GamePieceMirror(true, GamePiece.ORIENT_DOWN);
				GamePieceMirror mirL = new GamePieceMirror(true, GamePiece.ORIENT_LEFT);
				pieceIcon.put(mirU.getHash(), MirrorUp);
				pieceIcon.put(mirR.getHash(), MirrorRight);
				pieceIcon.put(mirD.getHash(), MirrorDown);
				pieceIcon.put(mirL.getHash(), MirrorLeft);
				//System.out.println("HASH DEBUG: \n" + mirU.getHash() + " " + mirR.getHash() + " " + mirD.getHash() + " " + mirL.getHash());
				
				GamePieceMirror mirUL = new GamePieceMirror(false, GamePiece.ORIENT_UP); 
				GamePieceMirror mirRL = new GamePieceMirror(false, GamePiece.ORIENT_RIGHT);
				GamePieceMirror mirDL = new GamePieceMirror(false, GamePiece.ORIENT_DOWN);
				GamePieceMirror mirLL = new GamePieceMirror(false, GamePiece.ORIENT_LEFT);
				pieceIcon.put(mirUL.getHash(), MirrorUpLocked);
				pieceIcon.put(mirRL.getHash(), MirrorRightLocked);
				pieceIcon.put(mirDL.getHash(), MirrorDownLocked);
				pieceIcon.put(mirLL.getHash(), MirrorLeftLocked);
				//System.out.println("HASH DEBUG: \n" + mirUL.getHash() + " " + mirRL.getHash() + " " + mirDL.getHash() + " " + mirLL.getHash());
				
				GamePieceEmpty empt = new GamePieceEmpty();
				pieceIcon.put(empt.getHash(), Empty);
				
				GamePieceWall wll = new GamePieceWall();
				pieceIcon.put(wll.getHash(), Wall);
				
				//JOptionPane.showMessageDialog(null, wll.getHash() + "");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Cannot find GamePiece images!");
			}
			initImportGamePieceImage = true;
		}
	}
	
	//
	public final int grid = 200;
	public static final int offsetX = 5, offsetY = 5;
	// -- draw
	public BufferedImage drawMap(GamePiece[][] map) {
		importGamePieceImage();
		//
		int h = map.length; int w = map[0].length;
		
		int sX = offsetX, sY = offsetY;
		int eX = h*grid, eY = w*grid;
		
		BufferedImage mapImg = new BufferedImage(w*grid + offsetY*2, h*grid + offsetX*2, BufferedImage.TYPE_INT_ARGB);
		Graphics g = mapImg.getGraphics();
		Graphics2D g2d = (Graphics2D) g;
		
		// -------- Draw grid
		g2d.setColor(new Color(0, 0, 0, 0));
		g2d.fillRect(0, 0, w*grid + offsetY*2, h*grid + offsetX*2);
		
		g.setColor(Color.WHITE);
		g.drawRect(sY, sX, eY, eX);

		// --
		int delta = grid - pieceIcon.get(map[0][0].getHash()).getIconHeight();
		if (delta < 10) delta = 10;
		int gridOffset = delta/2;
		
		for (int ih = 0; ih < h; ih++) {
			for (int iw = 0; iw < w; iw++) {
//				System.out.print(map[ih][iw].getHash() + " ");
//				if (iw + 1 == w) System.out.println("");
				
				//System.out.println(map[ih][iw].getHash());
				ImageIcon icon = pieceIcon.get(map[ih][iw].getHash());
				//System.out.println(icon == null);
				int x1 = ((iw * grid)) + offsetX;
				int y1 = ((ih * grid)) + offsetY;
				int x2 = x1 + grid;
				int y2 = y1 + grid;
				
				g.drawImage(icon.getImage(),
						x1 + gridOffset, y1 + gridOffset, x2 - gridOffset, y2 - gridOffset,
						5, 5, icon.getIconWidth() - 5, icon.getIconHeight() - 5,
				null);
				//JOptionPane.showMessageDialog(null, "" + "x1 = " + x1 + " | y1 = " + y1 + " | x2 = " +  x2 + " | y2 = "+ y2);
			}
		}
		
		return mapImg;
	}
	
	public BufferedImage drawLaserDirectionMap(LaserDirection[][] lsr) {
		int h = lsr.length; int w = lsr[0].length;
		
		int sX = offsetX, sY = offsetY;
		int eX = h*grid, eY = w*grid;
		
		BufferedImage lsrImg = new BufferedImage(w*grid + offsetY*2, h*grid + offsetX*2, BufferedImage.TYPE_INT_ARGB);
		Graphics g = lsrImg.getGraphics();
		
		// -------- Draw grid		
		Graphics2D g2d = (Graphics2D) g;	
		
		// -- draw laser here
		for (int ih = 0; ih < h; ih++) {
			for (int iw = 0; iw < w; iw++) {
				if (lsr[ih][iw] != null && !lsr[ih][iw].isLose()) {
					int x1 = ((iw * grid)) + offsetX;
					int y1 = ((ih * grid)) + offsetY;
					int x2 = x1 + grid;
					int y2 = y1 + grid;
					
					int gridOffset = 0;
					
					g2d.setColor(new Color(255, 0, 0, 255));
					g2d.fillRect(x1 + gridOffset, y1 + gridOffset, grid, grid);
				}
			}
		}

		// --		
		return lsrImg;
	}
}
