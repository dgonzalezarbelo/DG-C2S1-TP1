package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.logic.Game;

public class CheatCommand extends Command {

    private static final String NAME = "Cheat";

    private static final String DETAILS = "Cheat [1..5]";

    private static final String SHORTCUT = "[1..5]";

    private static final String HELP = "Removes all elements of last visible column, and adds an Advanced Object";

    private int number;

    public CheatCommand() {
        super(NAME, SHORTCUT, DETAILS, HELP);
    }

    @Override
    public Command parse(String[] words) {
        if(words.length == 1) {
            try {
                this.number = Integer.parseInt(words[0]);
                if(number < 1 || number > 5) {
                    return null;
                }
                return this;
            }
            catch (NumberFormatException nfe) {
                return null;
            }
        }
        return null;
    }

    @Override
    public boolean execute(Game game) {
        game.cheat(number);
        return true;
    }
}