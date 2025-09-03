package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Collider;
import es.ucm.tp1.supercars.logic.Game;

public abstract class GameObject implements Collider {

	protected int x, y;

	protected Game game;

	protected String symbol;
	
	protected boolean alive; //Añadido por nosotros

	public GameObject(Game game, int x, int y) {
		this.x = x;
		this.y = y;
		this.game = game;
		this.alive = true;
	}

	protected String getSymbol() {
		return symbol;
	}

	@Override
	public String toString() {
		if (isAlive()) {
			return getSymbol();
		}
		return "";
	}
	
	public String serialize() {
		String str;
		str = String.format("%s (%d, %d)", this.toString(), this.x, this.y);
		return str;
	}

	public boolean isInPosition(int x, int y) {
		return this.x == x && this.y == y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isAlive() {
		return alive;
	}
	
	public void kill() {
		this.alive = false;
		this.symbol = "";
	}

	public void moveRight() {
		Collider obj = game.getColliderInPos(this.x + 1, this.y);
		if(obj == null) this.x++;
	}
	
	public abstract void onEnter();

	public abstract void update();

	public abstract void onDelete();

}
