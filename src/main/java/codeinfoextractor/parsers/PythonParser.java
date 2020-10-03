package codeinfoextractor.parsers;

import codeinfoextractor.core.languageparser.ILanguageParser;
import codeinfoextractor.core.models.LanguageParseResult;
import codeinfoextractor.utils.Pair;
import codeinfoextractor.utils.RegexUtils;

public class PythonParser implements ILanguageParser {
    @Override
    public LanguageParseResult parse(String data) {
        int allComments = 0;
        int commentLinesInBlock = 0;
        int blockLineComments = 0;
        int todos = 0;

        allComments += RegexUtils.countOccurrences(data, "#.*");
        todos += RegexUtils.countOccurrences(data, "#[ ]?TODO:.*");

        Pair<Integer, Integer> blockCommentsResult =
                RegexUtils.countOccurrencesWithLines(data, "( *[#]{1,}.*\\n?){2,}"); // Also matches -> ( *#.*\n) *#.*\n
        blockLineComments += blockCommentsResult.getOne();
        commentLinesInBlock += blockCommentsResult.getTwo();


        final LanguageParseResult result = new LanguageParseResult();
        result.setLines(data.split("\n").length);
        result.setCommentLines(allComments);
        result.setSingleLineComments(allComments - commentLinesInBlock);
        result.setBlockLineComments(blockLineComments);
        result.setCommentLinesInBlock(commentLinesInBlock);
        result.setTodos(todos);

        return result;
    }
}
