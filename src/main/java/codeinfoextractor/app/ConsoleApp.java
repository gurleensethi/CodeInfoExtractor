package codeinfoextractor.app;

import codeinfoextractor.core.InfoExtractor;
import codeinfoextractor.core.fileloader.FileLoader;
import codeinfoextractor.core.models.LanguageParseResult;
import codeinfoextractor.core.models.SourceCodeFile;
import codeinfoextractor.parsers.JavaParser;
import codeinfoextractor.parsers.PythonParser;
import codeinfoextractor.parsers.TypescriptParser;
import java.util.List;
import java.util.logging.Logger;

public class ConsoleApp {
    private static final Logger logger = Logger.getLogger(ConsoleApp.class.getName());

    public void run(List<String> filePaths) {
        if (filePaths.size() == 0) {
            logger.info("Please provide file paths as runtime arguments.");
            return;
        }

        final FileLoader fileLoader = new FileLoader();

        List<SourceCodeFile> sourceCodeFileList = fileLoader.fromFilePaths(filePaths);

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
