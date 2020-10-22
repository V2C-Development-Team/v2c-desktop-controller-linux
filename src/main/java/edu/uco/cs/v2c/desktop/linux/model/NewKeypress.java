package edu.uco.cs.v2c.desktop.linux.model;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public enum NewKeypress {
	
	//list all neeeded keys
	//define ie VK_3 -> thre, three 3
	VK_0(KeyEvent.VK_0, "zero", "0"),
	VK_1(KeyEvent.VK_1, "one", "won",  "1"),
	VK_2(KeyEvent.VK_2, "two", "to", "too", "2"),
	VK_3(KeyEvent.VK_3, "three", "tree", "3"),
    VK_4(KeyEvent.VK_4, "four", "fore", "4"),
    VK_5(KeyEvent.VK_5, "five", "fife", "5"),
    VK_6(KeyEvent.VK_6, "six", "three", "3"),
    VK_7(KeyEvent.VK_7, "seven", "7" ),
    VK_8(KeyEvent.VK_8, "eight", "ate", "8"),
    VK_9(KeyEvent.VK_9, "nine", "niner", "9"),
    VK_A(KeyEvent.VK_A, "A", "alpha"),
    VK_B(KeyEvent.VK_B, "B", "bravo"),
    VK_C(),
    VK_D(),
    VK_E(),
    VK_F(),
    VK_G(),
    VK_H(),
    VK_I(),
    VK_J(),
    VK_K(),
    VK_L(),
    VK_M(),
    VK_N(),
    VK_O(),
    VK_P(),
    VK_Q(),
    VK_R(),
    VK_S(),
    VK_T(),
    VK_U(),
    VK_V(),
    VK_W(),
    VK_X(),
    VK_Y(),
    VK_Z(),
    VK_AMPERSAND(),
    VK_ASTERISK(),
    VK_AT(),
    VK_BACK_QUOTE(),
    VK_BACK_SPACE(),
    VK_BRACELEFT(),
    VK_BRACERIGHT(),
    VK_CIRCUMFLEX(),
    VK_CLOSE_BRACKET(),
    VK_COLON(),
    VK_COMMA(),
    VK_DELETE(),
    VK_DOLLAR(),
    VK_DOWN(),
    VK_UP(),
    VK_LEFT(),
    VK_RIGHT(),
    VK_ENTER(),
    VK_EQUALS(),
    VK_EURO_SIGN(),
    VK_EXCLAMATION_MARK(),
    VK_GREATER(),
    VK_LESS(),
    VK_INSERT(),
    VK_LEFT_PARENTHESIS(),
    VK_RIGHT_PARENTHESIS(),
    VK_PLUS(),
    VK_MINUS(),
    VK_MULTIPLY(),
    VK_NUMBER_SIGN(),
    VK_OPENBRACKET(),
    VK_PERIOD(),
    VK_QUOTE(),
    VK_QUOTEDBL(),
    VK_SEMICOLON(),
    VK_SHIFT(),
    VK_SLASH(),
    VK_SPACE(),
    VK_TAB(),
    VK_UNDERSCORE();
    
    
    
	
	private static Map<String, NewKeypress> directiveMap = null;

	private int keyEvent;
    private String[] directives;

	private NewKeypress(int event, String... directives) {
		this.keyEvent = event;
        this.directives = directives;
	}

    public int getKeyEvent() {
        return keyEvent;
    }

    public static NewKeypress getKeypress(String directive) {
        if(directiveMap == null) {
            directiveMap = new HashMap<>();
            for(NewKeypress keypress : values())
                for(String d : keypress.directives)
                    directiveMap.put(d, keypress);
        }

        // NewKeyPress.getKeypress("three").getKeyEvent();

        return directiveMap.get(directive);
    }
	//some way to send phoenetic and return a keypress
}
