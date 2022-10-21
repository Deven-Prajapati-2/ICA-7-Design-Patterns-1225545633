import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JRadioButton;

public class ProduceProductMenu extends ProductMenu {
	Product product;
	protected boolean isLoggedOut = true;
	ProductMenu theProductMenu;
	Product currentProduct;
	JComboBox<Trading> tradingCombox = new JComboBox<>();
	JButton tradingAddButton = new JButton();
	private JButton buttonLogout = new JButton();
	public void setProductMenu(ProductMenu pm) {
		theProductMenu = pm;
	}
	public ProduceProductMenu(Product p) {
		currentProduct = p;
		try {
		    this.getContentPane().setLayout(null);
		    this.setTitle("");
		    buttonLogout.setText("Logout");
		    buttonLogout.setBounds(new Rectangle(120, 215, 200, 40));
		    buttonLogout.addActionListener(this::buttonLogout_actionPerformed);
		    this.getContentPane().add(buttonLogout, null);
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	    setModal(true);
	    setSize(500,300);
	}
	public boolean showMenu() {
		theProductMenu.product = currentProduct;
		showAddButton();
		showComboxes();
		showRadioButton();
		showLabels();
		setVisible(true);
		return isLoggedOut();
	}
	void TradingAddButton_actionPerformed() {
		Trading trade = (Trading) tradingCombox.getSelectedItem();
		PTBS.mainFacade.acceptTrade(trade, PTBS.mainFacade.thePerson.getUserName());
		refresh();
	}
	void TradingViewButton_actionPerformed() {
		Trading t=(Trading)tradingCombox.getSelectedItem();
		PTBS.mainFacade.viewTrading(t);
	}
	void refresh() {
		tradingCombox.removeAllItems();
		if(PTBS.mainFacade.sellerPendingOffers.get(PTBS.mainFacade.thePerson.getUserName()) != null) {
			for(Trading trade : PTBS.mainFacade.sellerPendingOffers.get(PTBS.mainFacade.thePerson.getUserName())) {
				if(trade.tradeName.equals(currentProduct.getProductName()))
					tradingCombox.addItem(trade);
		    }
		}
	}
	private void buttonChangeProduct_actionPerformed(ActionEvent e) {
		isLoggedOut=false;
	    dispose();
	}
	private void buttonLogout_actionPerformed(ActionEvent e) {
		isLoggedOut=true;
	    dispose();
	}
	boolean isLoggedOut() {
		return isLoggedOut;
	}
	public void showAddButton() {
		tradingAddButton.addActionListener(e -> TradingAddButton_actionPerformed());
		tradingAddButton.setText("Accept Offer");
		tradingAddButton.setBounds(new Rectangle(240, 55, 200, 30));
		this.getContentPane().add(tradingAddButton, null);
	}
	public void showViewButton() {}
	public void showRadioButton() {}
	public void showLabels() {}
	public void showComboxes() {
		tradingCombox.setBounds(new Rectangle(20, 60, 200, 20));
		this.getContentPane().add(tradingCombox, null);
		refresh();
	}
}
