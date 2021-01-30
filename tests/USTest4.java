package tests;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JTextArea;

import org.junit.Test;

import commands.OpenDocument;
import model.Document;
import view.Text2SpeechEditorView;

public class USTest4 {
	
	ActionEvent E = null;
	Text2SpeechEditorView gui = new Text2SpeechEditorView();
	String filepath = "";
	JTextArea textArea = null;
	
	@Test
	public void test() throws IOException {
		filepath = "/home/s-tzo/eclipse-workspace/softwareEngeenering/newFile";
		textArea = gui.getTextArea();
		Document doc = new Document(filepath, null, textArea, 1, null, null, 0, 0, 0);
		OpenDocument open_doc = new OpenDocument(doc);
		open_doc.actionPerformed(E);
		@SuppressWarnings("resource")
		String contentsOfFile = new Scanner(new File("newFile")).useDelimiter("\\Z").next();
		String afterOpenFile = gui.getText();
		
		
		assertEquals(contentsOfFile, afterOpenFile);
	}

}
