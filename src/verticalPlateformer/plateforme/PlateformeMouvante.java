package verticalPlateformer.plateforme;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;
import verticalPlateformer.Player;

@SuppressWarnings("serial")
public class PlateformeMouvante extends Plateforme {
	private float speed;
	private float temps;
	private boolean sens;

	public PlateformeMouvante(float posx,float posy,float longueur,float epaisseur,boolean sens) {
		super(posx,posy,longueur,epaisseur,sens);
		this.temps = 6000;
		this.speed = .12f;
		this.sens = sens;
	}

	public void update (GameContainer container, StateBasedGame game, int delta) {
			this.temps -= delta;
			if (this.sens) {
				if (this.temps > 3000) {
					this.posy += this.speed * delta;
					this.setY (this.posy);
				} else if (temps > 0) {
					this.posy -= this.speed * delta;
					this.setY (this.posy);
				} else  {
					this.temps = 6000;
				}
			} else {
				if (this.temps > 3000) {
					this.posx += this.speed * delta;
					this.setX (this.posx);
				} else if (temps > 0) {
					this.posx -= this.speed * delta;
					this.setX (this.posx);
				} else  {
					this.temps = 6000;
				}
			}
		}

	public void collideWithPlayer(Player player) {

	}

	public float getSpeedX() {
		return this.sens ? 0 : speed;

	}

	public float getSpeedY() {
		return this.sens ? speed : 0;
	}

}
