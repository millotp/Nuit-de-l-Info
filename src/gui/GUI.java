package gui;

import static util.Renderer.font16;

import java.util.ArrayList;

public abstract class GUI {
	protected ArrayList<Clickable> buttons;
	protected int pressedButton;

	public GUI() {
		this.buttons = new ArrayList<Clickable>();
	}

	public void render() {
		for (Clickable b : this.buttons)
			b.render();
	}
	
	public void update(double timeMultiplier) {
		for (Clickable b : this.buttons)
			b.update();
	}

	protected Button addButton(float x, float y, float h, String text) {
		return addButton(x, y, (font16.getWidth(text) + 15), h, text, null);
	}

	protected Button addButton(float x, float y, float h, String text, String red) {
		return addButton(x, y, font16.getWidth(text) + 15, h, text, red);
	}

	protected Button addButton(float x, float y, float w, float h, String text, String red) {
		Button b = new Button(this.buttons.size(), x, y, w, h, text, red);
		this.buttons.add(b);
		return b;
	}

	public Clickable getClickble(int id) {
		return this.buttons.get(id);
	}

	public void onClick() {
		for (Clickable b : this.buttons) {
			if (b.hovered) {
				this.pressedButton = b.id;
				b.setPressed(true);
				return;
			}
		}
		this.pressedButton = -1;
	}

	public void onUnClick() {
		this.unPressAll();
	}

	protected void unPressAll() {
		for (Clickable b : this.buttons)
			b.setPressed(false);
	}

	protected int getSelectedButton() {
		for (Clickable b : this.buttons)
			if (b.pressed)
				return b.id;
		return -1;
	}

	public void onKeyPressed(int key) {
	}

	public void onKeyReleased(int key) {
	}

	public void wheelEvent(int amount) {
		for (Clickable b : this.buttons) {
			b.wheel(amount);
		}
	}

	public void close() {
	}
}
