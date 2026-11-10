package _05_DIP._01_version1;

public class MacBook {

	private final Keyboard fKeyboard;

	public MacBook(Keyboard keyboard) {
		fKeyboard = keyboard;
	}

	public Keyboard getKeyboard() {
		return fKeyboard;
	}
}
