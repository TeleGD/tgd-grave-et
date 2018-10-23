package haxBall;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import com.sun.javafx.geom.Ellipse2D;
import org.newdawn.slick.Input;

public class Ball {
	private int posx;
	private int posy;
	private float vitx;
	private float vity;
	private int rad;
	private Color color;
	private boolean colliding;
	private int r_origx;
	private int r_origy;
	private int r_larg;
	private int r_haut;
	private Circle hitbox;
	private int pointsJ1;
	private int pointsJ2;
	private Field field;
	private Player player;
	private float  speed;
	
	public Ball(int haut,int larg,int origx,int origy, Field field){ 
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
		this.field = field;
		this.player = null;
		this.colliding = false;
		this.speed = 1.0f;
		
		hitbox=new Circle(posx+rad/2, posy+rad/2, rad/2);	
	}
		
	
	public void update(GameContainer container, StateBasedGame game, int delta) {
		//on update la position
		int oldPosX = posx;
		int oldPosY = posy;
		
		if(!colliding) {
			//c'est pas beau mais si on fait pas ça les arrondis sont diff en négatif et en positif du coup si la balle a une vitesse negative elle met plus longtemps à s'arrêter sur cette composante...
			int newVitX = (int)(vitx*delta);
			if(vitx<0) {
				newVitX = -(int)(-vitx*delta);
			}
			int newVitY = (int)(vity*delta);
			if(vity<0) {
				newVitY = -(int)(-vity*delta);
			}
			
			posx+=newVitX;
			posy+=newVitY;
			
			vitx=vitx*0.97f;
			vity=vity*0.97f;
			//on regarde si la balle sort du terrain
			bordersCollision(oldPosX, oldPosY);
			
		}
		
		//si elle est en collision avec un joueur elle le suit
		if(colliding) {
			vitx = player.getSpeedX();
			vity = player.getSpeedY();
			
			posx+=vitx*delta;
			posy+=vity*delta;
			
			bordersCollision(oldPosX, oldPosY);
			
			//on regarde si on est toujours en collision avec le joueur
			updateShape();
			colliding = !(Math.sqrt(Math.pow(hitbox.getCenterX() - player.getShape().getCenterX(),2) + Math.pow(hitbox.getCenterY() - player.getShape().getCenterY(),2) ) > (hitbox.getRadius() + player.getShape().getRadius()+5) );
		}
		
		for (Player p : field.getPlayers()) {
			updateShape();
			if(hitbox.intersects(p.getShape())) { //si on a une collision avec un nouveau joueur
				if(p.getID()>1) {
					collideWithPlayer(p);
					shoot(p);
					bordersCollision(oldPosX, oldPosY);
					
				} else {
					if(!(colliding && p.equals(player))) {
						colliding = true;
						player = p;
						
						vitx = player.getSpeedX();
						vity = player.getSpeedY();
						
						posx+=vitx*delta;
						posy+=vity*delta;
					}
					
					collideWithPlayer(player);
					bordersCollision(oldPosX, oldPosY);
				}
			}
			
			if(p.isShooting() && colliding && player.equals(p)) {
				updateShape();
				shoot(player);
			}
		}
		
		updateShape();
	}
	
	private void updateShape() {
		hitbox.setLocation(posx,posy);
		hitbox.setRadius(rad/2);
	}
	
	private void collideWithPlayer(Player p) {
		//on replace la balle au bord du joueur
		//si on est en +-pi/2
		if(hitbox.getCenterX() == p.getShape().getCenterX()) {
			int signe = 1;
			if(hitbox.getCenterY()-p.getShape().getCenterY()<0) signe = -1;
			posy = (int)(p.getShape().getCenterY() + (hitbox.getRadius() + p.getShape().getRadius() + 1)*signe - hitbox.getRadius());
		
		} else {
			//signe pour les x
			int signe = 1;
			if(hitbox.getCenterX()-p.getShape().getCenterX()<0) signe = -1;
			
			double angle = (-signe)*Math.atan((hitbox.getCenterY() - p.getShape().getCenterY())/(hitbox.getCenterX() - p.getShape().getCenterX())); //angle en radians
			double hyp = hitbox.getRadius() + p.getShape().getRadius() + 1;
			
			posx = (int)(p.getShape().getCenterX() + Math.cos(angle)*hyp*signe - hitbox.getRadius());
			posy = (int)(p.getShape().getCenterY() - hyp*Math.sin(angle) - hitbox.getRadius());
		}
	}
	
	private void shoot(Player p) {
		double tmpSpeed = speed;
		if(p.getID()>1) tmpSpeed = Math.sqrt(Math.pow(vitx, 2)+Math.pow(vity, 2));
		
		double angle = 0;
		double hyp = Math.sqrt(2)*tmpSpeed;
		
		int signeX = 1;
		if(hitbox.getCenterX()-p.getShape().getCenterX()<0) signeX = -1;
		
		//si on est en +-pi/2
		if(hitbox.getCenterX() - p.getShape().getCenterX() == 0) {
			int signe = 1;
			if(hitbox.getCenterY()-p.getShape().getCenterY()>0) signe = -1;
			angle = signe*Math.PI/2;
				
		} else {
			
			angle = (-signeX)*Math.atan((hitbox.getCenterY() - p.getShape().getCenterY())/(hitbox.getCenterX() - p.getShape().getCenterX())); //angle en radians
		}
		
		vitx = (float)(Math.cos(angle)*hyp*signeX);
		vity = (float)(-hyp*Math.sin(angle));
		
		if(p.getID()<=1) colliding = false;
	}
	
	private void bordersCollision(int oldX, int oldY) {
		//s'il y a collision sur le bord de droite
		if (posx+rad> r_origx+r_larg){
			//on regarde s'il y a un but
			if ((posy>r_origy+r_haut*1/3)&&(posy<r_origy+2*r_haut/3)) {
				colliding = false;
				pointsJ1+=1;
				posx=r_origx+r_larg/2-rad/2;
				posy=r_origy+r_haut/2-rad/2;
				vitx=0;
				vity=0;
				System.out.println(pointsJ1);
				System.out.println(pointsJ2);
				
			} else {
				if(!colliding) vitx=-vitx;
				else vitx=0;
				
				posx = oldX;
			}
			
		} else if (posx<r_origx) { //s'il y a collision sur le bord de gauche
			//s'il y a un but
			if ((posy>r_origy+r_haut*1/3)&&(posy<r_origy+2*r_haut/3)) {
				colliding = false;
				pointsJ2+=1;
				posx=r_origx+r_larg/2-rad/2;
				posy=r_origy+r_haut/2-rad/2;
				vitx=0;
				vity=0;
				System.out.println(pointsJ1);
				System.out.println(pointsJ2);
				
			} else {
				if(!colliding) vitx=-vitx;
				else vitx=0;
				
				posx=oldX;
			}
		}
		
		//s'il y a collision avec le bord du bas ou celui du haut
		if (posy+rad>r_origy+r_haut || posy<r_origy){
			if(!colliding) vity=-vity;
			else vity=0;
			
			posy=oldY;	
		}
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics context){
		context.setColor(color);
//		if(colliding) context.setColor(new Color(255,0,0));
		context.fillOval(posx,posy,rad,rad);
		
//		context.setColor(new Color(0,0,255));
//		context.draw(hitbox);
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

