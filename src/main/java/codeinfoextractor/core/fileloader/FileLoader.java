package codeinfoextractor.core.fileloader;

import codeinfoextractor.core.models.SourceCodeFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Helps in loading code files and filtering out ones that are inappropriate (such as files with no extensions).
 */
public class FileLoader {
    private static final Logger logger = Logger.getLogger(FileLoader.class.getName());

    /**
     * Generates a SourceCodeFile object from File.
     * The file to be processed provided should exist and be valid.
     * In case of invalid files null is returned.
     *
     * @param file to convert
     * @return ProcessedFile object if file is valid or null.
     */
    private SourceCodeFile fileToSourceCodeFile(File file) {
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

            return new SourceCodeFile(name, extension, stringBuilder.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Processes files from the file paths provided.
     * Filters files based on their validity.
     *
     * A file is valid if it:
     *   - has a valid name.
     *   - has a valid extension.
     *
     * @param filePaths list of file paths to process.
     * @return list of ProcessedFile objects containing data for valid files.
     */
    public List<SourceCodeFile> fromFilePaths(List<String> filePaths) {
        logger.info("Checking for " + filePaths.size() + " file(s).");

        final List<SourceCodeFile> files = filePaths.stream()
                .filter(fileName -> {
                    int index = fileName.lastIndexOf(".");
                    return index != -1
                            && index != fileName.length() - 1
                            && fileName.charAt(0) != '.';
                })
                .map(File::new)
                .filter(File::exists)
                .map(this::fileToSourceCodeFile)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        logger.info(files.size() + " file(s) found.");

        return files;
    }
}
