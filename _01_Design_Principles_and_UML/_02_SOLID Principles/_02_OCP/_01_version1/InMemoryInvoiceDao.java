package _02_OCP._01_version1;

public class InMemoryInvoiceDao implements InvoiceDao {

	@Override
	public void save() {
		System.out.println("Save to inmemory");
	}
}
