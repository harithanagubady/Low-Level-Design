package _01_SRP._01_version1;

public class InvoiceDao {

	Invoice invoice;

	public InvoiceDao (Invoice invoice) {
		this.invoice = invoice;
	}

	public void saveToDB() {
		System.out.println("Saving to DB");
	}
}
