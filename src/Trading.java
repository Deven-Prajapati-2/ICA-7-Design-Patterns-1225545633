import java.text.DateFormat;
import java.util.Date;

public class Trading {
	String tradeName;
	Date dueDate = new Date();
	int price;
	String buyer;
	String seller;
	public Trading(String tradeName, String buyer, String seller, int price, Date dueDate) {
		super();
		this.tradeName = tradeName;
		this.buyer = buyer;
		this.seller = seller;
		this.price = price;
		this.dueDate = dueDate;
	}

	@Override
	public String toString() {
		DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
		return tradeName + " " + dateFormat.format(dueDate) + " $" + price;
	}
	public void accept(NodeVisitor visitor, int index) {
		visitor.visitTrading(this, index);
	}
	String getDueDateString() {
		DateFormat dateFormat=DateFormat.getDateInstance(DateFormat.SHORT);
		return  dateFormat.format(dueDate);
	}
}
