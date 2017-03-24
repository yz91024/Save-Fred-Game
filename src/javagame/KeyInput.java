package javagame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javagame.Game.STATE;
import javagame.Menu.MENU;

public class KeyInput extends KeyAdapter {
	
	private Handler handler;
	public int t153key;
	
	public static boolean nextLevel = false;
	public static boolean lastLevel = false;
	
	public static boolean selected = false;
	
	public KeyInput(Handler handler) {
		this.handler = handler;
	}
	
	//stuff to do on key down
	public void keyPressed(KeyEvent e) {
		if (Game.state == STATE.MENU || Game.state == STATE.STORY || Game.state == STATE.END)
			return;
		t153key = e.getKeyCode();
		if(Game.state == STATE.GAME) {
		for (int o = 0; o < handler.object.size(); o++) {
			GameObject tempObject = handler.object.get(o);
			
			if (tempObject.getId() == ID.Player) {
				//key events
				if ((t153key == KeyEvent.VK_UP) && !tempObject.isJumping()) {
					tempObject.setJumping(true);
					tempObject.setVelY(Game.jumpPower);
				}
				
				if (t153key == KeyEvent.VK_LEFT) {
					tempObject.setVelX(-5);
					tempObject.faceLeft = true;
					tempObject.faceRight = false;
				}
				
				if (t153key == KeyEvent.VK_RIGHT) {
					tempObject.setVelX(5);
					tempObject.faceRight = true;
					tempObject.faceLeft = false;
				}
				
				
			}
			if (tempObject.getId() == ID.Player2) {
				if ((t153key == KeyEvent.VK_UP) && !tempObject.isJumping()) {
					tempObject.setJumping(true);
					tempObject.setVelY(-5);
				}
				
				if (t153key == KeyEvent.VK_LEFT) tempObject.setVelX(-5);
				if (t153key == KeyEvent.VK_N) {
					//handler.addObject(new Explosion(tempObject.getX(),tempObject.getY(),ID.Explosion,handler, 0.05f));
				}
				if (t153key == KeyEvent.VK_RIGHT) tempObject.setVelX(5);
				
				
			}
		}
		if (t153key == KeyEvent.VK_SPACE) {
			if(Game.currentLevel >= 2)
				Player.attacking = true;
		}
		}
		
	}
	
	//stuff to do on key release
	public void keyReleased(KeyEvent e) {
		t153key = e.getKeyCode();
		if(Game.state == STATE.STORY) {
			if (t153key == KeyEvent.VK_SPACE) {
				Game.state = STATE.TUTORIAL;
			}
		}
		if(Game.state == STATE.END) {
			if (t153key == KeyEvent.VK_SPACE) {
				Game.state = STATE.MENU;
				Menu.menuState = MENU.START;
				Game.hasBegun = false;
			}
		}
		if(Game.state == STATE.GAME) {
			for (int i = 0; i < handler.object.size(); i++) {
				GameObject tempObject = handler.object.get(i);
			
				if (tempObject.getId() == ID.Player) {
					//key events
					if (t153key == KeyEvent.VK_SPACE) {
						if(tempObject.getVelY() > 0) {
							tempObject.setVelY(0);
						}
					}
					if (t153key == KeyEvent.VK_LEFT) tempObject.setVelX(0);
					if (t153key == KeyEvent.VK_RIGHT) tempObject.setVelX(0);
					if (t153key == KeyEvent.VK_DOWN) {
						selected = true;
					}else selected = false;
				
				}
				if (tempObject.getId() == ID.Player2) {
					//key events
					if (t153key == KeyEvent.VK_UP) {
						if(tempObject.getVelY() > 0)
							tempObject.setVelY(0);
						}
						if (t153key == KeyEvent.VK_LEFT) tempObject.setVelX(0);
						if (t153key == KeyEvent.VK_RIGHT) tempObject.setVelX(0);
				
				}
			
			}
		
			if (t153key == KeyEvent.VK_ESCAPE) {
				if(Game.state == STATE.GAME){
					Menu.menuState = MENU.PAUSE;
					Game.state = STATE.MENU;
				}
			}
		
			if (t153key == KeyEvent.VK_P){
				if(Game.state == STATE.GAME){
					Game.debug = !Game.debug;
				}
			}
			if (t153key == KeyEvent.VK_L)
				nextLevel = true;
		
			if (t153key == KeyEvent.VK_SPACE) {
				Player.attacking = false;
			}
			if (t153key == KeyEvent.VK_K)
				lastLevel = true;
		} else if(Game.state == STATE.TUTORIAL) {
			if (t153key == KeyEvent.VK_SPACE) {
				Game.action++;
			}
		}
	}

}
