package menu;

import static util.Global.width;
import static util.Global.height;

import static util.Renderer.fontMenu;
import static util.Renderer.writeCentered;

import gui.GUI;

public class Menu extends GUI {
	
	private int widthButton = 100;
	private int  heightButton = 30;
	
	public Menu(){
		super();
		
		addButton((width - widthButton) / 2, height/2, widthButton, heightButton, "New Game", "GAME");
		addButton((width - widthButton) / 2, height/2 + 40, widthButton, heightButton, "Quit", "EXIT");
	}
	
	@Override
	public void render(){
		super.render();
		writeCentered(fontMenu, "Menu", width / 2, height/3);
	}
	
	@Override
	public void onClick(){
		super.onClick();
		switch(this.pressedButton){
			case 1:
				System.exit(1);
				break;
		}
			
	}
	
}
