package es.ucm.tp1.supercars.logic;

import java.util.Random;

import es.ucm.tp1.supercars.control.Level;
import es.ucm.tp1.supercars.logic.gameobjects.Player;
import es.ucm.tp1.supercars.logic.actions.InstantAction;
import es.ucm.tp1.supercars.logic.gameobjects.GameObject;

public class Game { 
	private GameObjectContainer container;
	private Player player;
	private Level level;
	private long seed;
	private boolean test;
	private int cicle;
	private int distance;
	private long timeInicial;
	private long time;
	private Record record;
	private Random random;
	private boolean win;
	private boolean exit;
	private boolean end;
	private static final String FINISH_LINE_SYMBOL = "¦";
	
	public Game(long seed, Level level, boolean test) {
		this.seed = seed;
		this.level = level;
		this.test = test;
		this.win = false;
		this.end = false;
		this.exit = false;
		this.reset();
	}
	
	public void reset() {
		this.container = new GameObjectContainer();
		this.time = 0;
        this.cicle = 0;
        this.distance = level.getRoadLength();
        this.updateGameRecord();
        this.player = new Player(this, 0, level.getRoadWidth() / 2);
        this.random = new Random(this.seed);
        GameObjectGenerator.reset(this.level);
		GameObjectGenerator.generateGameObjects(this, this.level);
	}
	
	public void reset(Level level, long seed) {	
		this.level = level;
		if(Level.TEST.equals(level)) this.test = true;
		this.seed = seed;
		this.reset();
	}
	
	public boolean isFinished() { 
		return this.win || this.end || this.exit;
	}
	
	public void updateGameRecord() {
		this.record = Record.getRecord(this.level);
	}
	
	public void updateFinalRecord() {
		this.record.updateRecord(this.time);
	}
	
	public void goUp() {
		player.goUp();
		this.update();
	}
	
	public void goDown() {
		player.goDown();
		this.update();
	}
	
	public void playerUpdate() {
		player.doCollision();
		player.update();
		this.update();
	}
	
	public void update() {
		container.update();
		GameObjectGenerator.generateRuntimeObjects(this);
		if(cicle == 0) this.timeInicial = System.currentTimeMillis();	//Inicializamos el tiempo inicial al entrar por primera vez el update
		cicle++;
		distance = level.getRoadLength() - player.getX();
		this.time = System.currentTimeMillis() - this.timeInicial;
		container.removeDead();
		if(!player.isAlive()) end = true;
		else if(distance == -1) win = true;
	}
	
	public void tryToAddObject(GameObject object, double frequency) {
		if(getRandomNumber() < frequency && container.isPosEmpty(object.getX(), object.getY())) {
			container.add(object);
		}
	}

	public void tryToAddGrenade(GameObject object) {	//No hemos usado forceAddObject para hacer la comprobacion de que la posicion esta vacia
		container.add(object);	//Asi le estamos pasando un Collider, pero recibe un GameObject
	}
	
	public boolean isValidPosition(int x, int y) {
		if(x < player.getX() || x > player.getX() + level.getVisibility() - 1 || y < 0 || y > level.getRoadWidth() - 1 || x > level.getRoadLength() - 1) {
			return false;
		}
		else {
			if(!container.isPosEmpty(x, y)) return false;
			else return true;
		}
	}
	
	public String positionToString(int x, int y) {
		int relativeX = x;
		String s = "";
		if (player.isInPosition(relativeX, y)) {
			 s = player.toString() + " ";
		}
		s += container.getPositionToString(relativeX, y);
		if (relativeX == getRoadLength()) {
			s += FINISH_LINE_SYMBOL;
		}
		return s.trim();
	}
	
	public String serializedPositionToString(int x, int y) {
		StringBuilder buffer = new StringBuilder();
		if(player.isInPosition(x, y)) {
			buffer.append(String.format("%s%n", player.serialize()));
		}
		buffer.append(container.getSerializedPositionToString(x, y));
		return buffer.toString();
	}
	
	public void execute(InstantAction action) {
		action.execute(this);
	}
	
	public void clear() {
        this.container.clear();
    }

    public void cheat(int number) {
    	container.removeLastColumn(this);
        GameObjectGenerator.forceAdvanceObject(this, number, player.getX() + level.getVisibility() - 1);
    }
    
    public void forceAddObject(GameObject o) {
		container.add(o);
	}
	
	public void resetPlayerCoinAmount() {
		this.player.resetCoinAmount();
	}
	
	public void addPlayerCoins(int amount) {
		player.addCoin(amount);
	}
	
	public void toggleTest() {
		this.test = !this.test;
	}
	
	public void setExit() {
		this.exit = true;
	}
	
	public boolean isUserExit() {
		return this.exit;
	}
	
	public boolean hasArrived() {
		return this.win;
	}
	
	public Collider getColliderInPos(int x, int y) {	//Hay que devolver un Collider, no un GameObject
		return container.getObjectInPos(x, y);
	}
	
	public int getRandomLane() {
		return (int) (getRandomNumber() * getRoadWidth());
	}
	
	public int getRandomColumn() {
		return (int) (getRandomNumber() * getVisibility());
	}
	
	public Double getRandomNumber() {
		return random.nextDouble();
	}
	
	public int getRoadWidth() {
		return level.getRoadWidth();
	}
	
	public int getRoadLength() {
		return level.getRoadLength();
	}
	
	public int getVisibility() {
		return level.getVisibility();
	}
	
	public int distanceToGoal() {
		return this.distance;
	}
	
	public int playerCoins() {
		return this.player.getCoinAmount();
	}
	
	public int getCycle() {
		return this.cicle;
	}
	
	public boolean isTestMode() {
		return this.test;
	}
	
	public long elapsedTime() {
		return this.time;
	}
	
	public long getRecordTime() {
		return this.record.getRecordTime();
	}
	
	public boolean isNewRecord() {
		return this.time < this.getRecordTime();
	}
	
	public Level getLevel() {
		return this.level;
	}
	
	public int getPlayerPosX() {
		return player.getX();
	}
	
	public int getPlayerPosY() {
		return player.getY();
	}

	public String getLevelName() {
		return level.name();
	}
	
}
