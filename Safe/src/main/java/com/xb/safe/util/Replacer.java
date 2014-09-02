package com.xb.safe.util;

import java.util.HashMap;
import java.util.Map;

public class Replacer {
    public static final String engRegexString = "([^А-я]+)";
    private static volatile Map<Character, Character> chReplace;

    public Replacer() {
        chReplace = new HashMap<Character, Character>();
        chReplace.put('q', 'й');
        chReplace.put('w', 'ц');
        chReplace.put('e', 'у');
        chReplace.put('r', 'к');
        chReplace.put('t', 'е');
        chReplace.put('y', 'н');
        chReplace.put('u', 'г');
        chReplace.put('i', 'ш');
        chReplace.put('o', 'щ');
        chReplace.put('p', 'з');
        chReplace.put('[', 'х');
        chReplace.put(']', 'ї');
        chReplace.put('a', 'ф');
        chReplace.put('s', 'і');
        chReplace.put('d', 'в');
        chReplace.put('f', 'а');
        chReplace.put('g', 'п');
        chReplace.put('h', 'р');
        chReplace.put('j', 'о');
        chReplace.put('k', 'л');
        chReplace.put('l', 'д');
        chReplace.put(';', 'ж');
        chReplace.put('\'', 'є');
        chReplace.put('z', 'я');
        chReplace.put('x', 'ч');
        chReplace.put('c', 'с');
        chReplace.put('v', 'м');
        chReplace.put('b', 'и');
        chReplace.put('n', 'т');
        chReplace.put('m', 'ь');
        chReplace.put(',', 'б');
        chReplace.put('.', 'ю');
        chReplace.put('{', 'х');
        chReplace.put('}', 'ї');
        chReplace.put(':', 'ж');
        chReplace.put('\"', 'є');
        chReplace.put('<', 'б');
        chReplace.put('>', 'ю');
    }

    public boolean isFullEngString(final String text) {
        if (text == null || text.trim().isEmpty()) {
            return false;
        }
        return text.matches(engRegexString);
    }

    public String convertEnglishToUkr(final String text) {
        if (text == null || text.trim().isEmpty()) {
            return text;
        }
        char[] toCharArray = text.toLowerCase().toCharArray();
        char[] newCharArray = new char[toCharArray.length];
        for (int i = 0; i < toCharArray.length; i++) {
            char c = toCharArray[i];
            if (chReplace.containsKey(c)) {
                newCharArray[i] = chReplace.get(c);
            } else {
                newCharArray[i] = c;
            }
        }
        return new String(newCharArray);
    }

    public String removeEngI(final String str) {
        if (str == null || str.trim().isEmpty()) {
            return str;
        }
        return str.replace("i", "і").replace("I", "І");
    }
}
