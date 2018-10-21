package verticalPlateformer.plateforme;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import verticalPlateformer.World;


public class PlateformeGen {
	private World world;
	private Random r;
	private int compt;
	private int timer;
	
	public PlateformeGen(World w) {
		world = w;
		r = new Random();
		timer = 0;
		compt = r.nextInt(6);;
	}
	
	
	public void createPlateforme(GameContainer container, boolean classique, boolean horizontale) {
		if (horizontale) {
			if (classique) {
				world.addPlateforme(new PlateformeClassique(r.nextInt(container.getWidth()-400)+100,200,10,200,horizontale));
			} else {
				world.addPlateforme(new PlateformeMouvante(r.nextInt(container.getWidth()-800)+200,200,10,200,horizontale));
			}
		} else {
			if (classique) {
				world.addPlateforme(new PlateformeClassique(r.nextInt(2)*(container.getWidth()-200)+100,200,10,200,horizontale));
			} else {
				world.addPlateforme(new PlateformeMouvante(r.nextInt(2)*(container.getWidth()-200)+100,200,10,200,horizontale));
			}
		}
		
	}

	public void update(GameContainer container, StateBasedGame game, int delta) {
		if (timer <= 0) {
			if (compt==0) {
				createPlateforme(container, r.nextBoolean(), true);	
				compt = r.nextInt(6);
			} else {
				createPlateforme(container, r.nextBoolean(), false);
				compt--;
			}
			timer = 2000;
		} else {
			timer -= delta;
		}
	}
	
}
