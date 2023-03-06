package test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class Test {
    public static void main(String[] args) {
        String test = "428.9a#@~";
        System.out.println(StringFilter(test));
        String xval = "true", yval = "true";
        boolean xl, yl;
        if ((xl = Boolean.parseBoolean(xval)) && (yl = Boolean.parseBoolean(yval))) {
            System.out.println(xl && yl);
        } else {
            System.out.println("false");
        }

        System.out.println(StringFilter("ReportIndicatorDto(code=208, value=16.00mmHg，正常眼压：\n" +
                "正常范围为10～21mmHg。)"));

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
