package verticalPlateformer.plateforme;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import verticalPlateformer.Player;

public class PlateformeClassique extends Plateforme {
	
	public PlateformeClassique(float posx,float posy,float longueur,float epaisseur,boolean sens) {
		super(posx,posy,longueur,epaisseur,sens);
		
	}
	public void update(GameContainer container, StateBasedGame game, int delta) {
		
	}
	

	public void collideWithPlayer(Player player) {
		
	}
	
	public float getSpeedX() {
		return 0;
		
	}
	
	public float getSpeedY() {
		return 0;
	}
}
