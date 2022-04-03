package br.com.zarus.zarus_engine.conf;

import java.util.Properties;
import br.com.zarus.zarus_engine.core.BaseContext;
import br.com.zarus.zarus_engine.event.Message;
import br.com.zarus.zarus_engine.loop.GameLoop;

public class Configurations extends BaseContext {

	public static final String SCREEN_SIZE_WIDTH = "screen.size.width";
	public static final String SCREEN_SIZE_HEIGHT = "screen.size.height";
	public static final String SCREEN_FRAMERATE_RATE = "screen.framerate.rate";
	public static final String SCREEN_FRAMERATE_SHOW = "screen.framerate.show";
	public static final String SCREEN_DISPLAY_TITLE = "screen.display.title";
	public static final String LANGUAGE = "lang";

	private static final Configurations instance = new Configurations();
	private final Properties properties;


	private Configurations() {
		this.properties = new Properties();
		this.registerToReceiveMessages();
	}

	public static void load() {
		load("/conf/zarus_engine.properties");
	}

	public static void load(String path) {
		try {
			instance.properties.load(GameLoop.class.getResourceAsStream(path));
		} catch (Exception e) {
			instance.pushMessage(new Message(ConfigurationsMessage.LOAD_EXCEPTION_MESSAGE));
		}
	}

	public static int getInt(Object key) {
		return Integer.parseInt((String) instance.properties.get(key));
	}

	public static double getDouble(Object key) {
		return Double.parseDouble((String) instance.properties.get(key));
	}

	public static boolean getBoolean(Object key) {
		return Boolean.parseBoolean((String) instance.properties.get(key));
	}

	public static String getString(Object key) {
		return (String) instance.properties.get(key);
	}

}
