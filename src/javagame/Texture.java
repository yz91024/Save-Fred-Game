package javagame;

import java.awt.image.BufferedImage;

public class Texture {

	SpriteSheet bs, ps,os,ss,ds,es,b,e;
	private BufferedImage block_sheet = null;
	private BufferedImage other_sheet = null;
	private BufferedImage player_sheet = null;
	private BufferedImage story_sheet = null;
	private BufferedImage end_sheet = null;
	private BufferedImage door_sheet = null;
	private BufferedImage enemy_sheet = null;
	private BufferedImage boss_sheet = null;
	
	public BufferedImage[] block = new BufferedImage[2];
	public BufferedImage[] player = new BufferedImage[19];
	public BufferedImage[] enemy = new BufferedImage[19];
	public BufferedImage[] other = new BufferedImage[4];
	public BufferedImage[] story = new BufferedImage[12];
	public BufferedImage[] boss = new BufferedImage[16];

	
	public Texture() {
		
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			block_sheet = loader.loadImage("/blocks.png");
			player_sheet = loader.loadImage("/player_sheet.png");
			enemy_sheet = loader.loadImage("/enemy_sheet.png");
			other_sheet = loader.loadImage("/other_sheet.png");
			story_sheet = loader.loadImage("/story-1.png");
			door_sheet = loader.loadImage("/door_sheet.png");
			boss_sheet = loader.loadImage("/boss.png");
			end_sheet = loader.loadImage("/end.png");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		bs = new SpriteSheet(block_sheet);
		ps = new SpriteSheet(player_sheet);
		os = new SpriteSheet(other_sheet);
		ss = new SpriteSheet(story_sheet);
		ds = new SpriteSheet(door_sheet);
		e = new SpriteSheet(end_sheet);
		es = new SpriteSheet(enemy_sheet);
		b = new SpriteSheet(boss_sheet);

		getTextures();
	}


	private void getTextures() {
		
		//get the textures
		block[0] = bs.grabImage(1, 1, 16, 16); //dirt
		block[1] = bs.grabImage(2, 1, 16, 16); //grass
		player[0] = ps.grabImage(1, 1, 16, 32); //player
		player[1] = ps.grabImage(2, 1, 16, 32); //player2
		player[2] = ps.grabImage(3, 1, 16, 32); //player
		player[3] = ps.grabImage(4, 1, 16, 32); //player2
		player[4] = ps.grabImage(2, 2, 16, 32); //player
		player[5] = ps.grabImage(3, 2, 16, 32); //player2
		player[6] = ps.grabImage(4, 2, 16, 32); //player
		player[7] = ps.grabCustomImage(64, 1, 22, 32); //player2
		player[8] = ps.grabCustomImage(90, 1, 22, 32); //player
		player[9] = ps.grabCustomImage(117, 1, 22, 32); //player2
		player[10] = ps.grabCustomImage(70, 32, 22, 32); //player
		player[11] = ps.grabCustomImage(95, 32, 22, 32); //player2
		player[12] = ps.grabCustomImage(122, 32, 22, 32); //player
		player[13] = ps.grabCustomImage(64, 64, 22, 32); //player2
		player[14] = ps.grabCustomImage(90, 64, 22, 32); //player
		player[15] = ps.grabCustomImage(117, 64, 22, 32); //player2
		player[16] = ps.grabCustomImage(70, 96, 22, 32); //player
		player[17] = ps.grabCustomImage(95, 96, 22, 32); //player2
		player[18] = ps.grabCustomImage(122, 96, 22, 32); //player
		enemy[0] = es.grabImage(1, 1, 16, 32); //enemy
		enemy[1] = es.grabImage(2, 1, 16, 32); //enemy2
		enemy[2] = es.grabImage(3, 1, 16, 32); //enemy
		enemy[3] = es.grabImage(4, 1, 16, 32); //enemy2
		enemy[4] = es.grabImage(2, 2, 16, 32); //enemy
		enemy[5] = es.grabImage(3, 2, 16, 32); //enemy2
		enemy[6] = es.grabImage(4, 2, 16, 32); //enemy
		enemy[7] = es.grabCustomImage(64, 1, 22, 32); //enemy2
		enemy[8] = es.grabCustomImage(90, 1, 22, 32); //enemy
		enemy[9] = es.grabCustomImage(117, 1, 22, 32); //enemy2
		enemy[10] = es.grabCustomImage(70, 32, 22, 32); //enemy
		enemy[11] = es.grabCustomImage(95, 32, 22, 32); //enemy2
		enemy[12] = es.grabCustomImage(122, 32, 22, 32); //enemy
		enemy[13] = es.grabCustomImage(64, 64, 22, 32); //enemy2
		enemy[14] = es.grabCustomImage(90, 64, 22, 32); //enemy
		enemy[15] = es.grabCustomImage(117, 64, 22, 32); //enemy2
		enemy[16] = es.grabCustomImage(70, 96, 22, 32); //enemy
		enemy[17] = es.grabCustomImage(95, 96, 22, 32); //enemy2
		enemy[18] = es.grabCustomImage(122, 96, 22, 32); //enemy
		other[0] = os.grabImage(1, 1, 16, 32); //door
		other[1] = os.grabImage(2, 1, 16, 16); //coin
		other[2] = os.grabImage(2, 2, 16, 16);
		other[3] = ds.grabImage(1, 1, 16, 32);
		boss[0] = b.grabImage(1, 1, 64, 128);
		boss[1] = b.grabImage(2, 1, 64, 128);
		boss[2] = b.grabImage(3, 1, 64, 128);
		boss[3] = b.grabImage(4, 1, 64, 128);
		boss[4] = b.grabImage(1, 2, 64, 128);
		boss[5] = b.grabImage(2, 2, 64, 128);
		boss[6] = b.grabImage(3, 2, 64, 128);
		boss[7] = b.grabImage(4, 2, 64, 128);
		boss[8] = b.grabImage(1, 3, 64, 128);
		boss[9] = b.grabImage(2, 3, 64, 128);
		boss[10] = b.grabImage(3, 3, 64, 128);
		boss[11] = b.grabImage(4, 3, 64, 128);
		boss[12] = b.grabImage(1, 4, 64, 128);
		boss[13] = b.grabImage(2, 4, 64, 128);
		boss[14] = b.grabImage(3, 4, 64, 128);
		boss[15] = b.grabImage(4, 4, 64, 128);
		
		//get cut scene slides
		for(int i = 0; i < 6; i++) {
			story[i] = ss.grabImage(i+1,1,640,400);
			System.out.println("loaded story slide "+i);
		}
		
		for(int j = 0; j < 6; j++) {
			story[j+6] = e.grabImage(j+1,1,640,400);
			System.out.println("loaded other story slide "+j);
		}
		
	}
	
}
