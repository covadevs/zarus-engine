package br.com.zarus.zarus_engine.loop.services;

import br.com.zarus.zarus_engine.loop.GameLoop;
import br.com.zarus.zarus_engine.screen.MainScreen;

public class GameLoopCreatorService {

	private static final GameLoopCreatorService INSTANCE = new GameLoopCreatorService();

	private GameLoopCreatorService() {}

	public GameLoop create(MainScreen entrypoint) {
		return new GameLoop(entrypoint);
	}

	public static GameLoopCreatorService getInstance() {
		return INSTANCE;
	}

}
