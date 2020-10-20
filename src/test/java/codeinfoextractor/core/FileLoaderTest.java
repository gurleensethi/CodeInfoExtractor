package codeinfoextractor.core;

import codeinfoextractor.core.fileloader.FileLoader;
import codeinfoextractor.core.models.SourceCodeFile;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertNull;

public class FileLoaderTest {
    @Test
    public void fileToSourceCodeFileReturnNullIfFileNotFound() {
        FileLoader fileLoader = new FileLoader();
        File invalidFile = new File("this is an invalid path");

        SourceCodeFile sourceCodeFile = fileLoader.fileToSourceCodeFile(invalidFile);

        assertNull(sourceCodeFile);
    }
}
