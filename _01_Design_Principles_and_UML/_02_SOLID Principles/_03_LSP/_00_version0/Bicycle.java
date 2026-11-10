package _03_LSP._00_version0;

public class Bicycle extends Vehicle {

	String brand;
	Boolean hasGears;
	int speed;

	public Bicycle(String brand, Boolean hasGears, int speed) {
		this.brand = brand;
		this.hasGears = hasGears;
		this.speed = speed;
	}

}
