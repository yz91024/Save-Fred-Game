package javagame;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Key extends GameObject {
	
	private Handler handler;
	Texture tex = Game.getInstance();
	
	public Key(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler= handler;
		
	}
	public Rectangle getBounds() {
		return new Rectangle(x, y, 16, 16);
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
					if(KeyInput.selected){
						Game.hasKey = true;
					
						handler.removeObject(this);
						KeyInput.selected = false;
					}
				}
			}else if (tempObject.getId() == ID.Player2) {
				if (getBounds().intersects(tempObject.getBounds())) {
					if(KeyInput.selected){

						Game.hasKey = true;
					
						handler.removeObject(this);
						KeyInput.selected = false;
					}
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		//g.setColor(Color.yellow);
		//g.fillRect(x, y, 16, 16);
		g.drawImage(tex.other[2], x, y, null);
	}
	
}
