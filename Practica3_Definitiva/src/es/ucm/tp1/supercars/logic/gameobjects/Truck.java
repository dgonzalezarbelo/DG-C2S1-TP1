package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class Truck extends Obstacle {

	public static final String INFO = "[TRUCK] moves towards the player";
	
	public Truck(Game game, int x, int y) {
		super(game, x, y);
		this.symbol = "←";
	}
	
	public void update() {
		this.x--;
	}

}
