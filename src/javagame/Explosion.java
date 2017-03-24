package javagame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class Explosion extends GameObject {
	
	private Handler handler;
	private float gravity = 0.1f;
	private final float maxSpeed = 10;
	private float alpha = 1;
	private float life;

	
	public Explosion(int x, int y, ID id, Handler handler,float life) {
		super(x, y, id);
		
		this.life = life;
		
		this.handler= handler;
		
		Random r = new Random();
		velX = r.nextInt(6)-3;
		velY = r.nextInt(6)-3;
	
	}
	public Rectangle getBounds() {
		return new Rectangle(x, y, 8, 8);
	}
	public Rectangle getBoundsDown() {
		return new Rectangle(x+2, y+4, 4, 4);
	}
	public Rectangle getBoundsRight() {
		return new Rectangle(x+6, y+1, 2, 6);
	}
	public Rectangle getBoundsLeft() {
		return new Rectangle(x, y+1, 2, 6);
	}
	public Rectangle getBoundsUp() {
		return new Rectangle(x+2, y, 4, 4);
	}

	public void tick() {
		if ( alpha > life) {
			alpha -= (life - 0.0001f);
		}
		else {
			handler.removeObject(this);
		}
		x += velX;
		y += velY;
		x = Game.clamp(x,  0,  Game.WIDTH - 16);
		y = Game.clamp(y,  0,  Game.HEIGHT - 40);
		if ((y <= 0 || y >= Game.HEIGHT - 40)) velY *= -1;
		if ((x <= 0 || x >= Game.WIDTH - 16)) velX *= -1;
		collision();
		velY += gravity;
		
		if (velY > maxSpeed) {
			velY = maxSpeed;
		}
	
		
		//handler.addObject(new Trail(x, y, ID.Trail, Color.red, 16, 16, 0.05f, handler));
		
	}
	
	private void collision() {
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ID.BasicEnemy) {
				if (tempObject.getId() == ID.Block) {
					if ((getBoundsLeft().intersects(tempObject.getBounds())) || (getBoundsRight().intersects(tempObject.getBounds()))) {
					
						velX *= -1;
			
					}
					if ((getBoundsUp().intersects(tempObject.getBounds())) || (getBoundsDown().intersects(tempObject.getBounds()))) {
						velY *=1;
					}
				} else if (tempObject.getId() == ID.BasicEnemy) {
					if (getBounds().intersects(tempObject.getBounds())) {
						handler.removeObject(tempObject);
					}
				}
			}
		}	
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.GRAY);
		g.fillRect(x, y, 8, 8);
		g.setColor(Color.cyan);
		g2d.draw(getBounds());
		g.setColor(Color.red);
		g2d.draw(getBoundsRight());
		g.setColor(Color.orange);
		g2d.draw(getBoundsLeft());
		g.setColor(Color.yellow);
		g2d.draw(getBoundsUp());
	}
	
}
