package codeinfoextractor.core.models;

/**
 * Contains information for a source code file.
 *      - Name of file.
 *      - Type of file.
 *      - Data inside the file.
 */
public class SourceCodeFile {
    private String fileName;
    private String fileExtension;
    private String data;

    public SourceCodeFile(String fileName, String fileExtension, String data) {
        this.fileName = fileName;
        this.fileExtension = fileExtension;
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }
}
