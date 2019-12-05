package game;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

import org.lwjgl.opengl.GL11;

import util.Image;
import util.Renderer;
import util.VecPolar;

public class Ball {

	public VecPolar pos;
	public double radius;
	private Image texture;

	public Ball(double r, double a, double radius) {
		pos = new VecPolar(r, a);
		this.radius = radius;

		this.texture = new Image("meta/ball.jpg");
	}

	public void render() {

		GL11.glPushMatrix();

		this.texture.bind();
		Renderer.ellipse(pos.r * cos(pos.a), pos.r * sin(pos.a), 2 * this.radius, 2 * this.radius, 0.5);
		this.texture.unbind();
	}

	public void update() {
	}

}
