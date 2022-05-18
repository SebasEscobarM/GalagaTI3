package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class Bullet {
	
	private Canvas canvas;
	private GraphicsContext gc;
	private double x;
	private double y;
	private Image img;
	private boolean up;
	private int speed=2;
	
	public Bullet(double x, double y, boolean up, Canvas canvas) {
		this.canvas=canvas;
		this.gc=this.canvas.getGraphicsContext2D();
		this.up=up;
		if(up) {
			File file=new File("src/images/laserBullet.png");
			try {
				img=new Image(new FileInputStream(file));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			this.x=x-(img.getWidth()/2);
			this.y=y-(img.getHeight());
		}else {
			File file=new File("src/images/laserBulletDown.png");
			try {
				img=new Image(new FileInputStream(file));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			this.x=x-(img.getWidth()/2);
			this.y=y;
		}
	}
	
	public void paint() {
		gc.drawImage(img, x, y);
		if(up) {
			y+=-speed;
		}else {
			y+=speed;
		}
	}
	
	public Rectangle getRectangle() {
		return new Rectangle(x,y,img.getWidth(),img.getHeight());
	}

	public double getY() {
		return y;
	}
	
	public double getImgHeight() {
		return img.getHeight();
	}
	
}
