package haxBall;

import java.util.*;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import haxBall.bonus.*;

public class Field {
	private int height;
	private int width;
	private int pos_x;
	private int pos_y;
	private Color defaultColor;
	private Color actualColor;
	private List<Player> players;
	private Ball ball;
	private int world_height;
	private int world_width;
	private int bonusTimer;
	private float rnd;

	private List<Bonus> bonus;
	public Field(int world_height , int world_width){
		// normalement ca marche (pas)...
		this.height = (int)(0.7 * world_height);
		this.width = (int)(0.7 * world_width);
		this.pos_x = (int)(0.15 * world_width);
		this.pos_y = (int)(0.15 * world_height);
		this.defaultColor = new Color(102, 148, 68);
		this.actualColor = defaultColor;
		this.world_width = world_width;
		this.world_height = world_height;
		this.bonusTimer = 10*1000;

		this.rnd = (float) Math.random();
		//System.out.println(this.rnd);

		this.bonus = new ArrayList<Bonus>();

		// creation des joueurs ...
		this.players = new ArrayList<Player>();
		players.add(new Player(this.height,this.width,this.pos_x , this.pos_y, 0, this));
		players.add(new Player(this.height,this.width,this.pos_x , this.pos_y, 1, this));

		ball = new Ball(this.height,this.width,this.pos_x,this.pos_y,this);
	}

	public void setColor(Color c) {
		this.actualColor = c;

	}

	public void resetColor() {
		actualColor = defaultColor;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getPosX() {
		return pos_x;
	}

	public int getPosY() {
		return pos_y;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void addPlayer(Player p) {
		players.add(p);
	}

	public void removePlayer(Player p) {
		players.remove(p);
	}

	public void update (GameContainer container, StateBasedGame game, int delta) {
		/* Méthode exécutée environ 60 fois par seconde */
		//update les players
		for(Player p : players) {
			p.update(container, game, delta);
		}

		//update la ball
		ball.update(container, game, delta);

		//genere les bonus
		bonusTimer -= delta;
		if(bonusTimer <= 0) {
			generateBonus();
			bonusTimer = 10*1000;
		}

		//update les bonus
		for(int i=0 ; i<bonus.size() ; i++) {
			for(int j=0; j<2; j++) {
				if(bonus.get(i).getShape().intersects(players.get(j).getShape()) && !bonus.get(i).isActivated())
					bonus.get(i).activate(players.get(j), ball);
			}
			bonus.get(i).update(container, game, delta);

			if(bonus.get(i).isDeleted()) {
				bonus.remove(i);
			}
		}
	}

	private void generateBonus() {
		int k= (int)(Math.random()*6);
		int posX = (int)(Math.random()*5*width/6+pos_x+width/12);
		int posY = (int)(Math.random()*5*height/6+pos_y+height/12);

		switch (k) {
		case 0:
			bonus.add(new Deflate(posX, posY, this));
			break;
		case 1:
			bonus.add(new Flash(posX, posY, this));
			break;
		case 2:
			bonus.add(new Inflate(posX, posY, this, ball));
			break;
		case 3:
			bonus.add(new Teleport(posX, posY, this, ball));
			break;
		case 4:
			bonus.add(new Bip(posX, posY, this));
			break;
		case 5:
			bonus.add(new Pillars(posX, posY, this));
			break;
		default:
			break;
		}

	}

	public void render (GameContainer container, StateBasedGame game, Graphics context) {
		/* Méthode exécutée environ 60 fois par seconde on espère !  */

		//le tour du terrain
		context.setColor(new Color(102, 111, 69));
		context.fillRect(0, 0, this.world_width, this.world_height);
		//le fond du terrain
		context.setColor(actualColor);
		context.fillRect(this.pos_x,this.pos_y,this.width,this.height);
		//la ligne autour du terrain
		context.setColor(new Color(243, 241, 255));
		context.drawRect(this.pos_x, this.pos_y, this.width, this.height);
		//toutes les lignes du terrain et les buts
		if(this.rnd > 0.5) {
			context.setColor(new Color(128, 156, 255));// Buts
			context.fillRect(this.pos_x - this.width/16 , this.pos_y+ this.height / 3, this.width / 16, this.height / 3);// but 1
			context.setColor(new Color(255, 80, 80));// Buts
			context.fillRect(this.pos_x + this.width  , this.pos_y + this.height / 3, this.width / 16, this.height / 3); // but 2

			context.setColor(new Color(243, 241, 255)); // traits inutiles
			context.drawRect(this.pos_x ,  this.pos_y+ this.height/4,  this.width / 8, this.height / 2); // zone 1
			context.drawRect(this.pos_x +  7 *this.width /8 ,  this.pos_y+ this.height/4,  this.width / 8, this.height / 2); // zone 2
			context.drawLine(this.pos_x + this.width/2, this.pos_y, this.pos_x + this.width/2, this.pos_y + this.height); // ligne milieu
			context.drawRect(this.pos_x, this.pos_y, this.width, this.height); // touche
			context.drawOval(this.pos_x + this.width/2 - this.height/8 , this.pos_y + this.height/2 - this.height/8, height/4, height/4); // cercle

		} else {
			context.setColor(new Color(231, 235, 221));
			for(int i = 1 ; i < 6 ; i++) {
				context.drawLine(this.pos_x + i * (this.width / 6)  , this.pos_y,this.pos_x + i * (this.width / 6) , this.pos_y  + this.height);
			}
			context.setColor(new Color(128, 156, 255));
			context.fillRect(this.pos_x - this.width/20, this.pos_y + height/3- width/20, width/20, width/20);
			context.fillRect(this.pos_x - this.width/20, this.pos_y + 2*height/3 , width/20, width/20);
			context.setColor(new Color(255, 80, 80));
			context.fillRect(this.pos_x + this.width, this.pos_y + height/3- width/20, width/20, width/20);
			context.fillRect(this.pos_x + this.width, this.pos_y + 2*height/3 , width/20, width/20);
		}

		//on affiche les bonus
		for(Bonus b : bonus) {
			if(!b.isActivated())
				b.render(container, game, context);
		}

		//on affiche les joueurs
		for(Player p : players) {
			p.render(container, game, context);
		}

		//on affiche la ball
		ball.render(container, game, context);
	}

	public void addBonus(Bonus b) {
		bonus.add(b);

	}

	public void keyPressed(int key, char c) {
		for(Player p : players) {
			p.keyPressed(key,c);
		}
	}

	public void keyReleased(int key, char c) {
		for(Player p : players) {
			p.keyReleased(key,c);
		}
	}
}
