package tests;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

import org.junit.Test;

import commands.SaveDocument;
import model.Document;
import view.Text2SpeechEditorView;

public class USTest3 {
	ActionEvent E = null;
	java.util.Date date = Calendar.getInstance().getTime();  
    DateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");  
    String strDate = dateFormat.format(date);
    Text2SpeechEditorView gui = new Text2SpeechEditorView();
    String filename = "";
	String contents = "";
    

	@Test
	public void test() throws IOException {
		gui.giveFilename("newFile");
		gui.editText("hello zara");
		filename = gui.getFieldText();
		contents = gui.getText();
		Document doc = new Document(filename, contents, null, 1, null, null, 0, 0, 0);
		SaveDocument save_doc = new SaveDocument(doc,strDate);
		save_doc.actionPerformed(E);
		@SuppressWarnings("resource")
		String contentsOfFile = new Scanner(new File(filename)).useDelimiter("\\Z").next();
		
		assertEquals("Save Date: " + strDate + "\n" + contents, contentsOfFile);
	}

}
