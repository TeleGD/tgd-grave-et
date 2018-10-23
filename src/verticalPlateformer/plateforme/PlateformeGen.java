package verticalPlateformer.plateforme;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import verticalPlateformer.Player;
import verticalPlateformer.World;


public class PlateformeGen {
	private World world;
	private Random r;
	private int compt;
	private int timer;
	private int totalCompt;
	private Player p;
	
	public PlateformeGen(World w, Player p) {
		world = w;
		r = new Random();
		timer = 0;
		compt = r.nextInt(6);
		totalCompt=0;
		this.p=p;
	}
	
	public void createPlateforme(GameContainer container, boolean classique, boolean horizontale) {
		if (horizontale) {
			if (classique) {
				world.addPlateforme(new PlateformeClassique(r.nextInt(container.getWidth()-400)+100,container.getHeight()/2-300*totalCompt,200,30,horizontale,p));
			} else {
				world.addPlateforme(new PlateformeMouvante(r.nextInt(container.getWidth()-800)+200,container.getHeight()/2-300*totalCompt,200,30,horizontale,p));
			}
		} else {
			if (classique) {
				world.addPlateforme(new PlateformeClassique(r.nextInt(2)*(container.getWidth()-100)+50,container.getHeight()/2-300*totalCompt,200,30,horizontale,p));
			} else {
				totalCompt++;
				world.addPlateforme(new PlateformeMouvante(r.nextInt(2)*(container.getWidth()-100)+50,container.getHeight()/2-300*totalCompt,200,30,horizontale,p));
			}
		}
	}

	public void update(GameContainer container, StateBasedGame game, int delta) {
		if (timer <= 0) {
			totalCompt+=1;
			if (compt==0) {
				createPlateforme(container, r.nextInt(4)>0, true);	
				compt = r.nextInt(6);
			} else {
				createPlateforme(container, r.nextInt(4)>0, false);
				compt--;
			}
			timer = 1200;
		} else {
			timer -= delta;
		}
	}
	
}
