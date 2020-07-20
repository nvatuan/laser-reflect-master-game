package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import database.DatabaseComm;
import event.ScreenClickHandler;
import event.SqlHandler;
import gameplay.Level;
import gameplay.Stages;

import java.awt.Color;
import java.awt.Component;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.border.BevelBorder;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

@SuppressWarnings("serial")
public class MainBody extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainBody frame = new MainBody();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	JPanel panelOther;
	
	ScreenPanel panelUI;
	public ScreenClickHandler mouseHandler;
	public JScrollPane scrollPane;
	public JTable resultTable;
	
	public MainBody() {
		setTitle("Laser Reflect Master");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel navPanel = new JPanel();
		navPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		contentPane.add(navPanel, BorderLayout.EAST);
		navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.Y_AXIS));
		
		JLabel lblScreenTitle = new JLabel("version 0.9");
		lblScreenTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblScreenTitle.setPreferredSize(new Dimension(200, 40));
		lblScreenTitle.setMinimumSize(new Dimension(200, 40));
		lblScreenTitle.setMaximumSize(new Dimension(200, 40));
		lblScreenTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblScreenTitle.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lblScreenTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		navPanel.add(lblScreenTitle);
		
		JPanel panelLevelSelect = new JPanel();
		panelLevelSelect.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panelLevelSelect.setMaximumSize(new Dimension(32767, 200));
		panelLevelSelect.setPreferredSize(new Dimension(0, 100));
		navPanel.add(panelLevelSelect);
		panelLevelSelect.setLayout(new BorderLayout(0, 0));
		
		JLabel lblLevelSelect = new JLabel("LEVEL SELECT");
		lblLevelSelect.setPreferredSize(new Dimension(67, 30));
		lblLevelSelect.setHorizontalAlignment(SwingConstants.CENTER);
		lblLevelSelect.setHorizontalTextPosition(SwingConstants.CENTER);
		lblLevelSelect.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLevelSelect.setAlignmentX(0.5f);
		panelLevelSelect.add(lblLevelSelect, BorderLayout.NORTH);
		
		JPanel panelLevels = new JPanel();
		panelLevels.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelLevelSelect.add(panelLevels, BorderLayout.CENTER);
		panelLevels.setLayout(new GridLayout(1, 5, 2, 0));
		
		JButton btnLevel1 = new JButton("1");
		btnLevel1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelUI.switchLevel(Stages.Stage1());
			}
		});
		btnLevel1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnLevel1.setBackground(SystemColor.controlHighlight);
		btnLevel1.setMargin(new Insets(0, 0, 0, 0));
		btnLevel1.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelLevels.add(btnLevel1);
		
		JButton btnLevel2 = new JButton("2");
		btnLevel2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelUI.switchLevel(Stages.Stage2());
			}
		});
		btnLevel2.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnLevel2.setBackground(SystemColor.controlHighlight);
		btnLevel2.setMargin(new Insets(0, 0, 0, 0));
		btnLevel2.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelLevels.add(btnLevel2);
		
		JButton btnLevel3 = new JButton("3");
		btnLevel3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelUI.switchLevel(Stages.Stage3());
			}
		});
		btnLevel3.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnLevel3.setBackground(SystemColor.controlHighlight);
		btnLevel3.setMargin(new Insets(0, 0, 0, 0));
		btnLevel3.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelLevels.add(btnLevel3);
		
		JButton btnLevel4 = new JButton("4");
		btnLevel4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelUI.switchLevel(Stages.Stage4());
			}
		});
		btnLevel4.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnLevel4.setBackground(SystemColor.controlHighlight);
		btnLevel4.setMargin(new Insets(0, 0, 0, 0));
		btnLevel4.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelLevels.add(btnLevel4);
		
		JButton btnLevel5 = new JButton("5");
		btnLevel5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelUI.switchLevel(Stages.Stage5());
			}
		});
		btnLevel5.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnLevel5.setBackground(SystemColor.controlHighlight);
		btnLevel5.setMargin(new Insets(0, 0, 0, 0));
		btnLevel5.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelLevels.add(btnLevel5);
		
		JPanel panelLevelEditor = new JPanel();
		panelLevelEditor.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panelLevelEditor.setMinimumSize(new Dimension(10, 200));
		panelLevelEditor.setPreferredSize(new Dimension(10, 200));
		panelLevelEditor.setMaximumSize(new Dimension(32767, 200));
		navPanel.add(panelLevelEditor);
		panelLevelEditor.setLayout(new BorderLayout(0, 0));
		
		JLabel lblLevelEditor = new JLabel("LEVEL EDITOR");
		lblLevelEditor.setPreferredSize(new Dimension(68, 30));
		lblLevelEditor.setHorizontalTextPosition(SwingConstants.CENTER);
		lblLevelEditor.setHorizontalAlignment(SwingConstants.CENTER);
		lblLevelEditor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLevelEditor.setAlignmentX(0.5f);
		panelLevelEditor.add(lblLevelEditor, BorderLayout.NORTH);
		
		JPanel panelEditor = new JPanel();
		panelEditor.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelLevelEditor.add(panelEditor, BorderLayout.CENTER);
		
		JButton btnEditMode = new JButton("MAP MAKER MODE");
		btnEditMode.setPreferredSize(new Dimension(127, 30));
		btnEditMode.setMinimumSize(new Dimension(127, 30));
		btnEditMode.setMaximumSize(new Dimension(127, 30));
		btnEditMode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelUI.switchLevel(new Level(4, 4));
				panelUI.editMode = true;
				JOptionPane.showMessageDialog(null, "Welcome to Map Maker\n" + "use Mouse1 and Mouse2 to edit tiles\n" + "Map's size is locked at 4x4.");
			}
		});
		panelEditor.setLayout(new BorderLayout(0, 0));
		btnEditMode.setBackground(SystemColor.controlHighlight);
		btnEditMode.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnEditMode.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEditMode.setMargin(new Insets(0, 0, 0, 0));
		panelEditor.add(btnEditMode, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		panelEditor.add(panel);
		panel.setLayout(new GridLayout(2, 2, 2, 2));
		
		JButton btnSQLAdd = new JButton("ADD LEVEL");
		btnSQLAdd.setActionCommand("ADD");
		
		btnSQLAdd.setBackground(SystemColor.controlHighlight);
		btnSQLAdd.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnSQLAdd.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSQLAdd.setMargin(new Insets(0, 0, 0, 0));
		panel.add(btnSQLAdd);
		
		JButton btnSQLLoad = new JButton("LOAD LEVEL");
		btnSQLLoad.setActionCommand("LOAD");
		
		btnSQLLoad.setBackground(SystemColor.controlHighlight);
		btnSQLLoad.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnSQLLoad.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSQLLoad.setMargin(new Insets(0, 0, 0, 0));
		panel.add(btnSQLLoad);
		
		JButton btnSQLDelete = new JButton("DELETE LEVEL");
		btnSQLDelete.setActionCommand("DELETE");
		
		
		btnSQLDelete.setBackground(SystemColor.controlHighlight);
		btnSQLDelete.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnSQLDelete.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSQLDelete.setMargin(new Insets(0, 0, 0, 0));
		panel.add(btnSQLDelete);
		
		JButton btnSQLTestConnect = new JButton("SHOW");
		btnSQLTestConnect.setBackground(SystemColor.controlHighlight);
		btnSQLTestConnect.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));

		btnSQLTestConnect.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSQLTestConnect.setMargin(new Insets(0, 0, 0, 0));
		panel.add(btnSQLTestConnect);
		
		panelOther = new JPanel();
		panelOther.setMinimumSize(new Dimension(10, 200));
		panelOther.setMaximumSize(new Dimension(32767, 200));
		panelOther.setPreferredSize(new Dimension(10, 200));
		navPanel.add(panelOther);
		panelOther.setLayout(new BorderLayout(0, 0));
				
		panelUI = new ScreenPanel();
		
		mouseHandler = new ScreenClickHandler(panelUI);
		panelUI.addMouseListener(mouseHandler);
		
		panelUI.setBorder(new LineBorder(SystemColor.info, 3));
		
		JPanel UIContainer = new JPanel(new GridBagLayout());
		UIContainer.add(panelUI);
		UIContainer.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizePreview(panelUI, UIContainer);
            }
        });
		
		contentPane.add(UIContainer, BorderLayout.CENTER);
		
		resultTable = new JTable();
		resultTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane = new JScrollPane(resultTable);
		scrollPane.setPreferredSize(new Dimension(200, 10));
		
		panelOther.add(scrollPane, BorderLayout.CENTER);
		
		// -- first connection try
		try {
			DatabaseComm com = new DatabaseComm(false);
			Statement sm;
			sm = com.getConnection().createStatement();
			ResultSet rs = sm.executeQuery("SELECT * FROM levels;");
			resultTable.setModel(DatabaseComm.buildTableModel(rs));
		} catch (Exception e1) {
			//e1.printStackTrace();
		}
		
		btnSQLTestConnect.addActionListener(new SqlHandler(panelUI, resultTable));
		btnSQLAdd.addActionListener(new SqlHandler(panelUI, resultTable));
		btnSQLLoad.addActionListener(new SqlHandler(panelUI, resultTable));
		btnSQLDelete.addActionListener(new SqlHandler(panelUI, resultTable));
		System.out.println("Finished init MainBody");
	}
	
    private static void resizePreview(JPanel innerPanel, JPanel container) {
        int w = container.getWidth();
        int h = container.getHeight();
        int size =  Math.min(w, h);
        innerPanel.setPreferredSize(new Dimension(size, size));
        container.revalidate();
    }
}
