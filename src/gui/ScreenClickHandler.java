package gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import gamepiece.GamePiece;
import gameplay.LaserDirection;
import gameplay.Level;

public class ScreenClickHandler extends MouseAdapter {
	ScreenPanel container;
	
	public ScreenClickHandler(ScreenPanel contnr) {
		this.container = contnr;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
//		JOptionPane.showMessageDialog(null, "You clicked " 
//			+ ( e.getButton() == MouseEvent.BUTTON1 ? "Left click " : "?? " )
//			+ e.getPoint() + "\n"
//			+ container.getSize()
//		);
		// --
		int x = e.getPoint().x;
		int y = e.getPoint().y;
		
		int tileCount = Math.min(container.lvl.getHeight(), container.lvl.getWidth());
		double edgeLength = Math.min(container.getSize().getHeight(), container.getSize().getWidth());
		int c = x / ((int) (edgeLength/tileCount + 0.5));
		int r = y / ((int) (edgeLength/tileCount + 0.5));
		
		//JOptionPane.showMessageDialog(null, "ROW = " + r + " | COL = " + c);

		if (container.editMode) {
			if (e.getButton() == MouseEvent.BUTTON1) {
				container.lvl.map[r][c].rotateAnyway();
			}
			if (e.getButton() == MouseEvent.BUTTON3) {
				//System.out.println(container.lvl.map[r][c].getHash());
				//System.out.println(piece.name() + " | " + piece.getHash());
				GamePiece piece = GamePiece.nextGamePiece(container.lvl.map[r][c].getHash());
				container.lvl.map[r][c] = piece;
			}
//			for (int i = 0; i < container.lvl.getHeight(); i++)
//				for (int j = 0; j < container.lvl.getWidth(); j++)
//					System.out.print(container.lvl.map[i][j].name() + "\n");
			container.repaint();
		} else {
			if(container.lvl.map[r][c].rotate() == false) {
				JOptionPane.showMessageDialog(null, "This piece is locked! It can't be rotated");
			} else {
				container.repaint();
				LaserDirection[][] lsr = new LaserDirection[container.lvl.getHeight()][container.lvl.getWidth()];
				
				int status = container.lvl.testSolution(lsr); 
				if (status == Level.STATUS_WIN) {
					JOptionPane.showMessageDialog(null, "LEVEL SOLVED!");
				} else
				if (status == Level.STATUS_IMPOSSIBLE) {
					JOptionPane.showMessageDialog(null, "This level is impossible.");
				}
			}
		}
	}
}
