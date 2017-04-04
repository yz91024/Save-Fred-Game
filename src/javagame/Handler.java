package javagame;

import java.awt.Graphics;
import java.util.ArrayList;

public class Handler {
	
	ArrayList<GameObject> object = new ArrayList<GameObject>();
	GameObject player = null;
	
	public void tick() {
		//loop through and tick every object
		for (int i = 0; i < object.size(); i++) {
			try{
			GameObject tempObject = object.get(i);
			
			tempObject.tick();
			} catch(Exception e) {
				
			}
		}
	}
 
	public void render(Graphics g) {
		//render all objects
		for (int i = 0; i < object.size(); i++) {
			try{
				GameObject tempObject = object.get(i);
				if(tempObject.id == ID.Player) {
					player = tempObject;
				}
				tempObject.render(g);
				
			} catch(Exception e) {
				
			}
		}
		player.render(g);
	}
	
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	
}
