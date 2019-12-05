package util;

public class Global {
	public static int width, height;
	public static int mouseX, mouseY;
	public static boolean mousePressed;
	public static boolean endRequest;

	public static boolean mouseIn(double minX, double minY, double maxX, double maxY) {
		return mouseX >= minX && mouseX <= maxX && mouseY >= minY && mouseY <= maxY;
	}

	public static double constrain(double n, double min, double max) {
		return Math.min(Math.max(n, min), max);
	}

	public static double truncate(double num, int p) {
		double pow = Math.pow(10, p);
		return Math.round(num * pow) / pow;
	}

	public static double map(double x, double min1, double max1, double min2, double max2) {
		return (x - min1) / (max1 - min1) * (max2 - min2) + min2;
	}

	public static double lerp(double start, double stop, double amt) {
		return start + amt * (stop - start);
	}

	public static double smooth(double dd, double filterVal, double smoothedVal) {
		return (dd * (1 - filterVal)) + (smoothedVal * filterVal);
	}
}
