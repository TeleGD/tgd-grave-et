package games.graveEt;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import app.AppLoader;

import games.graveEt.plateforme.Plateforme;
import games.graveEt.plateforme.PlateformeGen;
import games.graveEt.plateforme.Portalforme;

import pages.Death;

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
	private Color backgroundColor;
	private Image grass;
	private int height;
	private int width;
	private int ID;
	private int state;

	private Audio trash;
	private Audio defouloir;
	private float defouloirPos;

	public World (int ID) {
		this.ID = ID;
		this.state = 0;
	}

	@Override
	public int getID () {
		return this.ID;
	}

	@Override
	public void init (GameContainer container, StateBasedGame game) {
		/* Méthode exécutée une unique fois au chargement du programme */
		backgroundColor = new Color(30, 53, 20, 160);
		grass = AppLoader.loadPicture("/images/graveEt/grass.png");
		height = container.getHeight();
		width = container.getWidth();
		trash = AppLoader.loadAudio("/sounds/graveEt/trash.ogg");
		defouloir = AppLoader.loadAudio("/musics/graveEt/Defouloir.ogg");
		defouloirPos = 0f;
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée à l'apparition de la page */
		if (this.state == 0) {
			this.play(container, game);
		} else if (this.state == 2) {
			this.resume(container, game);
		}
	}

	@Override
	public void leave(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée à la disparition de la page */
		if (this.state == 1) {
			this.pause(container, game);
		} else if (this.state == 3) {
			this.stop(container, game);
			this.state = 0; // TODO: remove
		}
	}

	@Override
	public void update (GameContainer container, StateBasedGame game, int delta) {
		/* Méthode exécutée environ 60 fois par seconde */
		Input input = container.getInput();
		if (input.isKeyDown(Input.KEY_ESCAPE)) {
			this.setState(1);
			game.enterState(2, new FadeOutTransition(), new FadeInTransition());
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
				trash.playAsSoundEffect(1, .4f, false);
			}
		}
		for (int i = this.bonuses.size () - 1; i >= 0; i--) {
			Bonus bonus = bonuses.get (i);
			if (bonus.isApplied () || bonus.getPosY () >= this.line.getPosY ()) {
				this.bonuses.remove (i);
				trash.playAsSoundEffect(1, .4f, false);
			}
		}

		plateformeGen.update(container, game, delta);
		bonusGen.update (container, game, delta);
		decorationGen.update(container, game, delta);

		for(Player player : players) {
			player.update(container, game, delta);
			for (Plateforme plat : plateformes) {
				if(player.getShape().intersects(plat) && !(plat instanceof Portalforme)) {
					if ((player.getGravity() == 0) == plat.getSens()) {
						player.freeze();
						player.setPlateforme(plat);
						// Le joueur s'arrête
					} else {
						this.setState (3);
						((Death) game.getState(4)).setSubtitle("Seulement " + player.getScore() + " points...");
						game.enterState (4, new FadeOutTransition (), new FadeInTransition ());
						// Le joueur meurt
					}
				} else if (plat.contains(player.getShape()) && (plat instanceof Portalforme) && player.getPortalCooldown()<=0) {
					player.teleport(((Portalforme) plat).getCouple().getX()+15, ((Portalforme) plat).getCouple().getY()+65);
					player.setPortalCooldown(500);
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
				((Death) game.getState(4)).setSubtitle("Seulement " + player.getScore() + " points...");
				game.enterState (4, new FadeOutTransition (), new FadeInTransition ());
			}
		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics context) {
		/* Méthode exécutée environ 60 fois par seconde */
		context.fillRect(0, -grass.getHeight(), container.getWidth(), container.getHeight() + grass.getHeight(), grass, 0, players.get(0).getPosY () % grass.getHeight());
		
		for(Decoration d: decorations) {
			d.render(container, game, context, players.get(0).getPosY ());
		}

		context.setColor(backgroundColor);
		context.fillRect(0, 0, container.getWidth(), container.getHeight());
		context.setColor(Color.white);

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

	public void play(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée une unique fois au début du jeu */
		defouloir.playAsMusic(1, .4f, true);
		this.players = new ArrayList<Player>();
		this.players.add(new Player("Amos",container.getWidth()/2,0,container));
		this.I = new Interface(players);

		this.line = new DeathLine(container);

		plateformes=new ArrayList<Plateforme>();
		this.bonuses = new ArrayList <Bonus> ();
		decorations=new ArrayList<Decoration>();

		plateformeGen = new PlateformeGen(this,players.get(0));
		this.bonusGen = new BonusGen (container, game);
		decorationGen = new DecorationGen(this,players);
	}

	public void pause(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée lors de la mise en pause du jeu */
		defouloirPos = defouloir.getPosition();
		defouloir.stop();
	}

	public void resume(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée lors de la reprise du jeu */
		defouloir.playAsMusic(1, .4f, true);
		defouloir.setPosition(defouloirPos);
	}

	public void stop(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée une unique fois à la fin du jeu */
		defouloir.stop();
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getState() {
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

}
