package javagame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HUD {

	public static int HEALTH = 100;
	public static int HEALTH2 = 100;
	public static int COINS = 0;
	public static int COINS2 = 0;
	private int greenValue = 25;
	private int greenValue2 = 25;
	Font comic = new Font("comic sans ms",Font.BOLD,15);
	private int redValue = 75;
	private int blueValue = 0;
	
	public static int fps = 0;
	
	public void tick() {
		
		if(HEALTH <= 0)
			if(Game.currentLevel == 5) {
				Game.respawn5 = true;
			} else if(Game.currentLevel == 4) {
				Game.respawn4 = true;
			}else if(Game.currentLevel == 3) {
				Game.respawn3 = true;
			}else if(Game.currentLevel == 2) {
				Game.respawn2 = true;
			}else if(Game.currentLevel == 1) {
				Game.respawn1 = true;
			} else {
				Game.respawn1 = true;
			}
			
		HEALTH = Game.clamp(HEALTH, 0, 100);
		HEALTH2= Game.clamp(HEALTH2, 0, 100);
		COINS = Game.clamp(COINS, 0, 100);
		COINS2= Game.clamp(COINS2, 0, 100);

		greenValue=Game.clamp(greenValue,0,255 );
		greenValue2=Game.clamp(greenValue2,0,255 );

		greenValue = HEALTH*2;
		greenValue2 = HEALTH2*2;
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(15, 15, 200, 16);
		g.setColor(new Color(redValue, greenValue, blueValue));
		g.fillRect(15, 15, HEALTH * 2, 16);
		g.setColor(Color.white);
		g.drawRect(15, 15, 200, 16);
		if(Game.twoPlayer) {
			g.setColor(Color.gray);
			g.fillRect(230, 15, 200, 16);
			g.setColor(new Color(redValue, greenValue2, blueValue));
			g.fillRect(230, 15, HEALTH2 * 2, 16);
			g.drawRect(230, 15, 200, 16);
			g.setColor(Color.white);
			g.drawRect(230, 15, 200, 16);
		}
		g.setColor(Color.gray);
		g.fillRect(15, 36, 200, 16);
		g.setColor(Color.yellow);
		g.fillRect(15, 36, COINS * 2, 16);
		g.setColor(Color.white);
		g.drawRect(15, 36, 200, 16);
		if(Game.twoPlayer) {
			g.setColor(Color.gray);
			g.fillRect(230, 36, 200, 16);
			g.setColor(Color.yellow);
			g.fillRect(230, 36, COINS2 * 2, 16);
			g.drawRect(230, 36, 200, 16);
			g.setColor(Color.white);
			g.drawRect(230, 36, 200, 16);
		}
		if(Game.debug){
			g.setFont(comic);
			g.setColor(Color.white);
			g.drawString("FPS: "+fps, 15, 70);
			g.drawString("Current Level: "+Game.currentLevel, 15, 85);
			g.drawString("Level Width: "+Game.maxW1, 15, 100);
			g.drawString("Level Height: "+Game.maxH1, 15, 115);
			g.drawString("Has Key: "+Game.hasKey, 15, 160);

		}
	}	
}
