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
	private Color color;
	private Player player0;
	private Player player1;
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
		this.color = new Color(102, 148, 68);
		this.world_width = world_width;
		this.world_height = world_height;
		this.bonusTimer = 10*1000;
		
		this.rnd = (float) Math.random();
		//System.out.println(this.rnd);
		
		this.bonus = new ArrayList<Bonus>();
		// creation des joueurs ...
		player0 = new Player(this.height,this.width,this.pos_x , this.pos_y, 0);
		player1 = new Player(this.height,this.width,this.pos_x , this.pos_y, 1);
		ball = new Ball(this.height,this.width,this.pos_x,this.pos_y);
	}
	
	public void setColor(Color c) {
		this.color = c;
		
	}
	
	public Color getColor() {
		return color;
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
	
	public void update (GameContainer container, StateBasedGame game, int delta) {
		/* Méthode exécutée environ 60 fois par seconde */
		player0.update(container, game, delta);
		player1.update(container, game, delta);
		ball.update(container, game, delta);
		bonusTimer -= delta;
		
		if(bonusTimer <= 0) {
			generateBonus();
			bonusTimer = 10*1000;
		}
		
		for(int i=0 ; i<bonus.size() ; i++) {
			bonus.get(i).update(container, game, delta);
			
			if(bonus.get(i).isDeleted()) {
				System.out.println("test");
				bonus.remove(i);
			}
		}
	}
	
	private void generateBonus() {
		int r = (int)Math.random()*5;
		
		int k= (int)(Math.random()*4);
		int posX = (int)(Math.random()*5*width/6+pos_x+width/12);
		int posY = (int)(Math.random()*5*height/6+pos_y+height/12);
		
		switch (k) {
		case 0:
			bonus.add(new Deflate(posX, posY, this, ball));
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
		}
		
	}
	
	public void render (GameContainer container, StateBasedGame game, Graphics context) {
		/* Méthode exécutée environ 60 fois par seconde on espère !  */
		context.setColor(new Color(102, 111, 69));
		context.fillRect(0, 0, this.world_width, this.world_height);
		context.setColor(new Color(102, 148, 68));
		context.fillRect(this.pos_x,this.pos_y,this.width,this.height);
		context.setColor(new Color(243, 241, 255));
		context.drawRect(this.pos_x, this.pos_y, this.width, this.height);
		if(this.rnd > 0.5) {
			
			context.setColor(new Color(155, 177, 181));// Buts
			context.fillRect(this.pos_x - this.width/16 , this.pos_y+ this.height / 3, this.width / 16, this.height / 3);// but 1
			context.fillRect(this.pos_x + this.width  , this.pos_y + this.height / 3, this.width / 16, this.height / 3); // but 2
	
			context.setColor(new Color(243, 241, 255)); // traits inutiles
			context.drawRect(this.pos_x ,  this.pos_y+ this.height/4,  this.width / 8, this.height / 2); // zone 1
			context.drawRect(this.pos_x +  7 *this.width /8 ,  this.pos_y+ this.height/4,  this.width / 8, this.height / 2); // zone 2
			context.drawLine(this.pos_x + this.width/2, this.pos_y, this.pos_x + this.width/2, this.pos_y + this.height); // ligne milieu
			context.drawRect(this.pos_x, this.pos_y, this.width, this.height); // touche
			context.drawOval(this.pos_x + this.width/2 - this.height/8 , this.pos_y + this.height/2 - this.height/8, height/4, height/4); // cercle
	
			player0.render(container, game, context);
			player1.render(container, game, context);
			ball.render(container, game, context);
		}
		else {
			context.setColor(new Color(231, 235, 221));
			for(int i = 1 ; i < 6 ; i++) {
				context.drawLine(this.pos_x + i * (this.width / 6)  , this.pos_y,this.pos_x + i * (this.width / 6) , this.pos_y  + this.height);
			}
			context.setColor(new Color(234, 217, 127));
			context.drawRect(this.pos_x - this.width/20, this.pos_y + height/3- width/20, width/20, width/20);
			context.drawRect(this.pos_x - this.width/20, this.pos_y + 2*height/3 , width/20, width/20);
			context.drawRect(this.pos_x + this.width, this.pos_y + height/3- width/20, width/20, width/20);
			context.drawRect(this.pos_x + this.width, this.pos_y + 2*height/3 , width/20, width/20);
			context.setColor(new Color(255, 217, 127));
			context.fillRect(this.pos_x - this.width/20, this.pos_y + height/3- width/20, width/20, width/20);
			context.fillRect(this.pos_x - this.width/20, this.pos_y + 2*height/3 , width/20, width/20);
			context.fillRect(this.pos_x + this.width, this.pos_y + height/3- width/20, width/20, width/20);
			context.fillRect(this.pos_x + this.width, this.pos_y + 2*height/3 , width/20, width/20);

		for(Bonus b : bonus) {
			if(!b.isActivated())
				b.render(container, game, context);
		}
		
		player0.render(container, game, context);
		player1.render(container, game, context);
		ball.render(container, game, context);

		}
	}
	
	public void addBonus(Bonus b) {
		bonus.add(b);

	}
	
	public void keyPressed(int key, char c) {
		player0.keyPressed(key,c);
		player1.keyPressed(key,c);
		ball.keyPressed(key,c);
	}
	
	public void keyReleased(int key, char c) {
		player0.keyReleased(key,c);
		player1.keyReleased(key,c);
		//*ball.keyReleased(key,c);
	}
}
