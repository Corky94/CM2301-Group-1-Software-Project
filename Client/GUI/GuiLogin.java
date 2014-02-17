package GUI;


import Console.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class GuiLogin {

	public GuiLogin() {
		JFrame frame = new JFrame("Login");
		frame.setSize(300, 160);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		placeComponents(frame);
		frame.setVisible(true);
	}

	private static void placeComponents(final JFrame frame) {
		frame.setLayout(null);


		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10, 10, 80, 25);
		frame.add(passwordLabel);

		final JPasswordField passwordText = new JPasswordField(20);
		passwordText.setBounds(100, 10, 160, 25);
		frame.add(passwordText);

		JButton loginButton = new JButton("login");
		loginButton.setBounds(10, 50, 80, 25);
		frame.add(loginButton);


		loginButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				char[] password = passwordText.getPassword();
                                
                                User u = new User();
				
                                u.login(password);
                                
                                frame.setVisible(false);
                                
                                GuiMenu menu = new GuiMenu();
                                
                                menu.createMenu();
			}
		});

	}
}

