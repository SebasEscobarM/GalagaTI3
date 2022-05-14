package model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Enemy {
	private Canvas canvas;
	private GraphicsContext gc;
	private double x=0;
	private double y=0;
	private boolean alive=true;
	
	public Enemy(Canvas canvas) {
		this.canvas=canvas;
		this.gc=canvas.getGraphicsContext2D();
	}
	
	public void paint() {
		if(alive) {
			gc.setFill(Color.RED);
			gc.fillRect(x, y, 90, 90);
		}
	}
	
	public boolean isAlive() {
		return alive;
	}
	
	public void setAlive(boolean alive) {
		this.alive=alive;
	}
	
	public Rectangle getRectangle() {
		return new Rectangle(x,y,90,90);
	}
}
