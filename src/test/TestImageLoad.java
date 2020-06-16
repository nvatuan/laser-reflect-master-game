package test;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class TestImageLoad extends JFrame {
	
	ImageIcon image;
	
	public TestImageLoad() {
		this.setSize(500, 500);
		this.setTitle("/???");
		
		String path = (new File("").getAbsolutePath());
		System.out.println(path);
		
		File f = new File(path + "\\img\\bg.png");
		System.out.println("File: " + path + "\\img\\bg.png" + " exists? > " + f.exists());
		image = new ImageIcon(path + "\\img\\bg.png");
		
		JLabel lbl = new JLabel(image);
		this.setLayout(new BorderLayout());
		this.add(lbl, BorderLayout.CENTER);
		
		
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new TestImageLoad();
	}
	
}
