package codeinfoextractor.core.languageparser;

import codeinfoextractor.core.models.LanguageParseResult;

/**
 * Contract to be satisfied by a parser.
 * A parser will receive code data in the form a long string.
 *
 * Once implemented, the parser should be registered with
 * an instance of InfoExtractor.
 */
public interface ILanguageParser {
    /**
     * Should contain the parsing logic for a language.
     *
     * @param data source code data to be parsed.
     * @return result of parsing.
     */
    LanguageParseResult parse(String data);
}
