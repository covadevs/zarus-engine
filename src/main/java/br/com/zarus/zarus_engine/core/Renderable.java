package br.com.zarus.zarus_engine.core;

import java.awt.Graphics;

@FunctionalInterface
public interface Renderable {
	void render(Graphics g);
}
