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
	
	//Zero OOP here:
	private ButtonGroup axisYButtonGroup;
	private JRadioButton axisYSetter1;
	private JRadioButton axisYSetter2;
	private JRadioButton axisYSetter3;
	private JRadioButton axisYSetter4;
	
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
		
		JPanel userControls = new JPanel(new GridLayout(1, 4));
		
		//Setting 
		Object[] data = {0, -5, 3, 20};
		axisXSetter = new JList(data);
		axisXSetter.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		userControls.add(axisXSetter);
		
		//SCREW Y
		
		SpinnerModel model = new SpinnerNumberModel(forme.getRadius(), 1, 100, 1);
		radiusSetter = new JSpinner(model);
		userControls.add(radiusSetter);
		
		pointLabel = new JLabel("Point here");
		userControls.add(pointLabel);
		
		add(userControls);
		
		pack();
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent event) {

	}
}
