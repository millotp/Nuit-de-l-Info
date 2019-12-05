package gui;

import static util.Global.mouseIn;
import static util.Renderer.*;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.TrueTypeFont;

import main.Main;

public class Button extends Clickable {
	public String text;
	public int colorUnpressed, colorHover;
	public int colorPressed, colorCountour;
	public boolean centerMode;
	private TrueTypeFont font;
	private String redirection;

	public Button(int id, double x, double y, double w, double h, String t, String red) {
		super(id, x, y, w, h);
		this.text = t;
		this.colorUnpressed = 0xD6D6D6FF;
		this.colorHover = 0xC2C2C2FF;
		this.colorPressed = 0x96FFFFFF;
		this.colorCountour = 0x000000FF;
		this.redirection = red;

		this.font = font16;
	}

	
	public void render() {
		setMode(GL11.GL_FILL);
		setColor(this.pressed ? this.colorPressed : hovered ? this.colorHover : this.colorUnpressed);
		rect(this.centerMode ? this.pos.x - this.size.x / 2 : this.pos.x, this.pos.y, this.size.x, this.size.y);
		setMode(GL11.GL_LINE);
		setColor(this.colorCountour);
		rect(this.centerMode ? this.pos.x - this.size.x / 2 : this.pos.x, this.pos.y, this.size.x, this.size.y);

		writeCentered(this.font, this.text, this.pos.x + (this.centerMode ? 0 : this.size.x / 2),
				this.pos.y + this.size.y / 2);
	}

	public void setText(String t) {
		this.text = t;
		this.size.x = this.font.getWidth(this.text) + 15;
	}

	public void setColors(int un, int press, int hov) {
		this.colorUnpressed = un;
		this.colorPressed = press;
		this.colorHover = hov;
	}

	public boolean mouseHover() {
		double xx = this.centerMode ? this.pos.x - this.size.x / 2 : this.pos.x;
		return mouseIn(xx, this.pos.y, xx + this.size.x, this.pos.y + this.size.y);
	}

	public void setPressed(boolean state) {
		super.setPressed(state);
		if (!this.pressed && this.hovered && this.redirection != null)
			Main.theMain.changeGUI(this.redirection);
	}
}