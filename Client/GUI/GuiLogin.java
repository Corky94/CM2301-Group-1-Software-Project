package GUI;


import Console.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import static java.awt.FlowLayout.RIGHT;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

public class GuiLogin {
        public static JFrame frame;
	public GuiLogin() {
		frame = new JFrame("Login");
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
        try{
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
                passwordText.addActionListener(new ActionListener(){

                public void actionPerformed(ActionEvent e){

                        char[] password = passwordText.getPassword();
                                
                                User u = new User();
				
                                u.login(password);
                                
                                panel.setVisible(false);
                                frame.setVisible(false);
                                
                                
                                GuiMenu menu = new GuiMenu(password);
                }});
		panel2.add(passwordText);

                JButton loginButton = new JButton("Login");  
                loginButton.setIcon(new ImageIcon("C:\\Users\\Melvin\\Documents\\group project2013\\loginButton.jpg"));  
                loginButton.setRolloverIcon(new ImageIcon("C:\\Users\\Melvin\\Documents\\group project2013\\loginButton2.jpg"));  
                //b.setPressedIcon(new ImageIcon(purple));  
                loginButton.setHorizontalTextPosition(JButton.CENTER);  
                loginButton.setRolloverEnabled(true);  
                loginButton.setFocusPainted(false);  
                loginButton.setBorderPainted(false);  
                loginButton.setContentAreaFilled(false);
		loginButton.setBounds(140, 90, 130, 45);
		panel2.add(loginButton);
                
                JButton helpButton = new JButton();
                helpButton.setIcon(new ImageIcon("C:\\Users\\Melvin\\Documents\\group project2013\\helpButton.jpg"));  
                helpButton.setRolloverIcon(new ImageIcon("C:\\Users\\Melvin\\Documents\\group project2013\\helpButton2.jpg"));  
                //b.setPressedIcon(new Image130, 100, 130, 45Icon(purple));  
                helpButton.setHorizontalTextPosition(JButton.CENTER);  
                helpButton.setRolloverEnabled(true);  
                helpButton.setFocusPainted(false);  
                helpButton.setBorderPainted(false);  
                helpButton.setContentAreaFilled(false);;
		//helpButton.setLayout ();
                helpButton.setBounds(340, 162, 80, 35);
		panel2.add(helpButton);
                
                JButton registerButton = new JButton("Register");
		registerButton.setLayout (new BorderLayout ());
                registerButton.setBounds(740, 0, 85, 20);
                registerButton.setBackground(Color.white);
		panel.add(registerButton);


		loginButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				char[] password = passwordText.getPassword();
                                
                                User u = new User();
				
                                u.login(password);
                                
                                panel.setVisible(false);
                                frame.setVisible(false);
                                
                                
                                GuiMenu menu = new GuiMenu(password);
                                
                                //menu.GuiMenu();
			}
		});
                
                
                
        }catch (Exception e){
            System.out.println(e);
        }

	}
}

