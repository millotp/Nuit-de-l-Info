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
		addButton((width - widthButton) / 2 - widthButton, height/3, widthButton, heightButton, "Roland Groz", "GAME");
		this.imagePlayers[0] = new Image("meta/ball.jpg");
		addButton((width - widthButton) / 2, height/3, widthButton, heightButton, "Jean Roch", "GAME");
		this.imagePlayers[1] = new Image("meta/jlr.png");
		addButton((width - widthButton) / 2 + widthButton, height/3, widthButton, heightButton, "Giroud", "GAME");
		this.imagePlayers[2] = new Image("meta/giroud.jpg");

		
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
				Ball.picture = "ball.jpg";
				break;
			case 1:
				Ball.picture = "jlr.png";
				break;
			case 2:
				Ball.picture = "giroud.jpg";
				break;
		}
			
	}
	
	
	
	
}
