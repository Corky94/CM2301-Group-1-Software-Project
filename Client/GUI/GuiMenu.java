package GUI;

import Console.*;
import Crypto.*;
import Message.Message;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.*;

class GuiMenu	extends	JFrame
{
	private		JTabbedPane tabbedPane;
	private		JPanel		inboxPanel;
	private		JPanel		composePanel;
        private         SecureDetails   s = new SecureDetails(); 
        private         Message[]       m;
        private         JFrame          mainFrame;
        private         JList           inboxList;

	public GuiMenu()
	{   
                
	}
        public void createMenu(){
                
                
		JPanel topPanel = new JPanel();
              
		
		topPanel.setPreferredSize(new Dimension(140, 480));
		JLabel userID = new JLabel("UserId: " + s.getID()  );
		userID.setBounds(150, 0, 550, 25);
		topPanel.add(userID);
		
		topPanel.setLayout( new BorderLayout() );
		

		// Create the tab pages
		createPage1();
		createPage2();

		// Create a tabbed pane
		tabbedPane = new JTabbedPane();
		tabbedPane.addTab( "Inbox", inboxPanel );
		tabbedPane.addTab( "Compose", composePanel );
		topPanel.add( tabbedPane, BorderLayout.CENTER );
                mainFrame = new JFrame();
		mainFrame.setVisible( true );
                mainFrame.setTitle( "Menu" );
		mainFrame.setSize( 700, 700 );
		mainFrame.setResizable(false);
		mainFrame.setBackground( Color.blue );
                mainFrame.getContentPane().add(topPanel);
        }


	public void createPage1()
	{
		   
		inboxPanel = new JPanel();
		inboxPanel.setLayout( null );

		JLabel label1 = new JLabel("Search");
        JTextField search = new JTextField();
        label1.setBounds(10, 10, 50, 25);
        search.setBounds( 60, 10, 610, 28 );
        inboxPanel.add(label1);
        inboxPanel.add(search);
        
        //adding buttons
        final JButton read = new JButton("Read");
        read.setEnabled(false);
        JButton delete = new JButton("Delete");
		read.setBounds(10, 50, 100, 25);
		delete.setBounds(120, 50, 100, 25);
		inboxPanel.add(read);
		inboxPanel.add(delete);
                

                
                
		
                User u = new User();
                
                m = u.receiveEmails();
                int length = m.length;
		String[] listContent = new String[length];
                Encryption e = new Encryption();


//               
                if (length >0){
                    
                
                for (int i =0; i< m.length ; i++){
                    String message = e.bTS(e.decryptString(m[i].getMessage(), "pass".toCharArray()));
                    String sender = e.bTS(e.decryptString(m[i].getSender(), "pass".toCharArray()));
                    String subject = e.bTS(e.decryptString(m[i].getSubject(), "pass".toCharArray()));
                    
                    listContent[i] ="From: " +sender +"\n " + subject + "\n " +message +"\n   \n";
                    
                }
                }
		inboxList = new JList(listContent);
                inboxList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                inboxList.setLayoutOrientation(JList.VERTICAL);
                ListSelectionModel mod = inboxList.getSelectionModel();
                mod.addListSelectionListener(new SharedListSelectionHandler(){
                    
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        if (e.getValueIsAdjusting() == false) {

                        if (inboxList.getSelectedIndex() == -1) {
                        
                            read.setEnabled(false);

                        } else {
                        
                            read.setEnabled(true);
                                    }
                        }
                    }
                    
                });
                
                
                
                JScrollPane listScroller = new JScrollPane(inboxList);
                listScroller.setBounds(10, 90, 660, 500);
		inboxPanel.add(listScroller);
               

               
               read.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
                                   int loc = inboxList.getSelectedIndex();

                                   GuiMessage mes = new GuiMessage();
                                   mainFrame.setVisible(false);
                                  
                                   mes.newMessage(m[loc]);
                                   
                        
                        }
               });
	}
       

	public void createPage2()
	{
		composePanel = new JPanel();
		composePanel.setLayout(null);
		
		JLabel toLabel = new JLabel("To");
		toLabel.setBounds(10, 10, 50, 25);
        composePanel.add(toLabel);
        final JTextField toText = new JTextField();
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
                            
                            


			}
		});
        }

    // Main method to get things started
	public static void main( String args[] )
	{
		            GuiMenu mainFrame	= new GuiMenu();
		mainFrame.setTitle( "Menu" );
		mainFrame.setSize( 700, 700 );
		mainFrame.setResizable(false);
		mainFrame.setBackground( Color.blue );
		mainFrame.setVisible( true );

	}
}

class SharedListSelectionHandler implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {

            
        }
}

