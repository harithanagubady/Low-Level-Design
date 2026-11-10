package _02_Polymorphism._01_runtime_polymorphism;

public class Main {

	public static void main(String[] args) {
		Animal animal;
		Lion lion = new Lion();
		animal = lion;

		animal.printAnimal();
		animal.printAnimalTwo();
	}
}
