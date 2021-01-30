package encodingstrategies;

public class StrategiesFactory {
	
	
	public Rot13Encoding createStrategy(String text, String encryText) {
		
			Rot13Encoding rot13;
			rot13 = new Rot13Encoding(text, encryText);
			return rot13;
	}
	
	public AtBashEncoding createStrategy1(String text, String encryText) {

		AtBashEncoding atbash;
		atbash = new AtBashEncoding(text, encryText);
		return atbash;
	}

}
