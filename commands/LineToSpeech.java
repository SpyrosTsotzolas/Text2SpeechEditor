package commands;

import java.awt.event.ActionEvent;


import model.Document;

public class LineToSpeech extends ActionListener implements java.awt.event.ActionListener{
	
	private String option;
	private Document doc;
	
	
	
	public LineToSpeech(Document doc, String option) {
		super();
		this.doc = doc;
		this.option = option;
	}
	
	
	public void actionPerformed(ActionEvent E){
		
		if(option=="NORMAL") {
			doc.playLine(doc.lineNum);
		}
		else if(option=="REVERSED") {
			doc.playReverseLine(doc.lineNum);
		}
		else if(option=="ENCODED") {
		    doc.playEncodedLine(doc.lineNum);
			
		}

	}
}
