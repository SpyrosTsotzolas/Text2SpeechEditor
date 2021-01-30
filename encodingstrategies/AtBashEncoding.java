package encodingstrategies;

import java.util.HashMap;


import java.util.Map;

public class AtBashEncoding extends TemplateEncoding{

	public AtBashEncoding(String text, String encryText) {
		super(text, encryText); 
	}
	
	public String encode(String text) {
		
		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			
			if(c == (char)32 || c == (char)10) {
				encryText = AtBashEncoding.encode(c);
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
		
		Map<String, String> map = new HashMap<>();
		map.put("A", "Z");
		map.put("B", "Y");
		map.put("C", "X");
		map.put("D", "W");
		map.put("E", "V");
		map.put("F", "U");
		map.put("G", "T");
		map.put("H", "S");
		map.put("I", "R");
		map.put("J", "Q");
		map.put("K", "P");
		map.put("L", "O");
		map.put("M", "N");
		map.put("N", "M");
		map.put("O", "L");
		map.put("P", "K");
		map.put("Q", "J");
		map.put("R", "I");
		map.put("S", "H");
		map.put("T", "G");
		map.put("U", "F");
		map.put("V", "E");
		map.put("W", "D");
		map.put("X", "C");
		map.put("Y", "B");
		map.put("Z", "A");
		map.put("a", "z");
		map.put("b", "y");
		map.put("c", "x");
		map.put("d", "w");
		map.put("e", "v");
		map.put("f", "u");
		map.put("g", "t");
		map.put("h", "s");
		map.put("i", "r");
		map.put("j", "q");
		map.put("k", "p");
		map.put("l", "o");
		map.put("m", "n");
		map.put("n", "m");
		map.put("o", "l");
		map.put("p", "k");
		map.put("q", "j");
		map.put("r", "i");
		map.put("s", "h");
		map.put("t", "g");
		map.put("u", "f");
		map.put("v", "e");
		map.put("w", "d");
		map.put("x", "c");
		map.put("y", "b");
		map.put("z", "a");
		String ns = Character.toString(c);
		char rc = map.get(ns).charAt(0);
		return rc;
		
	}
	
}
