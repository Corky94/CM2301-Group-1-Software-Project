/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Console.SecureDetails;
import Console.User;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Marc
 */
public class GuiReply {

	private		JFrame		composePanel;

        
    public void reply(String id)
	{
            	
		composePanel = new JFrame();
                composePanel.setSize( 700, 600);
		composePanel.setLayout(null);
                Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
                int x = (int) ((dimension.getWidth() - composePanel.getWidth()) / 2);
                int y = (int) ((dimension.getHeight() - composePanel.getHeight()) / 2);
                composePanel.setLocation(x, y);
                composePanel.setTitle( "Reply" );
		composePanel.setResizable(false);
		composePanel.setBackground( Color.blue );
		composePanel.setVisible( true );
		
		JLabel toLabel = new JLabel("To");
		toLabel.setBounds(10, 10, 50, 25);
        composePanel.add(toLabel);
        final JTextField toText = new JTextField(id);
        toText.setEnabled(false);
        toText.setBounds( 60, 10, 600, 28 );
        composePanel.add(toText);
        
        JLabel subjectLabel = new JLabel("Subject");
        subjectLabel.setBounds(10, 50, 50, 25);
        composePanel.add(subjectLabel);
        final JTextField subjectText = new JTextField();
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
                            if((subject.equals(""))||(message.equals(""))){
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
        }
    
}
