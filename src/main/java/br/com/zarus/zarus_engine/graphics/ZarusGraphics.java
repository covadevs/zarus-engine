package br.com.zarus.zarus_engine.graphics;

import br.com.zarus.zarus_engine.lang.LangTemplate;

import java.awt.Graphics;

public class ZarusGraphics {
    public static void drawString(String text, int x, int y, Graphics graphics) {
        graphics.drawString(LangTemplate.getInstance().templateString(text), x, y);
    }
}
