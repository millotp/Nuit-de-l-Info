package menu;

import static util.Global.height;
import static util.Global.width;
import static util.Renderer.fontMenu;
import static util.Renderer.writeCentered;

import gui.GUI;

public class Score extends GUI {

	private String[] bestScore;
	private int lineGap = 30;
	
	public Score(){
		super();
		this.loadBestScore();
		addButton((width - fontMenu.getWidth("Retour Menu")) / 2 , (this.bestScore.length+1) * this.lineGap + height/7, fontMenu.getWidth("Retour Menu"), 30, "Retour Menu", "MENU");

	}
	
	@Override
	public void render(){
		writeCentered(fontMenu, "Highest Scores", width / 2, height/12);
		for (int i = 0 ; i < this.bestScore.length ; i++){
			writeCentered(fontMenu, (i+1) + ". " + (this.bestScore[i] == null ? "" : this.bestScore[i]), width / 2, height/7 + (i+1) * this.lineGap);
		}
		super.render();
	}
	
	public void loadBestScore(){
		// a modifier
		this.bestScore = new String[20];
		for (int i = 0 ; i < this.bestScore.length - 10 ; i++){
			this.bestScore[i] = Integer.toString((int) (Math.random() * 1000));
		}
	}
	
}
