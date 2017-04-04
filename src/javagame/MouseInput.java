package javagame;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javagame.Game.STATE;
import javagame.Menu.MENU;

public class MouseInput extends MouseAdapter implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		/*
 		public Rectangle play = new Rectangle(Game.WIDTH/2-100,270,200,50);
		public Rectangle quit = new Rectangle(Game.WIDTH/2-100,390,200,50);
		public Rectangle help = new Rectangle(Game.WIDTH/2-100,520,200,50);
	 	*/
		int mx = e.getX();
		int my = e.getY();
		
		//get mouse click position
		if (Game.state == STATE.MENU) {
			if(Menu.menuState == MENU.CREDITS) {
					Menu.menuState = MENU.START;
				
			}
			if(mx >= Game.WIDTH/2-100 && mx <= Game.WIDTH/2+120) {
				if(my >= 260 && my <= 310) {
					Game.state = STATE.GAME;
				}
			}
			if(mx >= Game.WIDTH/2-100 && mx <= Game.WIDTH/2+120) {
				if(my >= 390 && my <= 440) {
					if(Menu.menuState != MENU.CREDITS)
						Game.state = STATE.HELP;
				}
			}
			
			if(mx >= Game.WIDTH/2-100 && mx <= Game.WIDTH/2+120) {
				if(my >= 520 && my <= 570) {
					if(Menu.menuState == MENU.PAUSE) {
						Menu.menuState = MENU.START;
						Game.hasBegun = false;
					} else {
						Menu.menuState = MENU.CREDITS;
					}
				}
			}
			
		} else if(Game.state == STATE.HELP ) {
			Game.state = STATE.MENU;
			if(Game.hasBegun){
				Menu.menuState = MENU.PAUSE;
			} else {
				Menu.menuState = MENU.START;
			}
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	
	/*
	private boolean mouseOver(int mx, int my, int x, int y, int w, int h) {
		if(mx > x && mx < x+w) {
			if(my > y && my < y + h) {
				return true;
			}else return false;
		}else return false;
	}
	*/
	
	public void mouseMoved(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		//menu effects
		if(mx >= Game.WIDTH/2-100 && mx <= Game.WIDTH/2+120) {
			if(my >= 260 && my <= 310) {
				Menu.playColor = Color.LIGHT_GRAY;
			} else Menu.playColor = Color.darkGray;
		}else Menu.playColor = Color.darkGray;
		
		if(mx >= Game.WIDTH/2-100 && mx <= Game.WIDTH/2+120) {
			if(my >= 390 && my <= 440) {
				Menu.helpColor = Color.LIGHT_GRAY;
			}else Menu.helpColor = Color.darkGray;
		}else Menu.helpColor = Color.darkGray;
		
		if(mx >= Game.WIDTH/2-100 && mx <= Game.WIDTH/2+120) {
			if(my >= 520 && my <= 570) {
				Menu.quitColor = Color.LIGHT_GRAY;
			}else Menu.quitColor = Color.darkGray;
		}else Menu.quitColor = Color.darkGray;
	}

}
