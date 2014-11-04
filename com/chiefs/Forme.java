package com.chiefs;

public class Forme {

	private float radius;

	public Forme(float r) {
		this.radius = r;
	}
	
	public float getRadius() {
		return radius;
	}
	
	public void setRadius(float r) {
		this.radius = r;
	}

	public boolean test(Nokta nokta) {
		double x = nokta.getX();
		double y = nokta.getY();

		return (x < 0 && y < 0 && Double.compare(x + y, -radius / 2) > 0)
				|| (x < 0 && y > 0 && Double.compare(x, -radius / 2) < 0 && Double.compare(y, radius) < 0)
				|| (x > 0 && y < 0 && Double.compare(Math.sqrt(x * x + y * y), radius / 2) < 0)
				|| (x == 0 && y < 0 && Double.compare(y, radius / 2) < 0)
				|| (y == 0 && x < 0 && Double.compare(x, radius / 2) < 0);
	}

}
