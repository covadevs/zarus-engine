package br.com.zarus.zarus_engine.loop;

import br.com.zarus.zarus_engine.conf.Configurations;
import br.com.zarus.zarus_engine.conf.ConfigurationsMessage;
import br.com.zarus.zarus_engine.core.BaseContext;
import br.com.zarus.zarus_engine.debug.DebugWriter;
import br.com.zarus.zarus_engine.event.Message;
import br.com.zarus.zarus_engine.loop.services.FramerateService;
import br.com.zarus.zarus_engine.loop.services.JVMInfoService;
import br.com.zarus.zarus_engine.screen.MainScreen;

public class GameLoop extends BaseContext {

	private static boolean running = false;
	private static boolean pause = false;

	// Concrete Objects
	private MainScreen entrypoint;

	public GameLoop(MainScreen entryPoint) {
		this.entrypoint = entryPoint;
		super.registerToReceiveMessages();
	}

	private synchronized void loop() {
		if (isRunning()) {
			entrypoint.setSize(Configurations.getInt(Configurations.SCREEN_SIZE_WIDTH),
					Configurations.getInt(Configurations.SCREEN_SIZE_HEIGHT));
			entrypoint.open();
			entrypoint.centralized();
			
			entrypoint.init();

			FramerateService.getInstance().start();
			JVMInfoService.getInstance().start();
		}


		long lastTimeNs = System.nanoTime();
		double desiredFps = Configurations.getDouble(Configurations.SCREEN_FRAMERATE_RATE);
		double intervalBetweenFps = 1000000000.0d / desiredFps;
		double lag = 0.0d;
		while (isRunning()) {
			long nowNs = nowNs();
			long elapsedTimeNs = nowNs - lastTimeNs;
			lastTimeNs = nowNs;
			lag += elapsedTimeNs;

			// process input

			while (lag >= intervalBetweenFps) {
				DebugWriter.resetCurrentLine();
				if (!isPaused()) {
					FramerateService.getInstance().update(lag);
					this.entrypoint.update(lag);
				}
				this.entrypoint.render();
				FramerateService.getInstance().addFramerateCount();
				lag -= intervalBetweenFps;
			}
		}
		
		System.exit(0);
	}

	public Long nowNs() {
		return System.nanoTime();
	}

	@Override
	public void consume(Message message) {
		if (ConfigurationsMessage.LOAD_EXCEPTION_MESSAGE.equals(message.getMessage()))
			setIsRunning(false);
	}

	public void start() {
		setIsRunning(true);
		loop();
	}

	private static void setIsRunning(boolean isRunning) {
		GameLoop.running = isRunning;
	}

	public static final boolean isRunning() {
		return running;
	}
	
	public static final boolean isPaused() {
		return pause;
	}
	
	public static final void exit() {
		setIsRunning(false);
	}
	
	public static final void pause() {
		if (!isPaused()) {
			GameLoop.pause = true;
			return;
		}
		
		resume();
	}
	
	public static final void resume() {
		GameLoop.pause = false;
	}

}
