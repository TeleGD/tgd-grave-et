package verticalPlateformer.plateforme;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import verticalPlateformer.Player;

@SuppressWarnings("serial")
public class PlateformeMouvante extends Plateforme {
	private float speed;
	private float temps;
	private boolean sens;

	public PlateformeMouvante(float posx,float posy,float longueur,float epaisseur,boolean sens, Player p) {
		super(posx,posy,longueur,epaisseur,sens,p,0);
		this.temps = 3000;
		this.speed = .2f;
		this.sens = sens;
	}

	public void update (GameContainer container, StateBasedGame game, int delta) {
		this.temps -= delta;
		if (this.sens) {
			if (temps < 0) {
				this.speed = -this.speed;
				this.temps = 3000;
			}
			this.setX(this.getX() + this.speed * delta);
		} else {
			if (temps < 0) {
				this.speed = -this.speed;
				this.temps = 3000;
			}
			this.setY(this.getY() + this.speed * delta);
		}
	}

	public float getSpeedX() {
		return this.sens ? speed : 0;
	}

	public float getSpeedY() {
		return this.sens ? 0 : speed;
	}

}
