package verticalPlateformer.plateforme;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;


public class PlateformeGen {
	private ArrayList<PlateformeClassique> plateformes;
	private Random r;
	
	public PlateformeGen(ArrayList<PlateformeClassique> plateformes) {
		this.plateformes=plateformes;
	}
	
	public void createPlateforme(GameContainer container) {
		plateformes.add(new PlateformeClassique(r.nextInt(container.getWidth()),r.nextInt(container.getHeight()),10,200,r.nextBoolean()));
	}
}
