package screens;

import java.util.ArrayList;

import controller.MainWindow;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import main.Main;
import model.Avatar;
import model.Boss;
import model.Bullet;
import model.Enemy;

public class GameScreen extends BaseScreen{

	private Avatar avatar;
	
	private ArrayList<Enemy> enemies;
	
	private ArrayList<Boss> boss;
	
	private int sizeEnemies = 0;
	
	private int enemy = 0;
	
	public GameScreen(Canvas canvas) {
		super(canvas);
		avatar=new Avatar(canvas);
		enemies=new ArrayList<>();
		boss=new ArrayList<>();
	}
	
	public void play() {
		if(MainWindow.levelActual == 1) {
			enemy = 4;
		}else if(MainWindow.levelActual == 2) {
			enemy = 6;
		}
		avatar.stop();
		avatar.iniPos(canvas.getWidth()/2, canvas.getHeight()-48);
		int x=48, y=48;
		if(MainWindow.levelActual != 3) {
			for (int i = 0; i < 4; i++) {
				enemies.add(new Enemy(canvas, x, y));
				if (enemy > 4) {
					if (i == 1 || i == 2) {
						enemies.add(new Enemy(canvas, x, y + 128));
					}
				}
				x += 136;
			}
		}else {
			x=0;
			boss.add(new Boss(canvas, x+160, y));
		}
		
		if(MainWindow.levelActual == 3) {
			for (Boss b : boss) {
				b.start();
			}
		}
		
		for(Enemy e:enemies) {
			e.start();
		}
	}
	
	public void stopGame(boolean finish) {
		avatar.getBllts().clear();
		avatar.stop();
		enemies.clear();
		if(MainWindow.levelActual == 1) {
			if(finish == true) {
				MainWindow.actScreen = 2;
				MainWindow.levelActual = 1;
			}else {
				MainWindow.actScreen = 3;
				MainWindow.levelActual = 2;
			}
		}else if(MainWindow.levelActual == 2) {
			if(finish == true) {
				MainWindow.actScreen = 2;
				MainWindow.levelActual = 1;
			}else {
				MainWindow.actScreen = 3;
				MainWindow.levelActual = 3;
			}
		}else if(MainWindow.levelActual == 3) {
			if(finish == true) {
				MainWindow.actScreen=2;
			}
			
		}
	}

	@Override
	public void paint() {
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
				
		avatar.paint();
		for(Enemy en:enemies) {
			en.paint();
 		}
		
		if(MainWindow.levelActual == 3) {
			for (Boss b : boss) {
				b.paint();
			}
		}
		
		ArrayList<Bullet> bToDel=new ArrayList<>();
		ArrayList<Enemy> eToDel=new ArrayList<>();
		for(Bullet b: avatar.getBllts()) {
			for(Enemy e:enemies) {
				if(b.getRectangle().getBoundsInLocal().intersects(e.getRectangle().getBoundsInLocal())) {
					bToDel.add(b);
					eToDel.add(e);
					if(MainWindow.levelActual == 1) {
						MainWindow.score += 200;
					}else if(MainWindow.levelActual == 2) {
						MainWindow.score += 240;
					}else if(MainWindow.levelActual == 3) {
						MainWindow.score += 500;
					}
				}
			}
		}
		avatar.getBllts().removeAll(bToDel);
		enemies.removeAll(eToDel);

		if (enemies.size() == 0) {
			ScoreScreen.gameOver = true;
			stopGame(false);
		}
		
		for(Enemy e:enemies) {
			ArrayList<Bullet> bsToDel=new ArrayList<>();
			for(Bullet bs: e.getBllts()) {
				if(bs.getRectangle().getBoundsInLocal().intersects(avatar.getRectangle().getBoundsInLocal())) {
					bsToDel.add(bs);
				}
			}
			if(!bsToDel.isEmpty()) {
				e.getBllts().removeAll(bsToDel);
				ScoreScreen.gameOver = false;
				stopGame(true);
				break;
			}
		}
		
		for(Enemy e:enemies) {
			if(e.getRectangle().getBoundsInLocal().intersects(avatar.getRectangle().getBoundsInLocal())) {
				ScoreScreen.gameOver = false;
				stopGame(true);
				break;
			}
		}
		
	}
	
	@Override
	public void onClick(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onKey(KeyEvent e) {
		if(e.getCode().equals(KeyCode.SPACE)) {
			avatar.shot();
		}
		if(e.isShiftDown()) {
			avatar.setSpeed(5);
		}
		if(e.getCode().equals(KeyCode.W)) {
			avatar.setUP(true);
		}
		if(e.getCode().equals(KeyCode.S)) {
			avatar.setDOWN(true);
		}
		if(e.getCode().equals(KeyCode.A)) {
			avatar.setLEFT(true);
		}
		if(e.getCode().equals(KeyCode.D)) {
			avatar.setRIGHT(true);
		}
	}

	@Override
	public void releasedKey(KeyEvent e) {
		if(e.getCode().equals(KeyCode.SHIFT)) {
			avatar.setSpeed(2);
		}
		if(e.getCode().equals(KeyCode.W)) {
			avatar.setUP(false);
		}
		if(e.getCode().equals(KeyCode.S)) {
			avatar.setDOWN(false);
		}
		if(e.getCode().equals(KeyCode.A)) {
			avatar.setLEFT(false);
		}
		if(e.getCode().equals(KeyCode.D)) {
			avatar.setRIGHT(false);
		}	
	}	

}
