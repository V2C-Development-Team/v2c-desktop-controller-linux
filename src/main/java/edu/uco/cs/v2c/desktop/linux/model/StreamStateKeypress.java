/*
 * Copyright (c) 2020 Caleb L. Power, Everistus Akpabio, Rashed Alrashed,
 * Nicholas Clemmons, Jonathan Craig, James Cole Riggall, and Glen Mathew.
 * All rights reserved. Licensed under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.uco.cs.v2c.desktop.linux.model;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public enum StreamStateKeypress {
	
	// list all neeeded keys
	// define ie VK_3 -> thre, three 3
	// dont use a directive ie zero 0, ieie the name you call it in more than one key
    VK_0(KeyEvent.VK_0, "zero", "0"),
    // VK_1(KeyEvent.VK_1, "one", "won",  "1"),
    VK_1(KeyEvent.VK_1, "one", "1"),
    // VK_2(KeyEvent.VK_2, "two", "to", "too", "2"),
    VK_2(KeyEvent.VK_2, "two", "too", "2"),
    // VK_3(KeyEvent.VK_3, "three", "tree", "3"),
    VK_3(KeyEvent.VK_3, "three", "3"),
    // VK_4(KeyEvent.VK_4, "four", "fore", "4"),
    VK_4(KeyEvent.VK_4, "four", "4"),
    // VK_5(KeyEvent.VK_5, "five", "fife", "5"),
    VK_5(KeyEvent.VK_5, "five", "5"),
    VK_6(KeyEvent.VK_6, "six", "6"),
    VK_7(KeyEvent.VK_7, "seven", "7" ),
    // VK_8(KeyEvent.VK_8, "eight", "ate", "8"),
    VK_8(KeyEvent.VK_8, "eight", "8"),
    VK_9(KeyEvent.VK_9, "nine", "niner", "9"),
    VK_A(KeyEvent.VK_A, "A", "a", "alpha"),
    VK_B(KeyEvent.VK_B, "B", "b", "bravo"),
    VK_C(KeyEvent.VK_C, "C", "c", "charlie"),
    VK_D(KeyEvent.VK_D, "D", "d", "delta"),
    VK_E(KeyEvent.VK_E, "E", "e", "echo"),
    VK_F(KeyEvent.VK_F, "F", "f", "foxtrot"),
    VK_G(KeyEvent.VK_G, "G", "g", "golf"),
    VK_H(KeyEvent.VK_H, "H", "h", "hotel"),
    VK_I(KeyEvent.VK_I, "I", "i", "india"),
    VK_J(KeyEvent.VK_J, "J", "j", "juliet"),
    VK_K(KeyEvent.VK_K, "K", "k", "kilo"),
    VK_L(KeyEvent.VK_L, "L", "l", "lima"),
    VK_M(KeyEvent.VK_M, "M", "m", "mike"),
    VK_N(KeyEvent.VK_N, "N", "n", "november"),
    VK_O(KeyEvent.VK_O, "O", "o", "oscar"),
    VK_P(KeyEvent.VK_P, "P", "p", "papa"),
    VK_Q(KeyEvent.VK_Q, "Q", "q", "quebec"),
    VK_R(KeyEvent.VK_R, "R", "r", "romeo"),
    VK_S(KeyEvent.VK_S, "S", "s", "sierra"),
    VK_T(KeyEvent.VK_T, "T", "t", "tango"),
    VK_U(KeyEvent.VK_U, "U", "u", "uniform"),
    VK_V(KeyEvent.VK_V, "V", "v", "victor"),
    VK_W(KeyEvent.VK_W, "W", "w", "whiskey"),
    VK_X(KeyEvent.VK_X, "X", "x", "x-ray", "xray", "xavier"), //in case it cant read xray as one token
    VK_Y(KeyEvent.VK_Y, "Y", "y", "yankee"),
    VK_Z(KeyEvent.VK_Z, "Z", "z", "zulu"),
    VK_AMPERSAND(KeyEvent.VK_AMPERSAND, "&", "ampersand", "and"),
    VK_ASTERISK(KeyEvent.VK_ASTERISK, "*", "asterisk", "star"),
    VK_AT(KeyEvent.VK_AT, "at", "@"),
    VK_BACK_SPACE(KeyEvent.VK_BACK_SPACE, "backspace"), 
    VK_CIRCUMFLEX(KeyEvent.VK_CIRCUMFLEX, "^", "circumflex", "carrot"),
    VK_OPENBRACKET(KeyEvent.VK_OPEN_BRACKET, "bra", "bracket"), //#TODO come up with one word directives
    VK_CLOSE_BRACKET(KeyEvent.VK_CLOSE_BRACKET, "kit", "close"), //#TODO come up with one word directive
    VK_COMMA(KeyEvent.VK_COMMA, ",", "comma", "dit"),
    VK_DELETE(KeyEvent.VK_DELETE, "delete", "dell"),
    VK_DOWN(KeyEvent.VK_DOWN, "down"),
    VK_UP(KeyEvent.VK_UP, "up"),
    VK_LEFT(KeyEvent.VK_LEFT, "left"),
    VK_RIGHT(KeyEvent.VK_RIGHT, "right"),
    VK_ENTER(KeyEvent.VK_ENTER, "enter", "newline"),
    VK_EQUALS(KeyEvent.VK_EQUALS, "=", "equal", "equals", "equality" ),
    VK_INSERT(KeyEvent.VK_INSERT, "insert"),
    VK_MULTIPLY(KeyEvent.VK_MULTIPLY, "multiply", "tyme", "thyme", "times"),
    VK_NUMBER_SIGN(KeyEvent.VK_NUMBER_SIGN, "#", "number", "pound", "hashtag", "sharp"),
    VK_PERIOD(KeyEvent.VK_PERIOD,".", "dot", "period"),
    VK_QUOTE(KeyEvent.VK_QUOTE, "\'", "quote", "cite"),
    VK_SEMICOLON(KeyEvent.VK_SEMICOLON, ";", "semicolon", "semi"),
    VK_SHIFT(KeyEvent.VK_SHIFT, "shift", "shifty"),
    VK_SLASH(KeyEvent.VK_SLASH,"/", "slash"),
    VK_SPACE(KeyEvent.VK_SPACE, "space", "slap"),
    VK_TAB(KeyEvent.VK_TAB, "tab", "tap"),
    VK_UNDERSCORE(KeyEvent.VK_UNDERSCORE, "_", "underscore", "under score");
    
    // nonfunctional keyevents, at least on windows/qwerty 
    // to type these keys please use a shift then the corresponding key
    
    // VK_BACK_QUOTE(), //#TODO come up with one word directive
    // VK_BRACELEFT(), //#TODO come up with one word directive
    // VK_BRACERIGHT(), //#TODO come up with one word directive
    // VK_COLON(KeyEvent.VK_COLON, ":", "colon", "full"),//#TODO nonfunc
    // VK_DOLLAR(KeyEvent.VK_DOLLAR, "$", "dollar", "cash"),//#TODO nonfunc
    // VK_EXCLAMATION_MARK(KeyEvent.VK_EXCLAMATION_MARK, "!", "exclamation", "bang"), //#TODO nonfunc
    // VK_GREATER(KeyEvent.VK_GREATER, ">", "greater", "great", "grater", "grate"),//#TODO nonfunc
    // VK_EURO_SIGN(KeyEvent.VK_EURO_SIGN, "euro"),//#TODO nonfunc
    // VK_LESS(KeyEvent.VK_LESS, "<", "lesser", "less"),//#TODO nonfunc
    // VK_LEFT_PARENTHESIS(), //#TODO come up with one word directive
    // VK_RIGHT_PARENTHESIS(),//#TODO come up with one word directive
    // VK_PLUS(KeyEvent.VK_PLUS, "+", "plus", "add"),//#TODO nonfunc
    // VK_MINUS(KeyEvent.VK_MINUS, "-", "minus", "subtract", "sub"),//#TODO nonfunc
    // VK_QUOTEDBL(KeyEvent.VK_QUOTEDBL, "\"", "rabbit", "double"),//#TODO nonfunc
	
	 private static Map<String, StreamStateKeypress> directiveMap = null;

    private static Map<Integer, String> keypressToDirective = new HashMap<>();
    private static Map<String, StreamStateKeypress> directiveToKeypressInteger = null;
     private static Map<String, StreamStateKeypress> directiveToKeypressString = null;

	private int keyEvent;
    private String[] directives;

	private StreamStateKeypress(int event, String... directives) {
		this.keyEvent = event;
        this.directives = directives;
	}

     public int getKeyEvent() {
         return keyEvent;
     }

     public static StreamStateKeypress getKeypress(String directive) {
         if(directiveMap == null) {
            directiveMap = new HashMap<>();
             for(StreamStateKeypress keypress : values())
                for(String d : keypress.directives)
                     directiveMap.put(d, keypress);
       }

        // NewKeyPress.getKeypress("three").getKeyEvent();

         return directiveMap.get(directive);
     }
    // //some way to send phoenetic and return a keypress
    
    public StreamStateKeypress directiveToInt(String directive) {
        return directiveToKeypressInteger.get(directive);
    }
    
    public void updateKeypresses() {
        for(StreamStateKeypress keypress : values())
            for(String d : keypress.directives)
                directiveToKeypressInteger.put(d, keypress);
    }
}
