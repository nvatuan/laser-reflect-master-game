package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import gameplay.Level;
import gameplay.Stages;
import test.Tests;

@SuppressWarnings("serial")
public class ScreenPanel extends JPanel {
	
	public Level lvl = null;
	public boolean editMode = false;
	
	public ScreenPanel() {
		super();
		switchLevel(Stages.Stage1());
		//switchLevel(Tests.Test4());
	}
	public ScreenPanel(Level ref) {
		super();
		switchLevel(ref);
	}
	
	public void switchLevel(Level nextLevel) {
		lvl = nextLevel;
		editMode = false;
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Dimension d = this.getSize();

		g.clearRect(0, 0, d.width, d.height);
		
		Draw dr = new Draw();
		BufferedImage img = dr.drawMap(lvl.map);

		int dstW = d.width;
		int dstH = d.height;
		
		int deltaW = (d.width - img.getWidth());
		int deltaH = (d.height - img.getHeight());
		
		if (deltaW < 0) {
			deltaW = 0;
		}
		if (deltaH < 0) {
			deltaH = 0;
		}
		
//		if (srcW > d.width) {
//			double ratio = (srcW*1.0)/srcH;
//			srcW = d.width - 10;
//			srcH = ((int) (srcH / ratio));
//		}
//		if (srcH > d.width) {
//			double ratio = (srcH*1.0)/srcW;
//			srcH = d.height - 10;
//			srcW = ((int) (srcW / ratio));
//		}
//		JOptionPane.showMessageDialog(null, " " + d.width + " | " + d.height);
//		JOptionPane.showMessageDialog(null, " " + srcW + " | " + srcH);
 		g.drawImage(img, 
 			deltaW/2, deltaH/2, dstW - deltaW/2, dstH - deltaH/2,
 			0, 0, img.getWidth(), img.getHeight(),
 			null
 		);
	}
}
