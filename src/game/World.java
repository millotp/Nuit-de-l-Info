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

	private static double gateEndAngle = 3 * Math.PI / 4;
	private static double gatePopAngle = 3.2 * Math.PI / 4;

	private ArrayList<Obstacle> obstacles;
	private ArrayList<Booster> boosters;
	private boolean curBooster;

	public World(double rotationSpeed, String groundTexture, String bkgTxt, String theme) {

		this.rotationSpeed = rotationSpeed;

		this.bkgTexture = new Image(bkgTxt);
		this.ceilTexture = new Image(groundTexture);
		this.theme = theme;
		this.angle = 0;
		this.boosters = new ArrayList<Booster>();
		if (theme == "moon") {
			boosters.add(new Booster(0, Math.PI, false));
			boosters.add(new Booster(Math.PI, 2*Math.PI, true));			
		}
		else if (theme == "xmas") {
			boosters.add(new Booster(0, Math.PI, true));
			boosters.add(new Booster(Math.PI, 2*Math.PI, false));					
		}
		else {
			boosters.add(new Booster(0, Math.PI, true));
			boosters.add(new Booster(Math.PI, 2*Math.PI, false));				
		}

		
		this.obstacles = new ArrayList<Obstacle>();
		for (int i = 0; i < 4; i++) {
			if (Math.random() < 0.5)
				this.obstacles.add(new Obstacle(new VecPolar(width / 6 , (i/4.0) * Math.PI * 2),
						new VecPolar(width / 16,  0.1 )));
			else
				this.obstacles.add(new Obstacle(new VecPolar(5 * width / 16 + width / 16, (i/4.0) * Math.PI * 2),
						new VecPolar(width / 16,  0.1 )));
		}
	}

	public String getTheme() {
		return theme;
	}
	
	public boolean getCurBooster() {
		return curBooster;
	}

	public void render() {

		bkgTexture.render(0, 0, width, height);

		GL11.glPushMatrix();
		GL11.glTranslated(width / 2, height / 2, 0);
		GL11.glRotated(angle, 0, 0, 1);

		this.ceilTexture.bind();
		Renderer.ellipse(0, 0, width, width, 0.45);
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
		if (angle%(2*Math.PI) < Math.PI) {
			curBooster = boosters.get(0).getBoost();
		}
		else {
			curBooster = boosters.get(1).getBoost();
		}
	
		angle += 0.1 * this.rotationSpeed;
		for (Obstacle o : this.obstacles) {
			o.update(0.1 * this.rotationSpeed);
		}
	}

	public boolean morph(boolean victime, Game game) {
		for (Obstacle o : obstacles) {
			if ((o.pos.a) % (2 * Math.PI) >= gateEndAngle
					&& (o.pos.a) % (2 * Math.PI) <= gatePopAngle && !o.isMorphing) {
				o.isPoping = false;

				game.increaseScore();

				o.isMorphing = true;
				if (o.pos.r > width / 6)
					o.morphSpeed = 20;
				else
					o.morphSpeed = -20;
			}

			if (o.isMorphing && o.size.r <= 0 && (o.pos.a) % (2 * Math.PI) >= gatePopAngle) {
				o.isPoping = true;
				if (!victime) {
					o.size.a = 0.1;
					
					o.goalSize = Math.random() * (7 * width/16 - width/6);
					if (Math.random() < 0.5) { // en bas
						o.morphSpeed = 20;
						o.pos.r = width / 6;
					} else { // en haut
						o.morphSpeed = -20;
						o.pos.r = 7 * width / 16;
					}
				} else {
					
					o.size.a = 0.1;
					
					o.goalSize = ((7 * width/16 - width/6)/2 - 27) * Math.random();
					if (Math.random() < 0.5) { // en bas
						o.morphSpeed = 20;
						o.pos.r = width / 6;
					} else { // en haut
						o.morphSpeed = -20;
						o.pos.r = 7 * width / 16;
					}
				}
				return true;

			}
		}
		return false;
	}

}
