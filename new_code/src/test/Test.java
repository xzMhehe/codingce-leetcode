package test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class Test {
    public static void main(String[] args) {
        String test = "428.9a#@~";

        test = "111";

        System.out.println(test);


    }

    // Filter UnNumbe rCharacters RegexExpress
    static String regEx = "[^-?\\d+.?\\d+]|([-+])";

    public static String StringFilter(String str) throws PatternSyntaxException {
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(str);
        System.out.println(matcher.matches());
        return matcher.replaceAll("").trim();
    }

}
