package tests;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.text.BadLocationException;

import org.junit.Test;

import commands.CommandFactory;
import encodingstrategies.Rot13Encoding;
import encodingstrategies.StrategiesFactory;
import model.Document;
import text2speechapis.FakeTextToSpeechAPI;
import text2speechapis.TextToSpeechAPI;
import text2speechapis.TextToSpeechAPIFactory;
import view.Text2SpeechEditorView;

public class USTest5_10 {
	Text2SpeechEditorView gui = new Text2SpeechEditorView();
	ActionEvent E = null;
	
	String contents = ""; 
	String line = "";
	
	int volumeNumber = 100;
    int rateNumber = 100;
    int pitchNumber = 100;
	
	String type = "fake";
	String expectedText = "";
	String actualText2Speech = "";
	
	TextToSpeechAPIFactory speech = new TextToSpeechAPIFactory(type);//type);
	TextToSpeechAPI audioManager = speech.createTTSAPI();//contents);
	Document doc = new Document(contents, null, null, 1, null, audioManager, volumeNumber, rateNumber, pitchNumber);
	FakeTextToSpeechAPI fake = new FakeTextToSpeechAPI();
	
	
	
	
	@Test
	public void test1() {
		gui.editText("normal transform");
		contents = gui.getText();
		doc.playContents();
		
		expectedText = doc.getText2Speech();
		actualText2Speech = fake.getText(expectedText);
		
		assertEquals(expectedText, actualText2Speech);
		
	}
	@Test
	public void test2() {
		gui.editText("revesed transform");
		contents = gui.getText();
		doc.playReverseContents();
		expectedText = doc.getText2Speech();
		
		actualText2Speech = fake.getText(expectedText);
		
		assertEquals(expectedText, actualText2Speech);
	}
	@Test
	public void test3() {
		gui.editText("encoded transform");
		contents = gui.getText();
		String encryText = "";
		StrategiesFactory encode = new StrategiesFactory();
		Rot13Encoding rot13 = encode.createStrategy(contents, encryText);
		Document doc = new Document(contents, null, gui.getJArea(), 1, rot13, null, volumeNumber, rateNumber, pitchNumber);
		doc.playEncodedContentsTest();
		
		expectedText = doc.getText2Speech();
		actualText2Speech = fake.getText(expectedText);
		
		assertEquals(expectedText, actualText2Speech);
	}
		
	@Test
	public void test4() throws BadLocationException {
		gui.editText("Line Normal Transform");
		contents = gui.getText();
		line = gui.getLine(0);
        Document doc = new Document(line,  null, null, 0,  null, audioManager, volumeNumber, rateNumber, pitchNumber);
		CommandFactory newObject = new CommandFactory();
		ActionListener currentCommand = newObject.createCommand("LineToSpeech", doc, "NORMAL");
		currentCommand.actionPerformed(E);
		
		expectedText = doc.getText2Speech();
		actualText2Speech = fake.getText(expectedText);
		
		assertEquals(expectedText, actualText2Speech);
		
		
		
	}
	
	@Test
	public void Test5() throws BadLocationException {
		gui.editText("Line Reveerse Transform");
		contents = gui.getText();
		line = gui.getLine(0);
        Document doc = new Document(line,  null, null, 0,  null, audioManager, volumeNumber, rateNumber, pitchNumber);
		CommandFactory newObject = new CommandFactory();
		ActionListener currentCommand = newObject.createCommand("LineToSpeech", doc, "REVERSED");
		currentCommand.actionPerformed(E);
		
		expectedText = doc.getText2Speech();
		actualText2Speech = fake.getText(expectedText);
		
		assertEquals(expectedText, actualText2Speech);
		
	}
	
	@Test
	public void Test6() throws BadLocationException {
		gui.editText("Line Encoded Transform");
		contents = gui.getText();
		line = gui.getLine(0);
		String encryText = "";
		StrategiesFactory encode = new StrategiesFactory();
		Rot13Encoding rot13 = encode.createStrategy(line, encryText);
		Document doc = new Document(line, null, gui.getJArea(), 1, rot13, null, volumeNumber, rateNumber, pitchNumber);
		doc.playEncodedLineTest(0);
		
		expectedText = doc.getText2Speech();
		actualText2Speech = fake.getText(expectedText);
		
		assertEquals(expectedText, actualText2Speech);
	}

}
