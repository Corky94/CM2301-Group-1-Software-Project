package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Console.*;
import static GUI.GuiLogin.frame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.Arrays;
import javax.swing.ImageIcon;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

public class GuiRegister {

    public static JFrame frame;

    public static void NewRegister() {
        frame = new JFrame("Register");
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
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);


        JLabel imgLabel = new JLabel();
        imgLabel.setIcon(new ImageIcon("logo.jpg"));// your image here 
        imgLabel.setBounds(100, 60, 780, 80);
        panel.add(imgLabel);


        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(220, 180, 80, 25);
        passwordLabel.setFont(new Font("Calibri", Font.BOLD, 18));
        panel.add(passwordLabel);
        final JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(310, 180, 200, 28);
        panel.add(passwordText);

        JLabel passwordReLabel = new JLabel("Confirm");
        passwordReLabel.setFont(new Font("Calibri", Font.BOLD, 18));
        JLabel passwordReLabel2 = new JLabel("Password");
        passwordReLabel2.setFont(new Font("Calibri", Font.BOLD, 18));
        passwordReLabel.setBounds(220, 240, 80, 25);
        passwordReLabel2.setBounds(220, 258, 80, 25);
        panel.add(passwordReLabel);
        panel.add(passwordReLabel2);
        final JPasswordField passwordReText = new JPasswordField(20);
        passwordReText.setBounds(310, 245, 200, 28);
        panel.add(passwordReText);

        Font bArial = new Font("Arial", Font.BOLD, 14);
        JButton registerButton = new JButton("Register");
        registerButton.setFont(bArial);
        registerButton.setBounds(320, 300, 130, 35);
        panel.add(registerButton);


        //action event for button
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                char[] password = passwordText.getPassword();
                char[] confirm = passwordReText.getPassword();
                if ((password.length == 0) || (confirm.length == 0)) {
                    JOptionPane.showMessageDialog(panel, "Sorry some of the fields are left Balnk, Please complete.", "Warning",
                                                  JOptionPane.WARNING_MESSAGE);
                }
                else if (Arrays.equals(password, confirm) == false) {
                    JOptionPane.showMessageDialog(panel, "Sorry the passwords entered are not identical, Please try again", "Warning",
                                                  JOptionPane.WARNING_MESSAGE);
                }
                else {
                    new Register(password);
                    JOptionPane.showMessageDialog(panel, "Successfully Registered", "Successfull",
                                                  JOptionPane.INFORMATION_MESSAGE);
                    frame.setVisible(false);
                    panel.setVisible(false);
                    new GuiLogin();
                }
            }
        });
        //action even when return pressed
        passwordReText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                char[] password = passwordText.getPassword();
                char[] confirm = passwordReText.getPassword();
                if ((password.length == 0) || (confirm.length == 0)) {
                    JOptionPane.showMessageDialog(panel, "Sorry some of the fields are left Balnk, Please complete.", "Warning",
                                                  JOptionPane.WARNING_MESSAGE);
                }
                else if (Arrays.equals(password, confirm) == false) {
                    JOptionPane.showMessageDialog(panel, "Sorry the passwords entered are not identical, Please try again", "Warning",
                                                  JOptionPane.WARNING_MESSAGE);
                }
                else {
                    new Register(password);
                    JOptionPane.showMessageDialog(panel, "Successfully Registered", "Successfull",
                                                  JOptionPane.INFORMATION_MESSAGE);
                    frame.setVisible(false);
                    panel.setVisible(false);
                    new GuiLogin();
                }
            }
        });

    }
}
