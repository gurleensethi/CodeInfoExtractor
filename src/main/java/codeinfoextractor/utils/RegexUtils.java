package codeinfoextractor.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {
    public static final String JAVA_SINGLE_LINE_COMMENT_REGEX = "//.*";
    public static final String JAVA_MULTI_LINE_COMMENT_REGEX = "\"/\\\\*([^*]|[\\\\r\\\\n]|(\\\\*+([^*/]|[\\\\r\\\\n])))*\\\\*+/\"";
    public static final String JAVA_TODOS_REGEX = "//[ ]?TODO:.*";
    public static final String TYPESCRIPT_SINGLE_LINE_COMMENT_REGEX = "//.*";
    public static final String TYPESCRIPT_MULTI_LINE_COMMENT_REGEX = "\"/\\\\*([^*]|[\\\\r\\\\n]|(\\\\*+([^*/]|[\\\\r\\\\n])))*\\\\*+/\"";
    public static final String TYPESCRIPT_TODOS_REGEX = "//[ ]?TODO:.*";
    public static final String PYTHON_SINGLE_LINE_COMMENT_REGEX = "#.*";
    public static final String PYTHON_MULTI_LINE_COMMENT_REGEX = "( *[#]{1,}.*\\n?){2,}";
    public static final String PYTHON_TODOS_REGEX = "#[ ]?TODO:.*";

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
