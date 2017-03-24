package javagame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javagame.Game.STATE;

public class Menu {

	public Rectangle play = new Rectangle(Game.WIDTH/2-100,260,200,50);
	public Rectangle help = new Rectangle(Game.WIDTH/2-100,390,200,50);
	public Rectangle quit = new Rectangle(Game.WIDTH/2-100,520,200,50);
	
	public static Color playColor = Color.gray;
	public static Color helpColor = Color.gray;
	public static Color quitColor = Color.gray;
	
	public enum MENU {
		START,
		PAUSE,
		CREDITS
	};
	
	public static MENU menuState = MENU.START;
	
	public void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		Font fnt0 = new Font("comic sans ms",Font.BOLD,70);
		g.setFont(fnt0);
		g.setColor(Color.black);
		if(menuState == MENU.START){
			g.drawString("SAVE FRED!", 190, 180);
		} else {
			g.drawString("PAUSED", 400, 180);
		}
		
		//draw menu
		Font fnt1 = new Font("comic sans ms",Font.BOLD,30);
		g.setFont(fnt1);
		if (Game.state == STATE.MENU) {
			if(menuState == MENU.CREDITS) {
				g.setColor(Color.black);
				g.drawString("Programmer: Steven Zheng", Game.WIDTH/2-250, 250);
				g.drawString("Level Design: Bubby Baggish and Austin He", Game.WIDTH/2-255, 350);
				g.drawString("Cutscenes: Austin He and Justin He", Game.WIDTH/2-255, 450);
				g.drawString("Graphics:Steven Zheng and Cash Cunningham", Game.WIDTH/2-255, 550);
				g.drawString("Music:Austin He and Steven Zheng", Game.WIDTH/2-255, 650);
				return;
			}
			g2d.setColor(playColor);
			g2d.fill(play);
			//g2d.draw(play);
			g.setColor(Color.black);
			g.drawString("PLAY", play.x+55, play.y+35);
			g2d.setColor(helpColor);
			g2d.fill(help);
			//g2d.draw(help);
			g.setColor(Color.black);
			g.drawString("HELP", help.x+55, help.y+35);
			if(menuState == MENU.PAUSE) {
				g2d.setColor(quitColor);
				g2d.fill(quit);
				//g2d.draw(quit);
				g.setColor(Color.black);
				g.drawString("QUIT", quit.x+55, quit.y+35);
			} else {
				g2d.setColor(quitColor);
				g2d.fill(quit);
				//g2d.draw(quit);
				g.setColor(Color.black);
				g.drawString("CREDITS", quit.x+30, quit.y+35);
			}
		} if (Game.state == STATE.HELP) {
			g.drawString("Controls:", Game.WIDTH/2-150, 300);
			g.drawString("Up: Jump", Game.WIDTH/2-155, 400);
			g.drawString("Left: Walk Left", Game.WIDTH/2-155, 500);
			g.drawString("Right: Walk Right", Game.WIDTH/2-155, 600);
			g.drawString("Down: Use", Game.WIDTH/2-155, 700);
			g.drawString("Space: Respawn", Game.WIDTH/2-155, 800);
		}
	}
	
	
	
}
