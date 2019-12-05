package menu;

import static util.Global.width;
import static util.Global.height;
import static util.Global.endRequest;

import static util.Renderer.fontMenu;
import static util.Renderer.writeCentered;

import gui.GUI;

public class Menu extends GUI {
	
	private int widthButton = 120;
	private int  heightButton = 30;
	private int buttonGap= 40;
	private int lastScore = -1;
	
	public Menu(){
		super();
		addButton((width - widthButton) / 2, height/2 + this.buttons.size()*this.buttonGap, widthButton, heightButton, "New Game", "GAME");
		addButton((width - widthButton) / 2, height/2 + this.buttons.size()*this.buttonGap, widthButton, heightButton, "Highest Score", "SCORE");
		addButton((width - widthButton) / 2, height/2 + this.buttons.size()*this.buttonGap, widthButton, heightButton, "Quit", "EXIT");
	}
	
	public Menu(int lastScore){
		this();
		this.lastScore = lastScore;
	}
	
	@Override
	public void render(){
		super.render();
		writeCentered(fontMenu, "Menu", width / 2, height/3);
		if (lastScore != -1){
			writeCentered(fontMenu, "Dernier Score : " + this.lastScore, width / 2, height/3 + this.buttonGap);
		}
	}
	
	@Override
	public void onClick(){
		super.onClick();
		switch(this.pressedButton){
			case 2:
				endRequest = true;
				break;
		}
			
	}
	
}
