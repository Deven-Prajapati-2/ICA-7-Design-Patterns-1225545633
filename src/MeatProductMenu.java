import java.util.Date;
import java.util.Iterator;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionEvent;

public class MeatProductMenu extends ProductMenu {
	Product product;
	protected boolean isLoggedOut = true;
	ProductMenu theProductMenu;
	Product currentProduct;
	JComboBox<String> tradingCombox = new JComboBox<>();
	JButton tradingAddButton = new JButton();
	private JButton buttonLogout = new JButton();
	public void setProductMenu(ProductMenu pm) {
		theProductMenu = pm;
	}
	public MeatProductMenu(Product p) {
		currentProduct = p;
		try {
		    this.getContentPane().setLayout(null);
		    this.setTitle("");
		    buttonLogout.setText("Logout");
		    buttonLogout.setBounds(new Rectangle(120, 215, 200, 35));
		    buttonLogout.addActionListener(this::buttonLogout_actionPerformed);
		    this.getContentPane().add(buttonLogout, null);
	    }
	    catch(Exception e) { e.printStackTrace(); };
	    setModal(true);
	    setSize(800,600	);
	}
	public boolean showMenu() {
		Iterator<Trading> it = currentProduct.tradingList.iterator();
		theProductMenu.product = currentProduct;
		Trading trading;
		while (it.hasNext()) {
			trading = (Trading) it.next();
			theProductMenu.tradingCombox.addItem(trading);
		}
		showAddButton();
		showComboxes();
		showRadioButton();
		showLabels();
		setVisible(true);
		return isLoggedOut();
	}
	void TradingAddButton_actionPerformed() {
		String seller = (String) tradingCombox.getSelectedItem();
		PTBS.mainFacade.addTrading(currentProduct, new Date(2022-1900, 9, 10), seller, 10, PTBS.mainFacade.thePerson.getUserName());
		refresh();
	}
	void TradingViewButton_actionPerformed() {
		Trading t=(Trading)tradingCombox.getSelectedItem();
		PTBS.mainFacade.viewTrading(t);
	}
	void refresh() {
		tradingCombox.removeAllItems();
		if(PTBS.mainFacade.sellerProductMap.get(currentProduct.getProductName()) != null) {
		    for(String seller : PTBS.mainFacade.sellerProductMap.get(currentProduct.getProductName())) {
		    	tradingCombox.addItem(seller);
		    }
		}
	}
	private void buttonLogout_actionPerformed(ActionEvent e) {
		isLoggedOut=true;
	    dispose();
	}
	boolean isLoggedOut() { return isLoggedOut; }
	public void showAddButton() {
		tradingAddButton.addActionListener(e -> TradingAddButton_actionPerformed());
		tradingAddButton.setText("Make an Offer!!");
		tradingAddButton.setBounds(new Rectangle(340, 55, 200, 30));
		this.getContentPane().add(tradingAddButton, null);
	}
	public void showViewButton() {}
	public void showRadioButton() {}
	public void showLabels() {}
	public void showComboxes() {
		tradingCombox.setBounds(new Rectangle(20, 60, 300, 25));
		this.getContentPane().add(tradingCombox, null);
		refresh();
	}
}