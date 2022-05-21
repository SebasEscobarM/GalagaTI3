package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

import controller.MainWindow;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class Enemy extends Thread{
	private Canvas canvas;
	private GraphicsContext gc;
	private double x=48;
	private double y=48;
	private Image img;
	private double speed=2;
	private boolean life=true;
	private ArrayList<Bullet> bllts;

	public Enemy(Canvas canvas) {
		this.canvas=canvas;
		this.gc=this.canvas.getGraphicsContext2D();
	}
	
	public Enemy(Canvas canvas, int x, int y) {
		this.canvas=canvas;
		this.gc=this.canvas.getGraphicsContext2D();
		this.x=x;
		this.y=y;
		bllts=new ArrayList<>();
		File file=new File("src/images/enemy.png");
		try {
			img=new Image(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		if(MainWindow.levelActual == 3) {
			File file2=new File("src/images/finalBoss.png");
			try {
				img=new Image(new FileInputStream(file2));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void resetPrmtrs() {
		
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
		return new Rectangle(x,y,90,90);
	}
	
	@Override
	public void run() {
		Random rnd=new Random();
		pause(500);
		int sht=0;
		while(life) {
			if(MainWindow.levelActual != 2) {
				sht = rnd.nextInt(100 + 1) + 1;
				for (int i = 0; i < 10; i++) {
					x += speed;
				}
				if (sht >= 1 && sht <= 19) {
					shot();
				}
				pause(500);
				for (int i = 0; i < 10; i++) {
					y += speed;
				}
				if (sht >= 20 && sht <= 39) {
					shot();
				}
				pause(500);
				for (int i = 0; i < 10; i++) {
					x -= speed;
				}
				if (sht >= 40 && sht <= 59) {
					shot();
				}
				pause(500);
				for (int i = 0; i < 10; i++) {
					y += speed;
				}
				if (sht > 60 && sht <= 79) {
					shot();
				}
				pause(500);
			}else {
				sht = rnd.nextInt(100 + 1) + 1;
				for (int i = 0; i < 10; i++) {
					x += speed;
				}
				if (sht >= 1 && sht <= 24) {
					shot();
				}
				pause(500);
				for (int i = 0; i < 10; i++) {
					y += speed;
				}
				if (sht >= 25 && sht <= 49) {
					shot();
				}
				pause(500);
				for (int i = 0; i < 10; i++) {
					x -= speed;
				}
				if (sht >= 50 && sht <= 74) {
					shot();
				}
				pause(500);
				for (int i = 0; i < 10; i++) {
					y += speed;
				}
				if (sht > 75 && sht <= 100) {
					shot();
				}
				pause(500);
			}
			
		}
	}
	
	public ArrayList<Bullet> getBllts(){
		return bllts;
	}
	
	public void setLife(boolean life) {
		this.life=life;
	}
	
	public boolean isLife() {
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
