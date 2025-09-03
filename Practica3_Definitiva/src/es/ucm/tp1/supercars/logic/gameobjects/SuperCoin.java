package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class SuperCoin extends GameObject {
	
	public static final String INFO = "[SUPERCOIN] gives 1000 coins";
	
	private static int totalSuperCoins = 0;
	
	private static boolean taken = false;
	
	private static final int PRIZE = 1000;
	
	public SuperCoin(Game game, int x, int y) {
		super(game, x, y);
		this.symbol = "$";
	}

	@Override
	public boolean receiveCollision(Player player) {
		this.kill();
		this.onDelete();
		taken = true;
		player.addCoin(PRIZE);
		return false;
	}

	@Override
	public void onEnter() {
		totalSuperCoins++;
	}

	@Override
	public void update() {}

	@Override
	public void onDelete() {
		totalSuperCoins--;
	}
	
	@Override
	public boolean doCollision() {
		return false;
	}

	public static boolean hasSuperCoin() {
		return (totalSuperCoins > 0 && !taken);
	}
	
	public static void reset() {	//Pensada para resetear el estado de las SuperCoins al hacer un reset de la partida
		totalSuperCoins = 0;
		taken = false;
	}

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
