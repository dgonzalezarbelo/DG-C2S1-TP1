package es.ucm.tp1.supercars.control.commands;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import es.ucm.tp1.supercars.control.exceptions.CommandExecuteException;
import es.ucm.tp1.supercars.control.exceptions.CommandParseException;
import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.view.GameSerializer;

public class SaveCommand extends Command {

	private static final String NAME = "save";

	private static final String DETAILS = "sa[v]e <filename>";

	private static final String SHORTCUT = "v";

	private static final String HELP = "Save the state of the game to a file.";
	
	private static final String FAILED_MSG = "An error ocurred on writing a file";
	
	private String filename;
	
	private GameSerializer serializer;
	
	public SaveCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}
	
	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		try (BufferedWriter outChars = new BufferedWriter(new FileWriter(filename + ".txt"))){
			serializer = new GameSerializer(game);
			String buffer = String.format("Super cars 3.0%n%n%s", serializer.toString());
			outChars.write(buffer);
			System.out.format("Game successfully saved to file %s.txt%n", filename);
		}
		catch (IOException e) {
			throw new CommandExecuteException(FAILED_MSG, e);
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
				throw new CommandParseException(String.format("[ERROR]: Command %s: %s", NAME, INCORRECT_NUMBER_OF_ARGS_MSG));
			}
		}
		return null;
	}
}
