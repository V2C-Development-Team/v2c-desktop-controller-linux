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

import java.util.HashMap;
import java.util.Map;

public enum Numeric {
	
	ZERO(0, "zero", "0"),
	ONE(1, "one", "1", "won" ),
	TWO(2, "two", "2", "too", "to"),
	THREE(3, "three", "tree" ,"3"),
	FOUR(4,"four", "fore", "fower", "4"),
	FIVE(5, "five", "fife", "5"),
    SIX(6,"six", "sex", "6", "sax"),
    SEVEN(7, "seven", "seben", "7"),
    EIGHT(8,"eight", "ate", "8"),
    NINE(9, "nine", "nein", "niner", "9"),
    TEN(10,"ten", "10"),
    ELEVEN(11, "eleven", "11", "elven"),
    TWELVE(12, "twelve", "12"),
    THIRTEEN(13, "thirteen", "13"),
    FOURTEEN(14, "fourteen", "14"),
    FIFTEEN(15,"fifteen", "15"),
    SIXTEEN(16, "sixteen", "16"),
    SEVENTEEN(17,"seventeen", "17"),
    EIGHTEEN(18,"eighteen", "18"),
    NINETEEN(19, "nineteen", "19"),
    TWENTY(20,"twenty", "20"),
    TWENTYONE(21, "twentyone", "twenty-one", "21"),
    TWENTYTWO(22, "twentytwo", "twenty-two", "22"),
    TWENTYTHREE(23, "twentythree", "twenty-three", "23"),
    TWENTYFOUR(24, "twentyfour", "twenty-four", "24"),
    TWENTYFIVE(25, "twentyfive", "twenty-five", "25"),
    TWENTYSIX(26, "twentysix", "twenty-six", "26"),
    TWENTYSEVEN(27, "twentyseven", "twenty-seven", "27"),
    TWENTYEIGHT(28, "twentyeight", "twenty-eight", "28"),
    TWENTYNINE(29, "twentynine", "twenty-nine", "29"),
    THIRTY(30, "thirty", "30"),
    THIRTYONE(31, "thirtyone", "thirty-one", "31"),
    THIRTYTWO(32, "thirtytwo", "thirty-two", "32"),
    THIRTYTHREE(33, "thirtythree", "thirty-three", "33"),
    THIRTYFOUR(34, "thirtyfour", "thirty-four", "34"),
    THIRTYFIVE(35, "thirtyfive", "thirty-five", "35"),
    THIRTYSIX(36, "thirtysix", "thirty-six", "36"),
    THIRTYSEVEN(37, "thirtyseven", "thirty-seven", "37"),
    THIRTYEIGHT(38, "thirtyeight", "thirty-eight", "38"),
    THIRTYNINE(39, "thirtynine", "thirty-nine", "39"),
    FOURTY(40, "forty", "40"),
    FOURTYONE(41,"fourtyone", "fourty-one", "41"),
    FOURTYTWO(42,"fourtytwo", "fourty-two", "42"),
    FOURTYTHREE(43,"fourtythree", "fourty-three", "43"),
    FOURTYFOUR(44,"fourtyfour", "fourty-four", "44"),
    FOURTYFIVE(45,"fourtyfive", "fourty-five", "45"),
    FOURTYSIX(46,"fourtysix", "fourty-six", "46"),
    FOURTYSEVEN(47,"fourtyseven", "fourty-seven", "47"),
    FOURTYEIGHT(48,"fourtyeight", "fourty-eight", "48"),
    FOURTYNINE(49,"fourtynine", "fourty-nine", "49"),
    FIFTY(50,"fifty", "50"),
    FIFTYONE(51, "fiftyone", "fifty-one", "51"),
    FIFTYTWO(52, "fiftytwo", "fifty-two", "52"),
    FIFTYTHREE(53, "fiftythree", "fifty-three", "53"),
    FIFTYFOUR(54, "fiftyfour", "fifty-four", "54"),
    FIFTYFIVE(55, "fiftyfive", "fifty-five", "55"),
    FIFTYSIX(56, "fiftysix", "fifty-six", "56"),
    FIFTYSEVEN(57, "fiftyseven", "fifty-seven", "57"),
    FIFTYEIGHT(58, "fiftyeight", "fifty-eight", "58"),
    FIFTYNINE(59, "fiftynine", "fifty-nine", "59"),
    SIXTY(60, "sixty", "60"),
    SIXTYONE(61, "sixtyone", "sixty-one", "61"),
    SIXTYTWO(62, "sixtytwo", "sixty-two", "62"),
    SIXTYTHREE(63, "sixtythree", "sixty-three", "63"),
    SIXTYFOUR(64, "sixtyfour", "sixty-four", "64"),
    SIXTYFIVE(65, "sixtyfive", "sixty-five", "65"),
    SIXTYSIX(66, "sixtysix", "sixty-six", "66"),
    SIXTYSEVEN(67, "sixtyseven", "sixty-seven", "67"),
    SIXTYEIGHT(68, "sixtyeight", "sixty-eight", "68"),
    SIXTYNINE(69, "sixtynine", "sixty-nine", "69"),
    SEVENTY(70, "seventy", "70"),
    SEVENTYONE(71, "seventyone", "seventy-one", "71"),
    SEVENTYTWO(72, "seventytwo", "seventy-two", "72"),
    SEVENTYTHREE(73, "seventythree", "seventy-three", "73"),
    SEVENTYFOUR(74, "seventyfour", "seventy-four", "74"),
    SEVENTYFIVE(75, "seventyfive", "seventy-five", "75"),
    SEVENTYSIX(76, "seventysix", "seventy-six", "76"),
    SEVENTYSEVEN(77, "seventyseven", "seventy-seven", "77"),
    SEVENTYEIGHT(78, "seventyeight", "seventy-eight", "78"),
    SEVENTYNINE(79, "seventynine", "seventy-nine", "79"),
    EIGHTY(80, "eighty", "eighty"),
    EIGHTYONE(81, "eightyone", "eighty-one", "81"),
    EIGHTYTWO(82, "eightytwo", "eighty-two", "82"),
    EIGHTYTHREE(83, "eightythree", "eighty-three", "83"),
    EIGHTYFOUR(84, "eightyfour", "eighty-four", "84"),
    EIGHTYFIVE(85, "eightfive", "eighty-five", "85"),
    EIGHTYSIX(86, "eightsix", "eighty-six", "86"),
    EIGHTYSEVEN(87, "eightyseven", "eighty-seven", "87"),
    EIGHTYEIGHT(88, "eightyeight", "eighty-eight", "81"),
    EIGHTYNINE(89, "eightynine", "eighty-nine", "81"),
    NINETY(90, "ninety", "90"),
    NINETYONE(91, "ninetyone", "ninety-one", "91"),
    NINETYTWO(92, "ninetytwo", "ninety-two", "92"),
    NINETYTHREE(93, "ninetythree", "ninety-three", "93"),
    NINETYFOUR(94, "ninetyfour", "ninety-four", "94"),
    NINETYFIVE(95, "ninetyfive", "ninety-five", "95"),
    NINETYSIX(96, "ninetysixe", "ninety-six", "96"),
    NINETYSEVEN(97, "ninetyseven", "ninety-seven", "97"),
    NINETYEIGHT(98, "ninetyeight", "ninety-eight", "98"), 
    NINETYNINE(99, "ninetynine", "ninety-nine", "99"),
    ONEHUNDRED(100, "onehundred", "one-hundred", "100");
    
    
    
    
	
	private static Map<String, Numeric> directiveMap = null;

	private int number;
    private String[] directives;

	private Numeric(int number, String... directives) {
		this.number = number;
        this.directives = directives;
	}

    public int getNumber() {
        return number;
    }

    public static Numeric getNumeric(String directive) {
        if(directiveMap == null) {
            directiveMap = new HashMap<>();
            for(Numeric number : values())
                for(String d : number.directives)
                    directiveMap.put(d, number);
        }

       

        return directiveMap.get(directive);
    }

}