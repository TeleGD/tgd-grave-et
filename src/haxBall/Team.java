package haxBall;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;

public class Team {
	
	private List<Player> players;
	private Field field;
	private int id;
	private Color color;
	private int size;
	
	public Team(Field fiels, int id) {
		this.field = field;
		this.id = id;
		players = new ArrayList<Player>();
		if (id==0) {
			color = Color.blue;
		} else {
			color = Color.red;
		}
		size = 0;
	}
	
	public void addPlayer(Player p){
		size+=1;
		
	
	}
	
	public void deletePlayer(Player p) {
		
	}
	
	public void engagement() {
		for (Player p : players) {
			p.resetPos();
		}
	}

}

