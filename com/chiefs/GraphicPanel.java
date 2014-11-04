package com.chiefs;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class GraphicPanel extends JPanel {

	private static final float VERTICAL_CENTER = 100;
	private static final float HORIZONTAL_CENTER = 150;
	private static final Color BLUE = new Color(0, 0, 255);
	private static final Color YELLOW = new Color(255, 255, 20);
	private final Forme forme;

	public GraphicPanel(Forme forme) {
		this.forme = forme;
		setBackground(YELLOW);
	}

	@Override
	public void paint(Graphics g) {
		super.paintComponent(g);
		drawForme(g);
	}

	private void drawForme(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(YELLOW);
		g2.fillRect(0, 0, getWidth(), getHeight());

		g2.setColor(BLUE);
		// draw rectangle
		g2.fill(new Rectangle2D.Double(HORIZONTAL_CENTER - forme.getRadius() / 2, 
				VERTICAL_CENTER - forme.getRadius(),
				forme.getRadius() / 2, forme.getRadius()));

		// draw triangle
		float xPoints[] = { HORIZONTAL_CENTER - forme.getRadius() / 2, HORIZONTAL_CENTER, HORIZONTAL_CENTER };
		float yPoints[] = { VERTICAL_CENTER, VERTICAL_CENTER, VERTICAL_CENTER + forme.getRadius() / 2 };
		GeneralPath triangle = new GeneralPath(GeneralPath.WIND_NON_ZERO, xPoints.length);
		
		triangle.moveTo(xPoints[0], yPoints[0]);
		for (int i = 1; i < xPoints.length; i++) {
			triangle.lineTo(xPoints[i], yPoints[i]);
		}
		
		triangle.closePath();
		g2.fill(triangle);
		
		// draw sector
		g2.fill(new Arc2D.Double(HORIZONTAL_CENTER - forme.getRadius() / 2, VERTICAL_CENTER - forme.getRadius() / 2, forme.getRadius(), forme.getRadius(), 270, 90, Arc2D.PIE));
	}
}
