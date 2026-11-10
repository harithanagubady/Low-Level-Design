package _01_Inheritance;

public class Main {

	public static void main(String[] args) {
		System.out.println("Single Inheritance:");

		FuelCar fuel = new FuelCar("Honda", "Accord", "Petrol");
		fuel.getFuelCar();
		System.out.println();

		System.out.println("Hierarchical inheritance:");

		ElectricCar electricCar = new ElectricCar("Tesla", "ModelX", "200MWH");
		electricCar.getElectricCar();
		System.out.println();

		System.out.println("Multi-level inheritance:");

		GasolineCar gasolineCar = new GasolineCar("Toyota", "Corolla", "Gasoline", "30 litres");
		gasolineCar.getGasolineCar();

		System.out.println();
		System.out.println("Java does not support Multiple Inheritance through classes");
	}
}
