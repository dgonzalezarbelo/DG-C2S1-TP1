package es.ucm.tp1.supercars.view;

import es.ucm.tp1.supercars.control.Level;
import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.gameobjects.Coin;
import es.ucm.tp1.supercars.logic.gameobjects.Grenade;
import es.ucm.tp1.supercars.logic.gameobjects.Obstacle;
import es.ucm.tp1.supercars.logic.gameobjects.Pedestrian;
import es.ucm.tp1.supercars.logic.gameobjects.SuperCoin;
import es.ucm.tp1.supercars.logic.gameobjects.Truck;
import es.ucm.tp1.supercars.logic.gameobjects.Turbo;
import es.ucm.tp1.supercars.logic.gameobjects.Wall;
import es.ucm.tp1.supercars.utils.StringUtils;

public class GamePrinter extends View {

	private Game game;
	
	private String indentedRoadBorder;

	private String indentedLanesSeparator;

	private String margin;
	
	private int visibility;

	public GamePrinter(Game game) {
		this.game = game;
		this.bordes(game);
	}
	
	public void bordes(Game game) {
		this.visibility = game.getVisibility();
		margin = StringUtils.repeat(SPACE, MARGIN_SIZE);

		String roadBorder = ROAD_BORDER_PATTERN
				+ StringUtils.repeat(ROAD_BORDER_PATTERN, (CELL_SIZE + 1) * game.getVisibility());
		indentedRoadBorder = String.format("%n%s%s%n", margin, roadBorder);

		String laneDelimiter = StringUtils.repeat(LANE_DELIMITER_PATTERN, CELL_SIZE);
		String lanesSeparator = SPACE + StringUtils.repeat(laneDelimiter + SPACE, game.getVisibility() - 1)
				+ laneDelimiter + SPACE;

		indentedLanesSeparator = String.format("%n%s%s%n", margin, lanesSeparator);
	}
	
	protected String getInfo() {
		StringBuilder buffer = new StringBuilder();
		/* @formatter:off */
		buffer
		.append(DISTANCE_MSG).append(game.distanceToGoal()).append(StringUtils.LINE_SEPARATOR)
		.append(COINS_MSG).append(game.playerCoins()).append(StringUtils.LINE_SEPARATOR)
		.append(CYCLE_MSG).append(game.getCycle()).append(StringUtils.LINE_SEPARATOR)
		.append(TOTAL_OBSTACLES_MSG).append(Obstacle.getObstaclesCount()).append(StringUtils.LINE_SEPARATOR)
		.append(TOTAL_COINS_MSG).append(Coin.getCoinsCount());
		/* @formatter:on */
		if (game.getLevel().hasAdvancedObjects()) {
			if (SuperCoin.hasSuperCoin()) {
				buffer.append(StringUtils.LINE_SEPARATOR).append(SUPERCOIN_PRESENT);
			}
		}

		if (!game.isTestMode()) {
			/* @formatter:off */
			buffer
			.append(StringUtils.LINE_SEPARATOR)
			.append(ELAPSED_TIME_MSG).append(formatTime(game.elapsedTime()));
			/* @formatter:on */
		}

		return buffer.toString();
	}

	@Override
	public String toString() {
		if(this.visibility != game.getVisibility()) {
			this.bordes(game);
		}
		StringBuilder str = new StringBuilder();

		// Game Status

		str.append(getInfo());

		// Paint game

		str.append(indentedRoadBorder);

		String verticalDelimiter = SPACE;

		for (int y = 0; y < game.getRoadWidth(); y++) {
			str.append(this.margin).append(verticalDelimiter);
			for (int x = game.getPlayerPosX(); x < game.getPlayerPosX() + game.getVisibility(); x++) { //Cambiado por nosotros
				str.append(StringUtils.centre(game.positionToString(x, y), CELL_SIZE)).append(verticalDelimiter);
			}
			if (y < game.getRoadWidth() - 1) {
				str.append(this.indentedLanesSeparator);
			}
		}
		str.append(this.indentedRoadBorder);

		return str.toString();
	}

	public String endMessage() {

		StringBuilder buffer = new StringBuilder(GAME_OVER_MSG);

		if (game.isUserExit()) {
			return buffer.append(DO_EXIT_MSG).toString();
		}

		if (game.hasArrived()) {
			buffer.append(WIN_MSG);
			if (!game.isTestMode()) {
				if (game.isNewRecord()) {
					buffer.append(NEW_RECORD_MSG).append(formatTime(game.elapsedTime()));
				} else {
					buffer.append(RECORD_MSG).append(formatTime(game.getRecordTime()));
				}
			}
		} else {
			buffer.append(CRASH_MSG);
		}

		return buffer.toString();
	}
	

	public static String description(Level level) {
		StringBuilder buffer = new StringBuilder("[Car] the racing car");
		/* @formatter:off */
		buffer
		.append(StringUtils.LINE_SEPARATOR).append(Coin.INFO)
		.append(StringUtils.LINE_SEPARATOR).append(Obstacle.INFO)
		.append(StringUtils.LINE_SEPARATOR).append(Grenade.INFO)
		.append(StringUtils.LINE_SEPARATOR).append(Wall.INFO)
		.append(StringUtils.LINE_SEPARATOR).append(Turbo.INFO)
		.append(StringUtils.LINE_SEPARATOR).append(SuperCoin.INFO)
		.append(StringUtils.LINE_SEPARATOR).append(Truck.INFO)
		.append(StringUtils.LINE_SEPARATOR).append(Pedestrian.INFO);
		/* @formatter:on */

		return buffer.toString();
	}
}
