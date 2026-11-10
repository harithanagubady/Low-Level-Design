package _05_DIP._01_version1;

public class Demo {

	public static void main(String[] args) {
		WiredKeyboard wiredKeyboard = new WiredKeyboard("USB", "Dell", "F602");
		MacBook macBookWithWiredParts = new MacBook(wiredKeyboard);

		BluetoothKeyboard bluetoothKeyboard = new BluetoothKeyboard("BT", "Dell", "Y602");
		MacBook macWithBTKeyboard = new MacBook(bluetoothKeyboard);
	}
}
