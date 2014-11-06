package com.chiefs;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.util.*;

import javax.swing.JPanel;

public class GraphicPanel extends JPanel {

	private static final float VERTICAL_CENTER = 200;
	private static final float HORIZONTAL_CENTER = 300;
	private static final int STANDARD_RADIUS = 14;

	private final Forme forme;
	private List<Nokta> points;

	private int counter = 0;
	
	public GraphicPanel(Forme forme, List<Nokta> points) {
		this.forme = forme;
		setBackground(Color.YELLOW);
		this.points = points;
	}

	@Override
	public void paint(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}

	private void draw(Graphics g) {
		drawForme(g);

		Graphics2D g2 = (Graphics2D) g;
		
		if (points.size() > 0) {
			for (int i = 0; i < points.size() - 1; i++) {
				Nokta point = points.get(i);
				if (forme.test(point)) {
					g2.setColor(Color.RED);
				} else {
					g2.setColor(Color.GREEN);
				}
				drawPoint(point, g2, STANDARD_RADIUS);
			}
			
			Nokta point = points.get(points.size() - 1);
			if (forme.test(point)) {
				g2.setColor(Color.RED);
				drawPoint(point, g2, STANDARD_RADIUS);
			} else {				
				g2.setColor(Color.GREEN);
				drawPoint(point, g2, STANDARD_RADIUS + counter);
			}
		}
	}
	
	private void drawPoint(Nokta point, Graphics2D g2, int radius) {
		g2.fill(new Ellipse2D.Double(
				GraphicPanel.HORIZONTAL_CENTER + point.getAxisX() - forme.getRadius() / (radius * 2),
				GraphicPanel.VERTICAL_CENTER - point.getAxisY() - forme.getRadius() / (radius * 2), 
				forme.getRadius() / radius, forme.getRadius() / radius));
	}
	
	private void drawForme(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.YELLOW);
		g2.fillRect(0, 0, getWidth(), getHeight());
		g2.setColor(Color.BLUE);

		// draw rectangle
		g2.fill(new Rectangle2D.Double(HORIZONTAL_CENTER - forme.getRadius() / 2, 
				VERTICAL_CENTER - forme.getRadius(),
				forme.getRadius() / 2, forme.getRadius()));

		// draw triangle
		float xPoints[] = { HORIZONTAL_CENTER - forme.getRadius() / 2,
				HORIZONTAL_CENTER, HORIZONTAL_CENTER };
		float yPoints[] = { VERTICAL_CENTER, VERTICAL_CENTER,
				VERTICAL_CENTER + forme.getRadius() / 2 };
		GeneralPath triangle = new GeneralPath(GeneralPath.WIND_NON_ZERO,
				xPoints.length);

		triangle.moveTo(xPoints[0], yPoints[0]);
		for (int i = 1; i < xPoints.length; i++) {
			triangle.lineTo(xPoints[i], yPoints[i]);
		}

		triangle.closePath();
		g2.fill(triangle);

		// draw sector
		g2.fill(new Arc2D.Double(HORIZONTAL_CENTER - forme.getRadius() / 2,
				VERTICAL_CENTER - forme.getRadius() / 2, forme.getRadius(),
				forme.getRadius(), 270, 90, Arc2D.PIE));
	}
	
	public void redraw() {
		for (int i = 0; i < 10; i++) {
			counter++;
			repaint();			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				
			}
		}
		for (int i = 0; i < 10; i++) {
			counter--;
			repaint();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				
			}
		}
	}
}
