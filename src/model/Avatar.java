package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class Avatar {
	private Canvas canvas;
	private GraphicsContext gc;
	private double x=0;
	private double y=0;
	private Image img;
	private boolean UP=false;
	private boolean DOWN=false;
	private boolean RIGHT=false;
	private boolean LEFT=false;
	private int speed=2;
	private ArrayList<Bullet> bllts;

	public Avatar(Canvas canvas) {
		this.canvas=canvas;
		gc=canvas.getGraphicsContext2D();
		bllts=new ArrayList<>();
		
		File file=new File("src/images/shipPlayer.png");
		try {
			img=new Image(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void stop() {
		UP=false;
		DOWN=false;
		RIGHT=false;
		LEFT=false;
	}
	
	public void iniPos(double d, double e) {
		this.x=d-(img.getWidth()/2);
		this.y=e-img.getHeight();
	}
	public void shot() {
		bllts.add(new Bullet(x+(img.getWidth()/2),y,true,canvas));
	}
	
	public void paint() {
		if(UP) {
			moveY(-speed);
		}
		if(DOWN) {
			moveY(speed);
		}
		if(LEFT) {
			moveX(-speed);
		}
		if(RIGHT) {
			moveX(speed);
		}
		ArrayList<Bullet> toDel=new ArrayList<>();
		if(!bllts.isEmpty()) {
			for(Bullet b:bllts) {
				if(b.getY()<canvas.getHeight() && b.getY()>(0-b.getImgHeight())) {
					b.paint();
				}else {
					toDel.add(b);
				}
			}
			if(!toDel.isEmpty()) {
				bllts.removeAll(toDel);
			}
		}
		gc.drawImage(img, x, y);
	}
	
	public Rectangle getRectangle() {
		return new Rectangle(x, y, img.getWidth(), img.getHeight());
	}
	
	public void setSpeed(int speed) {
		this.speed=speed;
	}
	
	public void moveY(int i) {
		y+=i;
		if(y<=0) {
			y=0;
		}else if(y>=canvas.getHeight()-img.getHeight()) {
			y=canvas.getHeight()-img.getHeight();
		}
	}

	public void moveX(int i) {
		x+=i;
		if(x<=0) {
			x=0;
		}else if(x>=canvas.getWidth()-img.getWidth()) {
			x=canvas.getWidth()-img.getWidth();
		}
	}
	
	public void setUP(boolean uP) {
		UP = uP;
	}
	
	public void setDOWN(boolean dOWN) {
		DOWN = dOWN;
	}
	
	public void setRIGHT(boolean rIGHT) {
		RIGHT = rIGHT;
	}
	
	public void setLEFT(boolean lEFT) {
		LEFT = lEFT;
	}
	
	public ArrayList<Bullet> getBllts(){
		return bllts;
	}
}
