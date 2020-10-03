package codeinfoextractor.core.models;

import java.util.List;

public class ProcessedFile {
    private String fileName;
    private String fileExtension;
    private List<String> lines;

    public ProcessedFile(String fileName, String fileExtension, List<String> lines) {
        this.fileName = fileName;
        this.fileExtension = fileExtension;
        this.lines = lines;
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
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
