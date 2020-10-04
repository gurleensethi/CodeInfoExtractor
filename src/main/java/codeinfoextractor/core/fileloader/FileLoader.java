package codeinfoextractor.core.fileloader;

import codeinfoextractor.core.models.ProcessedFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class FileLoader {
    private static final Logger logger = Logger.getLogger(FileLoader.class.getName());

    private ProcessedFile fileToProcessedFile(File file) {
        try {
            final StringBuilder stringBuilder = new StringBuilder();
            final BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = null;

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line)
                        .append("\n");
            }

            final int indexOfExtension = file.getName().lastIndexOf('.');
            final String name = file.getName().substring(0, indexOfExtension);
            final String extension = file.getName().substring(indexOfExtension + 1);

            return new ProcessedFile(name, extension, stringBuilder.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<ProcessedFile> fromArgs(String[] args) {
        logger.info("Checking for " + args.length + " file(s).");

        final List<ProcessedFile> files = Arrays.stream(args)
                .filter(fileName -> {
                    int index = fileName.lastIndexOf(".");
                    return index != -1
                            && index != fileName.length() - 1
                            && fileName.charAt(0) != '.';
                })
                .map(File::new)
                .filter(File::exists)
                .map(this::fileToProcessedFile)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        logger.info(files.size() + " file(s) found.");

        return files;
    }
}
