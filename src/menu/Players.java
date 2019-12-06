package menu;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static util.Global.endRequest;
import static util.Global.height;
import static util.Global.width;

import org.lwjgl.opengl.GL11;

import game.Ball;
import gui.GUI;
import util.Image;
import util.Renderer;

public class Players extends GUI {
		
	private int widthButton = 200;
	private int  heightButton = 30;
	private int buttonGapHeight= 70;
	private int buttonGapWidth = 20;
	private Image[] imagePlayers;
	
	public Players(){
		super();
		this.imagePlayers = new Image[3];
		addButton((width - widthButton) / 2 - widthButton, height/3, widthButton, heightButton, "Yellow Ghost", "GAME");
		this.imagePlayers[0] = new Image("meta/ghost.png");
		addButton((width - widthButton) / 2, height/3, widthButton, heightButton, "Red Ghost", "GAME");
		this.imagePlayers[1] = new Image("meta/red_ghost.png");
		addButton((width - widthButton) / 2 + widthButton, height/3, widthButton, heightButton, "Blue Ghost", "GAME");
		this.imagePlayers[2] = new Image("meta/ghost_blue.png");

		
	}
	
	public void render(){
		super.render();
		GL11.glPushMatrix();

		//affichage des tetes des joueurs
		this.imagePlayers[0].bind();
		Renderer.ellipse(width / 2 - widthButton,height/3 - 50, 50, 50, 0.5);
		this.imagePlayers[0].unbind();
		this.imagePlayers[1].bind();
		Renderer.ellipse(width / 2,  height/3 - 50, 50, 50, 0.5);
		this.imagePlayers[1].unbind();
		this.imagePlayers[2].bind();
		Renderer.ellipse(width / 2 + widthButton,height/3 - 50, 50, 50, 0.5);
		this.imagePlayers[2].unbind();
		
		GL11.glPopMatrix();
	}
	
	@Override
	public void onClick(){
		super.onClick();
		switch(this.pressedButton){
			case 0:
				Ball.picture = "ghost.png";
				break;
			case 1:
				Ball.picture = "red_ghost.png";
				break;
			case 2:
				Ball.picture = "ghost_blue.png";
				break;
		}
			
	}
	
	
	
	
}
