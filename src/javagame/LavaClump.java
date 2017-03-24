package javagame;

import java.awt.Graphics;
import java.awt.Rectangle;

public class LavaClump extends GameObject {
	
	Texture tex = Game.getInstance();
		
	public LavaClump(int x, int y, ID id) {
		super(x, y, id);
		
		
	
	}
	public Rectangle getBounds() {
		return new Rectangle(x, y, 16, 16);
	}

	public void tick() {
				
	}

	@Override
	public void render(Graphics g) {
		if ((this.x > -Game.camX + Game.WIDTH) || (this.x < -Game.camX - 32) || (this.y > -Game.camY + Game.HEIGHT) || (this.y < -Game.camY - 32)) {
			return;
		} else {
			g.drawImage(tex.block[1], x, y, 16,16,null);
		}
	}
	
}
