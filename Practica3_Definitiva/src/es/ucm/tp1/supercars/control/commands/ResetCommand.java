package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.SuperCars;
import es.ucm.tp1.supercars.control.Level;
import es.ucm.tp1.supercars.control.exceptions.CommandParseException;
import es.ucm.tp1.supercars.logic.Game;

public class ResetCommand extends Command {//Creada por nosotros
	private static final String NAME = "reset";

	private static final String DETAILS = "[r]eset [<level> <seed>]";

	private static final String SHORTCUT = "r";

	private static final String HELP = "reset game";
	
	private int parameters;
	
	private Level level;
	
	private long seed;

	public ResetCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}
	
	@Override
	public boolean execute(Game game) {
		if(this.parameters == 1) {
			game.reset();
		}
		else {
			System.out.print("Level: " + level.name() + String.format("%n"));
			System.out.print(SuperCars.SEED_INFO_MSG + seed + String.format("%n"));
			game.reset(level, seed);
		}
		return true;
	}
	
	//Opciones para invocar a reset:
	//reset 
	//reset level seed
	
	@Override
	public Command parse(String[] words) throws CommandParseException {
		if(matchCommandName(words[0])) {
			if(words.length == 1) {
				parameters = words.length;
				return this;
			}
			else if (words.length == 3) {
				this.level = Level.valueOfIgnoreCase(words[1]);
				if(this.level != null) {
					try {
						this.seed = Long.parseLong(words[2]);
						parameters = words.length;
						return this;
					}
					catch (NumberFormatException nfe) {
						throw new CommandParseException(String.format( "[ERROR]: Command %s: %s", words[0], PARSE_LONG_MSG), nfe);
					}
				}
				else {
					throw new CommandParseException(String.format( "[ERROR]: Command %s: %s", words[0], SuperCars.LEVEL_INFO_MSG));
				}
			}
			else {
				throw new CommandParseException(String.format("[ERROR]: Command %s: %s", words[0], INCORRECT_NUMBER_OF_ARGS_MSG));
			}
		}
		return null;
	}
	
}
