package Model;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Leonardo e Luiz
 */
public class Lexer {

    private Iterable<String> code;
    private ArrayList<Token> tokens;
    private ArrayList<Token> errors;

    public Lexer(Iterable<String> code) {
        this.code = code;
        this.tokens = null;
    }

    private void analize() {
        tokens = new ArrayList<>();
        errors = new ArrayList<>();

        final String reserved = "(var|const|typedef|struct|extends|procedure|function|start|return|if|else|then|while|read|print|int|real|boolean|string|true|false|global|local)";

        final String identifier = "([a-zA-Z]\\w*)";

        final String number = "(-?\\s*\\d+\\.?\\d+)";

        final String lineComment = "(//.*)";

        final String arithmetic = "(\\+\\+|\\-\\-|[\\%\\*\\-\\+/])";

        final String logicalOp = "(&&|\\|\\||!)";

        final String string = "(\"[^\"]*\")";

        final String commentBegin = "(/\\*)";

        final String commentEnd = "(\\*/)";

        final String relational = "(!=|<=|>=|=|<|>|==)";

        final String delimiter = "([;,\\(\\){}\\[\\]\\.])";

        Pattern beginCommentPattern = Pattern.compile("^" + commentBegin + "(.*)$");
        Pattern endCommentPattern = Pattern.compile(commentEnd + "(.*)$");
        ArrayList<TokenMatcher> matchers = new ArrayList<>();
        matchers.add(new TokenMatcher("^" + reserved + "([\\W].*){0,1}$", TokenType.Reserved));
        matchers.add(new TokenMatcher("^" + identifier + "([\\W].*){0,1}$", TokenType.Identifier));
        matchers.add(new TokenMatcher("^" + string + "(.*)$", TokenType.String));
        matchers.add(new TokenMatcher("^" + number + "(\\D.*){0,1}$", TokenType.Number));
        matchers.add(new TokenMatcher("^" + lineComment + "(.*)$", TokenType.LineComment));
        matchers.add(new TokenMatcher("^" + arithmetic + "(.*)$", TokenType.ArithmeticOperator));
        matchers.add(new TokenMatcher("^" + delimiter + "(.*)$", TokenType.Delimiter));
        matchers.add(new TokenMatcher("^" + relational + "(.*)$", TokenType.RelationalOperator));
        matchers.add(new TokenMatcher("^" + logicalOp + "([\\W].*){0,1}$", TokenType.LogicalOperator));

        // Errors!
        ArrayList<TokenMatcher> errorMatchers = new ArrayList<>();
        final String stringErrorInvalid = "^(\"[^\"]*\")(.*)$";
        final String stringErrorIncomplete = "^(\"[^\"]*)()$";
        final String characterErrorInvalid = "^('[^']*')(.*)$";
        final String characterErrorIncomplete = "^('[^']*)()$";
        final String numberError = "^(\\d+\\.)(.*){0,1}$";
        final String symbolError = "^(.)(.*)$";

        errorMatchers.add(new TokenMatcher(stringErrorInvalid, TokenType.InvalidString));
        errorMatchers.add(new TokenMatcher(stringErrorIncomplete, TokenType.InvalidString));
        errorMatchers.add(new TokenMatcher(characterErrorInvalid, TokenType.InvalidCharacter));
        errorMatchers.add(new TokenMatcher(characterErrorIncomplete, TokenType.InvalidCharacter));
        errorMatchers.add(new TokenMatcher(numberError, TokenType.InvalidNumber));
        errorMatchers.add(new TokenMatcher(symbolError, TokenType.InvalidSymbol));

        int lineNumber = 0;

        boolean commentSearch = false;
        for (String line : code) {
            lineNumber++;

            while (line != null && !(line = line.trim()).equals("")) {
                Matcher matcher;

                if (commentSearch) {
                    matcher = endCommentPattern.matcher(line);
                    if (matcher.find()) {
                        line = matcher.group(2);
                        commentSearch = false;
                    } else {
                        break;
                    }

                    continue;
                }

                // Searching comments
                matcher = beginCommentPattern.matcher(line);
                if (matcher.find()) {
                    line = matcher.group(2);
                    commentSearch = true;
                    continue;
                }

                boolean matched = false;
                for (TokenMatcher m : matchers) {
                    try {
                        TokenMatch match = m.match(line, lineNumber);
                        tokens.add(match.getToken());
                        line = match.getLine();
                        matched = true;
                        break;
                    } catch (Exception e) {
                    }
                }

                if (!matched) {
                    for (TokenMatcher m : errorMatchers) {
                        try {
                            TokenMatch match = m.match(line, lineNumber);
                            errors.add(match.getToken());
                            line = match.getLine();
                            matched = true;
                            break;
                        } catch (Exception e) {
                        }
                    }
                }

                if (!matched) {
                    System.err.println("Erro que ainda não tratei. Linha: " + lineNumber + ": " + line);
                    break;
                }

            }
        }

        if (commentSearch) {
            errors.add(new Token(0,TokenType.InvalidComment, "", 0, lineNumber));
        }
        int i =0;
        for(Token t: tokens){
            t.setPosition(i);
            i++;
        }
    }

    public ArrayList<Token> getTokens() {
        if (tokens == null) {
            analize();
        }

        return tokens;
    }

    public Iterable<Token> getErrors() {
        if (errors == null) {
            analize();
        }

        return errors;
    }
}
