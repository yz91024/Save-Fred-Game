package javagame;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Block extends GameObject {

	Texture tex = Game.getInstance();
	private int type;
	
	Handler handler;

	public Block(int x, int y, ID id,Handler handler,int type) {
		super(x, y, id);
		this.type = type;
		this.handler = handler;
		velX = 0;
		velY = 0;
		
		
	}

	@Override
	public void tick() {
		//do nothing on tick
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
		
		//only draw blocks within camera range
		if ((this.x > -Game.camX + Game.WIDTH) || (this.x < -Game.camX - 32) || (this.y > -Game.camY + Game.HEIGHT) || (this.y < -Game.camY - 32)) {
			return;
		} else {
			if (type == 0) {
				g.drawImage(tex.block[0], x, y,16,16, null);
			}
			if (type == 1) {
				g.drawImage(tex.block[1], x, y, 16,16,null);
			}

		}
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle(x,y,16,16);
	}
	
	

}
