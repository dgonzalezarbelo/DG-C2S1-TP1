package es.ucm.tp1.supercars.logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import es.ucm.tp1.supercars.control.Level;
import es.ucm.tp1.supercars.control.exceptions.InputOutputRecordException;

public class Record {

	private static final String FILENAME = "record.txt";
	
	private static final String FAILED_READ_MSG = "An error ocurred trying to read a file";
	
	private static final String FAILED_WRITE_MSG = "An error ocurred trying to write to a file";
	
	private static Record[] records;
	
	private Level level;	//Mantenemos el atributo level por posibles usos
	
	private long currentRecord;
	
	public Record(Level level, long currentRecord) {
		this.level = level;
		this.currentRecord = currentRecord;
	}
	
	public static Record getRecord(Level level) {
		if(records[level.ordinal()] == null) {
			System.out.format("Creating default record for level '%s'%n", level.name());	//Cada vez que se cargue un record no existente se tiene que mostrar el mensaje
			return new Record(level, Long.MAX_VALUE);
		}
		else return records[level.ordinal()];
	}
	
	public static void loadRecords() throws InputOutputRecordException {
		
		records = new Record[Level.values().length];
		
		for(int i = 0; i < records.length; i++) {
			records[i] = null;
		}
		
		try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(FILENAME)))) {
			long recordTime;
			while(scanner.hasNext()) {
				String line = scanner.nextLine();
				String[] splitLine = line.split(":");
				Level level = Level.valueOfIgnoreCase(splitLine[0]);
				if(level == null) {
					throw new InputOutputRecordException(FAILED_READ_MSG);
				}
				try {
					recordTime = Long.parseLong(splitLine[1]);
				}
				catch (NumberFormatException e) {
					throw new InputOutputRecordException(FAILED_READ_MSG, e);
				}
				records[level.ordinal()] = new Record(level, recordTime);
			}
		}
		catch (IOException e) {
			throw new InputOutputRecordException(FAILED_READ_MSG, e);
		}
	}
	
	public long getRecordTime()	{
		return currentRecord;
	}
	
	public void updateRecord(long elapsedTime) {
		if(elapsedTime < this.currentRecord) {
			currentRecord = elapsedTime;
		} 
	}
	
	public static void saveRecords() throws InputOutputRecordException {
		try(BufferedWriter outStream = new BufferedWriter(new FileWriter(FILENAME))){
			for(Level level : Level.values()) {
				if(records[level.ordinal()] != null) outStream.write(String.format("%s:%d", level.name(), records[level.ordinal()].getRecordTime()));
				else outStream.write(String.format("%s:%d", level.name(), Long.MAX_VALUE));
				outStream.newLine();
			}
		}
		catch (IOException e) {
			throw new InputOutputRecordException(FAILED_WRITE_MSG);
		}
	}
}
