package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class Turbo extends GameObject{
	
	public static final String INFO = "[TURBO] pushes the car: 3 columns";
	
	private static final int AVANCE = 3;
	
	public Turbo(Game game, int x, int y) {
		super(game, x, y);
		this.symbol = ">>>";
	}

	@Override
	public boolean receiveCollision(Player player) {
		player.addPos(AVANCE, 0);
		this.kill();
		return false;
	}

	@Override
	public boolean doCollision() {
		return false;
	}
	
	@Override
	public void onEnter() {}

	@Override
	public void update() {}

	@Override
	public void onDelete() {}

	@Override
	public boolean receiveShoot() {
		return false;
	}
	
	@Override
	public boolean receiveExplosion() {
		return false;
	}
	
	@Override
	public boolean receiveThunder() {
		return false;
	}
}
