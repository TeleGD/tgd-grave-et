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
	private Color m_color;
	private float m_speedX, m_speedY, m_speed;
	private boolean up, down, right, left, updown, rightLeft;
	private Player m_enemy;
	private Shape m_shape;
	
	public Player(int fieldHeight, int fieldWidth, int fieldOriginX, int fieldOriginY, int id) {
		m_id = id;
		
		m_fieldHeight = fieldHeight;
		m_fieldWidth = fieldWidth;
		m_fieldOriginX = fieldOriginX;
		m_fieldOriginY = fieldOriginY;
		
		m_radius = fieldHeight/15;
		m_posY = (fieldHeight/2) + fieldOriginY - (m_radius/2);
		m_speed = (float) 0.3;
		
		if(m_id == 0) {
			m_posX = ((fieldWidth)/4) + fieldOriginX;
			m_color = new Color(0, 0, 255);
		}
		else {
			m_posX = ((3*fieldWidth)/4) + fieldOriginX;
			m_color = new Color(255, 0, 0);
		}
		
		m_shape = new Circle(m_posX+(m_radius/2), m_posY+(m_radius/2), m_radius/2);
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
	
	public int getRadius() {
		return m_radius;
	}
	
	public int getID() {
		return m_id;
	}
	
	public Color getColor() {
		return m_color;
	}
	
	public void setColor(Color color) {
		m_color = color;
	}
	
	public Shape getShape() {
		return m_shape;
	}
	
	public void setShape(Shape shape) {
		m_shape = shape;
	}
	
	public void setEnemy(Player enemy) {
		m_enemy = enemy;
	}
	
	public void render (GameContainer container, StateBasedGame game, Graphics context) {
		context.setColor(m_color);
		context.fillOval(m_posX, m_posY, m_radius, m_radius);
	}
	
	public void update (GameContainer container, StateBasedGame game, int delta) {
		move(delta);
		m_shape.setLocation(m_posX+(m_radius/2), m_posY+(m_radius/2));
	}
	
	public boolean collision(Player enemy) {
		m_shape.setLocation(m_posX+(m_radius/2), m_posY+(m_radius/2));
		return m_shape.intersects(enemy.getShape());
		
	}
	
	public void keyPressed(int key, char c) {
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
	
			}
		}
		else {
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
	
			}
		}
	}

	public void keyReleased(int key, char c) {
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
			}
		}
		else {
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
			}
		}
	}

	public void move(int dt) {
		m_speedX = 0;
		m_speedY = 0;
	
		if(((up && !down) || (up && down && !updown)) && (m_posY > m_fieldOriginY)){
			m_speedY=-m_speed;
		}
	
		if(((down && !up) || (up && down && updown)) && ((m_posY + m_radius) < (m_fieldOriginY + m_fieldHeight))) {
			m_speedY=m_speed;
		}
	
		if(((left && !right)|| (left && right && rightLeft)) && (m_posX > m_fieldOriginX)) {
			m_speedX = -m_speed;
		}
		
		if(((!left && right)|| (left && right && !rightLeft)) && ((m_posX + m_radius) < (m_fieldOriginX + m_fieldWidth))) {
			m_speedX = m_speed;
		}
		
		if (m_speedX!=0 && m_speedY!=0) {
			m_speedX/=Math.sqrt(2);
			m_speedY/=Math.sqrt(2);
		}

		m_tempPosX = m_posX;
		m_posX += (int)(dt*m_speedX);
		m_tempPosY = m_posY;
		m_posY +=  + (int)(dt*m_speedY);
		if(collision(m_enemy)) {
			m_posX = m_tempPosX;
			m_posY = m_tempPosY;
		}
		
		if(m_posY <= m_fieldOriginY)
			m_posY = m_fieldOriginY;
		else if((m_posY + m_radius) > (m_fieldOriginY + m_fieldHeight))
			m_posY = m_fieldOriginY + m_fieldHeight - m_radius;
		else if (m_posX <= m_fieldOriginX)
			m_posX = m_fieldOriginX;
		else if ((m_posX + m_radius) > (m_fieldOriginX + m_fieldWidth))
			m_posX = m_fieldOriginX + m_fieldWidth - m_radius;
	}
}