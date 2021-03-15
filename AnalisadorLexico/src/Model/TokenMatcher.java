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

    public TokenMatcher(String pattern, TokenType type) {
        this.pattern = Pattern.compile(pattern);
        this.tokenType = type;
    }

    public TokenMatch match(String line, int lineNumber) throws Exception {
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            return new TokenMatch(new Token(0, tokenType, matcher.group(1),0, lineNumber), matcher.group(2));
        }
        throw new Exception();
    }

}
