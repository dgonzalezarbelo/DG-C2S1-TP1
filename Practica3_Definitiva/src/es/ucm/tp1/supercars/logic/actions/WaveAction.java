package es.ucm.tp1.supercars.logic.actions;

import es.ucm.tp1.supercars.logic.Collider;
import es.ucm.tp1.supercars.logic.Game;

public class WaveAction implements InstantAction {

	@Override
	public void execute(Game game) {
		int x = game.getPlayerPosX();
		for(int i = game.getVisibility() - 1; i >= 0; i--) {
			for(int j = 0; j < game.getRoadWidth(); j++) {
				Collider obj = game.getColliderInPos(x + i, j);
				if(obj != null) obj.moveRight();
			}
		}
	}

}
