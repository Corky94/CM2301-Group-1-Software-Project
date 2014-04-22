/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Joel
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Console.SecureDetails;
import Console.User;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class GuiForward {

	private		JFrame		composePanel;

        
    public void forward(String subject, String message)
    {

        composePanel = new JFrame();
        composePanel.setSize( 700, 600 );
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - composePanel.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - composePanel.getHeight()) / 2);
        composePanel.setLocation(x, y);
        composePanel.setLayout(null);
        composePanel.setTitle( "Forward" );		
        composePanel.setResizable(false);
        composePanel.setBackground( Color.blue );
        composePanel.setVisible( true );
		
	JLabel toLabel = new JLabel("To");
        toLabel.setBounds(10, 10, 50, 25);
        composePanel.add(toLabel);
        final JTextField toText = new JTextField();
        toText.setBounds( 60, 10, 535, 28 );
        composePanel.add(toText);
        
        JButton contactButton = new JButton("Add");
        contactButton.setBounds( 600, 10, 60, 28 );
        composePanel.add(contactButton);
        
        JLabel subjectLabel = new JLabel("Subject");
        subjectLabel.setBounds(10, 50, 50, 25);
        composePanel.add(subjectLabel);
        final JTextField subjectText = new JTextField("FW: " + subject);
        subjectText.setBounds( 60, 50, 600, 28 );
        composePanel.add(subjectText);
 
        JLabel contentLabel = new JLabel("Content");
        contentLabel.setBounds(10, 90, 50, 25);
        composePanel.add(contentLabel);
        final JTextArea contentText = new JTextArea();
        contentText.setLineWrap(true); 
        JScrollPane scrollPane = new JScrollPane(contentText);
        scrollPane.setBounds(60, 90, 600, 400);
        composePanel.add(scrollPane);
        
        JButton send = new JButton("Send");
        send.setBounds(560, 520, 100, 25);
        composePanel.add(send);
        
        JButton back = new JButton("Back");
        back.setBounds(60, 520, 100, 25);
        composePanel.add(back);
	
        send.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
                            String to = toText.getText();
                            String subject = subjectText.getText();
                            String message = contentText.getText();
                            if (to.equals(""))
                            {
                                JOptionPane.showMessageDialog(composePanel, "You must imput a contact inorder to send a Message, Please insert a Contact.", "Cannot send message",
                                JOptionPane.WARNING_MESSAGE); 
                            }else if((subject.equals(""))||(message.equals(""))){
                                int dialogButton = JOptionPane.YES_NO_OPTION;
                                int dialogResult = JOptionPane.showConfirmDialog(composePanel, "Some of the fields are left Blank, Do you still want to send message?", "Send Message?",dialogButton);
                                //if yes then
                                if(dialogResult == 0)
                                {
                                    System.out.println(to);
                                    User u = new User();
                                    u.createMessage(message, to, subject);
                                    JOptionPane.showMessageDialog(composePanel, "Message Successfully sent.", "Successfull",
                                    JOptionPane.INFORMATION_MESSAGE);
                                    try {
                                        Thread.sleep(3000);
                                    } catch (InterruptedException ex) {
                                        Logger.getLogger(GuiReply.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    composePanel.setVisible(false);
                                    
                                }
                            }else{
                                System.out.println(to);
                                User u = new User();
                                u.createMessage(message, to, subject);
                                JOptionPane.showMessageDialog(composePanel, "Message Successfully sent.", "Successfull",
                                JOptionPane.INFORMATION_MESSAGE);
                                try {
                                    Thread.sleep(3000);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(GuiReply.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                composePanel.setVisible(false);
                             
                            }
			}
		});
        back.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {                          
                            composePanel.setVisible(false);
                           
			}
		}); 
        
        contactButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0){
            
              EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }

                JPanel panel = new JPanel();
                JLabel label = new JLabel("Please select a contact:");
                label.setFont(new Font("Arial",Font.BOLD,12));
                panel.add(label);
                
                BufferedReader in;
                try {
                    in = new BufferedReader(new FileReader("Contacts.txt"));
                    String str;
                    ArrayList<String> arr = new ArrayList<>();
                    ArrayList<String> arr2 = new ArrayList<>();
                    while ((str = in.readLine()) != null) {
                        String[] ar=str.split(",");
                        arr.add(ar[0]);
                        arr2.add(ar[1]);
                    }
                    
                    String[] idArr = new String[arr2.size()];
                    idArr = arr2.toArray(idArr);
                    
                    DefaultComboBoxModel model = new DefaultComboBoxModel(arr.toArray());
                    JComboBox comboBox = new JComboBox(model);
                    comboBox.setFont(new Font("Arial",Font.BOLD,13));
                    panel.add(comboBox);
                    int result = JOptionPane.showConfirmDialog(null, panel, "Contacts", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                    switch (result) {
                        case JOptionPane.OK_OPTION:
                        toText.setText(idArr[comboBox.getSelectedIndex()]);
                        break;
                    }
                }catch (HeadlessException | IOException ex) {
                    JOptionPane.showMessageDialog(composePanel, "Sorry an error has occured while accessing your contacts, Please restart program.", "Error",
                    JOptionPane.ERROR_MESSAGE);
                }

            }
                
            });
            
           }
                      
        });
        }
    
}
