package GUI;


import Console.*;
import Crypto.KeyVault;
import static GUI.GuiRegister.frame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import static java.awt.FlowLayout.RIGHT;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

public class GuiLogin {
        public static JFrame frame;
	public GuiLogin() {
		frame = new JFrame("Login");
		frame.setSize(700, 600);
                frame.setResizable(false);
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
                
                JLabel imgLabel = new JLabel();
                imgLabel.setIcon(new ImageIcon("logo.jpg"));// your image here 
                imgLabel.setBounds(100, 60, 780, 80);
                panel.add (imgLabel);

		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(180, 180, 80, 25);
                passwordLabel.setFont(new Font("Calibri", Font.BOLD, 18));
		panel.add(passwordLabel);

		final JPasswordField passwordText = new JPasswordField(20);
		passwordText.setBounds(270, 180, 200, 28);
                passwordText.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e){

                    char[] password = passwordText.getPassword();
                    KeyVault kv = new KeyVault();
                    if (kv.checkPassword(password) == false){
                        JOptionPane.showMessageDialog(panel, "Sorry incorrect password, Please try again", "Incorrect Password",
                        JOptionPane.WARNING_MESSAGE);               
                    }else{
                    User u = new User();

                    u.login(password);

                    panel.setVisible(false);
                    frame.setVisible(false);
                        try {
                            GuiMenu menu = new GuiMenu();
                        } catch (IOException ex) {
                            Logger.getLogger(GuiLogin.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }});
		panel.add(passwordText);
                //setting font
                Font bArial = new Font("Arial", Font.BOLD, 14);
                JButton loginButton = new JButton("Login");  
                loginButton.setBackground(Color.white);
                loginButton.setFont(bArial);
		loginButton.setBounds(480, 180, 100, 28);
		panel.add(loginButton);
                
                Font bArial2 = new Font("Arial", Font.PLAIN, 14);
                JButton helpButton = new JButton("Help");
                helpButton.setFont(bArial2);
                helpButton.setBounds(220, 250, 130, 35);
		panel.add(helpButton);
                
                helpButton.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        JOptionPane.showMessageDialog(panel, "Please Enter your Password.\n If you wish to delete your registered account, Please select the 'Close Account' button.", "Help",
                        JOptionPane.INFORMATION_MESSAGE);
                    }
		});
                
                JButton registerButton = new JButton("Close Account");
                registerButton.setFont(bArial2);
                registerButton.setBounds(390, 250, 130, 35);
		panel.add(registerButton);
                
                registerButton.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        int dialogButton = JOptionPane.YES_NO_OPTION;
                        int dialogResult = JOptionPane.showConfirmDialog(panel, "Are you sure you want to Delete your Account?", "Close Account?",dialogButton);
                        //if yes then
                        if(dialogResult==0)
                        {
                            File file = new File("user2.ser");
                            File file2 = new File("keystore");
                            //error checking
                            if(file.delete()&& file2.delete()){
                                JOptionPane.showMessageDialog(panel, "Successfully deleted your Account", "Successful",
                                JOptionPane.INFORMATION_MESSAGE);
                                //exit from System
                                System.exit(0);
                            }else{
                                JOptionPane.showMessageDialog(panel, "Sorry an error had occured, Please restart program", "Warning",
                                JOptionPane.WARNING_MESSAGE);
                            } 
                        }
                    }
		});

		loginButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				char[] password = passwordText.getPassword();
                              
                                KeyVault kv = new KeyVault();
                                if (kv.checkPassword(password) == false){
                                    JOptionPane.showMessageDialog(panel, "Sorry incorrect password, Please try again", "Incorrect Password",
                                    JOptionPane.WARNING_MESSAGE);               
                                }else{
                                User u = new User();
				
                                u.login(password);
                                
                                panel.setVisible(false);
                                frame.setVisible(false);
                                    try {
                                        GuiMenu menu = new GuiMenu();
                                    } catch (IOException ex) {
                                        Logger.getLogger(GuiLogin.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
			}
		});
        }catch (Exception e){
            System.out.println(e);
        }

	}
}

