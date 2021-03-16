package Model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Leonardo e Luiz
 */
public class TokenMatcher {

    private Pattern pattern;
    private TokenType tokenType;
    private boolean error;

    public TokenMatcher(String pattern, TokenType type, boolean error) {
        this.pattern = Pattern.compile(pattern);
        this.tokenType = type;
        this.error = error;
    }

    public TokenMatch match(String line, int lineNumber) throws Exception {
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            return new TokenMatch(new Token(0, tokenType, matcher.group(1),0, lineNumber, error), matcher.group(2));
        }
        throw new Exception();
    }

}
