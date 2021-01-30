package encodingstrategies;

public class Rot13Encoding extends TemplateEncoding{
	
	public Rot13Encoding(String text, String encryText) {
		super(text, encryText);
	}
	
	
	public String encode(String text) {
		
        for (int i = 0; i < text.length(); i++) {
        	
            char c = text.charAt(i);
            if(c == (char)32 || c == (char)10) {
            	encryText = Rot13Encoding.encode(c);
            }
            else {
            	char nc = mapCharacter(c);
                encryText += nc;
            }
            
        }
        System.out.println(encryText);
        return encryText;
    
	}
	public char mapCharacter(char c) {
		
		 if       (c >= 'a' && c <= 'm') c += 13;
         else if  (c >= 'A' && c <= 'M') c += 13;
         else if  (c >= 'n' && c <= 'z') c -= 13;
         else if  (c >= 'N' && c <= 'Z') c -= 13;
		 return c;
		
	}

}
