package es.ucm.tp1.supercars.logic.actions;

import es.ucm.tp1.supercars.logic.Collider;
import es.ucm.tp1.supercars.logic.Game;

public class ThunderAction implements InstantAction {

	private int x;
	
	private int y;
	
	@Override
	public void execute(Game game) {
		this.x = game.getRandomColumn();
		this.y = game.getRandomLane();
		System.out.print(String.format("Thunder hit position: (%d , %d)", x, y));
		Collider obj = game.getColliderInPos(this.x + game.getPlayerPosX(), this.y);
		if(obj != null) obj.receiveThunder();
		System.out.print(String.format("%n"));
	}

}
