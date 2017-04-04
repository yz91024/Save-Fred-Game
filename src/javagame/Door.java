package javagame;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Door extends GameObject {
	
	private Handler handler;
	Texture tex = Game.getInstance();
	
	public Door(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler= handler;
		
		
	}
	public Rectangle getBounds() {
		return new Rectangle(x, y, 16, 32);
	}

	public void tick() {
		
		collision();
		
	}
	
	private void collision() 
	{
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ID.Player) {
				if (getBounds().intersects(tempObject.getBounds())) {
					if(KeyInput.selected && Game.hasKey) {
						KeyInput.nextLevel = true;
						KeyInput.selected = false;
						Game.hasKey = false;
					}
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		if(Game.hasKey) {
			g.drawImage(tex.other[0], x, y, null);
		} else {
			g.drawImage(tex.other[3], x, y, null);
		}
	}
	
}
