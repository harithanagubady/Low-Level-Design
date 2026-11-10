package _01_SRP._01_version1;

public class InvoicePrinter {

	private Invoice invoice;

	public InvoicePrinter(Invoice invoice) {
		this.invoice = invoice;
	}

	//print Invoice
	public void printInvoice() {
		System.out.println("Print invoice " + invoice.getPrice());
	}
}
