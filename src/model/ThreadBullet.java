package model;

public class ThreadBullet extends Thread{
	
	private Avatar avatar;

	public ThreadBullet(Avatar avatar) {
		this.avatar = avatar;
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(700);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		avatar.setTimeShot(true);
	}

}
