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

        // remove spaces character at most left and the end of string
        // and take all characters to lowercase
        normalString = normalString.trim().toLowerCase();

        // replace all consecutive spaces-string by a space
        // "\\s+" is a special string, which present successive space-string
        normalString = normalString.replaceAll("\\s+"," ");

        // get substring which consist of the first letter of origin string, and capitalize it.
        firstLetter = normalString.substring(0, 1).toUpperCase();

        // get to rest of origin string from position 1.
        remLetters = normalString.substring(1);

        // add two substrings to an unique string
        firstLetter = firstLetter.concat(remLetters);
        return firstLetter;
    }

}
