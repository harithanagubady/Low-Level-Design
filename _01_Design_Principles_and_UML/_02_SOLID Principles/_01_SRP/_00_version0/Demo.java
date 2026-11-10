package _01_SRP._00_version0;

public class Demo {

	public static void main(String[] args) {

		Marker marker = new Marker("Marker", "Yellow", 60);
		Invoice invoice = new Invoice(marker, 2);
		invoice.calculateTotal();
		invoice.saveToDB();
		invoice.printInvoice();
	}
}
