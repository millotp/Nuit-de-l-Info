package util;

import java.awt.Font;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.TextureImpl;

public class Renderer {
	public static TrueTypeFont font16 = new TrueTypeFont(new Font("Arial", Font.BOLD, 16), true);
	public static TrueTypeFont fontMenu = new TrueTypeFont(new Font("Cambrian", Font.BOLD, 20), true);

	public static void setColor(int c) {
		if (c == 0)
			GL11.glColor4f(((c >> 24) & 0xFF) / 255.0f, ((c >> 16) & 0xFF) / 255.0f, ((c >> 8) & 0xFF) / 255.0f, 1);
		else
			GL11.glColor4f(((c >> 24) & 0xFF) / 255.0f, ((c >> 16) & 0xFF) / 255.0f, ((c >> 8) & 0xFF) / 255.0f,
					(c & 0xFF) / 255.0f);
	}

	public static void setMode(int mode) {
		GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, mode);
	}

	public static void rect(double x, double y, double w, double h) {
		if (GL11.glGetInteger(GL11.GL_POLYGON_MODE) == GL11.GL_FILL)
			GL11.glRectd(x, y, x + w, y + h);
		else {
			GL11.glBegin(GL11.GL_QUADS);
			GL11.glVertex2d(x, y);
			GL11.glVertex2d(x + w, y);
			GL11.glVertex2d(x + w, y + h);
			GL11.glVertex2d(x - 1, y + h);
			GL11.glEnd();
		}
	}

	public static void ellipse(double x, double y, double w, double h) {
		GL11.glBegin(GL11.GL_POLYGON);
		for (int i = 0; i < 500; i++) {
			double angle = Math.PI * 2 * (i / 500.0);
			GL11.glVertex2d(x + w / 2 * Math.cos(angle), y + h / 2 * Math.sin(angle));
		}
		GL11.glEnd();
	}

	public static void write(TrueTypeFont f, String word, double x, double y, int col) {
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
		TextureImpl.bindNone();
		Color.white.bind();
		f.drawString((float)x, (float)y, word, new Color((col >> 16) & 0xFF, (col >> 8) & 0xFF, col & 0xFF));
		GL11.glDisable(GL11.GL_TEXTURE_2D);
	}

	public static void write(TrueTypeFont f, String word, double x, double y) {
		write(f, word, x, y, 0);
	}

	public static void writeCentered(TrueTypeFont f, String word, double x, double y, int col) {
		int wid = f.getWidth(word);
		int hei = f.getHeight(word);
		write(f, word, x - wid / 2, y - hei / 2, col);
	}

	public static void writeCentered(TrueTypeFont f, String word, double x, double y) {
		writeCentered(f, word, x, y, 0);
	}
}
