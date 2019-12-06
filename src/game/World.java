package game;

import static util.Global.height;
import static util.Global.width;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import gui.Game;
import util.Image;
import util.Renderer;
import util.VecPolar;

public class World {

	private Image ceilTexture;
	private Image bkgTexture;

	private double rotationSpeed;
	private double angle;
	private String theme;
	
	private static double gateAngle = 3 * Math.PI / 4;

	private ArrayList<Obstacle> obstacles;

	public World(double rotationSpeed, String groundTexture, String bkgTxt, String theme) {

		this.rotationSpeed = rotationSpeed;

		this.bkgTexture = new Image(bkgTxt);
		this.ceilTexture = new Image(groundTexture);
		this.theme = theme;
		this.angle = 0;

		this.obstacles = new ArrayList<Obstacle>();
		this.obstacles.add(new Obstacle(new VecPolar(50, 0), new VecPolar(130, 1)));
		this.obstacles.add(new Obstacle(new VecPolar(280, 2), new VecPolar(70, 0.5)));
	}

	public String getTheme() {
		return theme;
	}

	public void render() {

		bkgTexture.render(0, 0, width, height);

		GL11.glPushMatrix();
		GL11.glTranslated(width / 2, height / 2, 0);
		GL11.glRotated(angle, 0, 0, 1);

		this.ceilTexture.bind();
		Renderer.ellipse(0, 0, width, width, 0.5);
		GL11.glRotated(-angle, 0, 0, 1);
		this.bkgTexture.bind();
		Renderer.ellipse(0, 0, 7 * width / 8, 7 * width / 8, .25);

		this.bkgTexture.unbind();
		for (Obstacle o : this.obstacles) {
			o.render();
		}

		GL11.glRotated(angle, 0, 0, 1);
		this.ceilTexture.bind();
		Renderer.ellipse(0, 0, width / 3, width / 3, 0.5 / 3);
		this.ceilTexture.unbind();

		GL11.glPopMatrix();
	}

	public boolean collide(Ball ball) {
		for (Obstacle o : obstacles) {
			if (o.collideWith(ball))
				return true;
		}
		return false;
	}

	public void update() {
		angle += 0.1 * this.rotationSpeed;
		for (Obstacle o : this.obstacles) {
			o.update(0.1 * this.rotationSpeed);
		}
	}
	
	public void morph(Game game) {
		for(Obstacle o : obstacles) {
			if(o.pos.a + o.size.a >= gateAngle && !o.isMorphing) {
				if (o.scored  == false) {
				    o.scored = true;
				    game.setBoom(o.scored);
				}

				o.isMorphing = true;
				if(o.pos.r > width / 6)
					o.morphSpeed = 1;
				else
					o.morphSpeed = -1;
			}
			if(o.size.r <= 0) {
				
				o.isMorphing = false;
				
			}
		}
	}
	

}
