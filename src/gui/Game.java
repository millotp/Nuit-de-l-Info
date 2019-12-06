package gui;

import static util.Global.endRequest;
import static util.Global.height;
import static util.Global.width;
import static util.Renderer.fontMenu;
import static util.Renderer.writeCentered;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import audio.SoundsManager;
import game.Ball;
import game.World;

public class Game extends GUI {

	private Ball balle;

	private World moonWorld;
	private World xmasWorld;
	private World ringWorld;

	private ArrayList<World> worlds;

	private World currWorld;
	private int currWorldIndex;

	private Button btn1;
	private Button btn2;
	private Button btn3;

	private SoundsManager music;

	private int score;

	public Game() {

		super();
		score = 0;
		music = new SoundsManager();

		balle = new Ball((width / 6 + 7 * width / 16) / 2, Math.PI / 2, 25);

		moonWorld = new World(5, "meta/moon_ground.jpg", "meta/stars.jpg", "moon");
		xmasWorld = new World(5, "meta/xmas_ground.jpg", "meta/xmas_bkg.jpg", "xmas");
		ringWorld = new World(5, "meta/ring_ground.jpg", "meta/ring_bkg.jpg", "ring");
		worlds = new ArrayList<World>();
		worlds.add(moonWorld);
		worlds.add(xmasWorld);
		worlds.add(ringWorld);

		currWorld = this.worlds.get(0);
		currWorldIndex = 0;
		music.changeMusicTheme(currWorld.getTheme());

		btn1 = new Button(1, 10, height - 30, 100, 30, "Monde 1", null);
		btn2 = new Button(2, 10 + 100, height - 30, 100, 30, "Monde 2", null);
		btn3 = new Button(3, 10 + 200, height - 30, 100, 30, "Monde 3", null);
		btn1.setPressed(true);
		btn2.setPressed(false);
		btn3.setPressed(false);
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public void update(double timeMultiplier) {

		balle.update();
		int victime = (int) (Math.random() * worlds.size());
		for (int i = 0; i < worlds.size(); i++) {
			World w = worlds.get(i);
			w.update();
			w.morph(i == victime);
		}

		balle.isDead = currWorld.collide(balle);

		if (balle.isDead) {
			// Main.theMain.changeGUI("DEATH");
		}

	}

	@Override
	public void render() {
		super.render();

		currWorld.render();

		balle.render();

		writeCentered(fontMenu, "Score : " + score, width / 10, height / 17, 0xffffff);
		btn1.render();
		btn2.render();
		btn3.render();
	}

	@Override
	public void onKeyPressed(int key) {
		super.onKeyPressed(key);
		if (key == 27)
			endRequest = true;

		if (key == Keyboard.KEY_1) {
			currWorldIndex = 0;
			currWorld = worlds.get(currWorldIndex);
			btn1.setPressed(true);
			btn2.setPressed(false);
			btn3.setPressed(false);
			music.addMusicEffect("laser");
			music.changeMusicTheme(currWorld.getTheme());
		}
		if (key == Keyboard.KEY_2) {
			currWorldIndex = 1;
			currWorld = worlds.get(currWorldIndex);
			btn1.setPressed(false);
			btn2.setPressed(true);
			btn3.setPressed(false);
			music.addMusicEffect("laser");
			music.changeMusicTheme(currWorld.getTheme());
		}
		if (key == Keyboard.KEY_3) {
			currWorldIndex = 2;
			currWorld = worlds.get(currWorldIndex);
			btn1.setPressed(false);
			btn2.setPressed(false);
			btn3.setPressed(true);
			music.addMusicEffect("laser");
			music.changeMusicTheme(currWorld.getTheme());
		}
	}
}
