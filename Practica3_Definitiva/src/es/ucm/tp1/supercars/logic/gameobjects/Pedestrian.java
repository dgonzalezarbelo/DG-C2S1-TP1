package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class Pedestrian extends Obstacle {
	
	private boolean bajando;
	
	public static final String INFO = "[PEDESTRIAN] person crossing the road up and down";
	
	public Pedestrian(Game game, int x, int y) {
		super(game, x, y);
		bajando = true;
		this.symbol = "☺";
	}
	
	@Override
	public String serialize() {
		String str;
		if(bajando)	str = String.format("%s down", super.serialize());
		else str = String.format("%s up", super.serialize());
		return str;
	}
	
	@Override
	public boolean receiveCollision(Player player) {
		player.resetCoinAmount();
		this.kill();
		return super.receiveCollision(player);
	}
	
	@Override
	public boolean receiveShoot() {
		game.resetPlayerCoinAmount();
		return super.receiveShoot();
	}
	
	@Override
	public void update() {
		if(bajando) {
			if(this.y == game.getRoadWidth() - 1) {
				this.bajando = false;
				this.y--;
			}
			else this.y++;
		}
		else {
			if(this.y == 0) {
				this.bajando = true;
				this.y++;
			}
			else this.y--;
		}
	}
	
	@Override
	public boolean receiveThunder() {
		System.out.print(String.format(" -> %s", this.toString()));
		return super.receiveShoot();
	}
}
