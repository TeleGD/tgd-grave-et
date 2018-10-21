package haxBall;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import com.sun.javafx.geom.Ellipse2D;
import com.sun.javafx.geom.Shape;

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
	private Shape hitbox;
	private int pointsJ1;
	private int pointsJ2;
	
	public Ball(int haut,int larg,int origx,int origy){ 
		r_origx=origx;
		r_origy=origy;
		r_larg=larg;
		r_haut=haut;
		rad=haut/30;
		posx=r_origx+r_larg/2-rad/2;
		posy=r_origy+r_haut/2-rad/2;
		vitx=0;
		vity=0;
		color=Color.white;
		pointsJ1=0;
		pointsJ2=0;
		
		Circle hitbox=new Circle(posx+rad/2, posy+rad/2, rad/2, rad/2);
		
		}
		
	
	public void update(GameContainer container, StateBasedGame game, int delta) {
		posx+=vitx*delta;
		posy+=vity*delta;
		vitx=vitx*99/100;
		vity=vity*99/100;
		if (posx+rad> r_origx+r_larg){
			if ((posy>r_origy+r_haut*1/3)&&(posy<r_origy+2*r_haut/3)) {
				pointsJ1+=1;
				posx=r_origx+r_larg/2-rad/2;
				posy=r_origy+r_haut/2-rad/2;
				vitx=0;
				vity=0;
				System.out.println(pointsJ1);
				System.out.println(pointsJ2);
			}
			vitx=-vitx;
			//posx=r_origx+r_larg; marche sans mais problèmes avec
		}
		if (posx<r_origx) {
			vitx=-vitx;
			posx=r_origx;
			if ((posy>r_origy+r_haut*1/3)&&(posy<r_origy+2*r_haut/3)) {
				pointsJ2+=1;
				posx=r_origx+r_larg/2-rad/2;
				posy=r_origy+r_haut/2-rad/2;
				vitx=0;
				vity=0;
				System.out.println(pointsJ1);
				System.out.println(pointsJ2);
			}
		}
		if (posy+rad>r_origy+r_haut){
			vity=-vity;
			//posy=r_origy+r_haut; marche sans mais problèmes avec
		}
		if (posy<r_origy) {
			vity=-vity;
			posy=r_origy;
			
		}
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics context){
		context.setColor(color);
		context.fillOval(posx,posy,rad,rad);
	}
	

	public void keyPressed(int key,char c) {
		if(key==Input.KEY_SPACE) {
			vitx=1;
			vity=1;
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

