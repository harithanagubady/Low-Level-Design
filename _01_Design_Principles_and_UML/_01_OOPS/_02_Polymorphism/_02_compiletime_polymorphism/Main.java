package _02_Polymorphism._02_compiletime_polymorphism;

public class Main {

	public static void main(String[] args) {

		Sum sum = new Sum();
		System.out.println(sum.addition(12, 35));
		System.out.println(sum.addition(31, 32, 33));
	}
}
