package haxBall;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;

public class Player {
	private int m_posX, m_posY, m_tempPosX, m_tempPosY, m_radius, m_id, m_fieldHeight, m_fieldWidth, m_fieldOriginX, m_fieldOriginY;
	private Color m_actualColor, m_defaultColor;
	private float m_speedX, m_speedY, m_speed;
	private boolean up, down, right, left, updown, rightLeft;
	private Field field;
	private Circle m_shape;
	private boolean shooting;
	private int spawnX, spawnY;
	private String name;
	

	public Player(String name, int fieldHeight, int fieldWidth, int fieldOriginX, int fieldOriginY, int id, Field field) {

		this.field = field;
		this.name = name;
		this.shooting = false;
		m_id = id;
		
		m_fieldHeight = field.getHeight();
		m_fieldWidth = field.getWidth();
		m_fieldOriginX = field.getPosX();
		m_fieldOriginY = field.getPosY();
		
		m_radius = m_fieldHeight/15;
		spawnY = (m_fieldHeight/2) + m_fieldOriginY - (m_radius/2);
		m_speed = (float) 0.3;
		
		if(m_id == 0) {

			spawnX = ((m_fieldWidth)/6) + m_fieldOriginX;

			m_defaultColor = new Color(0, 0, 255);
		}
		else {
			spawnX = ((3*m_fieldWidth)/4) + m_fieldOriginX;
			m_defaultColor = new Color(255, 0, 0);
		}
		
		m_posX = spawnX;
		m_posY = spawnY;
		m_actualColor = m_defaultColor;
		m_shape = new Circle(m_posX+(m_radius/2), m_posY+(m_radius/2), m_radius/2);
	}
	
	public Player(int x, int y, Field field) {
		this.field = field;
		name = "";
		
		m_fieldHeight = field.getHeight();
		m_fieldWidth = field.getWidth();
		m_fieldOriginX = field.getPosX();
		m_fieldOriginY = field.getPosY();
		
		this.shooting = false;
		this.spawnX = x;
		this.spawnY = y;
		this.m_posX = spawnX;
		this.m_posY = spawnY;
		this.m_radius = m_fieldHeight/18;
		this.m_defaultColor = new Color(70,70,70);
		this.m_actualColor = m_defaultColor;
		this.m_speed = 0;
		this.m_id = 2;
		this.m_shape = new Circle(m_posX+(m_radius/2), m_posY+(m_radius/2), m_radius/2);
	}
	
	/*public Player(int fieldHeight, int fieldWidth, int fieldOriginX, int fieldOriginY, int id, Player enemy) {
		new Player(fieldHeight, fieldWidth, fieldOriginX, fieldOriginY, id);
		m_enemy = enemy;
	}*/
	
	public int getPosX() {
		return m_posX;
	}
	
	public int getPosY() {
		return m_posY;
	}
	
	public void resetPos() {
		m_posX = spawnX;
		m_posY = spawnY;
	}
	
	public float getSpeedX() {
		return m_speedX;
	}
	
	public float getSpeedY() {
		return m_speedY;
	}
	
	public void setSpeed(float speed) {
		m_speed = speed;
	}
	
	public int getRadius() {
		return m_radius;
	}
	
	public int getID() {
		return m_id;
	}
	
	public void setColor(Color color) {
		m_actualColor = color;
	}
	
	public void resetColor() {
		m_actualColor = m_defaultColor;
	}
	
	public Circle getShape() {
		return m_shape;
	}
	
	
	
//	public void setShape(Circle shape) {
//		m_shape = shape;
//	}
	
//	public void setEnemy(Player enemy) {
//		m_enemy = enemy;
//	}
	
	public int getSpawnX() {
		return spawnX;
	}

	public void setSpawnX(int spawnX) {
		this.spawnX = spawnX;
	}

	public int getSpawnY() {
		return spawnY;
	}

	public void setSpawnY(int spawnY) {
		this.spawnY = spawnY;
	}

	public void render (GameContainer container, StateBasedGame game, Graphics context) {
		context.setColor(m_actualColor);
		context.fillOval(m_posX, m_posY, m_radius, m_radius);
		context.setColor(Color.black);
		context.drawString(name, m_posX, m_posY+(float)1.1*(m_radius)); 
		
//		context.setColor(new Color(0,255,0));
//		context.draw(m_shape);
	}
	
	public void update (GameContainer container, StateBasedGame game, int delta) {
		move(delta);
		updateShape();
	}
	
	public boolean collision(Player enemy) {
		updateShape();
		return m_shape.intersects(enemy.getShape());
	}
	
	private void updateShape() {
		m_shape.setLocation(m_posX, m_posY);
		m_shape.setRadius(m_radius/2);
	}
	
	public void keyPressed(int key, char c) {
		if(key==Input.KEY_SPACE) shooting = true;
		
		if(m_id == 0) {
			switch (key){
			
			case Input.KEY_Z:
				up = true;
				updown=false;
				break;
	
			case Input.KEY_S:
				down=true;
				updown=true;
				break;
	
			case Input.KEY_Q:
				left=true;
				rightLeft=true;
				break;
			case Input.KEY_D:
				right=true;
				rightLeft=false;
				break;
			case Input.KEY_R:
				m_speed = (float) 0.45;
	
			}
		}
		else if(m_id == 1) {
			switch (key){
			
			case Input.KEY_UP:
				up = true;
				updown=false;
				break;
	
			case Input.KEY_DOWN:
				down=true;
				updown=true;
				break;
	
			case Input.KEY_LEFT:
				left=true;
				rightLeft=true;
				break;
			case Input.KEY_RIGHT:
				right=true;
				rightLeft=false;
				break;
			case Input.KEY_RCONTROL:
				m_speed = (float) 0.45;
	
			}
		}
	}

	public void keyReleased(int key, char c) {
		if(key==Input.KEY_SPACE) shooting = false;
		
		if(m_id == 0) {
			switch (key) {
			case Input.KEY_Z:
				up=false;
				break;
			case Input.KEY_D:
				right=false;
				break;
			case Input.KEY_Q:
				left=false;
				break;
			case Input.KEY_S:
				down=false;
				break;
			case Input.KEY_R:
				m_speed = (float) 0.3;
			}
		}
		else if (m_id == 1) {
			switch (key) {
			case Input.KEY_UP:
				up=false;
				break;
			case Input.KEY_RIGHT:
				right=false;
				break;
			case Input.KEY_LEFT:
				left=false;
				break;
			case Input.KEY_DOWN:
				down=false;
				break;
			case Input.KEY_RCONTROL:
				m_speed = (float) 0.3;
			}
		}
	}

	public void move(int dt) {
		m_speedX = 0;
		m_speedY = 0;
	
		if(((up && !down) || (up && down && !updown)) && (m_posY > m_fieldOriginY-m_radius)){
			m_speedY=-m_speed;
		}
	
		if(((down && !up) || (up && down && updown)) && (m_posY < (m_fieldOriginY + m_fieldHeight))) {
			m_speedY=m_speed;
		}
	
		if(((left && !right)|| (left && right && rightLeft)) && (m_posX > m_fieldOriginX-m_radius)) {
			m_speedX = -m_speed;
		}
		
		if(((!left && right)|| (left && right && !rightLeft)) && (m_posX < (m_fieldOriginX + m_fieldWidth))) {
			m_speedX = m_speed;
		}
		
		if (m_speedX!=0 && m_speedY!=0) {
			m_speedX/=Math.sqrt(2);
			m_speedY/=Math.sqrt(2);
		}

		m_tempPosX = m_posX;
		m_tempPosY = m_posY;
		
		m_posX += (int)(dt*m_speedX);
		m_posY += (int)(dt*m_speedY);
		
		for(Player p : field.getPlayers()) {
			if(!p.equals(this) && collision(p)) {
				m_posX = m_tempPosX;
				m_posY = m_tempPosY;
				//on regarde de quel côté est la collision
				if(m_shape.getCenterX()<p.getShape().getCenterX()) { //si on est à gauche de l'autre joueur
					if(m_shape.getCenterY()<p.getShape().getCenterY()) { //si on est au dessus de l'autre joueur
						//en haut à gauche
						if(m_speedX>0 && m_speedY<=0) { //si on va vers la droite et pas vers le bas
							m_speedX = 0;
							m_speedY = -m_speed;
							m_posY += (int)(dt*m_speedY);
							placeNextToPlayer(p); //on vient se coller à l'autre joueur pour éviter de se déplacer en escalier
							
						} else if(m_speedY>0 && m_speedX<=0) { //si on va vers le bas et pas vers la droite
							m_speedX = -m_speed;
							m_speedY = 0;
							m_posX += (int)(dt*m_speedX);
							placeNextToPlayer(p); //on vient se coller à l'autre joueur pour éviter de se déplacer en escalier
							
						} else if(m_speedY>0 && m_speedX>0) { //si on va vers le bas à droite
							if(m_shape.getCenterX() < p.getShape().getCenterX()-p.getShape().getRadius()*Math.sqrt(2)/2) { //si on a un angle>3pi/4
								m_speedX = -m_speed;
								m_speedY = 0;
								m_posX += (int)(dt*m_speedX);
								placeNextToPlayer(p); //on vient se coller à l'autre joueur pour éviter de se déplacer en escalier
								
							} else { //si angle < 3pi/4
								m_speedX = 0;
								m_speedY = -m_speed;
								m_posY += (int)(dt*m_speedY);
								placeNextToPlayer(p); //on vient se coller à l'autre joueur pour éviter de se déplacer en escalier
							}
						}
						
					} else { //si on est en dessous de l'autre joueur
						//en bas à gauche
						if(m_speedX>0 && m_speedY>=0) { //si on va vers la droite et pas vers le haut
							m_speedX = 0;
							m_speedY = m_speed;
							m_posY += (int)(dt*m_speedY);
							placeNextToPlayer(p); //on vient se coller à l'autre joueur pour éviter de se déplacer en escalier
							
						} else if(m_speedY<0 && m_speedX<=0) { //si on va vers le haut et pas vers la droite
							m_speedX = -m_speed;
							m_speedY = 0;
							m_posX += (int)(dt*m_speedX);
							placeNextToPlayer(p); //on vient se coller à l'autre joueur pour éviter de se déplacer en escalier
							
						} else if(m_speedY<0 && m_speedX>0) { //si on va vers le haut à droite
							if(m_shape.getCenterX() < p.getShape().getCenterX()-p.getShape().getRadius()*Math.sqrt(2)/2) { //si on a un angle<5pi/4
								m_speedX = -m_speed;
								m_speedY = 0;
								m_posX += (int)(dt*m_speedX);
								placeNextToPlayer(p); //on vient se coller à l'autre joueur pour éviter de se déplacer en escalier
								
							} else { //si angle > 5pi/4
								m_speedX = 0;
								m_speedY = m_speed;
								m_posY += (int)(dt*m_speedY);
								placeNextToPlayer(p); //on vient se coller à l'autre joueur pour éviter de se déplacer en escalier
							}
						}
					}
					
				} else { //si on est à droite de l'autre joueur
					if(m_shape.getCenterY()<p.getShape().getCenterY()) { //si on est au dessus de l'autre joueur
						//en haut à droite
						if(m_speedX<0 && m_speedY<=0) { //si on va vers la gauche et pas vers le bas
							m_speedX = 0;
							m_speedY = -m_speed;
							m_posY += (int)(dt*m_speedY);
							placeNextToPlayer(p); //on vient se coller à l'autre joueur pour éviter de se déplacer en escalier
							
						} else if(m_speedY>0 && m_speedX>=0) { //si on va vers le bas et pas vers la gauche
							m_speedX = m_speed;
							m_speedY = 0;
							m_posX += (int)(dt*m_speedX);
							placeNextToPlayer(p); //on vient se coller à l'autre joueur pour éviter de se déplacer en escalier
							
						} else if(m_speedY>0 && m_speedX<0) { //si on va vers le bas à gauche
							if(m_shape.getCenterX() > p.getShape().getCenterX()+p.getShape().getRadius()*Math.sqrt(2)/2) { //si on a un angle<pi/4
								m_speedX = m_speed;
								m_speedY = 0;
								m_posX += (int)(dt*m_speedX);
								placeNextToPlayer(p); //on vient se coller à l'autre joueur pour éviter de se déplacer en escalier
								
							} else { //si angle > pi/4
								m_speedX = 0;
								m_speedY = -m_speed;
								m_posY += (int)(dt*m_speedY);
								placeNextToPlayer(p); //on vient se coller à l'autre joueur pour éviter de se déplacer en escalier
							}
						}
						
					} else { //si on est en dessous de l'autre joueur
						//en bas à droite
						if(m_speedX<0 && m_speedY>=0) { //si on va vers la gauche et pas vers le haut
							m_speedX = 0;
							m_speedY = m_speed;
							m_posY += (int)(dt*m_speedY);
							placeNextToPlayer(p); //on vient se coller à l'autre joueur pour éviter de se déplacer en escalier
							
						} else if(m_speedY<0 && m_speedX>=0) { //si on va vers le haut et pas vers la gauche
							m_speedX = m_speed;
							m_speedY = 0;
							m_posX += (int)(dt*m_speedX);
							placeNextToPlayer(p); //on vient se coller à l'autre joueur pour éviter de se déplacer en escalier
							
						} else if(m_speedY<0 && m_speedX<0) { //si on va vers le haut à gauche
							if(m_shape.getCenterX() > p.getShape().getCenterX()+p.getShape().getRadius()*Math.sqrt(2)/2) { //si on a un angle>7pi/4
								m_speedX = m_speed;
								m_speedY = 0;
								m_posX += (int)(dt*m_speedX);
								placeNextToPlayer(p); //on vient se coller à l'autre joueur pour éviter de se déplacer en escalier
								
							} else { //si angle < 7pi/4
								m_speedX = 0;
								m_speedY = m_speed;
								m_posY += (int)(dt*m_speedY);
								placeNextToPlayer(p); //on vient se coller à l'autre joueur pour éviter de se déplacer en escalier
							}
						}
					}
				}
			}
		}
		
		if(m_posY <= m_fieldOriginY-m_radius) {
			m_posY = m_fieldOriginY-m_radius;
			m_speedY = 0;
			
		} else if((m_posY) > (m_fieldOriginY + m_fieldHeight)) {
			m_posY = m_fieldOriginY + m_fieldHeight;
			m_speedY = 0;			
		}
		
		if (m_posX <= m_fieldOriginX-m_radius) {
			m_posX = m_fieldOriginX-m_radius;
			m_speedX = 0;
			
		} else if ((m_posX) > (m_fieldOriginX + m_fieldWidth)) {
			m_posX = m_fieldOriginX + m_fieldWidth;
			m_speedX = 0;			
		}
	}
	
	private void placeNextToPlayer(Player p) {
		updateShape();
		//on se place au bord du joueur
		//si on est en +-pi/2
		if(m_shape.getCenterX() == p.getShape().getCenterX()) {
			int signe = 1;
			if(m_shape.getCenterY()-p.getShape().getCenterY()<0) signe = -1;
			m_posY = (int)(p.getShape().getCenterY() + (m_shape.getRadius() + p.getShape().getRadius() + 1)*signe - m_shape.getRadius());
				
		} else {
			//signe pour les x
			int signe = 1;
			if(m_shape.getCenterX()-p.getShape().getCenterX()<0) signe = -1;
					
			double angle = (-signe)*Math.atan((m_shape.getCenterY() - p.getShape().getCenterY())/(m_shape.getCenterX() - p.getShape().getCenterX())); //angle en radians
			double hyp = m_shape.getRadius() + p.getShape().getRadius() + 1;

			m_posX = (int)(p.getShape().getCenterX() + Math.cos(angle)*hyp*signe - m_shape.getRadius());
			m_posY = (int)(p.getShape().getCenterY() - hyp*Math.sin(angle) - m_shape.getRadius());
		}
	}
	
	public boolean isShooting() {
		return shooting;
	}
	
	public void placer(int x, int y) {
		m_posX = x;
		m_posY = y;
		m_speedX = 0;
		m_speedY = 0;
	}
	
}