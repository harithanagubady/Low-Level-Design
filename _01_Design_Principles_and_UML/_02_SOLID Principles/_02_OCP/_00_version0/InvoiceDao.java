package _02_OCP._00_version0;

import _01_SRP._01_version1.Invoice;

public class InvoiceDao {

	Invoice invoice;

	public InvoiceDao(Invoice invoice) {
		this.invoice = invoice;
	}

	public void saveToDB() {
		System.out.println("Saving to DB");
	}

	//new requirement to also support saving to file
	//modified existing - violation of OCP
	public void saveToFile() {
		System.out.println("Saving to File");
	}
}
