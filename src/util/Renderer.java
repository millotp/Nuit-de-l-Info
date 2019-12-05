package util;

import org.lwjgl.opengl.GL11;

public class Renderer {
	
	public static void ellipse(double x, double y, double w, double h) {
		GL11.glBegin(GL11.GL_POLYGON);
		for (int i=0; i < 500; i++) {
			double angle = Math.PI * 2 * (i/500.0);
			GL11.glVertex2d(x + w/2*Math.cos(angle), y + h/2*Math.sin(angle));
		}
		GL11.glEnd();
	}

}
