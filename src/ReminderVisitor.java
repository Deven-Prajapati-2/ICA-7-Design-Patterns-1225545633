import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class ReminderVisitor extends NodeVisitor {
	private Reminder reminder;
	public ReminderVisitor(Reminder reminder) {
		this.reminder = reminder;
	}
	public void visitProduct(Product product) {}
	public void visitTrading(Trading trading, int i) {
		Date today = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		calendar.setTime(trading.dueDate);
		if (i == 0) reminder.listPending.add(trading.tradeName + " Due date is " + trading.getDueDateString());
		else reminder.listComplete.add(trading.tradeName + " Due date is " + trading.getDueDateString());
	}
	public void visitFacade(Facade facade) {
		if(PTBS.mainFacade.buyerPendingOffers.get(PTBS.mainFacade.thePerson.getUserName()) != null) {
			for (Trading trade : PTBS.mainFacade.buyerPendingOffers.get(PTBS.mainFacade.thePerson.getUserName())) {
				trade.accept(this, 0);
			}
		}
		if(PTBS.mainFacade.buyerAcceptedOffers.get(PTBS.mainFacade.thePerson.getUserName()) != null) {
			for (Trading trade : PTBS.mainFacade.buyerAcceptedOffers.get(PTBS.mainFacade.thePerson.getUserName())) {
				trade.accept(this, 1);
			}
		}
	}
}
