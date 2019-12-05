package util;

public class Vec2 {
	public double x, y;

	public Vec2() {
	}
	
	public Vec2(Vec2 v) {
		this(v.x, v.y);
	}

	public Vec2(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void set(Vec2 v) {
		this.set(v.x, v.y);
	}
	
	public void set(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double magSq() {
		return x * x + y * y;
	}

	public double mag() {
		return Math.sqrt(this.magSq());
	}

	public void limit(double lim) {
		double magSq = this.magSq();
		if (magSq > lim * lim) {
			this.div(Math.sqrt(magSq));
			this.mult(lim);
		}
	}
	
	public void setMag(double mag) {
		this.normalize();
		this.mult(mag);
	}

	public void normalize() {
		double mag = this.mag();
		this.div(mag);
	}

	public void add(Vec2 other) {
		this.x += other.x;
		this.y += other.y;
	}

	public Vec2 addV(Vec2 other) {
		Vec2 v = new Vec2(x, y);
		v.add(other);
		return v;
	}

	public void sub(Vec2 other) {
		this.x -= other.x;
		this.y -= other.y;
	}

	public Vec2 subV(Vec2 other) {
		Vec2 v = new Vec2(x, y);
		v.sub(other);
		return v;
	}

	public void mult(double val) {
		this.x *= val;
		this.y *= val;
	}

	public Vec2 multV(double val) {
		Vec2 v = new Vec2(x, y);
		v.mult(val);
		return v;
	}

	public void div(double val) {
		this.x /= val;
		this.y /= val;
	}

	public Vec2 divV(double val) {
		Vec2 v = new Vec2(x, y);
		v.div(val);
		return v;
	}
	
	public double angle() {
		return Math.atan2(y, x);
	}
	
	public double dist(Vec2 v) {
		return this.subV(v).mag();
	}
}
