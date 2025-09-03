package es.ucm.tp1.supercars.control;

import es.ucm.tp1.supercars.control.exceptions.NotEnoughCoinsException;
import es.ucm.tp1.supercars.logic.Game;

public interface Buyable {
	
	public int cost();
	
	public default void buy(Game game) throws NotEnoughCoinsException {
		if(game.playerCoins() >= this.cost()) {
			game.addPlayerCoins(-this.cost());
		}	
		else {
			throw new NotEnoughCoinsException("Not enough coins");
		}
	}
}
