package br.com.zarus.zarus_engine.event;


public interface Observable {
	void add(Observer observer);
	void remove(Observer observer);
	void notifyObservers(Message message);
}
