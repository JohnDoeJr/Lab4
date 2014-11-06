package com.chiefs;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.util.List;

import javax.swing.JPanel;

public class GraphicPanel extends JPanel {

	public static final float VERTICAL_CENTER = 200;
	public static final float HORIZONTAL_CENTER = 300;
	
	private final Forme forme;
	private List<Nokta> points; 
	
	public GraphicPanel(Forme forme, List<Nokta> points) {
		this.forme = forme;
		setBackground(Color.YELLOW);
		this.points = points;
	}

	@Override
	public void paint(Graphics g) {
		super.paintComponent(g);
		drawForme(g);	
		drawPoints(g);
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
	
	private void drawPoints(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		for (Nokta point : points) {
			DrawCircle circle = new DrawCircle(point, this, g2);
			circle.start();
		}
	}
	
	public synchronized void drawCircle(Graphics2D g, Nokta point) {
		if (forme.test(point)) {
			g.setColor(Color.RED);
			g.fill(new Ellipse2D.Double(GraphicPanel.HORIZONTAL_CENTER + point.getX(), GraphicPanel.VERTICAL_CENTER - point.getY(), forme.getRadius() / 14, forme.getRadius() / 14));
		} else {			
			for (int i = 0; i < 10; i++) {
				float r = forme.getRadius() / (14 + i);
				g.setColor(Color.GREEN);
				g.fill(new Ellipse2D.Double(HORIZONTAL_CENTER + point.getX(), VERTICAL_CENTER - point.getY(), r, r));
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					
				}
				g.setColor(Color.YELLOW);
				g.fill(new Ellipse2D.Double(HORIZONTAL_CENTER + point.getX(), VERTICAL_CENTER - point.getY(), r, r));
			}
			
			for (int i = 0; i < 10; i++) {
				float r = forme.getRadius() / ( 24 - i);
				g.setColor(Color.GREEN);
				g.fill(new Ellipse2D.Double(HORIZONTAL_CENTER + point.getX(), VERTICAL_CENTER - point.getY(), r, r));
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					
				}
			}
		}
	}
}

class DrawCircle extends Thread {
	
	private final Nokta point;
	private final Graphics2D g;
	private final GraphicPanel panel;
	
	public DrawCircle(Nokta point, GraphicPanel panel, Graphics2D g) {
		this.point = point;
		this.panel = panel;
		this.g = g;
	}
	
	@Override
	public void run() {
		panel.drawCircle(g, point);
	}
}
