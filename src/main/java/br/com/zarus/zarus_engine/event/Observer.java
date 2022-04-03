package br.com.zarus.zarus_engine.event;

@FunctionalInterface
public interface Observer {
	void consume(Message message);
}
