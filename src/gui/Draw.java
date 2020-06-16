package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import gamepiece.*;

public class Draw {
	public final int width = 500, height = 500;
	public static final int offsetX = 5, offsetY = 5;
	// -- draw
	public BufferedImage drawMap(GamePiece[][] map) {
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
			
		//
		return mapImg;
	}
}
