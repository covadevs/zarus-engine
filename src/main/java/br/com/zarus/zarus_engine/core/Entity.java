package br.com.zarus.zarus_engine.core;

import br.com.zarus.zarus_engine.position.Vector2D;

public abstract class Entity extends BaseContext implements Updatable, Renderable {
		
	protected Vector2D position = new Vector2D(0, 0);
	protected boolean remove = false;
	
	protected Entity(Vector2D position) {
		this.position = position;
	}
	
	protected Entity() {
		
	}

	public boolean isRemove() {
		return remove;
	}
	
	public Vector2D getPosition() {
		return this.position;
	}

}
