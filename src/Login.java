import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Login extends JDialog {
	public boolean isLoggedOut = false;
	private JLabel userNameLabel = new JLabel();
	private JLabel passwordLabel = new JLabel();
	private JButton loginButton = new JButton();
	private JButton exitButton = new JButton();
	private JTextField userNameText = new JTextField();
	private JPasswordField passwordText = new JPasswordField();
	private JRadioButton buyerRadio = new JRadioButton();
	private JRadioButton sellerRadio = new JRadioButton();
	private ButtonGroup buttonGroup = new ButtonGroup();
	private String userBox = null;
	private UserInfoItem.USER_TYPE UserType = UserInfoItem.USER_TYPE.Buyer;
	
	Login() {
		try {
			this.getContentPane().setLayout(null);
			userNameLabel.setText("UserName");
			userNameLabel.setBounds(new Rectangle(25, 50, 100, 20));

			passwordLabel.setText("Password");
			passwordLabel.setBounds(new Rectangle(25, 120, 100, 20));

			loginButton.setText("Login");
			loginButton.setBounds(new Rectangle(30, 210, 100, 30));
			loginButton.addActionListener(this::loginButton_actionPerformed);

			exitButton.setText("Exit");
			exitButton.setBounds(new Rectangle(180, 210, 100, 30));
			exitButton.addActionListener(e -> buttonExit_actionPerformed());

			userNameText.setBounds(new Rectangle(120, 50, 145, 25));
			passwordText.setBounds(new Rectangle(120, 120, 145, 25));

			buyerRadio.setSelected(true);
			buyerRadio.setText("Buyer");
			buyerRadio.setBounds(new Rectangle(37, 164, 103, 26));

			sellerRadio.setText("Seller");
			sellerRadio.setBounds(new Rectangle(177, 162, 103, 26));

			this.getContentPane().add(userNameLabel, null);
			this.getContentPane().add(passwordLabel, null);
			this.getContentPane().add(userNameText, null);
			this.getContentPane().add(passwordText, null);
			this.getContentPane().add(buyerRadio, null);
			this.getContentPane().add(sellerRadio, null);
			this.getContentPane().add(loginButton, null);
			this.getContentPane().add(exitButton, null);
			buttonGroup.add(buyerRadio);
			buttonGroup.add(sellerRadio);
			setSize(800, 600);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void loginButton_actionPerformed(ActionEvent e) {
		BufferedReader file;
		isLoggedOut = false;
		try {
			if (buyerRadio.isSelected()) {
				UserType = UserInfoItem.USER_TYPE.Buyer;
				file = new BufferedReader(new FileReader("database\\BuyerInfo.txt"));
			} else {
				UserType = UserInfoItem.USER_TYPE.Seller;
				file = new BufferedReader(new FileReader("database\\SellerInfo.txt"));
			}
			userBox = userNameText.getText();
			String PasswordBox = new String(passwordText.getPassword());
			String LoginName = null;
			String line = file.readLine(), UserName, Password;
			while (line != null) {
				UserName = getUserName(line);
				Password = getPassword(line);
				if (UserName.compareTo(userBox) == 0 && Password.compareTo(PasswordBox) == 0) LoginName = UserName;
				line = file.readLine();
			}
			if (LoginName != null) this.dispose();
		} catch (Exception ignored) {}
	}
	
	private String getUserName(String line) {
		int index = line.lastIndexOf(':');
		return line.substring(0, index);
	}
	private String getPassword(String line) {
		int index = line.lastIndexOf(':');
		return line.substring(index + 1);
	}
	String getUserName() {
		return userBox;
	}
	UserInfoItem.USER_TYPE getUserType() {
		return UserType;
	}
	boolean getIsLoggedOut() {
		return isLoggedOut;
	}
	private void buttonExit_actionPerformed() {
		isLoggedOut = true;
		dispose();
	}
}