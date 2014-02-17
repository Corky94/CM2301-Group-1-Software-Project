package GUI;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Console.*;
import Crypto.Encryption;
import Message.Message;

import javax.swing.*;
public class GuiMessage {

	public static void newMessage(Message m) {
                Encryption e = new Encryption();
                String subject = e.bTS(e.decryptString(m.subject, "pass".toCharArray()));
		JFrame frame = new JFrame(subject);
		frame.setSize(700, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		placeComponents(frame, m);
		frame.setVisible(true);
	}

	private static void placeComponents(final JFrame frame, Message m) {
            
                Encryption e = new Encryption();
                String message = e.bTS(e.decryptString(m.message, "pass".toCharArray()));
                String sender = e.bTS(e.decryptString(m.sender, "pass".toCharArray()));
                String subject = e.bTS(e.decryptString(m.subject, "pass".toCharArray()));
            
            
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
                            new GuiMenu();
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

