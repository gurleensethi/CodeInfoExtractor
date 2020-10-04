package codeinfoextractor.core.models;

/**
 * Details of the parsing result for a file.
 *
 * Contains information regarding the comments for a piece of source code.
 */
public class LanguageParseResult {
    /**
     * Total lines of source code.
     */
    private int lines;

    /**
     * Total lines of comments.
     * Includes single line and lines in block comments.
     */
    private int commentLines;

    /**
     * Single line comments in source code.
     */
    private int singleLineComments;

    /**
     * Total number of block comments.
     */
    private int blockLineComments;

    /**
     * Total lines of contained inside block comments.
     */
    private int commentLinesInBlock;

    /**
     * Total number of todos.
     */
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

    @Override
    public String toString() {
        return "Total # of lines: " + lines +
                "\nTotal # of comment lines: " + commentLines +
                "\nTotal # of single line comments: " + singleLineComments +
                "\nTotal # of comment lines within block comments: " + commentLinesInBlock +
                "\nTotal # of block line comments: " + blockLineComments +
                "\nTotal # of TODO’s: " + todos;
    }

}
