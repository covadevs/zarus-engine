package br.com.zarus.zarus_engine.screen;

import java.awt.Graphics;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class BasicScreen {
	
	private final Map<String, BasicScreen> screens = new LinkedHashMap<>();

	public abstract void init();
	public abstract void render(Graphics g);
	public abstract void update(double delta);
	
	public void addScreen(String key, BasicScreen screen) {
		this.screens.put(key, screen);
	}
	
	public BasicScreen getScreen(String key) {
		return this.screens.getOrDefault(key, this);
	}
	
}
