package haxBall;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

public class Player {
	private int m_posX, m_posY, m_id, m_radius, m_speedX, m_speedY, m_newX, m_newY;
	private Color m_color;
	private boolean rightPress, downPress, leftPress, upPress, leftRight, upDown;

	public Player(int fieldHeight, int fieldWidth, int fieldOriginX, int fieldOriginY, int id) {
		m_id = id;
		m_radius = fieldHeight/15;
		m_posY = (fieldHeight/2) + fieldOriginY - (m_radius/2);
		
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
	
	public void update(GameContainer container, StateBasedGame game, int delta) {
		if(upPress == true) {
			m_speedX = 2;
			m_posX += m_speedX;
		}
		//m_newX = m_posX + m_speedX;
	}
	
	public void keyPressed(int key, char c) {
		switch (key) {
		case Input.KEY_UP:
			upPress = true;
			break;
		}
	}
}