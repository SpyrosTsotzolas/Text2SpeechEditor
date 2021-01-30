package tests;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;

import org.junit.Test;

import commands.EditDocument;
import view.Text2SpeechEditorView;

public class USTest2 {
	ActionEvent E = null;
	Text2SpeechEditorView gui = new Text2SpeechEditorView();
	EditDocument updateDoc = new EditDocument(gui, "selectAll");
	EditDocument finalUpdateDoc = new EditDocument(gui, "cut");
	String beforeEdit = "";
	String afterEdit = ""; 
	
	@Test
	public void test() {
		beforeEdit = "Hello Zara";
		gui.editText(beforeEdit);
		System.out.println("Before edit: " + gui.getText());
		updateDoc.actionPerformed(E);
		finalUpdateDoc.actionPerformed(E);
		System.out.println("After edit: " + gui.getText());
		afterEdit = gui.getText();
		assertEquals(afterEdit, beforeEdit);
	}

}
