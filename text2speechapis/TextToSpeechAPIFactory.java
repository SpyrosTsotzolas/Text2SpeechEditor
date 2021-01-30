package text2speechapis;

public class TextToSpeechAPIFactory {
	private String type;

	public TextToSpeechAPIFactory(String type) {
		super();
		this.type = type;
	}
	
	public TextToSpeechAPI createTTSAPI() {//String contents) {
		
		FreeTTSAdapter speech1;
		FakeTextToSpeechAPI speech2;
		
		if(type=="free") {
			
			speech1 = new FreeTTSAdapter();
			return speech1;
			
		}
		else {
			speech2 = new FakeTextToSpeechAPI();
			return speech2;
		}
		
			
		
		
			
		
		
	}

}
