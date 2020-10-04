package codeinfoextractor;

import codeinfoextractor.core.InfoExtractor;
import codeinfoextractor.core.fileloader.FileLoader;
import codeinfoextractor.core.models.LanguageParseResult;
import codeinfoextractor.core.models.SourceCodeFile;
import codeinfoextractor.parsers.JavaParser;
import codeinfoextractor.parsers.PythonParser;
import codeinfoextractor.parsers.TypescriptParser;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        if (args.length == 0) {
            logger.info("Please provide file path as runtime arguments.");
            return;
        }

        final FileLoader fileLoader = new FileLoader();

        List<SourceCodeFile> sourceCodeFileList = fileLoader.fromFilePaths(Arrays.asList(args));

        InfoExtractor infoExtractor = new InfoExtractor();
        infoExtractor.registerParser("java", JavaParser::new);
        infoExtractor.registerParser("ts", TypescriptParser::new);
        infoExtractor.registerParser("py", PythonParser::new);

        final List<LanguageParseResult> results = infoExtractor.parseFiles(sourceCodeFileList);
        for (LanguageParseResult result : results) {
            System.out.println(result);
        }
    }
}
