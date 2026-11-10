package _01_Inheritance;

//Single Inheritance
public class FuelCar extends Vehicle {

	private String combustType;

	public FuelCar(String name, String model, String combustType) {
		super(name, model);
		this.combustType = combustType;
	}

	public void getFuelCar() {
		getName();
		System.out.print(", combust type is " + combustType);
	}
}
