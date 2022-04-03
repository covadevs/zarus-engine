package br.com.zarus.zarus_engine.core;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import br.com.zarus.zarus_engine.event.Message;
import br.com.zarus.zarus_engine.position.Vector2D;

public abstract class Spawn<E extends Entity> extends Entity {

	private long ticksToSpawn;
	private long maxSurge;
	private long currentTick = 0L;

	private List<E> entitiesSpawned;

	private boolean checkForEntityToRemove = false;

	protected Spawn(long ticksToSpawn, long maxSurge) {
		super();
		this.entitiesSpawned = new ArrayList<>();
		this.ticksToSpawn = ticksToSpawn;
		this.maxSurge = maxSurge;
		super.registerToReceiveMessages();
	}
	
	protected Spawn(long ticksToSpawn, long maxSurge, Vector2D position) {
		this(ticksToSpawn, maxSurge);
		this.position = position;
	}

	public void render(Graphics g) {
		this.entitiesSpawned.forEach(e -> e.render(g));
	}

	public void update(double delta) {

		if (this.checkForEntityToRemove) {
			this.entitiesSpawned.removeIf(Entity::isRemove);
			this.checkForEntityToRemove = false;
		}

		this.entitiesSpawned.forEach(e -> e.update(delta));

		if (this.currentTick++ >= this.ticksToSpawn && this.entitiesSpawned.size() < this.maxSurge) {
			this.currentTick = 0L;
	
			this.entitiesSpawned.add(create());
		}

	}

	@Override
	public void consume(Message message) {
		super.consume(message);
		if (EntityMessages.REMOVE.equals(message.getMessage()) && !this.checkForEntityToRemove) {
			this.checkForEntityToRemove = true;
		}
	}

	public abstract E create();


}
