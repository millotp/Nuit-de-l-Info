package game;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

import org.lwjgl.opengl.GL11;

import util.VecPolar;

public class Obstacle {
	VecPolar pos;
	VecPolar size;

	public Obstacle(VecPolar pos, VecPolar size) {
		this.pos = pos;
		this.size = size;
	}

	public void render() {
		GL11.glBegin(GL11.GL_POLYGON);
		GL11.glVertex2d(pos.r * cos(pos.a), pos.r * sin(pos.a));
		GL11.glVertex2d((pos.r + size.r) * cos(pos.a), (pos.r + size.r) * sin(pos.a));

	}

	public void update() {

	}
}
