package verticalPlateformer.plateforme;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import verticalPlateformer.Player;

public class PlateformeMouvante extends Plateforme {
	private float speedX;
	private float speedY;
	private float temps;
	private boolean sens;
	
	public PlateformeMouvante(float posx,float posy,float longueur,float epaisseur,boolean sens, float speed) {
		super(posx,posy,longueur,epaisseur,sens);
		this.temps=2000;
		this.speedX=(float) 0.5;
		this.speedY=(float) 0.5;
		this.sens=sens;
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) {
			this.temps=temps-delta;
			if (sens==true) {
				if (temps>1000) {
					x+=speedX*delta;
				}
				else if (0<temps && temps<=1000 ) {
					x-=speedX*delta;
				}
				else if (temps==0) {
					this.temps=2000;
				}
			}
			else {
				if (temps>1000) {
					x+=speedY*delta;
				}
				else if (0<temps && temps<=1000 ) {
					x-=speedY*delta;
				}
				else if (temps==0) {
					this.temps=2000;
				}
			}
		}
	
	public void collideWithPlayer(Player player) {
				
	}

	public float getSpeedX() {
		return speedX;
		
	}
	
	public float getSpeedY() {
		return speedY;
	}

}	