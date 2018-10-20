package haxBall;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

public class Player {
	private int m_posX;
	private int m_posY;
	private int m_id;
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
		m_radius = fieldHeight/15;
		m_posY = (fieldHeight/2) + fieldOriginY - (m_radius/2);
		m_speed = (float) 0.3;
		System.out.println("vitesse : "+m_speed);
		
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
	
	public void render (GameContainer container, StateBasedGame game, Graphics context) {
		context.setColor(m_color);
		context.fillOval(m_posX, m_posY, m_radius, m_radius);
	}
	
	public void update (GameContainer container, StateBasedGame game, int delta) {
		move(delta);
		System.out.println(up);
	}
	
	public void keyPressed(int key, char c) {
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

	public void keyReleased(int key, char c) {
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

	public void move(int dt) {
		m_speedX = 0;
		m_speedY = 0;
		if((up && !down) || (up && down && !updown)){
			m_speedY=-m_speed;
		}
		if((down && !up) || (up && down && updown)) {
			m_speedY=m_speed;
		}
		if((left && !right)|| (left && right && rightLeft)) {
			m_speedX = -m_speed;
		}
		if((!left && right)|| (left && right && !rightLeft)) {
			m_speedX = m_speed;
		}
		if (m_speedX!=0 && m_speedY!=0) {
			m_speedX/=Math.sqrt(2);
			m_speedY/=Math.sqrt(2);
		}

		m_posX+=dt*m_speedX;
		m_posY+=dt*m_speedY;
	}

	
}