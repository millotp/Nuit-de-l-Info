package gui;

import static util.Global.endRequest;
import static util.Global.height;
import static util.Global.width;
import static util.Renderer.font16;
import static util.Renderer.writeCentered;

import game.Ball;
import game.World;

public class Game extends GUI {

	private Ball balle;
	private World world1;

	public Game() {
		super();
		balle = new Ball(width / 2, height / 2, 25);
		world1 = new World(2, "meta/sky.jpg");

	}

	@Override
	public void update(double timeMultiplier) {
		balle.update();
		world1.update();
	}

	@Override
	public void render() {
		super.render();

		writeCentered(font16, "Menu", width / 2, 10);
		balle.render();
		world1.render();
	}

	@Override
	public void onKeyPressed(int key) {
		super.onKeyPressed(key);
		if (key == 27)
			endRequest = true;
	}
}
