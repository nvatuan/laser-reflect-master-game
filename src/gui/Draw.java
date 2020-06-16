package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import gamepiece.*;

public class Draw {
	
	private static boolean initImportGamePieceImage = false;
	private static HashMap<Integer, ImageIcon> pieceIcon = new HashMap<Integer, ImageIcon>();
	
	public static ImageIcon Empty, MirrorDown, MirrorDownLocked, MirrorLeft, MirrorLeftLocked, MirrorRight, MirrorRightLocked, MirrorUp, MirrorUpLocked, Projector, 
		ProjectorDown, ProjectorLeft, ProjectorRight, ProjectorUp, Receiver, ReceiverDown, ReceiverLeft, ReceiverRight, ReceiverUp, Wall;
	
	public static void importGamePieceImage() {	
		if (initImportGamePieceImage == false) {
			try {
				String path = (new File("").getAbsolutePath());
				String prefix = "\\img\\piece\\";
				//
				Empty = new ImageIcon(path + prefix + "Empty.png");
				MirrorDown = new ImageIcon(path + prefix + "MirrorDown.png");
				MirrorDownLocked = new ImageIcon(path + prefix + "MirrorDownLocked.png");
				MirrorLeft = new ImageIcon(path + prefix + "MirrorLeft.png");
				MirrorLeftLocked = new ImageIcon(path + prefix + "MirrorLeftLocked.png");
				MirrorRight = new ImageIcon(path + prefix + "MirrorRight.png");
				MirrorRightLocked = new ImageIcon(path + prefix + "MirrorRightLocked.png");
				MirrorUp = new ImageIcon(path + prefix + "MirrorUp.png");
				MirrorUpLocked = new ImageIcon(path + prefix + "MirrorUpLocked.png");
				Projector = new ImageIcon(path + prefix + "Projector.png");
				ProjectorDown = new ImageIcon(path + prefix + "ProjectorDown.png");
				ProjectorLeft = new ImageIcon(path + prefix + "ProjectorLeft.png");
				ProjectorRight = new ImageIcon(path + prefix + "ProjectorRight.png");
				ProjectorUp = new ImageIcon(path + prefix + "ProjectorUp.png");
				Receiver = new ImageIcon(path + prefix + "Receiver.png");
				ReceiverDown = new ImageIcon(path + prefix + "ReceiverDown.png");
				ReceiverLeft = new ImageIcon(path + prefix + "ReceiverLeft.png");
				ReceiverRight = new ImageIcon(path + prefix + "ReceiverRight.png");
				ReceiverUp = new ImageIcon(path + prefix + "ReceiverUp.png");
				Wall = new ImageIcon(path + prefix + "Wall.png");
				
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
				
				GamePieceMirror mirUL = new GamePieceMirror(false, GamePiece.ORIENT_UP); 
				GamePieceMirror mirRL = new GamePieceMirror(false, GamePiece.ORIENT_RIGHT);
				GamePieceMirror mirDL = new GamePieceMirror(false, GamePiece.ORIENT_DOWN);
				GamePieceMirror mirLL = new GamePieceMirror(false, GamePiece.ORIENT_LEFT);
				pieceIcon.put(mirUL.getHash(), MirrorUpLocked);
				pieceIcon.put(mirRL.getHash(), MirrorRightLocked);
				pieceIcon.put(mirDL.getHash(), MirrorDownLocked);
				pieceIcon.put(mirLL.getHash(), MirrorLeftLocked);
				
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
	public final int width = 500, height = 500;
	public static final int offsetX = 5, offsetY = 5;
	// -- draw
	public BufferedImage drawMap(GamePiece[][] map) {
		importGamePieceImage();
		//
		BufferedImage mapImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = mapImg.getGraphics();
		//
		int sX = offsetX, sY = offsetY;
		int eX = width - offsetX*2, eY = height - offsetY*2;
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		// -------- Draw grid
		int h = map.length; int w = map[0].length;
		double dr, dc;
		dr = (eX - sX)*1.0/h;
		dc = (eY - sY)*1.0/w;
		
		g.setColor(Color.RED);
		g.drawRect(sX, sY, eX, eY);
		
		double dx = dr;
		for (int i = 1; i < h; i++) {
			g.drawLine(sX, (int) (dx+0.5), eX + offsetX, (int)(dx + 0.5));
			dx += dr;
		}
		double dy = dc;
		for (int i = 1; i < w; i++) {
			g.drawLine((int) (dy+0.5), sY, (int)(dy + 0.5), eY + offsetY);
			dy += dc;
		}
		// --
		for (int ih = 0; ih < h; ih++) {
			for (int iw = 0; iw < w; iw++) {
				System.out.print(map[ih][iw].getHash() + " ");
				if (iw + 1 == w) System.out.println("");
				
				ImageIcon icon = pieceIcon.get(map[ih][iw].getHash());
				int x1 = ((int) (iw * dc));
				int y1 = ((int) (ih * dr));
				int x2 = x1 + ((int) dc);
				int y2 = y1 + ((int) dr);
				
				g.drawImage(icon.getImage(),
						x1 + 10, y1 + 10, x2 - 10, y2 - 10,
						5, 5, icon.getIconWidth() - 5, icon.getIconHeight() - 5,
				null);
				//JOptionPane.showMessageDialog(null, "" + "x1 = " + x1 + " | y1 = " + y1 + " | x2 = " +  x2 + " | y2 = "+ y2);
			}
		}
		
		return mapImg;
	}
}