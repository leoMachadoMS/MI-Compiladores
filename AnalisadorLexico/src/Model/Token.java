package Model;

/**
 *
 * @author Leonardo e Luiz
 */
public class Token {

    private int id;
    private final TokenType type;
    private final String lexeme;
    private int position;
    private final int line;

    public Token(int id, TokenType type, String lexeme, int position, int line) {
        this.id = id;
        this.type = type;
        this.lexeme = lexeme;
        this.position = position;
        this.line = line;
    }

    public TokenType getType() {
        return type;
    }

    public final int getId() {
        return id;
    }

    public final String getLexeme() {
        return lexeme;
    }

    public final int getPosition() {
        return position;
    }

    public final int getLine() {
        return line;
    }

    @Override
    public String toString() {
        return String.format("%02d", line) + " <" + type.toString() + "> " + lexeme;
    }

    ;

    void setId(int id) {
        this.id = id;
    }

    void setPosition(int position) {
        this.position = position;
    }

}
