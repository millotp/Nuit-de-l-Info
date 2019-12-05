package gui;

import static util.Global.endRequest;
import static util.Global.height;
import static util.Global.width;
import static util.Renderer.font16;
import static util.Renderer.writeCentered;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import game.Ball;
import game.World;

public class Game extends GUI
{
	
	private Ball balle;
	private World moonWorld;
	private World xmasWorld;
	
	private ArrayList<World> worlds;
	
	private World currWorld;
	private int currWorldIndex;
	
	
	public Game()
	{
		super();
		balle = new Ball(width/2, height/2 - width/4, 25);
		moonWorld = new World(2, "meta/moon_ground.jpg", "meta/stars.jpg");
		xmasWorld = new World(2, "meta/xmas_ground.jpg", "meta/xmas_bkg.jpg");
		worlds = new ArrayList<World>();
		worlds.add(moonWorld); 
		worlds.add(xmasWorld);
		
		currWorld = this.worlds.get(0);
		currWorldIndex = 0;
		
	}
	
	@Override
	public void update(double timeMultiplier) {
		balle.update();
		currWorld.update();
	}
	
	
	
	@Override
	public void render()
	{
		super.render();
		
		
		
		
		writeCentered(font16, "Menu", width / 2, 10);
		
		currWorld.render();
		
		balle.render();
	}
	
	@Override
	public void onKeyPressed(int key)
	{
		super.onKeyPressed(key);
		if(key == 27)
			endRequest = true;
		
		if (key == Keyboard.KEY_1) {
			currWorldIndex = 0;
			currWorld = worlds.get(currWorldIndex);
		}
		if (key == Keyboard.KEY_2) {
			currWorldIndex = 1;
			currWorld = worlds.get(currWorldIndex);
		}
	}
}
