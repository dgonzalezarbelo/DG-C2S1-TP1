package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.actions.ExplosionAction;

public class Grenade extends GameObject {

	public static final String INFO = "[GRENADE] Explodes in 3 cycles, harming everyone around";
	
	private int ciclos;
	
	private ExplosionAction action;
	
	private static final int MAX_CICLOS = 4;	//Lo ponemos a 4 para que en el primer update pase a valer 3
	
	public Grenade(Game game, int x, int y) {
		super(game, x, y);
		this.symbol = "ð";
		this.ciclos = MAX_CICLOS;
	}

	@Override
	public boolean doCollision() {
		return false;
	}

	@Override
	public boolean receiveCollision(Player player) {
		return false;
	}

	@Override
	public boolean receiveShoot() {
		return false;
	}

	@Override
	public void onEnter() {}

	@Override
	public void update() {
		ciclos--;
		if(ciclos == 0) {
			this.kill();
		} 
	}
	
	@Override
	public String toString() {
		if (this.isAlive()) {
			return getSymbol() + String.format("[%d]", ciclos);
		}
		return "";
	}
	
	@Override
	public String serialize() {
		String str;
		str = String.format("%s %d", super.serialize(), this.ciclos);
		return str;
	}
	
	@Override
	public void onDelete() {
		this.action = new ExplosionAction(this.x, this.y);
		this.game.execute(action);
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
