package commands;

import java.awt.event.ActionEvent;

import java.io.BufferedWriter;
import java.io.FileWriter;

import model.Document;

public class SaveDocument extends ActionListener implements java.awt.event.ActionListener {
	
	private Document saveDoc;
	private String date;
	
	public SaveDocument(Document saveDoc, String date) {
		super();
		this.saveDoc = saveDoc;
		this.date = date;
	}
	
	public void actionPerformed(ActionEvent E){
		if(saveDoc.field1==null)
			System.out.println("Please give the filename before save the contents");
		else {
			try {
		          if(saveDoc.field2 != null  && !saveDoc.field2.isEmpty()){
		             String[] lines = saveDoc.field2.split("\n");
		             BufferedWriter fileOut = new BufferedWriter(new FileWriter(saveDoc.field1));
		             fileOut.write("Save Date: " + date + "\n");
		             for ( String line : lines )
		             {

		                fileOut.write( line, 0, line.length() );
		                fileOut.newLine();
		             }

		             fileOut.close();
		          }
		          else {
		        	  System.out.println("The current document is empty.. Please fill something before save it");
		          }


		        } 
			catch (Exception e) {
		          e.printStackTrace();
		    }
			
		}
		
	      
	}
	
}
