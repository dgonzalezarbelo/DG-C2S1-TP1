package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.control.Buyable;
import es.ucm.tp1.supercars.control.exceptions.CommandExecuteException;
import es.ucm.tp1.supercars.control.exceptions.CommandParseException;
import es.ucm.tp1.supercars.control.exceptions.InvalidPositionException;
import es.ucm.tp1.supercars.control.exceptions.NotEnoughCoinsException;
import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.gameobjects.GameObject;
import es.ucm.tp1.supercars.logic.gameobjects.Grenade;

public class GrenadeCommand extends Command implements Buyable {

	private static final String NAME = "grenade";

	private static final String DETAILS = "[g]renade <x> <y>";

	private static final String SHORTCUT = "g";

	private static final String HELP = "add a grenade in position x, y";
	
	private static final String FAILED_MSG = "Failed to add grenade";
	
	private static final String INCORRECT_ARGS_MSG = "Incorrect number of arguments for grenade command: [g]renade <x> <y>";

	private static final String INVALID_ARGS_MSG = "Invalid argument for grenade command, number expected";
	
	private int x;
	
	private int y;
	
	private static final int COST = 3;
	
	public GrenadeCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {	
		try {
			if(game.isValidPosition(x + game.getPlayerPosX(), y)) {
				this.buy(game);
				GameObject grenade = new Grenade(game, this.x + game.getPlayerPosX(), this.y);
				game.tryToAddGrenade(grenade);
				game.update();
				return true;
			}
			else {
				throw new InvalidPositionException("Invalid position.");
			}
		}
		catch(NotEnoughCoinsException e) {
			System.out.println(e.getMessage());
			throw new CommandExecuteException(String.format("[ERROR]: %s", FAILED_MSG, e));
		}
		catch(InvalidPositionException e) {
			System.out.println(e.getMessage());
			throw new CommandExecuteException(String.format("[ERROR]: %s", FAILED_MSG, e));
		}
	}

	@Override
	public Command parse(String[] words) throws CommandParseException {
		if(matchCommandName(words[0])) {
			if (words.length == 3) {
				try {
					this.x = Integer.parseInt(words[1]);
					this.y = Integer.parseInt(words[2]);
					return this;
				}
				catch(NumberFormatException nfe) {
					throw new CommandParseException(String.format( "[ERROR]: Command %s: %s",INVALID_ARGS_MSG), nfe);
				}
			}
			else {
				throw new CommandParseException(String.format("[ERROR]: %s", INCORRECT_ARGS_MSG));
			}
		}
		return null;
	}

	@Override
	public int cost() {
		return COST;
	}
	
}
