package br.com.zarus.zarus_engine.loop.services;

import java.awt.Color;
import java.awt.Graphics;
import br.com.zarus.zarus_engine.debug.DebugWriter;
import br.com.zarus.zarus_engine.loop.GameLoop;

public class JVMInfoService {

	private static final JVMInfoService INSTANCE = new JVMInfoService();
	private static final Runtime RUNTIME = Runtime.getRuntime();

	private double memoryInUse;
	private Long lastCall;
	
	private JVMInfoService() {}

	public void eval() {
		if (this.lastCall == null)
			this.lastCall = System.currentTimeMillis();

		if ((System.currentTimeMillis() - this.lastCall) / 1000L >= 1L) {
			this.memoryInUse = (getMemoryInUse()/(double)RUNTIME.totalMemory()) * 100.0d;
			this.lastCall = System.currentTimeMillis();
		}
	}
	
	public double getMemoryInUse() {
		return (double)RUNTIME.totalMemory() - (double)RUNTIME.freeMemory();
	}

	public void render(Graphics g) {
		g.setColor(Color.GREEN);
		DebugWriter.logRight("Memory in use (%): " + this.memoryInUse + "%", g);
		DebugWriter.logRight("Memory in use (MB): " + (int)getMemoryInUse()/1024 + "MB", g);
		DebugWriter.logRight("Total memory (MB): " + RUNTIME.totalMemory()/1024 + "MB", g);
		DebugWriter.logRight("Free memory (MB): " + RUNTIME.freeMemory()/1024 + "MB", g);
	}

	public static JVMInfoService getInstance() {
		return INSTANCE;
	}
	
	public void start() {
		new Thread(() -> {
			while(GameLoop.isRunning()) {
				eval();
			}
		}).start();
	}
}
