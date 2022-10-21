import java.util.Iterator;

public class Buyer extends Person {
	public Buyer() {}
	public boolean showMenu() {
		return theProductMenu.showMenu();
	}
	@Override
	public ProductMenu CreateProductMenu(Product product, int productType) {
		if(productType == 1) theProductMenu = new ProduceProductMenu(product);
		else theProductMenu = new MeatProductMenu(product);
		theProductMenu.setProductMenu(theProductMenu);
		return theProductMenu;
	}
}