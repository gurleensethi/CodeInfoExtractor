package codeinfoextractor.core;

import codeinfoextractor.core.languageparser.ILanguageParser;
import codeinfoextractor.core.models.LanguageParseResult;
import codeinfoextractor.core.models.ProcessedFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class InfoExtractor {
    private static final Logger logger = Logger.getLogger(InfoExtractor.class.getName());
    private final Map<String, Supplier<ILanguageParser>> languageParsers = new HashMap<>();

    public InfoExtractor() {

    }

    public void registerParser(String extension, Supplier<ILanguageParser> parser) {
        this.languageParsers.put(extension, parser);
    }

    public LanguageParseResult parseInfoFromFile(ProcessedFile file) {
        final ILanguageParser parser = this.languageParsers.get(file.getFileExtension()).get();
        return parser.parse(file.getData());
    }

    public List<LanguageParseResult> parseFiles(List<ProcessedFile> files) {
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
                .map(this::parseInfoFromFile)
                .collect(Collectors.toList());
    }
}
