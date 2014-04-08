package GUI;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Console.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

public class GuiRegister {
        public static JFrame frame;
	public static void NewRegister() {
		frame = new JFrame("Register");
		frame.setSize(900, 500);
                Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
                int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
                int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
                frame.setLocation(x, y);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JPanel panel = new JPanel();
                frame.add(panel);
		placeComponents(panel);
                frame.getContentPane().setBackground(Color.WHITE);
		frame.setVisible(true);
	}

	private static void placeComponents(final JPanel panel) {
		panel.setLayout(null);
                panel.setBackground(Color.WHITE);
                
                
                //second panel
                JPanel panel2 = new JPanel();
                panel2.setBackground(new Color(200,200,200));
                panel2.setBounds(250, 130, 400, 200);
                panel2.setLayout(null);
                panel2.setBorder(new BevelBorder(BevelBorder.RAISED));
                panel.add(panel2);
                
                JLabel imgLabel = new JLabel();
                imgLabel.setIcon(new ImageIcon("logo.jpg"));// your image here 
                imgLabel.setBounds(200, 10, 780, 80);
                panel.add (imgLabel);
                
                
		JLabel passwordLabel = new JLabel("Password");
                passwordLabel.setBounds(40, 40, 80, 25);
                passwordLabel.setFont(new Font("Calibri", Font.BOLD, 18));
		panel2.add(passwordLabel);
		final JPasswordField passwordText = new JPasswordField(20);
		passwordText.setBounds(120, 40, 200, 28);
		panel2.add(passwordText);
		
		JLabel passwordReLabel = new JLabel("Confirm");
                passwordReLabel.setFont(new Font("Calibri", Font.BOLD, 18));
		JLabel passwordReLabel2 = new JLabel("Password");
                passwordReLabel2.setFont(new Font("Calibri", Font.BOLD, 18));
		passwordReLabel.setBounds(40, 80, 80, 25);
		passwordReLabel2.setBounds(40, 98, 80, 25);
		panel2.add(passwordReLabel);
		panel2.add(passwordReLabel2);
		final JPasswordField passwordReText = new JPasswordField(20);
		passwordReText.setBounds(120, 91, 200, 28);
		panel2.add(passwordReText);
		

		/*JButton registerButton = new JButton("Register");
		registerButton.setBounds(130, 140, 150, 25);
                registerButton.setBackground(Color.orange);
		panel2.add(registerButton);*/
                
                JButton registerButton = new JButton("Register");  
                registerButton.setIcon(new ImageIcon("C:\\Users\\Melvin\\Documents\\group project2013\\loginButton.jpg"));  
                registerButton.setRolloverIcon(new ImageIcon("C:\\Users\\Melvin\\Documents\\group project2013\\loginButton2.jpg"));  
                //b.setPressedIcon(new ImageIcon(purple));  
                registerButton.setHorizontalTextPosition(JButton.CENTER);  
                registerButton.setRolloverEnabled(true);  
                registerButton.setFocusPainted(false);  
                registerButton.setBorderPainted(false);  
                registerButton.setContentAreaFilled(false);
		registerButton.setBounds(140, 140, 130, 45);
		panel2.add(registerButton);

                

               registerButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
                            char[] password = passwordText.getPassword();
                            char[] confirm = passwordReText.getPassword();
                            new Register(password, confirm);
                            frame.setVisible(false);
                            panel.setVisible(false);
                         
                            new GuiLogin();
			}
		});

	}
}

