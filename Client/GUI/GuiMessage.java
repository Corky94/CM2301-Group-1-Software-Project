package GUI;


import Connection.SessionKey;
import Console.*;
import Crypto.AuthenticatedList;
import Crypto.Encryption;
import Message.Message;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
public class GuiMessage {

	public static void newMessage(Message m) {
                SessionKey sKey = Encryption.decryptSessionKey(m.getSessionKey());
                String subject = Encryption.bTS(Encryption.decryptString(sKey, m.getSubject()));
		JFrame frame = new JFrame(subject);
		frame.setSize(700, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		placeComponents(frame, m);
		frame.setVisible(true);
	}

	private static void placeComponents(final JFrame frame, Message m) {
                
                AuthenticatedList.printList();
                SessionKey sKey = Encryption.decryptSessionKey(m.getSessionKey());
                String message = Encryption.bTS(Encryption.decryptString(sKey, m.getMessage()));
                String sender = Encryption.bTS(Encryption.decryptString(sKey, m.getSender()));
                String subject = Encryption.bTS(Encryption.decryptString(sKey, m.getSubject()));
            
            
		frame.setLayout(null);

		JLabel fromLabel = new JLabel("From:");
		fromLabel.setBounds(10, 10, 80, 25);
		frame.add(fromLabel);
		final JLabel fromText = new JLabel(sender);
		fromText.setBounds(100, 10, 550, 25);
		frame.add(fromText);
		
		JLabel subjectLabel = new JLabel("Subject");
		JLabel subjectText = new JLabel(subject);
		subjectLabel.setBounds(10, 45, 80, 25);
		subjectText.setBounds(100, 45, 250, 25);
		frame.add(subjectLabel);
		frame.add(subjectText);
                JLabel messageLabel = new JLabel("Message");
                messageLabel.setBounds(10,95,80,25);
                frame.add(messageLabel);
		JTextArea messageText = new JTextArea(message);
		messageText.setBounds(50, 120, 600, 400);
                messageText.setEditable(false);
                messageText.setOpaque(false);
		frame.add(messageText);
		

		JButton backButton = new JButton("Back");
		backButton.setBounds(10, 600, 150, 25);
		frame.add(backButton);
                
                JButton replyButton = new JButton("Reply");
		replyButton.setBounds(500, 600, 150, 25);
		frame.add(replyButton);


               backButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
//                            
                            frame.setVisible(false);
                            try {
                                new GuiMenu();
                            } catch (IOException ex) {
                                Logger.getLogger(GuiMessage.class.getName()).log(Level.SEVERE, null, ex);
                            }
			}
		});
               
                replyButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
//                            
                            frame.setVisible(false);
                            GuiReply rep = new GuiReply(); 
                            rep.reply(fromText.getText());
			}
		});

	}
}

