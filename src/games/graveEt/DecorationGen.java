package games.graveEt;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import app.AppLoader;

public class DecorationGen {
	private World world;
	private Random r;
	private static ArrayList<Image> decorations = new ArrayList<>();
	private ArrayList<Player> players;
	private int genCount;

	static {
		decorations.add(AppLoader.loadPicture("/images/graveEt/Tree.png"));
		decorations.add(AppLoader.loadPicture("/images/graveEt/Skeleton.png"));
		decorations.add(AppLoader.loadPicture("/images/graveEt/DeadBush.png"));
		for (int i=1; i<=24; i++) {
			decorations.add(AppLoader.loadPicture("/images/graveEt/TombStone"+i+".png"));
		}
	}

	public DecorationGen(World w, ArrayList<Player> players) {
		world = w;
		r = new Random();
		genCount=-3*world.getHeight();
		this.players=players;

		while (genCount < world.getHeight()) {
			genCount+=r.nextInt(100);
			world.addDecoration(new Decoration(r.nextInt(world.getWidth()-50),-genCount , decorations.get(r.nextInt(decorations.size()))));
		}
	}

	public void update(GameContainer container, StateBasedGame game, int delta) {
		int maxHighScore = 0;
		for (Player p: players) {
			maxHighScore=Math.max(maxHighScore, p.getScore());
		}
		if (genCount<maxHighScore+2000) {
			genCount+=r.nextInt(100);
			world.addDecoration(new Decoration(r.nextInt(container.getWidth()-50),-genCount , decorations.get(r.nextInt(decorations.size()))));
		}
	}

}
