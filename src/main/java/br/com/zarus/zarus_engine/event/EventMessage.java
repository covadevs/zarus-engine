package br.com.zarus.zarus_engine.event;

import java.util.ArrayList;
import java.util.List;

public class EventMessage implements Observable {

	private static final EventMessage INSTANCE = new EventMessage();

	private final List<Observer> observers;
	
	private EventMessage() {
		this.observers = new ArrayList<>();
	}

	public void add(Observer observer) {
		this.observers.add(observer);
	}

	public void remove(Observer observer) {
		this.observers.remove(observer);
	}
	
	public void push(Message message) {
		this.notifyObservers(message);
	}

	public void notifyObservers(Message message) {
		this.observers.forEach(o -> o.consume(message));
	}

	public static EventMessage getInstance() {
		return INSTANCE;
	}

	public int countSubscribers() {
		return this.observers.size();
	}

}
