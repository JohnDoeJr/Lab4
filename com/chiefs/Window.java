package com.chiefs;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Window extends JFrame implements ActionListener {

	private static final int ROWS_NUMBER = 2; 
	private static final int COLS_NUMBER = 1;

	private Forme forme;

	private GraphicPanel graphicArea;
	private JList axisXSetter;
	private JRadioButton axisYSetter;
	private JSpinner radiusSetter;
	private JLabel pointLabel;
	
	public Window(Forme forme) {
		this.forme = forme;
		initUI();
	}

	private void initUI() {
		setTitle("Laba 4");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Change layout?
		setLayout(new GridLayout(ROWS_NUMBER, COLS_NUMBER));
		
		graphicArea = new GraphicPanel(forme);		
		graphicArea.setPreferredSize(new Dimension(300, 200));
		add(graphicArea);
		
		//Change flow layout to grid layout
		JPanel userControls = new JPanel(new FlowLayout());
		//Not so easy, bitch
		axisXSetter = new JList();
		axisYSetter = new JRadioButton();
		radiusSetter = new JSpinner();
		pointLabel = new JLabel("Point here");
		userControls.add(axisXSetter);
		userControls.add(axisYSetter);
		userControls.add(radiusSetter);
		userControls.add(pointLabel);		
		add(userControls);
		
		pack();
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent event) {

	}
}
