package br.com.zarus.zarus_engine.utils;

import br.com.zarus.zarus_engine.position.Vector2D;

public class MathUtils {

    private MathUtils() {
    }

    public static double distance(Vector2D p1, Vector2D p2) {
        return Math.hypot(Math.abs(p2.getX() - p1.getX()), p2.getY() - p1.getY());
    }

}
