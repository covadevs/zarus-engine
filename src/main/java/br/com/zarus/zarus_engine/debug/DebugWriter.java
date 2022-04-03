package br.com.zarus.zarus_engine.debug;

import java.awt.Graphics;
import br.com.zarus.zarus_engine.conf.Configurations;

public class DebugWriter {
	
	private DebugWriter() {}
	
	private static int currentLineLeft = 0;
	private static int currentLineRight = 0;

	public static void logSpacesOnLeft(int times, Graphics g) {
		for (int i = 0; i < times; i++)
			g.drawString("", 1, ++currentLineLeft * 10);
	}

	public static void logSpacesOnRight(int times, Graphics g) {
		for (int i = 0; i < times; i++)
			g.drawString("", 1, ++currentLineRight * 10);
	}
	
	public static void log(String message, Graphics g) {
		g.drawString(message, 1, ++currentLineLeft * 10);
	}
	
	public static void logRight(String message, Graphics g) {
		g.drawString(message, Configurations.getInt(Configurations.SCREEN_SIZE_WIDTH) - g.getFontMetrics().stringWidth(message), ++currentLineRight * 10);
	}
	
	public static void resetCurrentLine() {
		currentLineLeft = 0;
		currentLineRight = 0;
	}

}
