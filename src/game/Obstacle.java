package game;

import static java.lang.Math.*;
import static java.lang.Math.sin;
import static util.Global.*;
import static util.Renderer.*;

import org.lwjgl.opengl.GL11;

import util.Renderer;
import util.VecPolar;

public class Obstacle {
	VecPolar pos;
	VecPolar size;
	double goalSize;

	boolean isMorphing;
	boolean isPoping;
	double morphSpeed;

	int color;

	public Obstacle(VecPolar pos, VecPolar size) {
		this.pos = pos;
		this.size = size;
		this.color = (int) (Math.random() * 0xFFFFFF) << 8 | 0xFF;
	}

	public void update(double speed) {
		pos.a += speed * Math.PI / 180;
		if (isMorphing) {
			if (isPoping && size.r < goalSize) {
				if (morphSpeed > 0) {
					size.r += morphSpeed;
				}
				if (morphSpeed < 0) {
					pos.r += morphSpeed;
					size.r -= morphSpeed;
				}

				if (size.r >= goalSize) {
					isMorphing = false;
					if (pos.r > width / 6)
						pos.r = 7 * width / 16 - goalSize;
					size.r = goalSize;
				}
			}
			if (!isPoping && size.r > 0) {
				if (morphSpeed < 0) {
					size.r += morphSpeed;
				}
				if (morphSpeed > 0) {
					pos.r += morphSpeed;
					size.r -= morphSpeed;
				}
			}
		}
	}

	public void render() {
		setColor(color);
		Renderer.setMode(GL11.GL_FILL);
		GL11.glBegin(GL11.GL_TRIANGLE_STRIP);
		for (int i = 0; i < 20; i++) {
			double angle = map(i, 0, 20, pos.a, pos.a + size.a);
			GL11.glVertex2d(pos.r * cos(angle), pos.r * sin(angle));
			GL11.glVertex2d((pos.r + size.r) * cos(angle), (pos.r + size.r) * sin(angle));
		}
		GL11.glVertex2d(pos.r * cos(pos.a + size.a), pos.r * sin(pos.a + size.a));
		GL11.glVertex2d((pos.r + size.r) * cos(pos.a + size.a), (pos.r + size.r) * sin(pos.a + size.a));
		GL11.glEnd();
	}

	public boolean collideWith(Ball ball) {
		if (ball.pos.r - ball.radius > pos.r + size.r || ball.pos.r + ball.radius < pos.r)
			return false;
		double half_angle = atan(ball.radius / ball.pos.r);

		if (ball.pos.a + half_angle < pos.a % (2 * Math.PI)
				|| ball.pos.a - half_angle > (pos.a + size.a) % (2 * Math.PI))
			return false;
		return true;
	}
}
