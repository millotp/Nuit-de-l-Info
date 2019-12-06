package game;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

import static util.Global.*;
import static util.Renderer.fontMenu;
import static util.Renderer.writeCentered;

import org.lwjgl.opengl.GL11;

import util.Image;
import util.Renderer;
import util.VecPolar;

public class Ball {

	public VecPolar pos;
	public VecPolar speed;
	public double radius;
	private Image texture;
	public boolean isDead;


	public Ball(double r, double a, double radius) {
		pos = new VecPolar(r, a);
		speed = new VecPolar(3, 0);
		this.radius = radius;
		this.texture = new Image("meta/ball.jpg");
	}

	public void render() {

		GL11.glPushMatrix();

		GL11.glTranslated(width / 2, height / 2, 0);

		this.texture.bind();
		if(isDead)
			GL11.glColor3d(1, 0, 0);
		Renderer.ellipse(pos.r * cos(pos.a), pos.r * sin(pos.a), 2 * this.radius, 2 * this.radius, 0.5);
		this.texture.unbind();
		GL11.glPopMatrix();

	}

	public void update() {
		if (this.pos.r - this.radius < width/6 || this.pos.r + this.radius > 7*width/16){
			this.speed.r *= -1;
		}
		this.pos.r += this.speed.r;
		
		
		
	}

}
