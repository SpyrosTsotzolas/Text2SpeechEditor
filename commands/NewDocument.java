package commands;

import java.awt.event.ActionEvent;

import model.Document;

public class NewDocument extends ActionListener implements java.awt.event.ActionListener {
	
	private String date;
	private Document newDoc;
	
	public NewDocument(Document newDoc, String date) {
		super();
		this.newDoc = newDoc;
		this.date = date;
	}
	
	public void actionPerformed(ActionEvent E){
		if(newDoc.field1==null | newDoc.field2==null)
			System.out.println("Please give both title and author to create new document");
		else {	
			String emptyDocument = "Creation date: " + date;
			newDoc.field3.setText(emptyDocument);
		}
		 
	}
	
	// Only for TESTING
	public String getText() {
		return newDoc.field3.getText();
	}
	
	public Document getCurrentDoc() {
		return this.newDoc;
	}

}
