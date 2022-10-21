public abstract class NodeVisitor {
	public abstract void visitProduct(Product product);
	public abstract void visitTrading(Trading trading, int index);
	public abstract void visitFacade(Facade facade);
}
