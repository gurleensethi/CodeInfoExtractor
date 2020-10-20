package codeinfoextractor.parsers;

import codeinfoextractor.core.languageparser.ILanguageParser;
import codeinfoextractor.core.models.LanguageParseResult;
import codeinfoextractor.utils.Pair;
import codeinfoextractor.utils.RegexUtils;

public class JavaParser implements ILanguageParser {
    @Override
    public LanguageParseResult parse(String data) {
        int singleLineComments = 0;
        int commentLinesInBlock = 0;
        int blockLineComments = 0;
        int todos = 0;

        singleLineComments += RegexUtils.countOccurrences(data, RegexUtils.JAVA_SINGLE_LINE_COMMENT_REGEX);
        todos += RegexUtils.countOccurrences(data, RegexUtils.JAVA_TODOS_REGEX);

        // Reference for regex: https://blog.ostermiller.org/finding-comments-in-source-code-using-regular-expressions/
        Pair<Integer, Integer> blockCommentsResult = RegexUtils.countOccurrencesWithLines(data, RegexUtils.JAVA_MULTI_LINE_COMMENT_REGEX);
        blockLineComments += blockCommentsResult.getOne();
        commentLinesInBlock += blockCommentsResult.getTwo();


        final LanguageParseResult result = new LanguageParseResult();
        result.setLines(data.split("\n").length);
        result.setCommentLines(singleLineComments + commentLinesInBlock);
        result.setSingleLineComments(singleLineComments);
        result.setBlockLineComments(blockLineComments);
        result.setCommentLinesInBlock(commentLinesInBlock);
        result.setTodos(todos);

        return result;
    }
}
