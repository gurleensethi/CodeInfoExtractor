package codeinfoextractor;

import codeinfoextractor.core.InfoExtractor;
import codeinfoextractor.core.fileprocessor.FileProcessor;
import codeinfoextractor.core.models.LanguageParseResult;
import codeinfoextractor.core.models.ProcessedFile;
import codeinfoextractor.parsers.JavaParser;

import java.util.List;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        if (args.length == 0) {
            logger.info("Please provide file path as runtime arguments.");
            return;
        }

        final FileProcessor fileProcessor = new FileProcessor();

        List<ProcessedFile> processedFileList = fileProcessor.fromArgs(args);

        InfoExtractor infoExtractor = new InfoExtractor();
        infoExtractor.registerParser("gradle", JavaParser::new);
        final List<LanguageParseResult> results = infoExtractor.parseFiles(processedFileList);
        for (LanguageParseResult result : results) {
            System.out.println(result);
        }
    }
}
