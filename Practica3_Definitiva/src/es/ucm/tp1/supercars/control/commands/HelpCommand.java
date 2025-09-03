package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.logic.Game;

public class HelpCommand extends Command {

	private static final String NAME = "help";

	private static final String DETAILS = "[h]elp";

	private static final String SHORTCUT = "h";

	private static final String HELP = "show this help";

	public HelpCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	@Override
	public boolean execute(Game game) {
		StringBuilder buffer = new StringBuilder("Available commands:");
		for(int i = 0; i < Command.AVAILABLE_COMMANDS.length; i++) { 
			buffer.append(String.format("%n") + AVAILABLE_COMMANDS[i].helpMsg());
		}

		System.out.println(buffer.toString());

		return false;
	}

}
