package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class Wall extends Obstacle {	//Tiene sentido que sea subclase
	
	public static final String INFO = "[WALL] hard obstacle";
	
	private int resistencia;
	
	private static final int PRIZE = 5;
	
	private static final String[] SYMBOLS = {"", "░", "▒", "█"};
	
	public Wall(Game game, int x, int y) {
		super(game, x, y);
		this.resistencia = 3;
		this.symbol = SYMBOLS[resistencia];;
	}

	@Override
	public String serialize() {
		String str;
		str = String.format("%s %d", super.serialize(), this.resistencia);
		return str;
	}
	
	@Override
	public void onDelete() {
		super.onDelete();
		game.addPlayerCoins(PRIZE);
	}
	
	@Override
	public boolean receiveShoot() {
		this.resistencia--;
		if(this.resistencia == 0) {
			super.receiveShoot();
		}
		else {
			this.symbol = SYMBOLS[resistencia];
		}
		return true;
	}
	
	@Override
	public boolean receiveExplosion() {
		return super.receiveShoot();
	}
	
	@Override
	public boolean receiveThunder() {
		System.out.print(String.format(" -> %s", this.toString()));
		return super.receiveShoot();
	}
	
}
