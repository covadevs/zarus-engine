package br.com.zarus.zarus_engine.position;

public class Vector2D {
	private float x = 0.0f;
	private float y = 0.0f;

	public Vector2D() {
		super();
	}

	public Vector2D(float x, float y) {
		super();
		this.x = x;
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public int getXInt() {
		return (int) x;
	}


	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public int getYInt() {
		return (int) y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void moveTo(Vector2D newPosition) {
		this.x = newPosition.getX();
		this.y = newPosition.getY();
	}

	public void incX(float newX) {
		this.x += newX;
	}

	public void incY(float newY) {
		this.y += newY;
	}
}
