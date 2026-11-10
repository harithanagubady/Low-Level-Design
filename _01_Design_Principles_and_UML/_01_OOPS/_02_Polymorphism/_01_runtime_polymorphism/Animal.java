package _02_Polymorphism._01_runtime_polymorphism;

public class Animal {

	public void printAnimal() {
		System.out.println("I am from the Animal class");
	}

	void printAnimalTwo() {
		System.out.println("I am from the Animal class");
	}
}

class Lion extends Animal {

	public void printAnimal() {
		System.out.println("I am from the Lion class");
	}
}
