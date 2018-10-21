package verticalPlateformer.plateforme;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import verticalPlateformer.World;


public class PlateformeGen {
	private World world;
	private Random r;
	
	public PlateformeGen(World w) {
		world=w;
		r=new Random();
	}
	
	
	public void createPlateforme(GameContainer container) {
		world.addPlateforme(new PlateformeMouvante(r.nextInt(container.getWidth()),r.nextInt(container.getHeight()),10,200,r.nextBoolean()));
	}

	public void update(GameContainer container, StateBasedGame game, int delta) {
		// TODO Auto-generated method stub
		if(Math.random()>0.95) {
			createPlateforme(container);
		}
	}
	
}
