package haxBall;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

public class Player {
	private int m_posX, m_posY, m_id, m_fieldHeight, m_fieldWidth, m_fieldOriginX, m_fieldOriginY;
	private Color m_color;
	private int m_radius;
	private float m_speedX;
	private float m_speedY;
	private float m_speed;
	private int m_newX;
	private int m_newY;
	private boolean up;
	private boolean down;
	private boolean right;
	private boolean left;
	private boolean updown;
	private boolean rightLeft;

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
	}
	
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
	
	public void render (GameContainer container, StateBasedGame game, Graphics context) {
		context.setColor(m_color);
		context.fillOval(m_posX, m_posY, m_radius, m_radius);
	}
	
	public void update (GameContainer container, StateBasedGame game, int delta) {
		move(delta);
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

		m_posX+=dt*m_speedX;
		m_posY+=dt*m_speedY;
		
		if(m_posY <= m_fieldOriginY)
			m_posY = m_fieldOriginY;
		else if((m_posY + m_radius) > (m_fieldOriginY + m_fieldHeight))
			m_posY = m_fieldOriginY + m_fieldHeight;
		else if (m_posX <= m_fieldOriginX)
			m_posX = m_fieldOriginX;
		else if ((m_posX + m_radius) > (m_fieldOriginX + m_fieldWidth))
			m_posX = m_fieldOriginX + m_fieldWidth;
	}
}