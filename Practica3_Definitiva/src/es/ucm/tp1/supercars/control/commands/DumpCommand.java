package es.ucm.tp1.supercars.control.commands;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import es.ucm.tp1.supercars.control.exceptions.CommandExecuteException;
import es.ucm.tp1.supercars.control.exceptions.CommandParseException;
import es.ucm.tp1.supercars.logic.Game;

public class DumpCommand extends Command {
	
	private static final String NAME = "dump";

	private static final String DETAILS = "[d]ump <filename>";

	private static final String SHORTCUT = "d";

	private static final String HELP = "Shows the content of a saved file";
	
	private static final String FAILED_MSG = "An error ocurred on reading a file";
	
	private String filename;

	public DumpCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		try (BufferedReader inChars = new BufferedReader(new FileReader(filename + ".txt"))){
			String l;
			while((l = inChars.readLine()) != null) {
				System.out.println(l);
			}
		}
		catch (IOException e) {
			throw new CommandExecuteException(FAILED_MSG);
		}
		return false;
	}
	
	@Override
	public Command parse(String[] words) throws CommandParseException {
		if(matchCommandName(words[0])) {
			if(words.length == 2) {
				filename = words[1];
				return this;
			}
			else {
				throw new CommandParseException(String.format("[ERROR]: Command %s: %s", SHORTCUT, INCORRECT_NUMBER_OF_ARGS_MSG));
			}
		}
		return null;
	}
}
