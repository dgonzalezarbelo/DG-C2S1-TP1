package es.ucm.tp1.supercars.logic;

import java.util.ArrayList;
import java.util.List;
import es.ucm.tp1.supercars.logic.gameobjects.GameObject;

public class GameObjectContainer {
	
	private List<GameObject> gameobjects;
	
	public GameObjectContainer() {
		gameobjects = new ArrayList<>();
	}
	
	public boolean isPosEmpty(int x, int y) {
		GameObject object = this.getObjectInPos(x, y);
		return object == null;
	}
	
	public GameObject getObjectInPos(int x, int y) {
		boolean encontrado = false;
		int i = 0;
		while(!encontrado && i < gameobjects.size()) {
			if(gameobjects.get(i).isInPosition(x, y) && gameobjects.get(i).isAlive()) encontrado = true;	//Poniendo la condicion de que este vivo nos evitamos el problema de los objetos invisibles
			else i++;
		}
		if(i < gameobjects.size())	return gameobjects.get(i);
		else return null;
	}
	
	public void add(GameObject object) {
		gameobjects.add(object);
		object.onEnter();
	}
	
	public void clear() {
		while(gameobjects.size() > 0) {
			gameobjects.get(0).onDelete();
			gameobjects.remove(0);
		}
    }
	
	public void removeDead() {
		for(int i = 0; i < gameobjects.size(); i++) {
			if(!gameobjects.get(i).isAlive()) {
				gameobjects.get(i).onDelete();
				gameobjects.remove(i);
				i--;
			}
		}
	}
	
	public void update() {
		for(int i = 0; i < gameobjects.size(); i++) gameobjects.get(i).update();
	}
	
	public void removeLastColumn(Game game) {
		for(int i = 0; i < gameobjects.size(); i++) {
			if(gameobjects.get(i).getX() == game.getPlayerPosX() + game.getVisibility() - 1) {
				gameobjects.get(i).onDelete();
				gameobjects.remove(i);
				i--;
			}
		}
	}
	
	public String getPositionToString(int x, int y) {
		String s = "";
		for (GameObject g : gameobjects) {
			if (g.isInPosition(x, y)) {
				s += g.toString() + " "; 
			}
		}
		return s;
	}
	
	public String getSerializedPositionToString(int x, int y) {
		String s = "";
		for(GameObject g : gameobjects) {
			if(g.isInPosition(x, y) && g.isAlive()) {	//Ponemos la comprobacion de isAlive para evitar que se muestren objetos sin simbolo por explosion de granada
				s += String.format("%s%n", g.serialize());
			}
		}
		return s;
	}
}
