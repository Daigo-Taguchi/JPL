package practice.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Obserbable {
	private List<Observer> observers = new ArrayList<>();
	
	public void addObserver(Observer observer) {
		this.observers.add(observer);
	}
	
	public void deleteObserver(Observer observer) {
		this.observers.remove(observer);
	}
	
	public void notifyConstructorObservers() {
		for (Observer observer: this.observers) {
			observer.updateConstructor();
		}
	}
	
	public void notifyIntanceObservers() {
		for (Observer observer: this.observers) {
			observer.updateInstance();
		}
	}
}
