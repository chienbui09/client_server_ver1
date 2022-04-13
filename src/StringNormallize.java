
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;
public class StringNormallize {
    public static void main(String[] args) {
        String string;
        String normalizedString = new String();
        Scanner scanner = new Scanner(System.in);
        string = scanner.nextLine().toLowerCase().trim();
        System.out.println(string);
        String[] nomallizingString = string.split(" ");
        StringBuffer stringBuffer = new StringBuffer();
        for(String str : nomallizingString){
            if(str!=" "){
                stringBuffer.append(str);
                stringBuffer.append(" ");
                normalizedString += str;
                normalizedString += " ";
            }
        }
        String  firstLetter = normalizedString.substring(0,1);
        String remLetter = normalizedString.substring(1);
        firstLetter = firstLetter.toUpperCase();
        firstLetter = firstLetter.concat(remLetter);
    }


}
