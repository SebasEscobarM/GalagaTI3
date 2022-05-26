package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
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
		
		for(int i=150;i<(life*30)+150;i+=30) {
			gc.setFill(Color.DARKRED);
			gc.fillRect(i, 20, 30, 30);
		}
		
		gc.setStroke(Color.YELLOW);
		gc.strokeRect(150, 20, 300, 30);
		
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
		int sht;
		Random rnd=new Random();
		sht = rnd.nextInt(105 + 1) + 1;
		if(sht >= 1 && sht <= 20) {
			bllts.add(new Bullet(x+(img.getWidth()/2),y+(img.getHeight()),false,canvas));
		}
		if (sht >= 21 && sht <= 40) {
			bllts.add(new Bullet(x+16,y+(img.getHeight()),false,canvas));
		}
		if (sht >= 41 && sht <= 60) {
			bllts.add(new Bullet(x+(img.getWidth()),y+(img.getHeight()),false,canvas));
		}
		if (sht >= 61 && sht <= 75) {
			bllts.add(new Bullet(x+(img.getWidth()/2),y+(img.getHeight()),false,canvas));
			bllts.add(new Bullet(x+16,y+(img.getHeight()),false,canvas));
		}
		if (sht >= 76 && sht <= 80) {
			bllts.add(new Bullet(x+(img.getWidth()/2),y+(img.getHeight()),false,canvas));
			bllts.add(new Bullet(x+(img.getWidth()),y+(img.getHeight()),false,canvas));
		}
		if (sht >= 81 && sht <= 95) {
			bllts.add(new Bullet(x+16,y+(img.getHeight()),false,canvas));
			bllts.add(new Bullet(x+(img.getWidth()),y+(img.getHeight()),false,canvas));
		}
		if (sht >= 96 && sht <= 105) {
			bllts.add(new Bullet(x+(img.getWidth()/2),y+(img.getHeight()),false,canvas));
			bllts.add(new Bullet(x+16,y+(img.getHeight()),false,canvas));
			bllts.add(new Bullet(x+(img.getWidth()),y+(img.getHeight()),false,canvas));
		}
	}
	
	public Rectangle getRectangle() {
		return new Rectangle(x,y,250,250);
	}
	
	@Override
	public void run() {
		Random rnd=new Random();
		pause(500);
		int sht=0;
		boolean left = true;
		while(life > 0) {
			while(left) {
				sht = rnd.nextInt(75 + 1) + 1;
				for (int i = 0; i < 5; i++) {
					x -= speed;
				}
				if (sht >= 1 && sht <= 50) {
					shot();
				}
				pause(500);
				if(x <= 0) {
					left = false;
				}
			}
			while(!left) {
				sht = rnd.nextInt(75 + 1) + 1;
				for (int i = 0; i < 5; i++) {
					x += speed;
				}
				if (sht >= 1 && sht <= 50) {
					shot();
				}
				pause(500);
				if(x == canvas.getWidth()-img.getWidth()) {
					left = true;
				}
			}
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
