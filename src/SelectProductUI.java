import java.awt.Rectangle;
import java.awt.event.ActionEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class SelectProductUI extends JDialog{
	int productCategory = 0;
	private Product selectedProduct;
	private boolean isLoggedOut = false;
	private JComboBox<Product> productNameCombo = new JComboBox<>();
	private JLabel jLabel1 = new JLabel();
	private JButton OKButton = new JButton();
	private ButtonGroup buttonGroup1 = new ButtonGroup();
	private JButton buttonLogout = new JButton();
	
	public SelectProductUI() {
		try {
			this.getContentPane().setLayout(null);
			productNameCombo.setBounds(new Rectangle(150, 40, 200, 20));
			jLabel1.setText("ProductName");
			jLabel1.setBounds(new Rectangle(40, 45, 85, 20));
			OKButton.setText("OK");
			OKButton.setBounds(new Rectangle(80, 140, 80, 30));
			OKButton.addActionListener(this::OKButton_actionPerformed);
			buttonLogout.setText("Logout");
			buttonLogout.setBounds(new Rectangle(225, 140, 75, 30));
			buttonLogout.addActionListener(this::buttonLogout_actionPerformed);
			this.getContentPane().add(productNameCombo, null);
			this.getContentPane().add(jLabel1, null);
			this.getContentPane().add(OKButton, null);
			this.getContentPane().add(buttonLogout, null);
			setSize(800, 600);
			setModal(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void OKButton_actionPerformed(ActionEvent e) {
		selectedProduct = (Product) productNameCombo.getSelectedItem();
		dispose();
	}
	
	private void buttonLogout_actionPerformed(ActionEvent e) {
		isLoggedOut = true;
		dispose();
	}
	
	boolean isLoggedOut() {
		return isLoggedOut;
	}
	
	Product getSelectedProduct(ClassProductList productList) {
		ProductIterator it = new ProductIterator(productList);
		while (it.hasNext()) productNameCombo.addItem((Product)it.next());
		setVisible(true);
		return selectedProduct;
	}
}