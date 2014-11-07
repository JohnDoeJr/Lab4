package com.chiefs;

public class Main {
	
	private static final int defaultRadius = 150;
	
	public static void main(String[] args) {

		Forme forme = new Forme(defaultRadius);
		Window window = new Window(forme);
		window.setVisible(true);
	}
}
