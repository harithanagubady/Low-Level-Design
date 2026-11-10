package _01_SRP._01_version1;

public class Demo {

	public static void main(String[] args) {

		Marker marker = new Marker("Marker", "Yellow", 60);
		Invoice invoice = new Invoice(marker, 2);
		invoice.calculateTotal();

		InvoiceDao invoiceDao = new InvoiceDao(invoice);
		invoiceDao.saveToDB();

		InvoicePrinter invoicePrinter = new InvoicePrinter(invoice);
		invoicePrinter.printInvoice();
	}
}
