package br.com.zarus.zarus_engine.utils;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public class GraphicsUtils {
	private GraphicsUtils() {}
	
	public static void draw(Rectangle2D rect, Graphics g) {
		g.drawRect((int)rect.getX(), (int)rect.getY(), (int)rect.getWidth(), (int)rect.getHeight());
	}
}
