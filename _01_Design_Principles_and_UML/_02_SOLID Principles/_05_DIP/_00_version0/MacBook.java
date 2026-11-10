package _05_DIP._00_version0;

public class MacBook {

	private final WiredKeyboard fKeyboard;

	public MacBook(WiredKeyboard wiredKeyboard) {
		fKeyboard = wiredKeyboard;
	}

	public Keyboard getKeyboard() {
		return fKeyboard;
	}
}
