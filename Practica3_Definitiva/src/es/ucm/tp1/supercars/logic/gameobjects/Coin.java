package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class Coin extends GameObject {
	public static final String INFO = "[Coin] gives 1 coin to the player";
	private static int totalCoins;
	private static final int PRIZE = 1;
	
	public Coin(Game game, int x, int y) {
		super(game, x, y);
		this.symbol = "¢";
	}

	@Override
	public boolean receiveCollision(Player player) {
		this.kill();
		player.addCoin(PRIZE);
		return false;
	}

	@Override
	public void onEnter() {	//Llamamos a la funcion en la creacion de la moneda
		totalCoins++;
	}

	@Override
	public void update() {}

	@Override
	public void onDelete() {	//Se utiliza cuando se coge la moneda
		totalCoins--;
	}
	
	public static void reset() {
		totalCoins = 0;
	}
	
	public static int getCoinsCount() {
		return totalCoins;
	}

	@Override
	public boolean doCollision() {
		return false;
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
