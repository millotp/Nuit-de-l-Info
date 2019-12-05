package game;

import static util.Global.height;
import static util.Global.width;

import org.lwjgl.opengl.GL11;

import util.Image;
import util.Renderer;

public class World {

	private Image ceilTexture;
	private Image bkgTexture;

	private int worldId;
	private double rotationSpeed;
	private double angle;
	private String theme;

	public World(double rotationSpeed, String groundTexture, String bkgTxt, String theme) {
		this.worldId = 0; // TODO
		this.rotationSpeed = rotationSpeed;

		this.bkgTexture = new Image(bkgTxt);
		this.ceilTexture = new Image(groundTexture);
		this.theme = theme;
		this.angle = 0;
	}

	public String getTheme() {
		return theme;
	}
	public void render() {

		bkgTexture.render(0, 0, width, height);

		GL11.glPushMatrix();

		this.ceilTexture.bind();
		// GL11.glColor3d(0, 0, 0);
		GL11.glTranslated(width / 2, height / 2, 0);
		GL11.glRotated(angle, 0, 0, 1);
		Renderer.ellipse(0, 0, width, width, 0.41);
		this.ceilTexture.unbind();

		GL11.glPopMatrix();

		GL11.glPushMatrix();

		this.bkgTexture.bind();
		// GL11.glColor3d(0, 0, 0);
		GL11.glTranslated(width / 2, height / 2, 0);
		Renderer.ellipse(0, 0, 7*width/8, 7*width/8, 0.43);
		this.bkgTexture.unbind();

		GL11.glPopMatrix();

		GL11.glPushMatrix();
		
		this.ceilTexture.bind();
		// GL11.glColor3d(0, 0, 0);
		GL11.glTranslated(width / 2, height / 2, 0);
		GL11.glRotated(angle, 0, 0, 1);
		Renderer.ellipse(0, 0, width/8, width/8, 0.5/8);
		this.ceilTexture.unbind();

		GL11.glPopMatrix();
	}

	public void update() {

		angle += 0.1 * this.rotationSpeed;

	}

}
