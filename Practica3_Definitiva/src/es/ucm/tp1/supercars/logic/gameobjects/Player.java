package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Collider;
import es.ucm.tp1.supercars.logic.Game;

public class Player extends GameObject {
	
	private int coinAmount;
	public static final String simbAlive = ">";
    public static final String simbCrashed = "@";
    
	public Player(Game game, int x, int y) {
		super(game, x, y);
		this.coinAmount = 5;
	}
	
	@Override
	public String toString() {
		return this.getSymbol();
	}
	
	@Override
	public String getSymbol() {
		if(this.alive) return simbAlive;
		else return simbCrashed;
	}
	
	@Override
	public boolean doCollision() {
		Collider other = game.getColliderInPos(x, y);
		if(other != null) {
			return other.receiveCollision(this);
		}
		return false;
	}

	@Override
	public void update() {
		if(this.alive) {
			this.addPos(1, 0);
			this.doCollision();
		}
	}
	
	public void addPos(int x, int y) {
		this.x += x;
		this.y += y;
	}
	
	public void goUp() {
		this.doCollision();
		if(this.alive && !this.isInPosition(this.x, 0)) {
			this.addPos(0, -1);
		}
		this.update();
	}
	
	public void goDown() {
		this.doCollision();
		if(this.alive && !this.isInPosition(this.x, this.game.getRoadWidth() - 1)) {
			this.addPos(0, 1);
		}
		this.update();
	}
	
	public void addCoin(int amount){
		this.coinAmount += amount;
	}
	
	public void resetCoinAmount() {
		this.coinAmount = 0;
	}
	
	public int getCoinAmount() {
		return this.coinAmount;
	}

	@Override
	public boolean receiveCollision(Player player) {
		return false;
	}

	@Override
	public void onEnter() {}

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
