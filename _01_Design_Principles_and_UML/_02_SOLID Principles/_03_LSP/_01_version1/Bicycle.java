package _03_LSP._01_version1;

public class Bicycle extends ManualVehicle {

	String brand;
	Boolean hasGears;
	int speed;

	public Bicycle(String brand, Boolean hasGears, int speed) {
		this.brand = brand;
		this.hasGears = hasGears;
		this.speed = speed;
	}
}
