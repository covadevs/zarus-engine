package br.com.zarus.zarus_engine.loop.services;

import java.awt.Color;
import java.awt.Graphics;
import java.text.DecimalFormat;
import java.util.concurrent.atomic.AtomicLong;

import br.com.zarus.zarus_engine.conf.Configurations;
import br.com.zarus.zarus_engine.core.Updatable;
import br.com.zarus.zarus_engine.debug.DebugWriter;
import br.com.zarus.zarus_engine.lang.LangTemplate;
import br.com.zarus.zarus_engine.loop.GameLoop;

public class FramerateService implements Updatable {

    private static final FramerateService INSTANCE = new FramerateService();

    private final AtomicLong framerateCount = new AtomicLong(0);
    private Long framerateToRender = framerateCount.get();
    private Long lastCall;
    private double deltaToRender = 0.0f;

    public void eval() {
        if (isNotEnabled()) return;

        if (this.lastCall == null)
            this.lastCall = System.currentTimeMillis();

        if ((System.currentTimeMillis() - this.lastCall) / 1000L >= 1L) {
            this.framerateToRender = this.framerateCount.get();
            this.framerateCount.set(0);
            this.lastCall = System.currentTimeMillis();
        }

    }

    public void render(Graphics g) {
        if (isNotEnabled()) return;

        g.setColor(Color.GREEN);
        DebugWriter.logSpacesOnRight(2, g);
        DebugWriter.logRight(LangTemplate.getInstance().templateString("${br.com.zarus.zarus_engine.loop.services.FramerateService.fps}" + this.framerateToRender + "(" + DecimalFormat.getInstance().format(this.deltaToRender) + "${br.com.zarus.zarus_engine.loop.services.FramerateService.fps.ms})"), g);
    }

    public void addFramerateCount() {
        if (isNotEnabled()) return;

        this.framerateCount.incrementAndGet();
    }

    private boolean isNotEnabled() {
        return !isEnabled();
    }

    private boolean isEnabled() {
        return Configurations.getBoolean(Configurations.SCREEN_FRAMERATE_SHOW);
    }

    public static FramerateService getInstance() {
        return INSTANCE;
    }

    public void start() {
        new Thread(() -> {
            while (GameLoop.isRunning()) {
                eval();
            }
        }).start();
    }

    @Override
    public void update(double delta) {
        this.deltaToRender = delta / 1000000;
    }
}
