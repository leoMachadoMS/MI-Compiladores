package Model;

/**
 *
 * @author Leonardo e Luiz
 */
public enum TokenType {
    Reserved,
    BlockComment,
    LineComment,
    String,
    LogicalOperator,
    ArithmeticOperator,
    RelationalOperator,
    Identifier,
    Delimiter,
    Number,
    InvalidString,
    InvalidCharacter,
    InvalidNumber,
    InvalidSymbol,
    InvalidComment;

    @Override
    public String toString() {
        switch (this) {
            case Reserved:
                return "PRE";
            case BlockComment:
                return "comentário_de_bloco";
            case LineComment:
                return "comentário_de_linha";
            case String:
                return "CAD";
            case LogicalOperator:
                return "LOG";
            case Identifier:
                return "IDE";
            case ArithmeticOperator:
                return "ART";
            case Delimiter:
                return "DEL";
            case RelationalOperator:
                return "REL";
            case Number:
                return "NRO";
            case InvalidString:
                return "CMF";
            case InvalidCharacter:
                return "caractere_mal_formado";
            case InvalidNumber:
                return "NMF";
            case InvalidSymbol:
                return "SIB";
            case InvalidComment:
                return "CoMF";
            default:
                return super.toString();
        }
    }
}
