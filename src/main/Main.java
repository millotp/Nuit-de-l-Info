package main;

import static util.Global.height;
import static util.Global.mousePressed;
import static util.Global.mouseX;
import static util.Global.mouseY;
import static util.Global.width;
import static util.Global.endRequest;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import game.Ball;
import gui.GUI;
import gui.Game;
import menu.Menu;
import menu.Score;

public class Main {
	public static Main theMain = new Main();

	Ball ball = new Ball(200, 400, 25);

	private boolean lastPressed;
	private long currentTime, elapsedTime, lastTime;
	private GUI currentGUI;

	private void init() throws LWJGLException {
		Display.setDisplayMode(new DisplayMode(400, 800));
		Display.create();
		Display.setResizable(true);
		Display.setVSyncEnabled(true);
		Keyboard.create();
		Mouse.create();

		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glShadeModel(GL11.GL_SMOOTH);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glAlphaFunc(GL11.GL_GREATER, 0);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthFunc(GL11.GL_ALWAYS);
		GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);

		this.resize(Display.getWidth(), Display.getHeight());
		
		this.changeGUI("MENU");
		this.lastTime = System.nanoTime();
	}

	public void resize(int wi, int hei) {
		if (wi == width && hei == height)
			return;
		try {
			Display.setDisplayMode(new DisplayMode(wi, hei));
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		width = wi;
		height = hei;
		GL11.glViewport(0, 0, width, height);
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, width, height, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
	}

	private void destroy() {
		Mouse.destroy();
		Keyboard.destroy();
		Display.destroy();
	}

	public void start() {
		try {
			this.init();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		try {
			do {
				GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
				GL11.glLoadIdentity();
				GL11.glClearColor(1, 1, 1, 1.0f);
				this.loop();
				Display.update();
				if (Display.wasResized())
					this.resize(Display.getWidth(), Display.getHeight());
			} while (!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) && !endRequest);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.destroy();
		}
	}

	private void loop() {
		this.currentTime = System.nanoTime();
		this.elapsedTime = this.currentTime - this.lastTime;
		this.lastTime = this.currentTime;
		// frameRate = smooth(1000000000f / this.elapsedTime, 0.9, frameRate);
		double timeMultiplier = this.elapsedTime / (1000000000f / 60f);
		this.handleInputs();

		this.currentGUI.update(timeMultiplier);
		this.currentGUI.render();
	}

	private void handleInputs() {
		mouseX = Mouse.getX();
		mouseY = height - Mouse.getY();
		mousePressed = Mouse.isButtonDown(0);

		while (Mouse.next()) {
			if (Mouse.getEventButton() >= 0) {
				boolean pressed = Mouse.getEventButtonState();
				if (pressed && !this.lastPressed)
					this.currentGUI.onClick();
				if (!pressed && this.lastPressed)
					this.currentGUI.onUnClick();
				this.lastPressed = pressed;
			}
		}
		while (Keyboard.next()) {
			boolean pressed = Keyboard.getEventKeyState();
			if (pressed)
				this.currentGUI.onKeyPressed(Keyboard.getEventCharacter());
			else
				this.currentGUI.onKeyReleased(Keyboard.getEventCharacter());
		}
	}

	public void changeGUI(String name) {
		if (this.currentGUI != null)
			this.currentGUI.close();
		switch (name) {
		case "GAME":
			currentGUI = new Game();
			break;
		case "MENU":
			currentGUI = new Menu();
			break;
		case "SCORE":
			currentGUI = new Score();
			break;
		}
	}

	public static void main(String[] args) {
		theMain.start();
	}
}
