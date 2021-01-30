package commands;

import java.io.*;

import model.Document;

import java.awt.event.ActionEvent;

public class OpenDocument extends ActionListener implements java.awt.event.ActionListener{
	
	private Document openDoc;

	
	public OpenDocument(Document openDoc) {
		super();
		this.openDoc = openDoc;
	}
	
	public void actionPerformed(ActionEvent E){
		if(openDoc.field1==null)
			System.out.println("Please enter the filepath before open the file");
		else {
			try{  
		        BufferedReader br=new BufferedReader(new FileReader(openDoc.field1));    
		        String s1="",s2="";                         
		        while((s1=br.readLine())!=null){    
		        s2+=s1+"\n";    
		        } 
		        s2 = s2.trim();
		        openDoc.field3.setText(s2);    
		        br.close();    
		        }catch (Exception ex) {ex.printStackTrace();  }   
			}  
		
	}
}
