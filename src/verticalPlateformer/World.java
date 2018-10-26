package verticalPlateformer;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import verticalPlateformer.pages.DeathPage;
import verticalPlateformer.plateforme.Plateforme;
import verticalPlateformer.plateforme.PlateformeClassique;
import verticalPlateformer.plateforme.PlateformeGen;

public class World extends BasicGameState {

	private ArrayList<Player> players;
	private Interface I;
	private DeathLine line;
	private ArrayList<Plateforme> plateformes;
	private PlateformeGen plateformeGen;
	private List <Bonus> bonuses;
	private BonusGen bonusGen;
	private ArrayList<Decoration> decorations;
	private DecorationGen decorationGen;
	private Color color = new Color(0x001e3514);
	private int height;
	private int width;
	private int ID;
	private int state;

	private static Sound trash;
	private static Music defouloir;
	static {
		try {
			defouloir = new Music("musics/verticalPlateformer/Defouloir.ogg");
			trash = new Sound("sound/verticalPlateformer/trash.ogg");
			new Image("images/verticalPlateformer/rules.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public World (int ID) {
		this.ID = ID;
		this.state = -1;
	}

	@Override
	public int getID () {
		return this.ID;
	}

	@Override
	public void init (GameContainer container, StateBasedGame game) {
		height = container.getHeight ();
		width = container.getWidth();
	}

	@Override
	public void enter (GameContainer container, StateBasedGame game) {
		/* Méthode exécutée à l'apparition de la page */
		if (this.state == 0) {
			this.play (container, game);
		} else if (this.state == 2) {
			this.resume (container, game);
		}
	}

	@Override
	public void leave (GameContainer container, StateBasedGame game) {
		/* Méthode exécutée à la disparition de la page */
		if (this.state == 1) {
			this.pause (container, game);
		} else if (this.state == 3) {
			this.stop (container, game);
		}
	}

	@Override
	public void update (GameContainer container, StateBasedGame game, int delta) {
		/* Méthode exécutée environ 60 fois par seconde */
		Input input = container.getInput ();
		if (input.isKeyDown (Input.KEY_ESCAPE)) {
			this.setState (1);
			game.enterState (3, new FadeOutTransition (), new FadeInTransition ());
		}

		line.update(container, game, delta);
		for(Plateforme p:plateformes) {
			p.update(container, game, delta);
		}
		for (Bonus bonus: this.bonuses) {
			bonus.update (container, game, delta);
		}
		for(int i=plateformes.size()-1;i>=0;i--) {
			if(plateformes.get(i).getPosY()>=this.line.getPosY() || plateformes.get(i).isDestroyed()) {
				plateformes.remove(i);
				trash.play(1, (float) 0.4);
			}
		}
		for (int i = this.bonuses.size () - 1; i >= 0; i--) {
			Bonus bonus = bonuses.get (i);
			if (bonus.isApplied () || bonus.getPosY () >= this.line.getPosY ()) {
				this.bonuses.remove (i);
				trash.play (1, .4f);
			}
		}

		plateformeGen.update(container, game, delta);
		bonusGen.update (container, game, delta);
		decorationGen.update(container, game, delta);

		for(Player player : players) {
			player.update(container, game, delta);
			for (Plateforme plat : plateformes) {
				if(player.getShape().intersects(plat)) {
					if ((player.getGravity() == 0) == plat.getSens()) {
						player.freeze();
						player.setPlateforme(plat);
						// Le joueur s'arrête
					}
					else {
						this.setState (3);
						((DeathPage) game.getState(7)).setScore(player.getScore());
						game.enterState (7, new FadeOutTransition (), new FadeInTransition ());
						// Le joueur meurt
					}
				}
			}
			for (Bonus bonus: this.bonuses) {
				if (player.getShape ().intersects (bonus.getShape ())) {
					bonus.apply (player);
				}
			}
		}

		for (Player player : players) {
			if (player.getPosY() > line.getPosY() || player.getPosX()+player.getWidth()<0 || player.getPosX()>container.getWidth()) {
				// TODO : à changer si on met plusieurs joueurs
				this.setState (3);
				((DeathPage) game.getState(7)).setScore(player.getScore());
				game.enterState (7, new FadeOutTransition (), new FadeInTransition ());
			}
		}
	}

	@Override
	public void render (GameContainer container, StateBasedGame game, Graphics context) {
		/* Méthode exécutée environ 60 fois par seconde */
		
		context.setColor(color);
		context.fillRect(0, 0, container.getWidth(), container.getHeight());
		context.setColor(Color.white);

		for(Decoration d: decorations) {
			d.render(container, game, context, players.get(0).getPosY ());
		}

		for (Bonus bonus: this.bonuses) {
			bonus.render (container, game, context);
		}

		for(Plateforme p:plateformes) {
			p.render(container, game, context, players.get(0).getPosY ());
		}

		for(Player player : players) {
			player.render(container, game, context);
		}

		I.render(container,game,context);
		line.render (container, game, context, players.get(0).getPosY ());
	}

	public void play (GameContainer container, StateBasedGame game) {
		/* Méthode exécutée une unique fois au début du jeu */
		defouloir.loop(1, (float) 0.4);
		this.players = new ArrayList<Player>();
		this.players.add(new Player("Amos",container.getWidth()/2,0));
		this.I = new Interface(players);

		this.line = new DeathLine(container);

		plateformes=new ArrayList<Plateforme>();
		this.bonuses = new ArrayList <Bonus> ();
		decorations=new ArrayList<Decoration>();

		addPlateforme(new PlateformeClassique(container.getWidth()/2-65, 90, 200, 30, true, players.get(0)));

		plateformeGen = new PlateformeGen(this,players.get(0));
		this.bonusGen = new BonusGen (container, game);
		decorationGen = new DecorationGen(this,players);
	}

	public void pause (GameContainer container, StateBasedGame game) {
		/* Méthode exécutée lors de la mise en pause du jeu */
		defouloir.pause();
	}

	public void resume (GameContainer container, StateBasedGame game) {
		/* Méthode exécutée lors de la reprise du jeu */
		defouloir.resume();
	}

	public void stop (GameContainer container, StateBasedGame game) {
		/* Méthode exécutée une unique fois à la fin du jeu */
		defouloir.stop();
	}

	public void setState (int state) {
		this.state = state;
	}

	public int getState () {
		return this.state;
	}

	public void addPlateforme(Plateforme plateforme ) {
		plateformes.add(plateforme);
	}

	public void addBonus (Bonus bonus) {
		this.bonuses.add (bonus);
	}

	public void addDecoration(Decoration decoration) {
		decorations.add(0,decoration);
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public List <Player> getPlayers () {
		return this.players;
	}

	public void showRules() {
	}

}
