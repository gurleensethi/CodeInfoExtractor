package codeinfoextractor.core;

import codeinfoextractor.core.languageparser.ILanguageParser;
import codeinfoextractor.core.models.LanguageParseResult;
import codeinfoextractor.core.models.SourceCodeFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Class to be used for parsing.
 * Handles parser registration and using appropriate parsers.
 */
public class InfoExtractor {
    private static final Logger logger = Logger.getLogger(InfoExtractor.class.getName());
    private final Map<String, Supplier<ILanguageParser>> languageParsers = new HashMap<>();

    public InfoExtractor() {
    }

    /**
     * Register a new parser for a file type.
     *
     * If a parser is already registered for a file type, it will be overridden.
     *
     * @param extension of file to run the parser for.
     * @param parser to be used for files with provided extension.
     */
    public void registerParser(String extension, Supplier<ILanguageParser> parser) {
        this.languageParsers.put(extension, parser);
    }

    /**
     * Calls the appropriate parser for provided file data
     * and returns the parsing result.
     *
     * @param file to get results for.
     * @return parsing results of the file.
     */
    public LanguageParseResult parseInfoFromFile(SourceCodeFile file) {
        final ILanguageParser parser = this.languageParsers.get(file.getFileExtension()).get();
        return parser.parse(file.getData());
    }

    /**
     * Calls the appropriate parser for provided files
     * and returns the parsing result for all.
     *
     * Files that don't have a matching parser will be ignored.
     *
     * @param files to get parsing result for.
     * @return parsing results of files that had a parser registered.
     */
    public List<LanguageParseResult> parseFiles(List<SourceCodeFile> files) {
        return files.parallelStream()
                .filter(file -> {
                    boolean hasRegisteredParser = this.languageParsers.containsKey(file.getFileExtension());
                    if (!hasRegisteredParser) {
                        logger.warning(
                                String.format("No parsers found for '.%s' files. Skipping '%s.%s' file.",
                                        file.getFileExtension(),
                                        file.getFileName(),
                                        file.getFileExtension())
                        );
                    }
                    return hasRegisteredParser;
                })
                .map(sourceCodeFile -> {
                    LanguageParseResult result = this.parseInfoFromFile(sourceCodeFile);
                    result.setSourceCodeFile(sourceCodeFile);
                    return result;
                })
                .collect(Collectors.toList());
    }
}
