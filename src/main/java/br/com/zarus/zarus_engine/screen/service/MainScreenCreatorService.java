package br.com.zarus.zarus_engine.screen.service;

import br.com.zarus.zarus_engine.screen.BasicScreen;
import br.com.zarus.zarus_engine.screen.MainScreen;

public class MainScreenCreatorService {
	
	private static final MainScreenCreatorService INSTANCE = new MainScreenCreatorService();

	private MainScreenCreatorService() {}

	public MainScreen create(BasicScreen screen) {
		return new MainScreen(screen);
	}

	public static MainScreenCreatorService getInstance() {
		return INSTANCE;
	}


}
