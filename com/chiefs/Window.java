package com.chiefs;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Window extends JFrame implements ActionListener, ChangeListener, ListSelectionListener {

	private static final int ROWS_NUMBER = 1; 
	private static final int COLS_NUMBER = 4;

	private Forme forme;

	private GraphicPanel graphicArea;
	private JList axisXSetter;
	
	private ButtonGroup axisYButtonGroup;
	private JRadioButton axisYSetter1;
	private JRadioButton axisYSetter2;
	private JRadioButton axisYSetter3;
	private JRadioButton axisYSetter4;
	
	private JSpinner radiusSetter;
	private JLabel pointLabel;
	
	private List<Nokta> points;
	
	public Window(Forme forme) {
		this.forme = forme;
		points = new ArrayList<Nokta>();
		initUI();
	}

	private void initUI() {
		setTitle("Laba 4");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setLayout(new FlowLayout());
		
		graphicArea = new GraphicPanel(forme, points);		
		graphicArea.setPreferredSize(new Dimension(600, 400));
		
		graphicArea.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Nokta point = new Nokta(e.getX() - 300, 200 - e.getY());
				points.add(point);
				pointLabel.setText(point.toString());				
				graphicArea.repaint();
			}
		});
		
		add(graphicArea);
		
		JPanel userControls = new JPanel(new GridLayout(ROWS_NUMBER, COLS_NUMBER));
		
		//Setting x
		Object[] data = {0, -5, 3, 20};
		axisXSetter = new JList(data);
		axisXSetter.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		axisXSetter.addListSelectionListener(this);
		axisXSetter.setLayout(new FlowLayout());
		userControls.add(axisXSetter);
		
		//Setting y
		axisYSetter1 = new JRadioButton("0");
		axisYSetter1.setActionCommand("0");
		axisYSetter1.addActionListener(this);
		
		axisYSetter2 = new JRadioButton("20");
		axisYSetter2.setActionCommand("20");
		axisYSetter2.addActionListener(this);
		
		axisYSetter3 = new JRadioButton("-40");
		axisYSetter3.setActionCommand("-40");
		axisYSetter3.addActionListener(this);
		
		axisYSetter4 = new JRadioButton("30");
		axisYSetter4.setActionCommand("30");
		axisYSetter4.addActionListener(this);
		
		axisYButtonGroup = new ButtonGroup();
		axisYButtonGroup.add(axisYSetter1);
		axisYButtonGroup.add(axisYSetter2);
		axisYButtonGroup.add(axisYSetter3);
		axisYButtonGroup.add(axisYSetter4);
		
		JPanel axisYSetterPanel = new JPanel(new FlowLayout());
		
		axisYSetterPanel.add(axisYSetter1);
		axisYSetterPanel.add(axisYSetter2);
		axisYSetterPanel.add(axisYSetter3);
		axisYSetterPanel.add(axisYSetter4);
		userControls.add(axisYSetterPanel);
		
		SpinnerModel model = new SpinnerNumberModel(forme.getRadius(), 50f, 200f, 1f);
		radiusSetter = new JSpinner(model);
		radiusSetter.addChangeListener(this);
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

	@Override
	public void stateChanged(ChangeEvent e) {
		forme.setRadius((float) (double) (Double) radiusSetter.getValue());
		graphicArea.repaint();
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {		
		
	}
}
