package _02_OCP._01_version1;

public class DBInvoiceDao implements InvoiceDao {
	@Override
	public void save() {
		System.out.println("Save to DB");
	}
}
