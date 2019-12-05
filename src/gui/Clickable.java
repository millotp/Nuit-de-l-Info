package gui;

import static util.Global.mouseIn;

import util.Vec2;

public abstract class Clickable {
	protected Vec2 pos, size;
	public int id;
	protected boolean pressed;
	protected boolean hovered;

	public Clickable(int id, double x, double y, double w, double h) {
		this.id = id;
		this.pos = new Vec2(x, y);
		this.size = new Vec2(w, h);
	}

	public void update() {
		this.hovered = mouseHover();
	}

	public abstract void render();
	
	public void setPressed(boolean state) {
		this.pressed = state;
	}

	public boolean mouseHover() {
		return mouseIn(this.pos.x, this.pos.y, this.pos.x + this.size.x, this.pos.y + this.size.y);
	}

	public void wheel(int amount) {
	}
}
