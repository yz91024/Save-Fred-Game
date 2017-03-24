package javagame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Animation {
	
	private int speed;
	private int frames;
		
	public int frameCount = 0;
	
	private int index = 0;
	private int count = 0;
	
	private BufferedImage[] images;
	private BufferedImage currentImg;
	
	//animation stuff
	public Animation(int speed, BufferedImage ... args) {
		this.speed = speed;
		this.images = new BufferedImage[args.length];
		for(int i = 0; i < args.length;i++) {
			images[i] = args[i];
		}
		currentImg = args[0];
		frames = args.length;
	}
	
	public void runAnimation() {
		index++;
		if(index>speed) {
			index = 0;
			nextFrame();
			frameCount++;
		}
	}
	
	private void nextFrame() {
		for(int i = 0; i < frames; i++) {
			if(count == i)
				currentImg = images[i];
		}
		count++;
		if(count > frames)
			count = 0;
	}
	
	public void drawAnimation(Graphics g, int x, int y) {
		g.drawImage(currentImg, x, y, null);
	}
	
	public void drawAnimation(Graphics g, int x, int y,int sx, int sy) {
		g.drawImage(currentImg, x, y, sx, sy, null);
	}
	
}