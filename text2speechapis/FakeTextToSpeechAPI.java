package text2speechapis;

public class FakeTextToSpeechAPI implements TextToSpeechAPI{ 
	
	public void play(String text, int volume, int rate, int pitch) {
	
	}
	
	public String getText(String text) {
		return text;
	}

}
