package view;


import java.awt.EventQueue;


import java.text.DateFormat;  
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.util.Calendar;

import java.io.File;
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.Font;
import java.awt.GridLayout;

//import javax.swing.event.AncestorListener;
import javax.swing.text.BadLocationException;
//import javax.swing.text.DefaultHighlighter;

import commands.CommandFactory;
import commands.EditDocument;
//import commands.LineToSpeech;
//import commands.SaveDocument;
import encodingstrategies.AtBashEncoding;
import encodingstrategies.Rot13Encoding;
import encodingstrategies.StrategiesFactory;
//import jdk.internal.platform.Container;
import model.Document;
import text2speechapis.TextToSpeechAPI;
import text2speechapis.TextToSpeechAPIFactory;

//import javax.swing.event.AncestorEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
//import java.awt.Component;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
import java.awt.Color;
//import java.awt.SystemColor;
import java.util.*;

public class Text2SpeechEditorView {

	private JFrame frame;
	private JMenuItem New_Doc;
	private JMenuItem Save_Doc;
	private JMenuItem Open_Doc;
	private JTextField textField;
	private JTextField textField_1;
	private JMenuItem mntmNewMenuItem;
	private JMenuItem mntmCopy;
	private JMenuItem mntmPaste;
	private JMenuItem mntmSelectall;
	private JTextArea textArea;
	private JTextArea refToArea = new JTextArea();
	private JTextField textField_2;
	private JLabel lblJtextarea;
	private JMenuItem mntmEncode;
	private JButton btnLineToSpeech;
	
	private JComboBox<Object> cbox;
	private JTextField lineField;
    private String[] optionNames = {"NORMAL", "REVERSED", "ENCODED"};
    private String[] optionNames2 = {"Rot13", "Atbash"};
    private String[] optionNames3 = {"NORMAL", "REVERSED"};
    
	private JTextField volumeField;
	private JTextField rateField;
	private JTextField pitchField;
	private JMenuItem mntmTransform;
	private String filepath;
	
	List <JMenuItem> actions = new ArrayList<JMenuItem>();
	HashMap<JMenuItem, ActionEvent> map = new HashMap<JMenuItem, ActionEvent>();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Text2SpeechEditorView window = new Text2SpeechEditorView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Text2SpeechEditorView() {
		

		initialize();
		createEvents();
	}

	///////////////////////////////////////////////////////////
	// This method contain all the code for creating and
	// initializing components
	//////////////////////////////////////////////////////////
	private void initialize() {
		
		JFrame frame = new JFrame("Text to Speech");
		frame.setBackground(Color.PINK);
		frame.getContentPane().setForeground(new Color(176, 224, 230));
		frame.getContentPane().setBackground(new Color(36, 37, 42));
		frame.setSize(615,379);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBackground(new Color(176, 224, 230));
		textField.setBounds(82, 27, 70, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBackground(new Color(176, 224, 230));
		textField_1.setBounds(82, 52, 70, 19);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblTitle = new JLabel("title:");
		lblTitle.setForeground(new Color(221, 160, 221));
		lblTitle.setBounds(12, 29, 70, 15);
		frame.getContentPane().add(lblTitle);
		
		JLabel lblAuthor = new JLabel("author:");
		lblAuthor.setForeground(new Color(221, 160, 221));
		lblAuthor.setBounds(12, 54, 70, 15);
		frame.getContentPane().add(lblAuthor);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(237, 53, 305, 170);
		frame.getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		refToArea = textArea;
		//textArea.setEditable(false);
		textArea.setBackground(new Color(176, 224, 230));
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);
		
		JLabel lblForNewDocument = new JLabel("for new document give:");
		lblForNewDocument.setFont(new Font("Dialog", Font.BOLD, 10));
		lblForNewDocument.setForeground(Color.PINK);
		lblForNewDocument.setBounds(12, 2, 140, 15);
		frame.getContentPane().add(lblForNewDocument);
		
		JLabel lblSaveTo = new JLabel("for save document give:");
		lblSaveTo.setForeground(Color.PINK);
		lblSaveTo.setFont(new Font("Dialog", Font.BOLD, 10));
		lblSaveTo.setBounds(12, 86, 140, 15);
		frame.getContentPane().add(lblSaveTo);
		
		JLabel lblFilename = new JLabel("filename:");
		lblFilename.setFont(new Font("Dialog", Font.BOLD, 11));
		lblFilename.setForeground(new Color(221, 160, 221));
		lblFilename.setBounds(12, 113, 60, 15);
		frame.getContentPane().add(lblFilename);
		
		textField_2 = new JTextField();
		textField_2.setBackground(new Color(176, 224, 230));
		textField_2.setBounds(82, 110, 70, 19);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		lblJtextarea = new JLabel("JTextArea");
		lblJtextarea.setBackground(new Color(221, 160, 221));
		lblJtextarea.setForeground(new Color(221, 160, 221));
		lblJtextarea.setFont(new Font("Dialog", Font.BOLD, 14));
		lblJtextarea.setBounds(242, 29, 87, 15);
		frame.getContentPane().add(lblJtextarea);
		
		btnLineToSpeech = new JButton("Line To Speech");
		btnLineToSpeech.setForeground(new Color(221, 160, 221));
		btnLineToSpeech.setBackground(new Color(36, 37, 42));
		
		btnLineToSpeech.setBounds(260, 235, 269, 25);
		frame.getContentPane().add(btnLineToSpeech);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		JMenuBar menubar = new JMenuBar();
		menubar.setForeground(new Color(221, 160, 221));
		menubar.setBackground(new Color(221, 160, 221));
		frame.setJMenuBar(menubar);
		
		
		JMenu file = new JMenu("File");
		menubar.add(file);
		New_Doc = new JMenuItem("Create New Document");
		
		Save_Doc = new JMenuItem("Save Document");
		
		Open_Doc = new JMenuItem("Open Document");
		
		file.add(New_Doc);
		file.add(Save_Doc);
		file.add(Open_Doc);
		
		mntmTransform = new JMenuItem("Transform");
		
		file.add(mntmTransform);
		
		mntmEncode = new JMenuItem("Encode");
		
		file.add(mntmEncode);
		
		JMenu mnEdit = new JMenu("Edit");
		menubar.add(mnEdit);
		
		mntmNewMenuItem = new JMenuItem("cut");
		mnEdit.add(mntmNewMenuItem);
		
		mntmCopy = new JMenuItem("copy");
		mnEdit.add(mntmCopy);
		
		mntmPaste = new JMenuItem("paste");
		mnEdit.add(mntmPaste);
		
		mntmSelectall = new JMenuItem("selectAll");
		
		mnEdit.add(mntmSelectall);
		
		
	}
	
	
	///////////////////////////////////////////////////////////
	// This method contain all the code for creating events
	//////////////////////////////////////////////////////////
	private void createEvents(){
		
		
		extracted();
		
		Save_Doc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String type = "SaveDocument";
				actions.add(Save_Doc);
				map.put(Save_Doc, arg0);
				String filename = textField_2.getText();
				String contents = textArea.getText();
				Date date = Calendar.getInstance().getTime();  
		        DateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");  
		        String strDate = dateFormat.format(date);  
				if(filename.trim().isEmpty()){
					filename = null;
				}
				Document doc = new Document(filename, contents, null, 1, null, null, 0, 0, 0);
				CommandFactory newObject = new CommandFactory();
				
				ActionListener currentCommand = newObject.createCommand(type, doc, strDate);
				currentCommand.actionPerformed(arg0);
			}
		});
		
		Open_Doc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 String type = "OpenDocument";
				 actions.add(Open_Doc);
				 map.put(Open_Doc, arg0);
				 JFileChooser fc=new JFileChooser();    
				    int i=fc.showOpenDialog(frame);    
				    if(i==JFileChooser.APPROVE_OPTION){    
				        File f=fc.getSelectedFile();    
				        filepath=f.getPath(); 
				        Document doc = new Document(filepath, null, textArea, 1, null, null, 0, 0, 0);
						CommandFactory newObject = new CommandFactory();
						
						ActionListener currentCommand = newObject.createCommand(type, doc, null);
						currentCommand.actionPerformed(arg0);
				    }    
				}    
				
		});
		
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actions.add(mntmNewMenuItem);
				map.put(mntmNewMenuItem, arg0);
				String option = "cut";
				EditDocument listener = new EditDocument(Text2SpeechEditorView.this, option);
				listener.actionPerformed(arg0);
			}
		});
		
		mntmCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actions.add(mntmCopy);
				map.put(mntmCopy, arg0);
				String option = "copy";
				EditDocument listener = new EditDocument(Text2SpeechEditorView.this, option);
				listener.actionPerformed(arg0);
			}
		});
		
		mntmPaste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actions.add(mntmPaste);
				map.put(mntmPaste, arg0);
				String option = "paste";
				EditDocument listener = new EditDocument(Text2SpeechEditorView.this, option);
				listener.actionPerformed(arg0);
			}
		});
		
		mntmSelectall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actions.add(mntmSelectall);
				map.put(mntmSelectall, arg0);
				String option = "selectAll";
				EditDocument listener = new EditDocument(Text2SpeechEditorView.this, option);
				listener.actionPerformed(arg0);
			}
		});
		
		
		mntmTransform.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actions.add(mntmTransform);
				map.put(mntmTransform, arg0);
				int selection = JOptionPane.showConfirmDialog(
                        frame, getOptionPanel4(), "Encoding Options : ", JOptionPane.OK_CANCEL_OPTION
                                                , JOptionPane.PLAIN_MESSAGE);
                if (selection == JOptionPane.OK_OPTION)                             
                {
                    System.out.println("OK Selected");
                    int volumeNumber = Integer.parseInt(volumeField.getText().trim());
                    int rateNumber = Integer.parseInt(rateField.getText().trim());
                    int pitchNumber = Integer.parseInt(pitchField.getText().trim());
					String option = (String) cbox.getSelectedItem();
					
					if(option==optionNames3[0]) {
						System.out.println("Normal Transform");
						String contents = textArea.getText();
						String type = "free";
						TextToSpeechAPIFactory speech = new TextToSpeechAPIFactory(type);
						TextToSpeechAPI audioManager = speech.createTTSAPI();
						
						
						Document doc = new Document(contents, null, null, 1, null, audioManager, volumeNumber, rateNumber, pitchNumber);
						doc.playContents();
					}
					else if(option==optionNames3[1]) {
						System.out.println("Reversed Transform");
						String contents = textArea.getText();
						String type = "free";
						TextToSpeechAPIFactory speech = new TextToSpeechAPIFactory(type);
						TextToSpeechAPI audioManager = speech.createTTSAPI();
						
						
						Document doc = new Document(contents, null, null, 1, null, audioManager, volumeNumber, rateNumber, pitchNumber);
						doc.playReverseContents();
					}
                }	
			}
		});
		
		mntmEncode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actions.add(mntmEncode);
				map.put(mntmEncode, arg0);
				int selection = JOptionPane.showConfirmDialog(
                        frame, getOptionPanel2(), "Encoding Options : ", JOptionPane.OK_CANCEL_OPTION
                                                , JOptionPane.PLAIN_MESSAGE);
                if (selection == JOptionPane.OK_OPTION)                             
                {
                    System.out.println("OK Selected");
                    int volumeNumber = Integer.parseInt(volumeField.getText().trim());
                    int rateNumber = Integer.parseInt(rateField.getText().trim());
                    int pitchNumber = Integer.parseInt(pitchField.getText().trim());
					String option = (String) cbox.getSelectedItem();
					
					
					if (option == optionNames2 [0])
					{
					    System.out.println("Rot13 Encode");
					    String contents = textArea.getText();
						String encryText = "";
						StrategiesFactory encode = new StrategiesFactory();
						Rot13Encoding rot13 = encode.createStrategy(contents, encryText);
						Document doc = new Document(contents, null, textArea, 1, rot13, null, volumeNumber, rateNumber, pitchNumber);
						doc.playEncodedContents();
						
					    
					}
					else if (option == optionNames2[1])
					{
					    System.out.println("Atbash Encode");
					    String contents = textArea.getText();
						String encryText = "";
						StrategiesFactory encode = new StrategiesFactory();
						AtBashEncoding atbash = encode.createStrategy1(contents, encryText);
						Document doc = new Document(contents, null, textArea, 1, atbash, null, volumeNumber, rateNumber, pitchNumber);
						doc.playEncodedContents();
					   
					}
					
                }
                else if (selection == JOptionPane.CANCEL_OPTION)
                {
                    System.out.println("CANCEL Selected");
                }
                else if (selection == JOptionPane.CLOSED_OPTION)
                {
                    System.out.println("JOptionPane closed deliberately.");
                }
            }
			
		});
		
		btnLineToSpeech.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String type = "LineToSpeech";
				int selection = JOptionPane.showConfirmDialog(
                        frame, getOptionPanel(), "Transforming Options : ", JOptionPane.OK_CANCEL_OPTION
                                                , JOptionPane.PLAIN_MESSAGE);
                if (selection == JOptionPane.OK_OPTION)                             
                {
                    System.out.println("OK Selected");
                    int lineNumber = Integer.parseInt(lineField.getText().trim());
                    int volumeNumber = Integer.parseInt(volumeField.getText().trim());
                    int rateNumber = Integer.parseInt(rateField.getText().trim());
                    int pitchNumber = Integer.parseInt(pitchField.getText().trim());
                    try
                    {
                        int startIndex = textArea.getLineStartOffset(lineNumber);
                        int endIndex = textArea.getLineEndOffset(lineNumber);
                        String line = textArea.getText(startIndex, endIndex - startIndex);
                        String option = (String) cbox.getSelectedItem();

                        if (option == optionNames[0])
                        {
                            System.out.println("Normal Transform");
                            String typeS = "free";
            				TextToSpeechAPIFactory speech = new TextToSpeechAPIFactory(typeS);//type);
            				TextToSpeechAPI audioManager = speech.createTTSAPI();//contents);
                            Document doc = new Document(line,  null, null, lineNumber,  null, audioManager, volumeNumber, rateNumber, pitchNumber);
            				CommandFactory newObject = new CommandFactory();
            				ActionListener currentCommand = newObject.createCommand(type, doc, option);
            				currentCommand.actionPerformed(arg0);
                        }
                        else if (option == optionNames[1])
                        {
                            System.out.println("Reversed Transform");
                            String typeS = "free";
            				TextToSpeechAPIFactory speech = new TextToSpeechAPIFactory(typeS);//type);
            				TextToSpeechAPI audioManager = speech.createTTSAPI();//contents);
                            Document doc = new Document(line,  null, null, lineNumber,  null, audioManager, volumeNumber, rateNumber, pitchNumber);
            				CommandFactory newObject = new CommandFactory();
            				ActionListener currentCommand = newObject.createCommand(type, doc, option);
            				currentCommand.actionPerformed(arg0);
                        }
                        else if (option == optionNames[2])
                        {
                            System.out.println("Encoded transform");
                            int selection1 = JOptionPane.showConfirmDialog(
                                    frame, getOptionPanel3(), "Encoding Options : ", JOptionPane.OK_CANCEL_OPTION
                                                            , JOptionPane.PLAIN_MESSAGE);
                            if (selection1 == JOptionPane.OK_OPTION)                             
                            {
                                System.out.println("OK Selected");
            					String select = (String) cbox.getSelectedItem();
            					
            					
            					if (select == optionNames2 [0])
            					{
            					    System.out.println("Rot13 Encode");
            						String encryText = "";
            						StrategiesFactory encode = new StrategiesFactory();
            						Rot13Encoding rot13 = encode.createStrategy(line, encryText);
            						Document doc = new Document(line, null, textArea, 1, rot13, null, volumeNumber, rateNumber, pitchNumber);
            						doc.playEncodedLine(lineNumber);
            						
            					}
            					else if (select == optionNames2[1])
            					{
            					    System.out.println("Atbash Encode");
            						String encryText = "";
            						StrategiesFactory encode = new StrategiesFactory();
            						AtBashEncoding atbash = encode.createStrategy1(line, encryText);
            						Document doc = new Document(line, null, textArea, 1, atbash, null, volumeNumber, rateNumber, pitchNumber);
            						doc.playEncodedLine(lineNumber);
                            }
                            else if (selection == JOptionPane.CANCEL_OPTION)
                            {
                                System.out.println("CANCEL Selected");
                            }
                            else if (selection == JOptionPane.CLOSED_OPTION)
                            {
                                System.out.println("JOptionPane closed deliberately.");
                            }
                        }
                    }
                }
                    catch(BadLocationException ble)
                    {
                        ble.printStackTrace();
                    }
                }
                else if (selection == JOptionPane.CANCEL_OPTION)
                {
                    System.out.println("CANCEL Selected");
                }
                else if (selection == JOptionPane.CLOSED_OPTION)
                {
                    System.out.println("JOptionPane closed deliberately.");
                }
            }
			
		});
		

		
	}

	/**
	 * 
	 */
	private void extracted() {
		New_Doc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String type = "NewDocument";
				actions.add(New_Doc);
				map.put(New_Doc, arg0);
				String title=textField.getText();  
		        String author=textField_1.getText();
		        Date date = Calendar.getInstance().getTime();  
		        DateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");  
		        String strDate = dateFormat.format(date);  
		        if(title.trim().isEmpty()){
					title = null;
				}
		        if(author.trim().isEmpty()){
					author = null;
				}
		        Document doc = new Document(title, author, textArea, 1, null, null, 0, 0, 0);
				CommandFactory newObject = new CommandFactory();
				ActionListener currentCommand = newObject.createCommand(type, doc, strDate);
				currentCommand.actionPerformed(arg0);

			}
		});
	}

	private JPanel getOptionPanel(){
	    JPanel panel = new JPanel();
	    panel.setLayout(new GridLayout(0, 2, 5, 5));
	
	    JLabel lineNumberLabel = new JLabel("Enter Line Number : ");
	    lineField = new JTextField(10);
	    
	    JLabel volumeLabel = new JLabel("Enter The Volume : ");
	    volumeField = new JTextField(10);
	    
	    JLabel rateLabel = new JLabel("Enter The Speech Rate : ");
	    rateField = new JTextField(10);
	    
	    JLabel pitchLabel = new JLabel("Enter The Pitch : ");
	    pitchField = new JTextField(10);
	
	    JLabel optionLabel = new JLabel("Select One Option : ");
	    cbox = new JComboBox<Object>(optionNames);
	
	    panel.add(lineNumberLabel);
	    panel.add(lineField);
	    panel.add(volumeLabel);
	    panel.add(volumeField);
	    panel.add(rateLabel);
	    panel.add(rateField);
	    panel.add(pitchLabel);
	    panel.add(pitchField);
	    panel.add(optionLabel);
	    panel.add(cbox);

	    return panel;
	}
	
	private JPanel getOptionPanel2(){
	    JPanel panel = new JPanel();
	    panel.setLayout(new GridLayout(0, 2, 5, 5));
	
	    JLabel volumeLabel = new JLabel("Enter The Volume : ");
	    volumeField = new JTextField(10);
	    
	    JLabel rateLabel = new JLabel("Enter The Speech Rate : ");
	    rateField = new JTextField(10);
	    
	    JLabel pitchLabel = new JLabel("Enter The Pitch : ");
	    pitchField = new JTextField(10);
	
	    JLabel optionLabel = new JLabel("Select One Option : ");
	    cbox = new JComboBox<Object>(optionNames2);
	
	    panel.add(volumeLabel);
	    panel.add(volumeField);
	    panel.add(rateLabel);
	    panel.add(rateField);
	    panel.add(pitchLabel);
	    panel.add(pitchField);
	    panel.add(optionLabel);
	    panel.add(cbox);

	    return panel;
	}
	
	private JPanel getOptionPanel3(){
		JPanel panel = new JPanel();
	    panel.setLayout(new GridLayout(0, 2, 5, 5));
	
	
	    JLabel optionLabel = new JLabel("Select One Option : ");
	    cbox = new JComboBox<Object>(optionNames2);
	
	   
	    panel.add(optionLabel);
	    panel.add(cbox);

	    return panel;
	}
	
	private JPanel getOptionPanel4(){
	    JPanel panel = new JPanel();
	    panel.setLayout(new GridLayout(0, 2, 5, 5));
	
	    JLabel volumeLabel = new JLabel("Enter The Volume : ");
	    volumeField = new JTextField(10);
	    
	    JLabel rateLabel = new JLabel("Enter The Speech Rate : ");
	    rateField = new JTextField(10);
	    
	    JLabel pitchLabel = new JLabel("Enter The Pitch : ");
	    pitchField = new JTextField(10);
	
	    JLabel optionLabel = new JLabel("Select One Option : ");
	    cbox = new JComboBox<Object>(optionNames3);
	
	    panel.add(volumeLabel);
	    panel.add(volumeField);
	    panel.add(rateLabel);
	    panel.add(rateField);
	    panel.add(pitchLabel);
	    panel.add(pitchField);
	    panel.add(optionLabel);
	    panel.add(cbox);

	    return panel;
	}
	
	//just for testing
	public void editText(String text) {
		this.refToArea.setText(text);
	}
		
	public String getText() {
		return this.refToArea.getText();
	}
	
	public JTextArea getJArea() {
		return textArea;
	}
	
	public void giveFilename(String text) {
		textField_2.setText(text);
	}
	 
	public String getFieldText() {
		return textField_2.getText();
	}
	
	
	public String getfilepath() {
		return filepath;
	}
	
	public JTextArea getTextArea() {
        return textArea;
    }
	
	public String getLine(int lineNumber) throws BadLocationException {
		int startIndex = textArea.getLineStartOffset(lineNumber);
        int endIndex = textArea.getLineEndOffset(lineNumber);
        String line = textArea.getText(startIndex, endIndex - startIndex);
        return line;      
		
	}
	
}
