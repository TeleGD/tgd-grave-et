package verticalPlateformer.plateforme;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

@SuppressWarnings("serial")
public class PlateformeMouvante extends Plateforme {
	private float speed;
	private float temps;
	private boolean sens;
	
	public PlateformeMouvante(float posx,float posy,float longueur,float epaisseur,boolean sens) {
		super(posx,posy,longueur,epaisseur,sens);
		this.temps=6000;
		this.speed = (float) 0.1;
		this.sens=sens;
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) {
			this.temps=temps-delta;
			if (sens==true) {
				if (temps>3000) {
					posx+=speed*delta;
				}
				else if (0<temps && temps<=3000 ) {
					posx-=speed*delta;
				}
				else if (temps<=0) {
					this.temps=6000;
				}
			}
			else {
				if (temps>3000) {
					posy+=speed*delta;
				}
				else if (0<temps && temps<=3000 ) {
					posy-=speed*delta;
				}
				else if (temps<=0) {
					this.temps=6000;
				}
			}
	}


}	