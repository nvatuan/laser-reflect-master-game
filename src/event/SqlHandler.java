package event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import database.DatabaseComm;
import gamepiece.GamePiece;
import gamepiece.GamePieceEmpty;
import gameplay.Level;
import gui.ScreenPanel;

public class SqlHandler implements ActionListener {
	
	DatabaseComm C = new DatabaseComm();
	ScreenPanel screen;
	JTable table;
	
	public SqlHandler(ScreenPanel scr, JTable tb) {
		screen = scr;
		table = tb;
	}
	
	public void actionPerformed(ActionEvent e) {
//		JOptionPane.showMessageDialog(null, e.getActionCommand());
		if (e.getActionCommand() == "LOAD") {
			loadLevel();
			return;
		}
		if (e.getActionCommand() == "DELETE") {
			deleteLevel();
			return;
		}
		if (screen.editMode == false) {
			JOptionPane.showMessageDialog(null, "You have to be in EDITMODE to do this action.");
			return;
		}
		if (e.getActionCommand() == "ADD") addLevel();
	}
	
	public void addLevel() {
		String query = "INSERT INTO levels (created, i00,  i01,  i02,  i03,  i10,  i11,  i12,  i13,  i20,  i21,  i22,  i23,  i30,  i31,  i32,  i33)"
				+ "\nVALUES (NOW() ";
		//System.out.println(query);
		//System.out.println(screen.lvl.getMap());
		int H = screen.lvl.getHeight();
		int W = screen.lvl.getWidth();
		for (int ih = 0; ih < H; ih++)
			for (int iw = 0; iw < W; iw++) {
				query += (", " + screen.lvl.map[ih][iw].getHash());
			}
		query += ");";
		// --
		System.out.println(query);
		try {
			Statement sm = C.getConnection().createStatement();
			sm.execute(query);
			JOptionPane.showMessageDialog(null, "INSERT succeeded");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "INSERT failed");
			e.printStackTrace();
		}
	}
	
	public void loadLevel() {
		int[] selection = table.getSelectedRows();
		if (selection.length == 0) {
			JOptionPane.showMessageDialog(null, "Please select the Level you want to load on the table.");
		} else {
			int sel = ((int) table.getValueAt(selection[0], 0));
			//
			String query = "SELECT * FROM levels\n" + "WHERE id = " + sel + ";";
			int[][] hashes = new int[4][4];
			
			try {
				Statement sm = C.getConnection().createStatement();
				ResultSet rs = sm.executeQuery(query);
				
				if (rs.first()) {
					System.out.println(sel);
					System.out.println(rs.getInt(1));
					for (int i = 0; i < 16; i++) 
						hashes[i/4][i%4] = rs.getInt(i + 3);
				} 
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception ex) {
			}
			//
			GamePiece[][] map = new GamePiece[4][4];
			for (int i = 0; i < 4; i++)
				for (int j = 0; j < 4; j++) {
					map[i][j] = new GamePieceEmpty();
					if (GamePiece.getHashToPiece().containsKey(hashes[i][j])) {
						map[i][j] = GamePiece.getHashToPiece().get(hashes[i][j]).clone();
					}
				}
			Level lvl = new Level(map);
			screen.switchLevel(lvl);
			JOptionPane.showMessageDialog(null, "LOAD succeeded");
		}
	}
	
	public void deleteLevel() {
		int[] selection = table.getSelectedRows();
		if (selection.length == 0) {
			JOptionPane.showMessageDialog(null, "Please select the Level you want to load on the table.");
		} else {
			String query = "DELETE FROM levels\n" 
				+ "WHERE id = " + selection[0] + ";";
			try {
				Statement sm = C.getConnection().createStatement();
				sm.execute(query);
				System.out.println(query);
				JOptionPane.showMessageDialog(null, "DELETE succeeded");
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "DELETE failed");
				e.printStackTrace();
			}
		}
	}
}
