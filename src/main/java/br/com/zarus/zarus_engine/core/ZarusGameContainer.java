package br.com.zarus.zarus_engine.core;

import java.awt.event.KeyListener;
import br.com.zarus.zarus_engine.conf.Configurations;
import br.com.zarus.zarus_engine.lang.LangLoader;
import br.com.zarus.zarus_engine.loop.GameLoop;
import br.com.zarus.zarus_engine.loop.services.GameLoopCreatorService;
import br.com.zarus.zarus_engine.screen.BasicScreen;
import br.com.zarus.zarus_engine.screen.MainScreen;
import br.com.zarus.zarus_engine.screen.service.MainScreenCreatorService;

public class ZarusGameContainer {

	private GameLoop gameLoop;
	private MainScreen mainScreen;

	public ZarusGameContainer(BasicScreen screen) {
		Configurations.load();
		LangLoader.getInstance().load();
		this.mainScreen = MainScreenCreatorService.getInstance().create(screen);
		configureGameLoop(mainScreen);
	}

	private void configureGameLoop(MainScreen entrypoint) {
		this.gameLoop = GameLoopCreatorService.getInstance().create(entrypoint);
	}

	public void start() {
		this.gameLoop.start();
	}
	
	public void addKeyListener(KeyListener l) {
		this.mainScreen.addKeyListener(l);
	}
	
	public void requestFocus() {
		this.mainScreen.requestFocus();
	}

}
