package codeinfoextractor.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {
    public static int countOccurrences(String source, String regex) {
        int count = 0;
        int start = 0;

        Matcher matcher = Pattern.compile(regex, Pattern.MULTILINE).matcher(source);

        while (start < source.length() && matcher.find(start)) {
            count += 1;
            start = matcher.start() + 1;
        }

        return count;
    }

    public static Pair<Integer, Integer> countOccurrencesWithLines(String source, String regex) {
        int count = 0;
        int start = 0;
        int lines = 0;

        Matcher matcher = Pattern.compile(regex, Pattern.MULTILINE).matcher(source);

        while (start < source.length() && matcher.find(start)) {
            count += 1;
            start = matcher.end() + 1;
            lines += source.substring(matcher.start(), matcher.end()).split("\n").length;
        }

        return new Pair<>(count, lines);
    }
}
