package graveEt;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class DecorationGen {
	private World world;
	private Random r;
	private static ArrayList<Image> decorations = new ArrayList<>();
	private ArrayList<Player> players;
	private int genCount;

	static {
		try {
			decorations.add(new Image("images/Tree.png"));
			decorations.add(new Image("images/Skeleton.png"));
			decorations.add(new Image("images/DeadBush.png"));
			for (int i=1; i<=24; i++) {
				decorations.add(new Image("images/TombStone"+i+".png"));
			}
		} catch(SlickException e) {
			e.printStackTrace();
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
