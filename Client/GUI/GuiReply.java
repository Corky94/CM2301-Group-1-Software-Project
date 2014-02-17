/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Console.SecureDetails;
import Console.User;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
		composePanel.setLayout(null);
                composePanel.setTitle( "Reply" );
		composePanel.setSize( 700, 700 );
		composePanel.setResizable(false);
		composePanel.setBackground( Color.blue );
		composePanel.setVisible( true );
		
		JLabel toLabel = new JLabel("To");
		toLabel.setBounds(10, 10, 50, 25);
        composePanel.add(toLabel);
        final JTextField toText = new JTextField(id);
   
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
        final JTextArea content = new JTextArea();
        content.setLineWrap(true); 
        //content.setWrapStyleWord(true);
        //JScrollPane spane = new JScrollPane(content);
        //spane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        //composePanel.add(content);
        //content.setWrapStyleWord(true);
        content.setBounds(60, 90, 600, 500);
        //composePanel.add(spane);
        composePanel.add(content);
        
        JButton send = new JButton("Send");
        send.setBounds(560, 610, 100, 25);
        composePanel.add(send);
	
        send.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
                            String to = toText.getText();
                            String subject = subjectText.getText();
                            String message = content.getText();
                            System.out.println(to);
                            User u = new User();
                            u.createMessage(message, to, subject);
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(GuiReply.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            composePanel.setVisible(false);
                            GuiMenu menu = new GuiMenu();
                            menu.createMenu();


			}
		});
        }
    
}
