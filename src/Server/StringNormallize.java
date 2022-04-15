package Server;

import java.util.regex.*;
public class StringNormallize {
    String normalString;
    String firstLetter = null;
    String remLetters = null;

    public void setNormalString(String normalString) {
        this.normalString = normalString;
    }

    public String normalize() {
        String normalizedString = "";

        normalString = normalString.trim().toLowerCase();
//        regex trimmer = new Regex()
        String[] nomalizingString = normalString.split(" ");
        StringBuffer normalStringBuffer = new StringBuffer();
        for (String str : nomalizingString) {
            if (!str.equals("")) {
                normalizedString += str;
                normalizedString += " ";
            }
        }
        firstLetter = normalizedString.substring(0, 1).toUpperCase();
        remLetters = normalizedString.substring(1);
        firstLetter = firstLetter.concat(remLetters);
//        System.out.println("normalized string: " + firstLetter);
        return firstLetter;
    }

}
