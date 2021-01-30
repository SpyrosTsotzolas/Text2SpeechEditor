package encodingstrategies;

public abstract class TemplateEncoding extends EncodingStrategy{
	protected String text;
	protected static String encryText;
	
	
	public TemplateEncoding(String text, String encryText) {
		super();
		this.text = text;
		TemplateEncoding.encryText = encryText;
	}
	
	public static String encode(char c) {
		
		if(c == (char)32) {
			encryText += " ";
		}
		else {
			encryText += "\n";
		}
		return encryText;
		
	}
	public abstract char mapCharacter(char c);

}
