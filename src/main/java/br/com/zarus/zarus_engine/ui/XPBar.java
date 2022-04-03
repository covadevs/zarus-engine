package br.com.zarus.zarus_engine.ui;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import br.com.zarus.zarus_engine.core.Entity;
import br.com.zarus.zarus_engine.utils.GraphicsUtils;

public class XPBar extends Entity {
	
	private Rectangle2D bar;
	private Long currentXp;
	private Long nextLevelXp;
	private XPBarService service;
	
	public XPBar(Rectangle2D bar) {
		 this.bar = bar;
		 super.registerToReceiveMessages();
	}
	
	@Override
	public void update(double delta) {
		
	}

	@Override
	public void render(Graphics g) {
		GraphicsUtils.draw(bar, g);
		
	}

}
