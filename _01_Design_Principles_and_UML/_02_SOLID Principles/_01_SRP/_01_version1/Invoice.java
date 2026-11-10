package _01_SRP._01_version1;

public class Invoice {

	private Marker marker;
	private int quantity;
	private long price;

	public Marker getMarker() {
		return marker;
	}

	public int getQuantity() {
		return quantity;
	}

	public long getPrice() {
		return price;
	}

	public Invoice (Marker marker, int quantity) {
		this.marker = marker;
		this.quantity = quantity;
	}

	//calculte Price
	public long calculateTotal() {
		return price = this.quantity * this.marker.price;
	}

}
