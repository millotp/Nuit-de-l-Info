package game;

import static util.Global.height;
import static util.Global.width;

import org.lwjgl.opengl.GL11;

import util.Image;
import util.Renderer;
import util.Vec2;

public class Ball {
	
	private Vec2 pos;
	private Vec2 speed;
	private double radius;
	private Image texture;
	
	
	public Ball (double x, double y, double radius) {
		pos = new Vec2(x, y);
		speed = new Vec2(3, 0);
		this.radius = radius;
		
		this.texture = new Image("meta/ball.jpg");
	}
	
	public void render() {
		
		GL11.glPushMatrix();
		
		this.texture.bind();
		// GL11.glColor3d(0, 0, 0);
		Renderer.ellipse(this.pos.x, this.pos.y, 2*this.radius, 2*this.radius, 0.5);
		this.texture.unbind();
	}
	
	private void bords() {
		if(this.pos.x - this.radius < 0) {
			this.pos.x = 0 + this.radius;
			this.speed.x *= -1;
		}
		
		if (this.pos.x + this.radius > 400) {
			this.pos.x = 400 - this.radius;
			this.speed.x *= -1;
		}
	}
	
	public void update() {
		
		/*
		this.pos.add(this.speed);
		this.bords();
		*/
	}
	

}
