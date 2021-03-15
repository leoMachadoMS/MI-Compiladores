package Model;

/**
 *
 * @author Leonardo e Luiz
 */
public class TokenMatch {

    private Token token;
    private String remain;

    public TokenMatch(Token token, String remainingLine) {
        this.token = token;
        this.remain = remainingLine;
    }

    public Token getToken() {
        return token;
    }

    public String getLine() {
        return remain;
    }
}
