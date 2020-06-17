package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DatabaseComm {
	Connection conn = null;
	public Connection getConnection() {
		return this.conn;
	}
	
	public void tryConnect() {
		String url = "jdbc:mysql://localhost:3306/level_database";
        String username = "root";
        String password = "root";
        
        String details = "url=" + url + "\nusername=" + username +"\npassword=" + password;
        try {
        	conn = DriverManager.getConnection(url, username, password);
        	JOptionPane.showMessageDialog(null, "Connect Succeeded.\n" + details);
        } catch (SQLException e) {
        	JOptionPane.showMessageDialog(null, "Connect Failed!\n" + details);
        }
	}
	
	public DatabaseComm() throws IllegalStateException {
        String url = "jdbc:mysql://localhost:3306/level_database";
        String username = "root";
        String password = "root";
        try {
        	conn = DriverManager.getConnection(url, username, password);
//        	System.out.println("Success");
//
//        	Statement sm = conn.createStatement();
//          ResultSet rs = sm.executeQuery("SELECT * FROM levels;");
//            
//            JTable tb = new JTable(buildTableModel(rs));
            //JOptionPane.showMessageDialog(null, new JScrollPane(tb));
        	
        } catch (SQLException e) {
	        throw new IllegalStateException("Cannot connect the database!", e);
	    }
	}
	
    public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();

        // names of columns
        Vector<String> columnNames = new Vector<String>();
        int columnCount = 2; //metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // data of the table
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames);
    }
}
