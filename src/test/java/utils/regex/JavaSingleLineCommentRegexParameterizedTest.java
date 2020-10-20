package utils.regex;

import codeinfoextractor.utils.RegexUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class JavaSingleLineCommentRegexParameterizedTest {
    @Parameter(0)
    public String input;

    @Parameter(1)
    public int matchCount;

    @Parameters
    public static Collection<Object[]> getData() {
        Object[][] data = new Object[][]{
                {"", 0},
                {"this is not a comment", 0},
                {"//This is a single comment", 1},
                {"//Trying to be //smart", 1},
                {"System.out.println(\"Testing\") //smart\n" +
                        "//Another comment", 2},
        };

        return Arrays.asList(data);
    }

    @Test
    public void testRegexValidity() {
        Pattern pattern = Pattern.compile(RegexUtils.JAVA_SINGLE_LINE_COMMENT_REGEX);
        Matcher matcher = pattern.matcher(input);

        int count = 0;
        while (matcher.find()) {
            count++;
        }

        assertEquals(matchCount, count);
    }
}
