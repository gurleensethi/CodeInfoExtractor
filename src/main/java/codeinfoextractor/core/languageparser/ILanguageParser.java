package codeinfoextractor.core.languageparser;

import codeinfoextractor.core.models.LanguageParseResult;

public interface ILanguageParser {
    LanguageParseResult parse(String data);
}
