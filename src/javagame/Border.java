package javagame;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Border extends GameObject {

	Texture tex = Game.getInstance();
	
	Handler handler;

	public Border(int x, int y, ID id,Handler handler) {
		super(x, y, id);
		this.handler = handler;
		velX = 0;
		velY = 0;
		
		
	}

	@Override
	public void tick() {
		collision();
	}

	@Override
	/*
	public void render(Graphics g) {
		if (type == 0) { //dirt
			g.drawImage(tex.block[0], x, y, null);
		}
		if (type == 1) { //grass
			g.drawImage(tex.block[1], x, y, null);
		}
		//Graphics2D g2d = (Graphics2D) g;
		//g.setColor(Color.white);
		//g.fillRect(x, y, 16, 16);
		//g2d.draw(getBounds());
		
	}
	*/
	
	public void render(Graphics g) {
		

	}
	
	private void collision() {
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ID.Player) {
				if (getBounds2().intersects(tempObject.getBounds())) {
					HUD.HEALTH = 0;
					return;
				}
			}
		}
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle(x,y,16,16);
	}
	
	public Rectangle getBounds2() {
		return new Rectangle(x,y-1,16,18);
	}
	
	

}
