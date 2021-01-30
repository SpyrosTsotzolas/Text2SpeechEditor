package text2speechapis;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class FreeTTSAdapter implements TextToSpeechAPI{
	
	
	public void play(String text, int volume, int rate, int pitch) {
		
		try {
			System.out.println("mhke");
		    System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
		
		    String voiceName = "kevin16";
		    VoiceManager voiceManager = VoiceManager.getInstance();                 
		    Voice objVoice = voiceManager.getVoice(voiceName);
		    if (objVoice == null) {
		      System.err.println("Cannot find a voice named " + voiceName + ".  Please specify a different voice.");
		     
		    }
		    objVoice.allocate();
		    objVoice.setRate(rate);// Setting the rate of the voice
		    objVoice.setPitch(pitch);// Setting the Pitch of the voice
		    objVoice.setVolume(volume);// Setting the volume of the voice
	
		   
		    objVoice.speak(text);
		    objVoice.deallocate();
		  
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
	}
	
}
