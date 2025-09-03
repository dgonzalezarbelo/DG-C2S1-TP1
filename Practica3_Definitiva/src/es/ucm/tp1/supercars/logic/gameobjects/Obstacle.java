package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class Obstacle extends GameObject {
	
	public static final String INFO = "[Obstacle] hits car";
	
	private static int totalObstacles = 0;
	
	public Obstacle(Game game, int x, int y) {
		super(game, x, y);
		this.symbol = "░";
	}

	@Override
	public boolean receiveCollision(Player player) {
		player.kill();
		return false;
	}

	@Override
	public void onEnter() {	//Llamamos a la funcion en la creacion del obstaculo
		totalObstacles++;
	}

	@Override
	public void update() {}

	@Override
	public void onDelete() {
		totalObstacles--;
	}
	
	@Override
	public boolean receiveShoot() {
		this.kill();
		return true;
	}
	
	public static void reset() {
		totalObstacles = 0;
	}
	
	public static int getObstaclesCount() {
		return totalObstacles;
	}

	@Override
	public boolean receiveExplosion() {
		return this.receiveShoot();
	}

	@Override
	public boolean receiveThunder() {
		System.out.print(String.format(" -> %s", this.toString()));
		return this.receiveShoot();
	}
	
	@Override
	public boolean doCollision() {
		return false;
	}

}
