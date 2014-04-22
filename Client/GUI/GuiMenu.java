package GUI;

import Console.*;
import Crypto.*;
import static GUI.GuiLogin.frame;
import Message.Message;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;

class GuiMenu	extends	JFrame
{
      private	      JTabbedPane   tabbedPane;
      private	      JPanel        inboxPanel;
      private	      JPanel        composePanel;
      private	      JPanel        contactsPanel;
      private	      JPanel        settingsPanel;
      private	      JPanel        stegPanel;
      private         SecureDetails s = new SecureDetails(); 
      private         Message[]     m;
      private         JFrame        mainFrame;
      private         JList         inboxList;
      
      public GuiMenu() throws IOException
      {   
        mainFrame = new JFrame("Menu");
        //mainFrame.setResizable(false);
        mainFrame.setSize(850, 650);
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
      
      public void createMenu(final JPanel panel) throws IOException{
        panel.setLayout( null );
        panel.setBackground(Color.WHITE);
        //adding our logo 
        JLabel imgLabel = new JLabel();
        imgLabel.setIcon(new ImageIcon("mainScreenLogo.jpg"));
        imgLabel.setBounds(680, 0, 140, 30);
        panel.add (imgLabel);
        // Create the tab pages
        createPage1();
        createPage2();
        createPage3();
        createPage4();
        createPage5();
        // Create a tabbed pane
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab( "Inbox", inboxPanel );
        tabbedPane.addTab( "Compose", composePanel );
        tabbedPane.addTab( "Contacts", contactsPanel );
        tabbedPane.addTab( "Settings", settingsPanel );
        tabbedPane.addTab( "Image Encode/Decode", stegPanel );
        tabbedPane.setBounds(0, 10, 835, 612);
        panel.add( tabbedPane);
      }

      //Inbox.
      @SuppressWarnings("empty-statement")
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
        final JButton update = new JButton("Update");
        final JButton delete = new JButton("Delete");
        delete.setEnabled(false);
        JButton selectAll = new JButton("Select All");
        final JButton forward = new JButton("Forward");
        forward.setEnabled(false);
        final JButton reply = new JButton("Reply");
        reply.setEnabled(false);
        update.setBounds(20, 52, 100, 25);
        delete.setBounds(187, 50, 100, 25);
        reply.setBounds(357, 50, 100, 25);
        forward.setBounds(520, 50, 100, 25);
        selectAll.setBounds(680, 50, 100, 25);
        inboxPanel.add(update);
        inboxPanel.add(delete);
        inboxPanel.add(selectAll);
        inboxPanel.add(forward);
        inboxPanel.add(reply);

        User u = new User();
        m = u.receiveEmails(s);
        int length = m.length;
        String[] listContent = new String[length];
        Encryption e = new Encryption();

        if (length >0){
            for (int i =0; i< m.length ; i++){
                String message = e.bTS(e.decryptString(m[i].getMessage()));
                String sender = e.bTS(e.decryptString(m[i].getSender()));
                String subject = e.bTS(e.decryptString(m[i].getSubject()));
                listContent[i] =/*"From: " +sender +*/"Subject: " + subject ;;
            }
        }
        inboxList = new JList(listContent);
        inboxList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        inboxList.setLayoutOrientation(JList.VERTICAL);
        
        JScrollPane listScroller = new JScrollPane(inboxList,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED ,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        listScroller.setBounds(10, 90, 250, 470);
        inboxPanel.add(listScroller);         
        
        
        final JLabel fromLabel = new JLabel();
        fromLabel.setBounds(270, 90, 80, 25);
        inboxPanel.add(fromLabel);
        final JLabel fromIDLabel = new JLabel();
        fromIDLabel.setBounds(290, 110, 475, 20);
        fromIDLabel.setVisible(false);
        inboxPanel.add(fromIDLabel);
        final JLabel fromText = new JLabel();
        fromText.setBounds(290, 110, 475, 20);
        
        inboxPanel.add(fromText); 
        
        final JLabel subjectLabel = new JLabel();
        subjectLabel.setBounds(270, 130, 80, 25);
        inboxPanel.add(subjectLabel);
        final JLabel subjectText = new JLabel();
        subjectText.setBounds(290, 150, 475, 20);
        subjectText.setOpaque(false);
        inboxPanel.add(subjectText); 
                
        final JLabel messageLabel = new JLabel();
        messageLabel.setBounds(270, 170, 80, 25);
        inboxPanel.add(messageLabel);
        final JTextArea messageText = new JTextArea();
        messageText.setBounds(290, 190, 475, 400);
        messageText.setEditable(false);
        messageText.setLineWrap(true);
        messageText.setBackground(new Color(238,238,238));
        inboxPanel.add(messageText); 
        
        //read action even is empty
        //Being changed to an Update button.
        update.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                
                try {
                    //inboxPanel.repaint();
                    mainFrame.setVisible(false);
                    new GuiMenu(); 
                    //inboxPanel = new JPanel();
                    JOptionPane.showMessageDialog(contactsPanel, "Updated Inbox.", "",
                    JOptionPane.INFORMATION_MESSAGE);
                    
                } catch(IOException ex) {  
                    JOptionPane.showMessageDialog(contactsPanel, "Sorry an error has occured while trying to collect your mail, Please restart program.", "Error",
                    JOptionPane.ERROR_MESSAGE);     
                }
            }
        });
        
        ListSelectionModel mod = inboxList.getSelectionModel();
        mod.addListSelectionListener(new SharedListSelectionHandler(){

            @Override
            public void valueChanged(ListSelectionEvent e) {
               if (e.getValueIsAdjusting() == false) {

                if (!(inboxList.getSelectedIndex() == -1)) {
                    int loc = inboxList.getSelectedIndex();
                    Encryption en = new Encryption();

                    String message = en.bTS(en.decryptString(m[loc].getMessage()));                               
                    String sender = en.bTS(en.decryptString(m[loc].getSender()));
                    String subject = en.bTS(en.decryptString(m[loc].getSubject()));
                    
                    //output user's name rather that long hashcoded to indicate the recepiants id
                    //if id not in contacts folder then output the long id
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

                        String[] IDArr = new String[arr2.size()];
                        IDArr = arr2.toArray(IDArr);

                        String[]nameArr = new String[arr.size()];
                        nameArr = arr.toArray(nameArr);
                        
                    String userName = String.valueOf(sender);
                    for(int p = 0; p<nameArr.length; p++)
                    {
                        if(IDArr[p].equals(String.valueOf(sender)))
                        {
                            userName = nameArr[p];
                            break;
                        }
                    }
                    fromLabel.setText("From:");
                    subjectLabel.setText("Subject:");
                    messageLabel.setText("Message:");
                    fromText.setText(userName);
                    fromIDLabel.setText(String.valueOf(sender));
                    subjectText.setText(String.valueOf(subject));
                    messageText.setText(String.valueOf(message));
                    fromText.setOpaque(true);
                    subjectText.setOpaque(true);
                    messageText.setOpaque(true);

                    }catch (IOException ex){
                        JOptionPane.showMessageDialog(contactsPanel, "Sorry an error has occured while accessing your contacts, Please restart program.", "Error",
                        JOptionPane.ERROR_MESSAGE); 
                    }
                    delete.setEnabled(true);
                    forward.setEnabled(true);
                    reply.setEnabled(true);
                }
              }
            }
        });
       
        reply.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {                         
                GuiReply rep = new GuiReply(); 
                rep.reply(fromIDLabel.getText());
            }
        });
                
        forward.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {  
                GuiForward rep = new GuiForward(); 
                rep.forward(subjectText.getText(), messageText.getText());         
            }
        });
        
        //****************************************************************************
        //delete message button incomplete.
        /*delete.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) { 
                
                try {
                    int x = inboxList.getSelectedIndex();
                    User u = new User();
                    u.deleteMessage(x);                 
                    mainFrame.setVisible(false);
                    new GuiMenu(); 
                    
                } catch(IOException ex) {
                    JOptionPane.showMessageDialog(contactsPanel, "Sorry an error has occured while trying to delete the choosen mail, Please restart program.", "Error",
                    JOptionPane.ERROR_MESSAGE);   
                }
            }
        });*/
        //****************************************************************************
    }
  
    //compose.
    public void createPage2()
    {
        composePanel = new JPanel();
        composePanel.setLayout(null);

        JLabel toLabel = new JLabel("To");
        toLabel.setBounds(10, 10, 50, 25);
        composePanel.add(toLabel);
        final JTextField toText = new JTextField();
        toText.setBounds( 70, 10, 645, 28 );
        composePanel.add(toText);
        
        JButton contactButton = new JButton("Add");
        contactButton.setBounds( 715, 10, 60, 28 );
        composePanel.add(contactButton);
        
        JLabel subjectLabel = new JLabel("Subject");
        subjectLabel.setBounds(10, 50, 50, 25);
        composePanel.add(subjectLabel);
        final JTextField subjectText = new JTextField(15);
        subjectText.setBounds( 70, 50, 705, 28 );
        composePanel.add(subjectText);
 
        JLabel contentLabel = new JLabel("Content");
        contentLabel.setBounds(10, 90, 50, 25);
        composePanel.add(contentLabel);
        final JTextArea content = new JTextArea();
        content.setLineWrap(true); 
        JScrollPane scrollPane = new JScrollPane(content);
        scrollPane.setBounds(70, 90, 705, 420);
        composePanel.add(scrollPane);
        
        JButton send = new JButton("Send");
        send.setBounds(680, 535, 100, 25);
        composePanel.add(send);

        
        contactButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0){
            
            EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                }catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) { }

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
                    JOptionPane.showMessageDialog(contactsPanel, "Sorry an error has occured while accessing your contacts, Please restart program.", "Error",
                    JOptionPane.ERROR_MESSAGE); 
                }
            }              
            });   
            }            
        });
        
        send.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String to = toText.getText();
                String subject = subjectText.getText();
                String message = content.getText();
                if (to.equals(""))
                {
                    JOptionPane.showMessageDialog(composePanel, "You must have a contact in order to send a Message, Please insert a Contact.", "Cannot send message",
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
                        mainFrame.setVisible(false);
                        new GuiMenu();
                        } catch (IOException ex) {
                        Logger.getLogger(GuiMenu.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }else{
                    System.out.println(to);
                    User u = new User();
                    u.createMessage(message, to, subject);
                    JOptionPane.showMessageDialog(composePanel, "Message Successfully sent.", "Successfull",
                    JOptionPane.INFORMATION_MESSAGE); 

                    try {
                        mainFrame.setVisible(false);
                        new GuiMenu();
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(contactsPanel, "Sorry an error has occured, Please restart program.", "Error",
                        JOptionPane.ERROR_MESSAGE); 
                    }
                    
                }
	    }
          });
        }
        
        public void createPage3()
        //Contact.
        {
           contactsPanel = new JPanel();
           contactsPanel.setLayout(null);
           contactsPanel.setBackground(Color.white);
           try{
           BufferedReader in = new BufferedReader(new FileReader("Contacts.txt"));
           String str;
           ArrayList<String> arr = new ArrayList<String>();
           while ((str = in.readLine()) != null) {
                String[] ar=str.split(",");
                arr.add(ar[0]);
           }
           
            //adding buttons
            final JButton newContact = new JButton("New Contacts");
            newContact.setBounds(220, 10, 120, 25);        
            contactsPanel.add(newContact);
            
            final JButton edit = new JButton("Edit");
            edit.setEnabled(false);
            edit.setBounds(420, 10, 120, 25);
            contactsPanel.add(edit);
            
            final JButton delete = new JButton("Delete");
            delete.setEnabled(false);
            delete.setBounds(620, 10, 120, 25);
            contactsPanel.add(delete);
            
            
            final JList list = new JList(arr.toArray());
            list.setFont(new Font("Arial",Font.BOLD,16));
            list.setBackground(new Color(244, 244, 244));
            //list.setBorder(new BevelBorder(BevelBorder.LOWERED));
            
            
            
            list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            list.setLayoutOrientation(JList.VERTICAL);
            ListSelectionModel mod = list.getSelectionModel();
            mod.addListSelectionListener(new SharedListSelectionHandler(){

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting() == false) {

                if (!(list.getSelectedIndex() == -1)) {

                    edit.setEnabled(true);
                    delete.setEnabled(true);

                }
               }
             }

            });
            JScrollPane listScroller = new JScrollPane(list);
            listScroller.setBounds(20, 10, 170, 550);
            contactsPanel.add(listScroller);

            final JLabel contactName = new JLabel("Alias");
            contactName.setFont(new Font("Arial",Font.BOLD,14));
            contactName.setBounds(210, 60, 80, 25);
            contactName.setVisible(false);
            contactsPanel.add(contactName);
            
            final JTextField nameField = new JTextField(20);
	    nameField.setBounds(210, 90, 200, 28);
            nameField.setVisible(false);
            contactsPanel.add(nameField);
            
            final JLabel contactID = new JLabel("ID");
            contactID.setFont(new Font("Arial",Font.BOLD,14));
            contactID.setBounds(460, 60, 80, 25);
            contactID.setVisible(false);
            contactsPanel.add(contactID);
            
            final JTextField idField = new JTextField(100);
            idField.setVisible(false);
	    idField.setBounds(460, 90, 270, 28);
            contactsPanel.add(idField);
            
            final JButton save = new JButton("Save");
            save.setVisible(false);
            save.setBounds(610, 140, 120, 25);
            contactsPanel.add(save);
            
            final JLabel warningLabel = new JLabel();
            warningLabel.setIcon(new ImageIcon("warningPic.jpg"));
            warningLabel.setVisible(false);
            warningLabel.setBounds(430, 230, 140, 300);
            contactsPanel.add (warningLabel);
    
            
            final JLabel warningMessage = new JLabel("WARNING! when creating an alias it is important NOT to use the contacts real name as this data is not ");
            warningMessage.setFont(new Font("Arial",Font.BOLD,12));
            warningMessage.setVisible(false);
            warningMessage.setForeground(Color.red);
            warningMessage.setBounds(220, 440, 600, 20);
            contactsPanel.add (warningMessage);
            
            final JLabel warningMessage2 = new JLabel("held securely, an alias should be a name you will know them by which no-body else will associate ");
            warningMessage2.setFont(new Font("Arial",Font.BOLD,12));
            warningMessage2.setForeground(Color.red);
            warningMessage2.setVisible(false);
            warningMessage2.setBounds(220, 460, 600, 20);
            contactsPanel.add (warningMessage2);
            
            final JLabel warningMessage3 = new JLabel("with that person, for example, John could be Dr J or Mr Duck.");
            warningMessage3.setFont(new Font("Arial",Font.BOLD,12));
            warningMessage3.setForeground(Color.red);
            warningMessage3.setVisible(false);
            warningMessage3.setBounds(220, 480, 600, 20);
            contactsPanel.add (warningMessage3);
            
            newContact.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                contactName.setVisible(true);
                nameField.setVisible(true);
                contactID.setVisible(true);
                idField.setVisible(true);
                save.setVisible(true);
                warningLabel.setVisible(true);
                warningMessage.setVisible(true);
                warningMessage2.setVisible(true);
                warningMessage3.setVisible(true);
                
		}
	    });
            
            save.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String name = nameField.getText();
                String id = idField.getText();
                
                if((name.equals(""))||(id.equals(""))){
                    JOptionPane.showMessageDialog(contactsPanel, "Sorry you left one or more fields Blank, Please complete to continue.", "Invalid Details",
                     JOptionPane.WARNING_MESSAGE);
                }else if(name.matches("^.*[^a-zA-Z0-9].*$")){
                    JOptionPane.showMessageDialog(contactsPanel, "Name must not have Non-Alphanumerics, Please try again.", "Invalid Details",
                     JOptionPane.WARNING_MESSAGE);
                }else if(id.matches("^.*[^a-zA-Z0-9].*$")){
                   JOptionPane.showMessageDialog(contactsPanel, "ID must not have Non-Alphanumerics, Please try again.", "Invalid Details",
                     JOptionPane.WARNING_MESSAGE); 
                }else{
                  try{
                    File file =new File("Contacts.txt");
 
                    //if file doesnt exists, then create it
                    if(!file.exists()){
    			file.createNewFile();
                    }
 
                    //true = append file
                    FileWriter fileWritter = new FileWriter(file.getName(),true);
                    try (BufferedWriter bufferWritter = new BufferedWriter(fileWritter)) {
                        bufferWritter.newLine();
                        bufferWritter.write(name + "," + id );
                        JOptionPane.showMessageDialog(contactsPanel, "A new contact has been added Successfully.", "Successful",
                        JOptionPane.INFORMATION_MESSAGE);
                    }                    
                   }catch(IOException ex){
                      JOptionPane.showMessageDialog(contactsPanel, "Sorry an error has occured while saving a new Contact, Please restart program.", "Error",
                      JOptionPane.ERROR_MESSAGE); 
                   }
                  
                }
                    
            }
	    });
            }catch(IOException e){
               JOptionPane.showMessageDialog(contactsPanel, "Sorry an error has occured while accessing your contacts, Please restart program.", "Error",
               JOptionPane.ERROR_MESSAGE); 
            }
        }
        
        public void createPage4()
        {
           settingsPanel = new JPanel();
           settingsPanel.setLayout(null);
           settingsPanel.setBackground(Color.white);
           JLabel imgLabel = new JLabel();
           imgLabel.setIcon(new ImageIcon("logo2.jpg"));// your image here 
           imgLabel.setBounds(160, 10, 780, 90);
           settingsPanel.add(imgLabel);

           JButton copyButton = new JButton("Copy UserID To Clipboard");
           copyButton.setBounds(160, 140, 180, 25);
           settingsPanel.add(copyButton);
           copyButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
            String userID = s.getID(); 
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Clipboard clipboard = toolkit.getSystemClipboard();
            StringSelection strSel = new StringSelection(userID);
            clipboard.setContents(strSel, null);
            JOptionPane.showMessageDialog(settingsPanel, "Successfully Copied", "Copied",
            JOptionPane.INFORMATION_MESSAGE);
            }
            });

           JButton changePass = new JButton("Change Password");
           changePass.setBounds(490, 140, 180, 25);
           settingsPanel.add(changePass);
           
           final JLabel existingPass = new JLabel("Existing Password:");
           existingPass.setFont(new Font("Ariel", Font.BOLD, 14));
           existingPass.setBounds(490, 170, 180, 25);
           existingPass.setVisible(false);
           settingsPanel.add(existingPass);
           final JPasswordField existPasswordText = new JPasswordField(20);
	   existPasswordText.setBounds(490, 200, 180, 25);
           existPasswordText.setVisible(false);
           settingsPanel.add(existPasswordText);
           
           final JLabel newPass = new JLabel("New Password:");
           newPass.setFont(new Font("Ariel", Font.BOLD, 14));
           newPass.setBounds(490, 240, 180, 25);
           newPass.setVisible(false);
           settingsPanel.add(newPass);
           final JPasswordField newPassTxt = new JPasswordField(20);
	   newPassTxt.setBounds(490, 270, 180, 25);
           newPassTxt.setVisible(false);
           settingsPanel.add(newPassTxt);
           
           final JLabel confirmPass = new JLabel("Confirm Password:");
           confirmPass.setFont(new Font("Ariel", Font.BOLD, 14));
           confirmPass.setBounds(490, 310, 180, 25);
           confirmPass.setVisible(false);
           settingsPanel.add(confirmPass);
           final JPasswordField confirmPassTxt = new JPasswordField(20);
	   confirmPassTxt.setBounds(490, 340, 180, 25);
           confirmPassTxt.setVisible(false);
           settingsPanel.add(confirmPassTxt);
           
           final JButton change = new JButton("Change");
           change.setBackground(Color.orange);
           change.setBounds(570, 375, 100, 25);
           change.setVisible(false);
           settingsPanel.add(change);

           //a listener to make everything visible
           changePass.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                
                existingPass.setVisible(true);
                existPasswordText.setVisible(true);
                newPass.setVisible(true);
                newPassTxt.setVisible(true);
                confirmPass.setVisible(true);
                confirmPassTxt.setVisible(true);
                change.setVisible(true);                 
            }
		});
           
            //a listener to change the password
            change.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                 //check if existing password was entered accurately
                 char[] eistPassword = existPasswordText.getPassword();
                 char[] newPass = newPassTxt.getPassword();
                 char[] conNewPass = confirmPassTxt.getPassword();
                 KeyVault kv = new KeyVault();
                 //if not then display error
                 
                 if((eistPassword.length ==0)||(newPass.length == 0)||(conNewPass.length == 0)){
                     JOptionPane.showMessageDialog(settingsPanel, "Sorry you left one or more fields Blank, Please complete to continue.", "Invalid Details",
                     JOptionPane.WARNING_MESSAGE);
                 }else if (kv.checkPassword(eistPassword) == false){
                     JOptionPane.showMessageDialog(settingsPanel, "Your existing password is Incorrect, Please try again.", "Invalid Details",
                     JOptionPane.WARNING_MESSAGE); 
                 }else if(Arrays.equals(newPass, conNewPass) == false){
                     JOptionPane.showMessageDialog(settingsPanel, "Sorry the new passwords entered are not identical, Please try again.", "Invalid Details",
                     JOptionPane.WARNING_MESSAGE);
                 }else{
                     
                     new Register(newPass);
                     JOptionPane.showMessageDialog(settingsPanel, "Successfully changed password", "Successfull",
                     JOptionPane.INFORMATION_MESSAGE);
                 }
                 
            }
		});
           
        }  

    boolean debug = true;

    JLabel lblEncode, lblDecode, lblMessageOut;
    JButton btnEncode, btnBrowseIn, btnDecode, btnAddContact, btnBrowseOut;
    JTextField txtFileIn, txtFileOut, txtMessage;
    JFileChooser fc;

    String fileInPath;
    String fileOutPath;
    String pathIn, originalIn, ext1In, steganIn, messageIn;

        //Steganography.
        public void createPage5()
        {
        stegPanel = new JPanel();
        stegPanel.setLayout(null);
           
           // Create encode panel
   
        //encodePanel.setLocation(0, 0);
        //encodePanel.setSize(580, 100);

        lblEncode = new JLabel("Select image to hide ID within: ");
        lblEncode.setLocation(60, 100);
        lblEncode.setSize(300, 30);
        stegPanel.add(lblEncode);

        txtFileIn = new JTextField();
        txtFileIn.setLocation(60, 125);
        txtFileIn.setSize(400, 30);
        stegPanel.add(txtFileIn);

        btnBrowseIn = new JButton("Browse...");
        btnBrowseIn.setLocation(470, 125);
        btnBrowseIn.setSize(120, 30);
        stegPanel.add(btnBrowseIn);

        btnEncode = new JButton("Encode");
        btnEncode.setLocation(470, 160);
        btnEncode.setSize(120, 30);
        //btnEncode.addActionListener(this);
        stegPanel.add(btnEncode);
        
        //******************************************
        
        lblDecode = new JLabel("Select encoded image: ");
        lblDecode.setLocation(60, 200);
        lblDecode.setSize(300, 30);
        stegPanel.add(lblDecode);

        txtFileOut = new JTextField();
        txtFileOut.setLocation(60, 225);
        txtFileOut.setSize(700, 30);
        stegPanel.add(txtFileOut);

        btnBrowseOut = new JButton("Browse...");
        btnBrowseOut.setLocation(470, 305);
        btnBrowseOut.setSize(120, 30);
        //btnBrowseOut.addActionListener(this);
        stegPanel.add(btnBrowseOut);

        btnDecode = new JButton("Decode");
        btnDecode.setLocation(470, 345);
        btnDecode.setSize(120, 30);
        //btnDecode.addActionListener(this);
        stegPanel.add(btnDecode);

        lblMessageOut = new JLabel("Message: ");
        lblMessageOut.setLocation(60, 280);
        lblMessageOut.setSize(300, 30);
        stegPanel.add(lblMessageOut);

        txtMessage = new JTextField();
        txtMessage.setLocation(60, 305);
        txtMessage.setSize(400, 30);
        stegPanel.add(txtMessage);

    btnBrowseIn.addActionListener(new ActionListener(){        
        @Override
        public void actionPerformed(ActionEvent e) {
            //open file browser
            System.out.println("btnBrowse pressed");

            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "PNG images", "png");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(btnBrowseIn);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                fileInPath = chooser.getSelectedFile().getAbsolutePath();
                txtFileIn.setText(fileInPath);
                pathIn = chooser.getCurrentDirectory().toString();
                originalIn = chooser.getSelectedFile().toString();
                //ext1In = fileInPath.substring(fileInPath.lastIndexOf("."),fileInPath.length());
                steganIn = "OUTPUT FILE NAME";
                messageIn = s.getID();
            }
        }
    });
    
    btnEncode.addActionListener(new ActionListener(){        
        @Override
        public void actionPerformed(ActionEvent e) {
        //encode the message
        System.out.println("btnEncode pressed");
        encode(pathIn, originalIn, steganIn, messageIn);
        }
    });
    
    btnBrowseOut.addActionListener(new ActionListener(){        
        @Override
        public void actionPerformed(ActionEvent e) {
        System.out.println("btnBrowseOut pressed");

            //open file browser
            System.out.println("btnBrowse pressed");

            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "PNG images", "png");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(btnBrowseOut);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                fileOutPath = chooser.getSelectedFile().getAbsolutePath();
                txtFileOut.setText(fileOutPath);
            }
        }
    });
    
    btnDecode.addActionListener(new ActionListener(){        
        @Override
        public void actionPerformed(ActionEvent e) {
        System.out.println("btnDecode pressed");
        txtMessage.setText(decode(fileOutPath, fileOutPath));
        }
    });
    
    }
    /*
     * Encrypt an image with text, the output file will be of type .png
     *
     * @param path The path (folder) containing the image to modify
     *
     * @param original The name of the image to modify
     *
     * @param stegan The output name of the file
     *
     * @param message The text to hide in the image
     *
     * @param type integer representing either basic or advanced encoding
     */
    public boolean encode(String path, String original, String stegan, String message) {
        String file_name = image_path();
        if(debug) System.out.println(file_name);
        BufferedImage image_orig = getImage(file_name);

        // user space is not necessary for Encrypting
        BufferedImage image = user_space(image_orig);
        image = add_text(image, message);

        return (setImage(image, new File(fileInPath),
                "png"));
    }
    
    /*
     * Decrypt assumes the image being used is of type .png, extracts the hidden
     * text from an image
     *
     * @param path The path (folder) containing the image to extract the message
     * from
     *
     * @param name The name of the image to extract the message from
     *
     * @param type integer representing either basic or advanced encoding
     */
    public String decode(String path, String name) {
        byte[] decode;
        try {
            // user space is necessary for decrypting
            BufferedImage image = user_space(getImage(image_path()));
            decode = decode_text(get_byte_data(image));
            System.out.println(new String(decode));
            return (new String(decode));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "There is no hidden message in this image!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return "";
        }
    }
    /*
     * Returns the complete path of a file, in the form: path\name.ext
     *
     * @param path The path (folder) of the file
     *
     * @param name The name of the file
     *
     * @param ext The extension of the file
     *
     * @return A String representing the complete path of a file
     */
    private String image_path() {
        return fileInPath;
    }

    /*
     * Get method to return an image file
     *
     * @param f The complete path name of the image.
     *
     * @return A BufferedImage of the supplied file path
     *
     * @see Steganography.image_path
     */
    private BufferedImage getImage(String f) {
        BufferedImage image = null;
        File file = new File(f);

        try {
            image = ImageIO.read(file);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Image could not be read!",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        return image;
    }

    /*
     * Set method to save an image file
     *
     * @param image The image file to save
     *
     * @param file File to save the image to
     *
     * @param ext The extension and thus format of the file to be saved
     *
     * @return Returns true if the save is successful
     */
    private boolean setImage(BufferedImage image, File file, String ext) {
        try {
            //file.delete(); // delete resources used by the File
            ImageIO.write(image, ext, file);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "File could not be saved!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /*
     * Handles the addition of text into an image
     *
     * @param image The image to add hidden text to
     *
     * @param text The text to hide in the image
     *
     * @return Returns the image with the text embedded in it
     */
    private BufferedImage add_text(BufferedImage image, String text) {
        // convert all items to byte arrays: image, message, message length
        byte img[] = get_byte_data(image);
        byte msg[] = text.getBytes();
        byte len[] = bit_conversion(msg.length);
        try {
            encode_text(img, len, 0); // 0 first positiong
            encode_text(img, msg, 32); // 4 bytes of space for length:
                                        // 4bytes*8bit = 32 bits
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Target File cannot hold message!", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return image;
    }

    /*
     * Creates a user space version of a Buffered Image, for editing and saving
     * bytes
     *
     * @param image The image to put into user space, removes compression
     * interferences
     *
     * @return The user space version of the supplied image
     */
    private BufferedImage user_space(BufferedImage image) {
        // create new_img with the attributes of image
        BufferedImage new_img = new BufferedImage(image.getWidth(),
                image.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D graphics = new_img.createGraphics();
        graphics.drawRenderedImage(image, null);
        graphics.dispose(); // release all allocated memory for this image
        return new_img;
    }

    /*
     * Gets the byte array of an image
     *
     * @param image The image to get byte data from
     *
     * @return Returns the byte array of the image supplied
     *
     * @see Raster
     *
     * @see WritableRaster
     *
     * @see DataBufferByte
     */
    private byte[] get_byte_data(BufferedImage image) {
        WritableRaster raster = image.getRaster();
        DataBufferByte buffer = (DataBufferByte) raster.getDataBuffer();
        return buffer.getData();
    }

    /*
     * Gernerates proper byte format of an integer
     *
     * @param i The integer to convert
     *
     * @return Returns a byte[4] array converting the supplied integer into
     * bytes
     */
    private byte[] bit_conversion(int i) {
        // originally integers (ints) cast into bytes
        // byte byte7 = (byte)((i & 0xFF00000000000000L) >>> 56);
        // byte byte6 = (byte)((i & 0x00FF000000000000L) >>> 48);
        // byte byte5 = (byte)((i & 0x0000FF0000000000L) >>> 40);
        // byte byte4 = (byte)((i & 0x000000FF00000000L) >>> 32);

        // only using 4 bytes
        byte byte3 = (byte) ((i & 0xFF000000) >>> 24); // 0
        byte byte2 = (byte) ((i & 0x00FF0000) >>> 16); // 0
        byte byte1 = (byte) ((i & 0x0000FF00) >>> 8); // 0
        byte byte0 = (byte) ((i & 0x000000FF));
        // {0,0,0,byte0} is equivalent, since all shifts >=8 will be 0
        return (new byte[] { byte3, byte2, byte1, byte0 });
    }

    /*
     * Encode an array of bytes into another array of bytes at a supplied offset
     *
     * @param image Array of data representing an image
     *
     * @param addition Array of data to add to the supplied image data array
     *
     * @param offset The offset into the image array to add the addition data
     *
     * @return Returns data Array of merged image and addition data
     */
    private byte[] encode_text(byte[] image, byte[] addition, int offset) {
        // check that the data + offset will fit in the image
        if (addition.length + offset > image.length) {
            throw new IllegalArgumentException("File not long enough!");
        }
        // loop through each addition byte
        for (int i = 0; i < addition.length; ++i) {
            // loop through the 8 bits of each byte
            int add = addition[i];
            for (int bit = 7; bit >= 0; --bit, ++offset) // ensure the new
                                                            // offset value
                                                            // carries on
                                                            // through both
                                                            // loops
            {
                // assign an integer to b, shifted by bit spaces AND 1
                // a single bit of the current byte
                int b = (add >>> bit) & 1;
                // assign the bit by taking: [(previous byte value) AND 0xfe] OR
                // bit to add
                // changes the last bit of the byte in the image to be the bit
                // of addition
                image[offset] = (byte) ((image[offset] & 0xFE) | b);
            }
        }
        return image;
    }

    /*
     * Retrieves hidden text from an image
     *
     * @param image Array of data, representing an image
     *
     * @return Array of data which contains the hidden text
     */
    private byte[] decode_text(byte[] image) {
        int length = 0;
        int offset = 32;
        // loop through 32 bytes of data to determine text length
        for (int i = 0; i < 32; ++i) // i=24 will also work, as only the 4th
                                        // byte contains real data
        {
            length = (length << 1) | (image[i] & 1);
        }

        byte[] result = new byte[length];

        // loop through each byte of text
        for (int b = 0; b < result.length; ++b) {
            // loop through each bit within a byte of text
            for (int i = 0; i < 8; ++i, ++offset) {
                // assign bit: [(new byte value) << 1] OR [(text byte) AND 1]
                result[b] = (byte) ((result[b] << 1) | (image[offset] & 1));
            }
        }
        return result;
    }       

        // Main method to get things started
	public static void main( String args[] ) throws IOException
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

