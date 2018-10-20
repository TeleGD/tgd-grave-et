package haxBall;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.Input;

public class Ball {
	private int posx;
	private int posy;
	private int vitx;
	private int vity;
	private int rad;
	private Color color;
	private boolean contact;
	private boolean space;
	
	public Ball(int haut,int larg,int origx,int origy){
		
		posx=origx+larg/2-rad/2;
		posy=origy+haut/2-rad/2;
		vitx=0;
		vity=0;
		rad=haut/30;
		color=Color.white;
		
		}
		
	
	public void update(GameContainer container, StateBasedGame game, int delta) {
		posx+=vitx*delta;
		
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics context){
		context.setColor(color);
		context.fillOval(posx,posy,rad,rad);
	}
	

	public void keyPressed(int key,char c) {
		if(key==Input.KEY_SPACE) {
			vitx=1;
			System.out.println("salut");
		}
	}
	
	public void setPosX(int posx) {
		this.posx = posx;
	}
	
	public void setPosY(int posy) {
		this.posy = posy;
	}
	
	public void setRad(int r) {
		rad = r;
	}
	
	public int getRad() {
		return rad;
	}
}

