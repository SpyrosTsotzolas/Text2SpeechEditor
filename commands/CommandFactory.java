package commands;

import java.awt.event.ActionListener;
import model.Document;

public class CommandFactory {
	
		public ActionListener createCommand(String type, Document doc, String option) {
			
			if(type.equals("NewDocument")) {
				return new NewDocument(doc, option);
			}
			else if(type.equals("OpenDocument")) {
				return new OpenDocument(doc);
			}
			else if(type.equals("SaveDocument")) {
				return new SaveDocument(doc, option);
			}
			else if(type.equals("LineToSpeech")) {
				return new LineToSpeech(doc, option);
			}
			else {
				throw new IllegalArgumentException();
			}	
		
		}

}
