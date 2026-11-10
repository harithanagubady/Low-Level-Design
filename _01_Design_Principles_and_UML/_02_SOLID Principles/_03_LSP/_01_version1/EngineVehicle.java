package _03_LSP._01_version1;

import _01_Inheritance.Vehicle;

public class EngineVehicle extends Vehicle {

	public EngineVehicle(String name, String model) {
		super(name, model);
	}

	public boolean hasEngine() {
		return true;
	}
}
