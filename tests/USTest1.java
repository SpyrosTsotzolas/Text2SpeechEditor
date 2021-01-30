package tests;

import static org.junit.Assert.*;



import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Test;

import commands.NewDocument;
import model.Document;
import view.Text2SpeechEditorView;

public class USTest1 {
	
	public  USTest1(){
		
	}
	ActionEvent E = null;
	java.util.Date date = Calendar.getInstance().getTime();  
    DateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");  
    String strDate = dateFormat.format(date);
    Text2SpeechEditorView gui = new Text2SpeechEditorView();
	Document doc = new Document("zaras", "Spuros", gui.getJArea(), 0, null, null, 0, 0, 0);
	NewDocument new_doc = new NewDocument(doc, strDate);
	

	
	@Test
	public void test()  {
		
		new_doc.actionPerformed(E);
		Document d = new_doc.getCurrentDoc();
		String currentText = d.field3.getText();
		String TextInGUI = gui.getText();
		assertEquals(currentText, TextInGUI);
	}

	
}
