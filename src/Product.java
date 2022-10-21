import java.util.ArrayList;

public class Product {
	private String productName;
	private int productType;
	public Product() {}
	public Product(String productName, int productType) {
		super();
		this.productName = productName;
		this.productType = productType;
	}
	public String toString() {
		return productName + " " + productType;
	}
	public String getProductName() {
		return productName;
	}
	ArrayList<Trading> tradingList = new ArrayList<>();
}
