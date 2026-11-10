package _01_Inheritance;

//Hierarchical inheritance
//Alongside _01_Inheritance.FuelCar class, _01_Inheritance.ElectricCar class is also extending from _01_Inheritance.Vehicle class
public class ElectricCar extends Vehicle {

	private String batteryPower;

	public ElectricCar(String name, String model, String batteryPower) {
		super(name, model);
		this.batteryPower = batteryPower;
	}

	public void getElectricCar() {
		getName();
		System.out.print(", batter power is " + batteryPower);
	}
}
