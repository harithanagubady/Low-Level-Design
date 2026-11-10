package _01_Inheritance;

//Multi-level inheritance
//_01_Inheritance.GasolineCar class is derived from _01_Inheritance.FuelCar which is further derived from _01_Inheritance.Vehicle class
public class GasolineCar extends FuelCar {

	private String combustType;
	private String gasCapacity;

	public GasolineCar(String name, String model, String combustType, String gasCapacity) {
		super(name, model, combustType);
		this.gasCapacity = gasCapacity;
	}

	public void getGasolineCar() {
		getName();
		System.out.println(", gas capacity is " + gasCapacity);
	}
}
