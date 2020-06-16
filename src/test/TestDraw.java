package test;

import java.awt.Graphics;

import javax.swing.JFrame;

import gameplay.Level;
import gui.Draw;

@SuppressWarnings("serial")
public class TestDraw extends JFrame {
	Level lvl;
	public TestDraw(Level LVL) {
		lvl = LVL;
		//
		this.setSize(1000, 1000);
		this.setTitle("TestDraw");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setVisible(true);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Draw dr = new Draw();
		g.drawImage(dr.drawMap(lvl.map), 100, 100, null);
	}
	
	public static void main(String[] args) {
		Level debug = Tests.Test5();
		System.out.println(debug);
		new TestDraw(debug);
	}
}
