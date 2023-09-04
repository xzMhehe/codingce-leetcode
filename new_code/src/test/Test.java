package test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        strings.add("15263019900127473X");
        strings.add("21110319750311113x");
        strings.add("412326199709063914");
        strings.add("61242519980112531x");
        strings = strings.stream().map(e-> {
            e = e.toUpperCase();
            return e;
        }).collect(Collectors.toList());
        strings.stream().forEach(System.out::println);
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
