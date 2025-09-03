package es.ucm.tp1.supercars.view;

import es.ucm.tp1.supercars.logic.Game;

public class GameSerializer extends View {

	private Game game;
	
	public GameSerializer(Game game) {
		this.game = game;
	}
	
	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		buffer.append(String.format("%s%n", GamePrinter.SERIALIZER_MSG));
		buffer.append(String.format("%s%s%n", GamePrinter.LEVEL_MSG, game.getLevelName()));
		buffer.append(String.format("%s%s%n", GamePrinter.CYCLE_MSG, game.getCycle()));
		buffer.append(String.format("%s%s%n", GamePrinter.COINS_MSG,game.playerCoins()));
		if(!game.isTestMode()) buffer.append(String.format("%s%s%n", GamePrinter.ELAPSED_TIME_MSG, game.elapsedTime()));
		buffer.append(String.format("%s%n", GamePrinter.GAME_OBJECTS_MSG));
		
		for(int i = 0; i < game.getRoadLength(); i++	) {
			for(int j = 0; j < game.getRoadWidth(); j++) {
				buffer.append(game.serializedPositionToString(i, j));
			}
		}
		
		return buffer.toString();
	}
	
}
