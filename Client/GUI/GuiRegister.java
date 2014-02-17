package GUI;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Console.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class GuiRegister {

	public static void NewRegister() {
		JFrame frame = new JFrame("Register");
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
		
		JLabel passwordReLabel = new JLabel("Confirm");
		JLabel passwordReLabel2 = new JLabel("Password");
		passwordReLabel.setBounds(10, 45, 80, 25);
		passwordReLabel2.setBounds(10, 60, 80, 25);
		frame.add(passwordReLabel);
		frame.add(passwordReLabel2);
		final JPasswordField passwordReText = new JPasswordField(20);
		passwordReText.setBounds(100, 50, 160, 25);
		frame.add(passwordReText);
		

		JButton registerButton = new JButton("Register");
		registerButton.setBounds(10, 90, 150, 25);
		frame.add(registerButton);


               registerButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
                            char[] password = passwordText.getPassword();
                            char[] confirm = passwordReText.getPassword();
                            new Register(password, confirm);
                            frame.setVisible(false);
                         
                            new GuiLogin();
			}
		});

	}
}

