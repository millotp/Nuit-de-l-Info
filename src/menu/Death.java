package menu;

import static util.Global.height;
import static util.Global.width;
import static util.Renderer.fontMenu;
import static util.Renderer.writeCentered;

import org.lwjgl.opengl.GL11;

import gui.GUI;
import util.Image;

public class Death extends GUI {

	private Image bkg;
	
	public Death(){
		super();
		this.bkg = new Image("meta/game_over.jpg");
		addButton((width - fontMenu.getWidth("Back to the Menu")) / 2, 2*height/3,
				fontMenu.getWidth("Back to the Menu"), 30, "Back to the Menu", "MENU");
	}
	
	@Override
	public void render(){
		this.bkg.render(0, 0, width, height);
		super.render();
		


	}
}
