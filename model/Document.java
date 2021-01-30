package model;

import text2speechapis.TextToSpeechAPI;
import text2speechapis.TextToSpeechAPIFactory;
import encodingstrategies.EncodingStrategy;
import javax.swing.JTextArea;



public class Document {
	public String field1;
	public String field2;
	public JTextArea field3;
	public int lineNum;
	private EncodingStrategy encodingStrategy;
	private TextToSpeechAPI audioManager;
	private int volume;
	private int rate;
	private int pitch;
	
	//just for testing
	private  String text2Speech = "";
	
	
	
	public Document(String field1, String field2, JTextArea field3, int lineNum, EncodingStrategy encodingStrategy,
			TextToSpeechAPI audioManager, int volume, int rate, int pitch) {
		super();
		this.field1 = field1;
		this.field2 = field2;
		this.field3 = field3;
		this.lineNum = lineNum;
		this.encodingStrategy = encodingStrategy;
		this.audioManager =  audioManager;
		this.volume = volume;
		this.rate = rate;
		this.pitch = pitch;
	}

	public void playContents() {
		
		audioManager.play(field1, volume, rate, pitch);
		text2Speech = field1;
		
	}
	
	public void playReverseContents() {
		String reverse = "";
		String s[] = field1.split(" ");
		
		for (int i = s.length - 1; i >= 0; i--) { 
            reverse += s[i] + " "; 
        } 
		audioManager.play(reverse, volume, rate, pitch);
		text2Speech = reverse;
		
	}
	
	public void playEncodedContents() {
		String encryText = "";
		encryText = encodingStrategy.encode(field1);
		field3.setText(encryText); 
		
		String type = "free";
		TextToSpeechAPIFactory speech = new TextToSpeechAPIFactory(type);//type);
		TextToSpeechAPI audioManager = speech.createTTSAPI();//contents);
		
		
		
		Document doc = new Document(encryText, null, null, 1, null, audioManager, volume, rate, pitch);
		doc.playContents();
		
		
		
	}
	
	public void playLine(int lineNum){
		
       
        audioManager.play(field1, volume, rate, pitch);
        text2Speech = field1;
        
		
	}
	
	public void playReverseLine(int lineNum) {
		String reverse = "";
		String s[] = field1.split(" ");
		
		for (int i = s.length - 1; i >= 0; i--) { 
            reverse += s[i] + " "; 
        } 
		audioManager.play(reverse, volume, rate, pitch);
		text2Speech = reverse;
		
	}
	
	public void playEncodedLine(int lineNum) {
		String encryText = "";
		encryText = encodingStrategy.encode(field1);
		field3.setText(encryText); 
		
		String type = "free";
		TextToSpeechAPIFactory speech = new TextToSpeechAPIFactory(type);//type);
		TextToSpeechAPI audioManager = speech.createTTSAPI();//contents);
		
		
		
		Document doc = new Document(encryText, null, null, 1, null, audioManager, volume, rate, pitch);
		doc.playContents();
		
	}
	
	public void tuneEncodingStrategy(EncodingStrategy strat) {
		
		
		
	}
	
	//just for testing
	public String getText2Speech() {
		return text2Speech;
	}
	
	
	public void playEncodedContentsTest() {
		String encryText = "";
		encryText = encodingStrategy.encode(field1);
		field3.setText(encryText); 
		
		String type = "fake";
		TextToSpeechAPIFactory speech = new TextToSpeechAPIFactory(type);//type);
		TextToSpeechAPI audioManager = speech.createTTSAPI();//contents);
		
		
		
		Document doc = new Document(encryText, null, null, 1, null, audioManager, volume, rate, pitch);
		doc.playContents();
	}
	
	public void playEncodedLineTest(int lineNum) {
		String encryText = "";
		encryText = encodingStrategy.encode(field1);
		field3.setText(encryText); 
		
		String type = "fake";
		TextToSpeechAPIFactory speech = new TextToSpeechAPIFactory(type);//type);
		TextToSpeechAPI audioManager = speech.createTTSAPI();//contents);
		
		
		
		Document doc = new Document(encryText, null, null, 1, null, audioManager, volume, rate, pitch);
		doc.playContents();
		
	}
	
}