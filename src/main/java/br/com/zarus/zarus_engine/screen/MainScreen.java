package br.com.zarus.zarus_engine.screen;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import br.com.zarus.zarus_engine.conf.Configurations;
import br.com.zarus.zarus_engine.loop.GameLoop;
import br.com.zarus.zarus_engine.loop.services.FramerateService;
import br.com.zarus.zarus_engine.loop.services.JVMInfoService;

public final class MainScreen extends Canvas {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6012913780508430095L;

	private JFrame frame;
	private String title = "-";
	private transient BasicScreen currentScreen;

	public MainScreen(BasicScreen screen) {
		this.currentScreen = screen;
		this.title = Configurations.getString(Configurations.SCREEN_DISPLAY_TITLE);
		initFrame();		
		
		if (super.getBufferStrategy() == null)
			super.createBufferStrategy(3);
	}

	private void initFrame() {
		this.frame = new JFrame();
		this.frame.add(this);
		this.frame.setTitle(this.title);
		this.frame.setLocationRelativeTo(null);
		this.frame.setResizable(false);
		this.frame.setVisible(true);
		this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	@Override
	public void setSize(Dimension d) {
		this.frame.setSize(d);
	}

	public void centralized() {
		this.frame.setLocationRelativeTo(null);
	}
	
	public void open() {
		this.frame.pack();
	}
	
	public void init() {
		this.currentScreen.init();
	}

	public void render() {
		this.currentScreen.render(getPen());
		FramerateService.getInstance().render(getPen());
		JVMInfoService.getInstance().render(getPen());
		whenIsPaused(getPen());
		super.getBufferStrategy().show();
	}

	private void whenIsPaused(Graphics g) {
		if (GameLoop.isPaused()) {
			g.setColor(Color.WHITE);
			g.drawString("PAUSED", (int) (Configurations.getInt(Configurations.SCREEN_SIZE_WIDTH) * 0.5), (int) (Configurations.getInt(Configurations.SCREEN_SIZE_HEIGHT) * 0.5));
		}
	}

	private Graphics getPen() {
		return super.getBufferStrategy().getDrawGraphics();
	}

	public void update(double delta) {
		this.currentScreen.update(delta);
	}

	public void setCurrentScreen(BasicScreen currentScreen) {
		this.currentScreen = currentScreen;
	}

}
