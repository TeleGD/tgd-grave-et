package verticalPlateformer;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import verticalPlateformer.plateforme.Plateforme;
import verticalPlateformer.plateforme.PlateformeGen;

public class World extends BasicGameState {

	private ArrayList<Player> players;
	private Interface I;
	private Player amos;
	private DeathLine line;
	private ArrayList<Plateforme> plateformes;
	private PlateformeGen plateformeGen;
	private ArrayList<Decoration> decorations;
	private DecorationGen decorationGen;
	
	
	private int ID;
	private int state;

	private Sound trash;
	private Music defouloir;
	
	/*   Ajout d'un commentaire important    */

	public World (int ID) {
		this.ID = ID;
		this.state = -1;
		try {
			defouloir = new Music("res/musics/verticalPlateformer/Defouloir.ogg");
			trash = new Sound("res/sound/verticalPlateformer/trash.ogg");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int getID () {
		return this.ID;
	}

	@Override
	public void init (GameContainer container, StateBasedGame game) {
		container.getWidth ();
		container.getHeight ();
	}

	@Override
	public void enter (GameContainer container, StateBasedGame game) {
		/* Méthode exécutée à l'apparition de la page */
		if (this.state == 0) {
			this.play (container, game);
			defouloir.play(1, (float) 0.4);
		} else if (this.state == 2) {
			this.resume (container, game);
			defouloir.resume();
		}
	}

	@Override
	public void leave (GameContainer container, StateBasedGame game) {
		/* Méthode exécutée à la disparition de la page */
		if (this.state == 1) {
			this.pause (container, game);
			defouloir.pause();
		} else if (this.state == 3 || this.state == 6) {
			this.stop (container, game);
			defouloir.stop();
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
		for(int i=plateformes.size()-1;i>=0;i--) {
			if(plateformes.get(i).getPosY()>=this.line.getPosY() || plateformes.get(i).isDestroyed()) {
				plateformes.remove(i);
				trash.play(1, (float) 0.4);
			}
		}

		plateformeGen.update(container, game, delta);
		decorationGen.update(container, game, delta);

		for(Player player : players) {
			player.update(container, game, delta);
			for (Plateforme plat : plateformes) {
				if(player.getShape().intersects(plat)) {
					if ((player.getGravity() == 0) == plat.getSens()) {
						player.freeze();
						player.setPlateforme(plat);
						// Le joueur s'arrête
						//System.out.println("FREEZE");
					}
					else {
						((DeathPage) game.getState(6)).setScore(player.getScore());
						game.enterState (6, new FadeOutTransition (), new FadeInTransition ());
						// Le joueur meurt
						//System.out.println("FREEZE - UNFREEZE");
					}
				}
			}
		}
		
		for (Player player : players) {
			if (player.getPosY() > line.getPosY() || player.getPosX()+player.getWidth()<0 || player.getPosX()>container.getWidth()) {
				// TODO : à changer si on met plusieurs joueurs
				((DeathPage) game.getState(6)).setScore(player.getScore());
				game.enterState (6, new FadeOutTransition (), new FadeInTransition ());
			}
		}



	}

	@Override
	public void render (GameContainer container, StateBasedGame game, Graphics context) {
		/* Méthode exécutée environ 60 fois par seconde */
		I.render(container,game,context);
		
		for(Decoration d: decorations) {
			d.render(container, game, context, amos.getPosY ());
		}
		
		amos.render(container, game, context);
		
		line.render (container, game, context, amos.getPosY ());
		
		for(Plateforme p:plateformes) {
			p.render(container, game, context, amos.getPosY ());
		}
	}

	public void play (GameContainer container, StateBasedGame game) {
		/* Méthode exécutée une unique fois au début du jeu */
		this.amos = new Player("Amos",100,100);
		this.players = new ArrayList<Player>();
		this.players.add(amos);
		this.I = new Interface(players);

		this.line = new DeathLine(container);

		plateformes=new ArrayList<Plateforme>();
		decorations=new ArrayList<Decoration>();
		plateformeGen = new PlateformeGen(this,players.get(0));
		decorationGen = new DecorationGen(this,players);
	}

	public void pause (GameContainer container, StateBasedGame game) {
		/* Méthode exécutée lors de la mise en pause du jeu */
		defouloir.pause();
	}

	public void resume (GameContainer container, StateBasedGame game) {
		/* Méthode exécutée lors de la reprise du jeu */
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

	public void addDecoration(Decoration decoration) {
		decorations.add(0,decoration);
	}

}
