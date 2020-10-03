package codeinfoextractor;

import codeinfoextractor.core.fileprocessor.FileProcessor;
import codeinfoextractor.core.models.ProcessedFile;

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
    }
}
