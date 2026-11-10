package _05_DIP._01_version1;

public class BluetoothKeyboard implements Keyboard {

	public BluetoothKeyboard(String connectionType, String company, String modelVersion) {
		this.connectionType = connectionType;
		this.company = company;
		this.modelVersion = modelVersion;
	}

	private final String connectionType;
	private final String company;
	private final String modelVersion;

	@Override
	public void getSpecifications() {
		System.out.println("=====> Bluetooth Keyboard");
		System.out.println("Connection Type: " + connectionType);
		System.out.println("Company: " + company);
		System.out.println("Model version: " + modelVersion);
	}
}
