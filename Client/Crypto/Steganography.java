package Crypto;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Steganography implements ActionListener{
	boolean debug = true;
	
	JPanel encodePanel, decodePanel;
	JLabel lblEncode, lblDecode, lblMessageOut;
	JButton btnEncode, btnDecode, btnAddContact, btnBrowseIn, btnBrowseOut;
	JTextField txtFileIn, txtFileOut, txtMessage;
	JFileChooser fc;
	
	String fileInPath, fileOutPath;
	String pathIn, originalIn, ext1In, steganIn, messageIn;
	
	public JPanel Steganography() {
		// Bottom panel to put everything on
		JPanel totalGUI = new JPanel();
		totalGUI.setLayout(null);
		
		// Create encode panel
		encodePanel = new JPanel();
		encodePanel.setLayout(null); 
		encodePanel.setLocation(0, 0);
		encodePanel.setSize(580, 100);
		totalGUI.add(encodePanel);
		
		lblEncode = new JLabel("Select image to hide ID within: ");
		lblEncode.setLocation(10, 0);
		lblEncode.setSize(300, 30);
		encodePanel.add(lblEncode);
		
		txtFileIn = new JTextField();
		txtFileIn.setLocation(10, 25);
		txtFileIn.setSize(400, 30);
		encodePanel.add(txtFileIn);
		
		btnBrowseIn = new JButton("Browse...");
		btnBrowseIn.setLocation(420, 25);
		btnBrowseIn.setSize(120, 30);
		btnBrowseIn.addActionListener(this);
		encodePanel.add(btnBrowseIn);
		
		btnEncode = new JButton("Encode");
		btnEncode.setLocation(420, 60);
		btnEncode.setSize(120, 30);
		btnEncode.addActionListener(this);
		encodePanel.add(btnEncode);
		
		
		
		// Create decode panel 
		decodePanel = new JPanel();
		decodePanel.setLayout(null); 
		decodePanel.setLocation(0, 100);
		decodePanel.setSize(580, 200);
		totalGUI.add(decodePanel);
		
		lblDecode = new JLabel("Select encoded image: ");
		lblDecode.setLocation(10, 0);
		lblDecode.setSize(300, 30);
		decodePanel.add(lblDecode);
		
		txtFileOut = new JTextField();
		txtFileOut.setLocation(10, 25);
		txtFileOut.setSize(400, 30);
		decodePanel.add(txtFileOut);
		
		btnBrowseOut = new JButton("Browse...");
		btnBrowseOut.setLocation(420, 25);
		btnBrowseOut.setSize(120, 30);
		btnBrowseOut.addActionListener(this);
		decodePanel.add(btnBrowseOut);
		
		btnDecode = new JButton("Decode");
		btnDecode.setLocation(420, 60);
		btnDecode.setSize(120, 30);
		btnDecode.addActionListener(this);
		decodePanel.add(btnDecode);
		
		lblMessageOut = new JLabel("Message: ");
		lblMessageOut.setLocation(10, 80);
		lblMessageOut.setSize(300, 30);
		decodePanel.add(lblMessageOut);
		
		txtMessage = new JTextField();
		txtMessage.setLocation(10, 105);
		txtMessage.setSize(400, 30);
		decodePanel.add(txtMessage);
		
		btnAddContact = new JButton("Add Contact");
		btnAddContact.setLocation(420, 105);
		btnAddContact.setSize(120, 30);
		btnAddContact.addActionListener(this);
		decodePanel.add(btnAddContact);
		
		
		totalGUI.setOpaque(true);
		return totalGUI;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnBrowseIn) {
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
				messageIn ="USER_ID GOES HERE";
			}
			
		} else if (e.getSource() == btnEncode) {
			//encode the message
			System.out.println("btnEncode pressed");
			encode(pathIn, originalIn, steganIn, messageIn);
			
		} else if(e.getSource() == btnBrowseOut) {
			System.out.println("btnBrowseOut pressed");
			
			//open file browser
			System.out.println("btnBrowse pressed");
			
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
			    "PNG images", "png");
			chooser.setFileFilter(filter);
			int returnVal = chooser.showOpenDialog(btnBrowseIn);
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				fileOutPath = chooser.getSelectedFile().getAbsolutePath();
				txtFileOut.setText(fileOutPath);
			}
			
		} else if(e.getSource() == btnDecode) {
			System.out.println("btnDecode pressed");
			txtMessage.setText(decode(fileInPath, fileInPath));
			
		} else if(e.getSource() == btnAddContact) {
			System.out.println("btnAddContact pressed");
			
		}

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

	private static void initializeGUI() {
		JFrame frame = new JFrame();
		
		try {
		    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (ClassNotFoundException e) {
		}
		catch (InstantiationException e) {
		}
		catch (IllegalAccessException e) {
		}
		catch (UnsupportedLookAndFeelException e) {
		}
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Steganography");
		frame.setSize(600, 300);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		Steganography window = new Steganography();
		frame.setContentPane(window.Steganography());
		frame.setVisible(true);	
	}
	
	public static void main(String[] args) {
		//Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	initializeGUI();
            }
        });
	}
}
