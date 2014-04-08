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
        private         SecureDetails   s;
        private         Message[]       m;
        private         JFrame          mainFrame;
        private         JList           inboxList;

	public GuiMenu(char[] password)
	{   
           s = new SecureDetails(password);
           mainFrame = new JFrame("Menu");
		mainFrame.setSize(800, 755);
                Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
                int x = (int) ((dimension.getWidth() - mainFrame.getWidth()) / 2);
                int y = (int) ((dimension.getHeight() - mainFrame.getHeight()) / 2);
                mainFrame.setLocation(x, y);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
                mainFrame.add(panel);
		createMenu(panel);
                mainFrame.getContentPane().setBackground(Color.WHITE);
		mainFrame.setVisible(true);     
	}
        public GuiMenu()
	{   
           
		mainFrame.setSize(800, 755);
                Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
                int x = (int) ((dimension.getWidth() - mainFrame.getWidth()) / 2);
                int y = (int) ((dimension.getHeight() - mainFrame.getHeight()) / 2);
                mainFrame.setLocation(x, y);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
                mainFrame.add(panel);
		createMenu(panel);
                mainFrame.getContentPane().setBackground(Color.WHITE);
		mainFrame.setVisible(true);     
	}
        public void createMenu(final JPanel panel){
                
                
                panel.setLayout( null );
                panel.setBackground(Color.WHITE);

		JLabel userID = new JLabel("UserId: " + s.getID()  );
		userID.setBounds(5, 0, 550, 25);
		panel.add(userID);
		
                //adding our logo 
		JLabel imgLabel = new JLabel();
                imgLabel.setIcon(new ImageIcon("C:\\Users\\Melvin\\Documents\\group project2013\\mainScreenLogo.jpg"));// your image here 
                imgLabel.setBounds(635, 0, 140, 30);
                panel.add (imgLabel);
                
		//adding a dropdown menu
                String[] dropDownList = { "","Inbox", "Settings", "Contacts", "Sign Out"};
                JComboBox drpDwnList = new JComboBox(dropDownList);
                drpDwnList.setSelectedIndex(0);
                drpDwnList.setBounds(635, 30, 140, 20);
                drpDwnList.setBackground(Color.white);
                //drpDwnList.setForeground(Color.red);
                panel.add(drpDwnList);
                
		// Create the tab pages
		createPage1();
		createPage2();

		// Create a tabbed pane
		tabbedPane = new JTabbedPane();
		tabbedPane.addTab( "Inbox", inboxPanel );
		tabbedPane.addTab( "Compose", composePanel );
                tabbedPane.setBounds(0, 40, 785, 680);
		panel.add( tabbedPane);
		//mainFrame.setBackground( Color.white );
                //mainFrame.getContentPane().add(topPanel);
        }


	public void createPage1()
	{
		   
		inboxPanel = new JPanel();
		inboxPanel.setLayout( null );

		JLabel label1 = new JLabel("Search");
        JTextField search = new JTextField();
        label1.setBounds(10, 10, 50, 25);
        search.setBounds( 60, 10, 715, 28 );
        inboxPanel.add(label1);
        inboxPanel.add(search);
        
        //adding buttons
        final JButton read = new JButton("Read");
        read.setEnabled(false);
        final JButton delete = new JButton("Delete");
        delete.setEnabled(false);
        JButton selectAll = new JButton("Select All");
        final JButton forward = new JButton("Forward");
        forward.setEnabled(false);
        final JButton reply = new JButton("Reply");
        reply.setEnabled(false);
		read.setBounds(10, 50, 100, 25);
		delete.setBounds(180, 50, 100, 25);
                reply.setBounds(350, 50, 100, 25);
                forward.setBounds(520, 50, 100, 25);
                selectAll.setBounds(675, 50, 100, 25);
		inboxPanel.add(read);
		inboxPanel.add(delete);
                inboxPanel.add(selectAll);
                inboxPanel.add(forward);
                inboxPanel.add(reply);
                
                

                
                
		
                User u = new User();
                
                m = u.receiveEmails(s);
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
                            delete.setEnabled(true);
                            forward.setEnabled(true);
                            reply.setEnabled(true);
                                    }
                        }
                    }
                    
                });
                
                
                
                JScrollPane listScroller = new JScrollPane(inboxList);
                listScroller.setBounds(10, 90, 765, 550);
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
        toText.setBounds( 60, 10, 645, 28 );
        composePanel.add(toText);
        
        JButton contactButton = new JButton("+");
        contactButton.setBounds( 720, 10, 45, 28 );
        composePanel.add(contactButton);
        
        JLabel subjectLabel = new JLabel("Subject");
        subjectLabel.setBounds(10, 50, 50, 25);
        composePanel.add(subjectLabel);
        final JTextField subjectText = new JTextField();
        subjectText.setBounds( 60, 50, 705, 28 );
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
        content.setBounds(60, 90, 705, 520);
        //composePanel.add(spane);
        composePanel.add(content);
        
        JButton send = new JButton("Send");
        send.setBounds(665, 620, 100, 25);
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
                GuiMenu mainFrame = new GuiMenu();
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

