package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.control.exceptions.CommandExecuteException;
import es.ucm.tp1.supercars.control.exceptions.CommandParseException;
import es.ucm.tp1.supercars.logic.Game;

public abstract class Command {

	private static final String UNKNOWN_COMMAND_MSG = "Unknown command";

	protected static final String INCORRECT_NUMBER_OF_ARGS_MSG = "Incorrect number of arguments";
	
	protected static final String PARSE_LONG_MSG = "the seed is not a proper long number";

	/* @formatter:off */
	protected static final Command[] AVAILABLE_COMMANDS = {
		new HelpCommand(),
		new InfoCommand(),
		new UpdateCommand(),
		new MoveUpCommand(),
		new MoveDownCommand(),
		new ExitCommand(),
		new ResetCommand(),
		new TestCommand(),
		new ShootCommand(),
		new GrenadeCommand(),
		new WaveCommand(),
		new SerializeCommand(),
		new SaveCommand(),
		new DumpCommand(),
		new ShowRecordCommand(),
		new ClearCommand(),
		new CheatCommand()
	};
	/* @formatter:on */
	
	public static Command getCommand(String[] commandWords) throws CommandParseException { //Habria que hacer un bucle para ver con qu� comando coincide
		Command command = null;
		boolean found = false;
		int i = 0;
		while(!found && i < AVAILABLE_COMMANDS.length) {
			command = AVAILABLE_COMMANDS[i].parse(commandWords);
			if(command != null) found = true;
			else i++;
		}
		if(!found) {
			throw new CommandParseException(String.format("[ERROR]: %s", UNKNOWN_COMMAND_MSG));
		}
		return command;
	}
	
	private final String name;

	private final String shortcut;

	private final String details;

	private final String help;

	public Command(String name, String shortcut, String details, String help) {
		this.name = name;
		this.shortcut = shortcut;
		this.details = details;
		this.help = help;
	}

	public abstract boolean execute(Game game) throws CommandExecuteException;

	protected boolean matchCommandName(String name) {
		return this.shortcut.equalsIgnoreCase(name) || this.name.equalsIgnoreCase(name);
	}

	protected Command parse(String[] words) throws CommandParseException {
		if (matchCommandName(words[0])) {
			if (words.length != 1) {
				throw new CommandParseException(String.format("[ERROR]: Command %s: %s", name, INCORRECT_NUMBER_OF_ARGS_MSG));
			} else {
				return this;
			}
		}
		return null;
	}
	
	protected String helpMsg() {
		StringBuilder msg = new StringBuilder(this.details);
		msg.append(": ").append(this.help);
		return msg.toString();
	}

}
