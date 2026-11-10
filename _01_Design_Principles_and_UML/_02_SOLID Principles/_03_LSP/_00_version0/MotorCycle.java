package _03_LSP._00_version0;

public class MotorCycle extends Vehicle {

	String company;
	boolean isEngineOn;
	int speed;

	public MotorCycle(String company, int speed) {
		this.company = company;
		this.speed = speed;
	}
}
