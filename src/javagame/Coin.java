package javagame;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Coin extends GameObject {
	
	private Handler handler;
	Texture tex = Game.getInstance();
	
	public Coin(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler= handler;
		
		Random r = new Random();
		velX = r.nextInt(5) + 3;
		velY = r.nextInt(5) + 3;
	
	}
	public Rectangle getBounds() {
		return new Rectangle(x, y, 16, 16);
	}

	public void tick() {
		//x += velX;
		//y += velY;
		//x = Game.clamp(x,  0,  Game.WIDTH - 16);
		//y = Game.clamp(y,  0,  Game.HEIGHT - 40);
		//if ((y <= 0 || y >= Game.HEIGHT - 40)) velY *= -1;
		//if ((x <= 0 || x >= Game.WIDTH - 16)) velX *= -1;
		
		//handler.addObject(new Trail(x, y, ID.Trail, Color.yellow, 16, 16, 0.05f, handler));
		collision();
		
	}
	
	private void collision() 
	{
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ID.Player) {
				if (getBounds().intersects(tempObject.getBounds())) {
					if(KeyInput.selected){
						HUD.COINS += 2;
					
						handler.removeObject(this);
					}
				}
			}else if (tempObject.getId() == ID.Player2) {
				if (getBounds().intersects(tempObject.getBounds())) {
					if(KeyInput.selected){

						KeyInput.selected = false;
						HUD.COINS2 += 2;
					
						handler.removeObject(this);
					}
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		//g.setColor(Color.yellow);
		//g.fillRect(x, y, 16, 16);
		g.drawImage(tex.other[1], x, y, null);
	}
	
}
