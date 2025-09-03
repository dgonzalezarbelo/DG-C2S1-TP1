package es.ucm.tp1.supercars.logic.actions;

import es.ucm.tp1.supercars.logic.Collider;
import es.ucm.tp1.supercars.logic.Game;

public class ShootAction implements InstantAction {

	private int x;
	
	private int y;
	
	public ShootAction(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void execute(Game game) {
		boolean hit = false;
		int i = 0;
		while(!hit && i < game.getVisibility()) {
			Collider obj = game.getColliderInPos(x + i, y);
			if(obj != null) {
				hit = obj.receiveShoot();
			}
			i++;
		}
	}
}
