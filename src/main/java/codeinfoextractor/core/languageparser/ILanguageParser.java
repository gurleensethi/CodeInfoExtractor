package codeinfoextractor.core.languageparser;

import codeinfoextractor.core.models.LanguageParseResult;

import java.util.List;

public interface ILanguageParser {
    LanguageParseResult parse(List<String> lines);
}
