package verticalPlateformer.plateforme;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import verticalPlateformer.Player;

@SuppressWarnings("serial")
public class PlateformeClassique extends Plateforme {
	
	public PlateformeClassique(float posx,float posy,float longueur,float epaisseur,boolean sens, Player p) {
		super(posx,posy,longueur,epaisseur,sens,p);
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) {
	}
	
	public float getSpeedX() {
		return 0;
	}
	
	public float getSpeedY() {
		return 0;
	}
}
