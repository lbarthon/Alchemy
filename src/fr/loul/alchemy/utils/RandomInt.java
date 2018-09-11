package fr.loul.alchemy.utils;

public class RandomInt {

	public static int get(int max) {
		return (int) Math.floor(Math.random() * max);
	}
	
	public static int get(int min, int max) {
		if (min >= max) return 0;
		int i = (int) Math.floor(Math.random() * max);
		while (i < min) {
			i = (int) Math.floor(Math.random() * max);
		}
		return i;
	}
}
