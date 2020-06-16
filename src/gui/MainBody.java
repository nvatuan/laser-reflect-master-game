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

import gameplay.Level;
import test.Stages;

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
import java.awt.event.ActionEvent;

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
	ScreenPanel panelUI;
	public ScreenClickHandler mouseHandler;
	
	public MainBody() {
		setTitle("Game?");
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
		
		JLabel lblScreenTitle = new JLabel("New label");
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
		panelEditor.setLayout(new GridLayout(0, 1, 2, 2));
		
		JButton btnEditMode = new JButton("ENABLE EDIT MODE");
		btnEditMode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelUI.switchLevel(new Level(6, 6));
				panelUI.editMode = true;
				JOptionPane.showMessageDialog(null, "Welcome to Level Maker\n" + "use Mouse1 and Mouse2 to edit tiles\n" + "Map's size is locked at 6x6.");
			}
		});
		btnEditMode.setBackground(SystemColor.controlHighlight);
		btnEditMode.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnEditMode.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEditMode.setMargin(new Insets(0, 0, 0, 0));
		panelEditor.add(btnEditMode);
		
		JButton btnSaveLoad = new JButton("TEST LEVEL");
		btnSaveLoad.setBackground(SystemColor.controlHighlight);
		btnSaveLoad.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnSaveLoad.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSaveLoad.setMargin(new Insets(0, 0, 0, 0));
		panelEditor.add(btnSaveLoad);
		
		JButton btnSaveLoadSQL = new JButton("SAVE / LOAD with DB");
		btnSaveLoadSQL.setBackground(SystemColor.controlHighlight);
		btnSaveLoadSQL.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnSaveLoadSQL.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSaveLoadSQL.setMargin(new Insets(0, 0, 0, 0));
		panelEditor.add(btnSaveLoadSQL);
		
		JPanel panelOther = new JPanel();
		navPanel.add(panelOther);
		
		JLabel lblOther = new JLabel("DATABASE");
		lblOther.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblOther.setAlignmentX(0.5f);
		panelOther.add(lblOther);
		
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
	}
	
    private static void resizePreview(JPanel innerPanel, JPanel container) {
        int w = container.getWidth();
        int h = container.getHeight();
        int size =  Math.min(w, h);
        innerPanel.setPreferredSize(new Dimension(size, size));
        container.revalidate();
    }
}
