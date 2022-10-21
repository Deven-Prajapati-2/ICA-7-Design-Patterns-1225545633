public class Seller extends Person {
	public Seller() {}
	public boolean showMenu() {
		return theProductMenu.showMenu();
	}
	@Override
	public ProductMenu CreateProductMenu(Product product, int productType) {
		theProductMenu = new ProduceProductMenu(product);
		theProductMenu.setProductMenu(theProductMenu);
		return theProductMenu;
	}
}