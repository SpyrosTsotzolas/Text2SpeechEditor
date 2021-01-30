package commands;

import view.Text2SpeechEditorView;

import javax.swing.JTextArea;
import java.awt.event.ActionEvent;

public class EditDocument extends ActionListener{
		private String option;
		private Text2SpeechEditorView gui;
	    private JTextArea area;

		public EditDocument(Text2SpeechEditorView text2SpechEditorView, String option) {
			super();
			this.gui = text2SpechEditorView;
			this.area = gui.getTextArea();
			this.option = option;
		}
		
		public void actionPerformed(ActionEvent E){
			if(option=="cut") {
				area.cut();
			}
			else if(option=="copy") {
				area.copy();
			}
			else if(option=="paste") {
				area.paste();
			}
			else if(option=="selectAll") {
				area.selectAll();
			}
		}

}
