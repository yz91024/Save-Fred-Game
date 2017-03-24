package javagame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Boss extends GameObject {
	
	private Handler handler;
	
	private SpriteAnimation attackRight;
	private SpriteAnimation attackLeft;
	private SpriteAnimation deathAnimationLeft;
	private SpriteAnimation deathAnimationRight;
	
	int attackCount = 50;
	int count = 20;
	int deathCount = 50;
	
	Texture tex = Game.getInstance();
	
	public Boss(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		Health = 200;
		this.handler= handler;
		isAttacking = true;
		velX = 0;
		velY = 0;
		faceLeft = true;
		attackRight = new SpriteAnimation(5,tex.boss[4],tex.boss[5],tex.boss[6],tex.boss[7]);
		attackLeft = new SpriteAnimation(5,tex.boss[0],tex.boss[1],tex.boss[2],tex.boss[3]);
		deathAnimationLeft = new SpriteAnimation(10,tex.boss[8],tex.boss[9],tex.boss[10],tex.boss[11]);
		deathAnimationRight = new SpriteAnimation(10,tex.boss[12],tex.boss[13],tex.boss[14],tex.boss[15]);
	
	}
	public Rectangle getBounds() {
		return new Rectangle(x-20, y-256,130,512);
	}
	
	public Rectangle getLeftBounds() {
		return new Rectangle(x-20, y-256,65,512);
	}
	
	public Rectangle getRightBounds() {
		return new Rectangle(x+40, y-256,65,512);
	}
	
	public Rectangle getTopBounds() {
		return new Rectangle(x-19, y-256,128,100);
	}

	public void tick() {
		if(dead) {
			if(deathCount > 1) {
				if(faceLeft) {
					deathAnimationLeft.runAnimation();
				}
				if(faceRight) {
					deathAnimationRight.runAnimation();
				}
			}
			if(deathCount == 1) {
				handler.addObject(new Key(x,y+10,ID.Key,handler));
			}
			if(deathCount > 0) {
				deathCount--;
			}
			return;
		}
		
		if(isAttacking) {
			if(faceLeft) {
				attackLeft.runAnimation();
			}
			if(faceRight) {
				attackRight.runAnimation();
			}
		}
		
		if(Health <= 0) {
			dead = true;
		}
		
		collision();
	}

	@Override
	public void render(Graphics g) {
		
		if(dead) {
			if(deathCount > 1) {
				if(faceLeft) {
					deathAnimationLeft.drawAnimation(g, x-100, y-230,256,512);
				}
				if(faceRight) {
					deathAnimationRight.drawAnimation(g, x-100, y-230,256,512);
				}
			}
			if(!(deathCount > 0)) {
				deathCount--;
			}
			return;
		}
		
		g.setColor(Color.gray);
		g.fillRect(x-50, y-250, 200, 16);
		g.setColor(new Color(255, 0, 0));
		g.fillRect(x-50, y-250, Health, 16);
		g.setColor(Color.white);
		g.drawRect(x-50, y-250, 200, 16);
		if(faceLeft) {
			attackLeft.drawAnimation(g, x-100, y-230,256,512);
		}
		if(faceRight){
			attackRight.drawAnimation(g, x-100, y-230,256,512);
		}
	}
	
	private void collision() {
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ID.Player) {
				if (getTopBounds().intersects(tempObject.getBounds())) {
					tempObject.x+=4;
					if(faceLeft) {
						tempObject.x+=4;
					}
					if(faceRight) {
						tempObject.x-=4;
					}
				}		
			}
		}
	}
	
}
