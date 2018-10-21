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
	private float vitx;
	private float vity;
	private int rad;
	private Color color;
	private boolean contact;
	private int r_origx;
	private int r_origy;
	private int r_larg;
	private int r_haut;
	
	public Ball(int haut,int larg,int origx,int origy){
		
		posx=origx+larg/2-rad/2;
		posy=origy+haut/2-rad/2;
		vitx=0;
		vity=0;
		rad=haut/30;
		color=Color.white;
		r_origx=origx;
		r_origy=origy;
		r_larg=larg;
		r_haut=haut;
		
		
		}
		
	
	public void update(GameContainer container, StateBasedGame game, int delta) {
		posx+=vitx*delta;
		vitx=vitx*99/100;
		vity=vity*99/100;
		if (posx+rad> r_origx+r_larg){
			vitx=-vitx;
		}
		if (posx<r_origx) {
			vitx=-vitx;
		}
		if ((posy+rad>r_origy+r_haut)||(posy>r_origy)){
			vity=-vity;
		}
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics context){
		context.setColor(color);
		context.fillOval(posx,posy,rad,rad);
	}
	

	public void keyPressed(int key,char c) {
		if(key==Input.KEY_SPACE) {
			vitx=1;
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

