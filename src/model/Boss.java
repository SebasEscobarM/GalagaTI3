package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class Boss extends Thread{
	private Canvas canvas;
	private GraphicsContext gc;
	private double x=48;
	private double y=70;
	private Image img;
	private double speed=2;
	private int life=10;
	private ArrayList<Bullet> bllts;

	public Boss(Canvas canvas) {
		this.canvas=canvas;
		this.gc=this.canvas.getGraphicsContext2D();
	}
	
	public Boss(Canvas canvas, int x, int y) {
		this.canvas=canvas;
		this.gc=this.canvas.getGraphicsContext2D();
		this.x=x;
		this.y=y;
		bllts=new ArrayList<>();
		File file=new File("src/images/finalBoss.png");
		try {
			img=new Image(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void paint() {
		gc.drawImage(img, x, y);
		
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
	}
	
	public void shot() {
		bllts.add(new Bullet(x+(img.getWidth()/2),y+(img.getHeight()),false,canvas));
	}
	
	public Rectangle getRectangle() {
		return new Rectangle(x,y,250,250);
	}
	
	@Override
	public void run() {
		Random rnd=new Random();
		pause(500);
		int sht=0;
		while(life >= 0) {
			sht=rnd.nextInt(200 + 1) + 1;
			for(int i=0;i<10;i++) {
				x+=speed;
			}
			if(sht>=1 && sht<=49) {
				shot();
			}
			pause(500);
			for(int i=0;i<10;i++) {
				y+=speed;
			}
			if(sht>=50 && sht<=99) {
				shot();
			}
			pause(500);
			for(int i=0;i<10;i++) {
				x-=speed;
			}
			if(sht>=100 && sht<=149) {
				shot();
			}
			pause(500);
			for(int i=0;i<10;i++) {
				y+=speed;
			}
			if(sht>150 && sht<=200) {
				shot();
			}
			pause(500);
		}
	}
	
	public ArrayList<Bullet> getBllts(){
		return bllts;
	}
	
	public void setLife(int life) {
		this.life=life;
	}
	
	public int getLife() {
		return life;
	}

	private void pause(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
	
}
