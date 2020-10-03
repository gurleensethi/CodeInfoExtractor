package codeinfoextractor.core.models;

public class LanguageParseResult {
    private int lines;
    private int commentLines;
    private int singleLineComments;
    private int commentLinesInBlock;
    private int blockLineComments;
    private int todos;

    public LanguageParseResult() {
    }

    public int getLines() {
        return lines;
    }

    public void setLines(int lines) {
        this.lines = lines;
    }

    public int getCommentLines() {
        return commentLines;
    }

    public void setCommentLines(int commentLines) {
        this.commentLines = commentLines;
    }

    public int getSingleLineComments() {
        return singleLineComments;
    }

    public void setSingleLineComments(int singleLineComments) {
        this.singleLineComments = singleLineComments;
    }

    public int getCommentLinesInBlock() {
        return commentLinesInBlock;
    }

    public void setCommentLinesInBlock(int commentLinesInBlock) {
        this.commentLinesInBlock = commentLinesInBlock;
    }

    public int getBlockLineComments() {
        return blockLineComments;
    }

    public void setBlockLineComments(int blockLineComments) {
        this.blockLineComments = blockLineComments;
    }

    public int getTodos() {
        return todos;
    }

    public void setTodos(int todos) {
        this.todos = todos;
    }
}
