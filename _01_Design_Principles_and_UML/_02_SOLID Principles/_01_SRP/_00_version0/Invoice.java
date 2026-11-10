package _01_SRP._00_version0;

public class Invoice {

	private Marker marker;
	private int quantity;
	private long price;

	public Invoice (Marker marker, int quantity) {
		this.marker = marker;
		this.quantity = quantity;
	}

	//calculte Price
	public long calculateTotal() {
		return price = this.quantity * this.marker.price;
	}

	//print Invoice
	public void printInvoice() {
		System.out.println("Print invoice " + price);
	}

	//save to DB
	public void saveToDB() {
		System.out.println("Saving to DB");
	}
}
