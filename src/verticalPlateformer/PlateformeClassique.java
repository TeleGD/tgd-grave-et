package verticalPlateformer;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

public class PlateformeClassique extends Plateforme {
	
	private PlateformeClassique(float posx,float posy,float longueur,float epaisseur,boolean sens) {
		super(posx,posy,longueur,epaisseur,sens);
		
	}
	public void update(GameContainer container, StateBasedGame game, int delta) {
		
	}
}
