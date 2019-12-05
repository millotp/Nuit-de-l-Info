package util;

import static util.Renderer.setColor;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public class Image {
	public int wid, hei;
	public byte[] data;
	private int texture = -1;
	public boolean changed;
	private ByteBuffer buffer;

	public Image(int w, int h) {
		wid = w;
		hei = h;
		data = new byte[w * h * 4];
		changed = true;
	}

	public Image(BufferedImage img) {
		load(img);
	}

	public Image(String file) {
		try {
			load(ImageIO.read(new File(file)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Image(Image other) {
		this.wid = other.wid;
		this.hei = other.hei;
		this.data = new byte[wid * hei * 4];
		System.arraycopy(other.data, 0, data, 0, data.length);
		changed = true;
	}

	private void load(BufferedImage img) {
		wid = img.getWidth();
		hei = img.getHeight();
		data = new byte[wid * hei * 4];
		for (int i = 0; i < wid; i++)
			for (int j = 0; j < hei; j++) {
				int c = img.getRGB(i, j);
				data[(i + j * wid) * 4] = (byte) (c >> 16 & 0xFF);
				data[(i + j * wid) * 4 + 1] = (byte) (c >> 8 & 0xFF);
				data[(i + j * wid) * 4 + 2] = (byte) (c & 0xFF);
			}
		changed = true;
	}

	public void update(byte[] newData) {
		for (int i = 0; i < wid; i++) {
			for (int j = 0; j < hei; j++) {
				data[(i + j * wid) * 4] = newData[(wid - 1 - i + j * wid) * 4 + 2];
				data[(i + j * wid) * 4 + 1] = newData[(wid - 1 - i + j * wid) * 4 + 1];
				data[(i + j * wid) * 4 + 2] = newData[(wid - 1 - i + j * wid) * 4];
			}
		}
		changed = true;
	}

	public void save(String file) {
		BufferedImage b = new BufferedImage(wid, hei, BufferedImage.TYPE_INT_RGB);

		for (int i = 0; i < wid; i++)
			for (int j = 0; j < hei; j++)
				b.setRGB(i, j,
						data[(i + j * wid) * 4] << 16 | data[(i + j * wid) * 4 + 1] << 8 | data[(i + j * wid) * 4 + 2]);

		try {
			ImageIO.write(b, "png", new File(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void reset() {
		this.data = new byte[data.length];
		changed = true;
	}

	public void render(float x, float y, float w, float h) {
		if (texture == -1 || changed)
			createTexture();
		setColor(0xFFFFFFFF);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2d(0, 0);
		GL11.glVertex2d(x, y);
		GL11.glTexCoord2d(1, 0);
		GL11.glVertex2d(x + w, y);
		GL11.glTexCoord2d(1, 1);
		GL11.glVertex2d(x + w, y + h);
		GL11.glTexCoord2d(0, 1);
		GL11.glVertex2d(x, y + h);
		GL11.glEnd();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
	}

	public void render(float x, float y, float w) {
		this.render(x, y, w, hei * w / wid);
	}

	public void render(float x, float y) {
		this.render(x, y, wid, hei);
	}

	public void createTexture() {
		if (texture == -1) {
			texture = GL11.glGenTextures();
		}
		if (buffer == null)
			buffer = BufferUtils.createByteBuffer(data.length);

		buffer.clear();
		buffer.put(data);
		buffer.flip();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, 4, wid, hei, 0, GL11.GL_RGB, GL11.GL_UNSIGNED_BYTE, buffer);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		changed = false;
	}

	public void release() {
		if (texture != -1)
			GL11.glDeleteTextures(texture);
		if (buffer != null)
			buffer.clear();
	}
}
