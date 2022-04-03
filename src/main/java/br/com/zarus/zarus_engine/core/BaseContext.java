package br.com.zarus.zarus_engine.core;

import br.com.zarus.zarus_engine.event.EventMessage;
import br.com.zarus.zarus_engine.event.Message;
import br.com.zarus.zarus_engine.event.Observer;

public class BaseContext implements Observer {

	private static final EventMessage EVENT_MESSAGE = EventMessage.getInstance();
	private boolean isRegistred = false;
	
	protected final void pushMessage(Message message) {
		if(this.isRegistred)
			EVENT_MESSAGE.push(message);
	}
	
	protected final void registerToReceiveMessages() {
		EVENT_MESSAGE.add(this);
		this.isRegistred = true;
	}

	protected final void unsubscriber() {
		EVENT_MESSAGE.remove(this);
		this.isRegistred = false;
	}

	@Override
	public void consume(Message message) {
		//Do nothing
	}
	
}
