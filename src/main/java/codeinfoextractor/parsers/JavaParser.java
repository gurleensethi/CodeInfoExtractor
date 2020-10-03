package codeinfoextractor.parsers;

import codeinfoextractor.core.languageparser.ILanguageParser;
import codeinfoextractor.core.models.LanguageParseResult;

import java.util.List;

public class JavaParser implements ILanguageParser {
    @Override
    public LanguageParseResult parse(List<String> lines) {
        return new LanguageParseResult();
    }
}
