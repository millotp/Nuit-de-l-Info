package gui;

import static util.Global.endRequest;
import static util.Global.width;
import static util.Renderer.font16;
import static util.Renderer.writeCentered;

public class Game extends GUI
{
	public Game()
	{
		super();
		addButton(width / 2 - 24, 60, 30, "Track", "TRACK");
	}
	
	@Override
	public void render()
	{
		super.render();
		writeCentered(font16, "Menu", width / 2, 10);
	}
	
	@Override
	public void onKeyPressed(int key)
	{
		super.onKeyPressed(key);
		if(key == 27)
			endRequest = true;
	}
}
