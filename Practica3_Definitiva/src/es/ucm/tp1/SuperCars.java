package es.ucm.tp1;

import java.util.Scanner;
import es.ucm.tp1.supercars.control.Controller;
import es.ucm.tp1.supercars.control.Level;
import es.ucm.tp1.supercars.control.exceptions.InputOutputRecordException;
import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.Record;

public class SuperCars {

	private static final String VERSION = "3.0";

	private static final String USAGE_MSG = "Usage: Super cars <level> [<seed>]";

	private static final String WELCOME_MSG = String.format("Super cars %s%n%n", VERSION);

	public static final String LEVEL_INFO_MSG = "Level must be one of: " + Level.all(", ");	//Lo pasamos a public porque es una constante y nos viene bien en reset

	private static final String SEED_IS_NUMBER_MSNG = "the seed must be a number";

	public static final String SEED_INFO_MSG = "Random generator initialized with seed: ";	//Lo pasamos a public porque es una constante y nos viene bien en reset

	
	private static void usage() {
		System.out.print(USAGE_MSG + "\n");
		System.out.print("\t<level>: " + Level.all(", ") + "\n");
		System.out.print("\t<seed>: " + SEED_IS_NUMBER_MSNG + "\n");
	}

	public static void main(String[] args) throws InputOutputRecordException {
		if (args.length < 1 || args.length > 2) {
			usage();
		} else {
			Level level = Level.valueOfIgnoreCase(args[0]);
			if (level == null) {
				System.out.println(LEVEL_INFO_MSG);
				usage();
			} else {
				Long seed;
				try {
					if (args.length == 2) {
						seed = Long.parseLong(args[1]);
					} else {
						seed = System.currentTimeMillis() % 1000;
					}

					System.out.print(WELCOME_MSG);

					System.out.print("Level: " + level.name() + String.format("%n"));
					System.out.print(SEED_INFO_MSG + seed + String.format("%n"));

					boolean isTestMode = Level.TEST.equals(level);
					Record.loadRecords();
					Controller controller = new Controller(new Game(seed, level, isTestMode), new Scanner(System.in)); // aqui se crea el game
					controller.run();
				} catch (NumberFormatException nfe) {
					System.out.println(SEED_IS_NUMBER_MSNG);
					usage();
				} 
			}
		}
	}

}

