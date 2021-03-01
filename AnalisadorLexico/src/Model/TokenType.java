/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Leonardo
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

    public String toString() {
        switch (this) {
            case Reserved:
                return "palavra_reservada";
            case BlockComment:
                return "comentário_de_bloco";
            case LineComment:
                return "comentário_de_linha";
            case String:
                return "cadeia_de_caracteres";
            case LogicalOperator:
                return "operador_lógico";
            case Identifier:
                return "identificador";
            case ArithmeticOperator:
                return "operador_aritmético";
            case Delimiter:
                return "delimitador";
            case RelationalOperator:
                return "operador_relacional";
            case Number:
                return "número";
            case InvalidString:
                return "cadeia_mal_formada";
            case InvalidCharacter:
                return "caractere_mal_formado";
            case InvalidNumber:
                return "número_mal_formado";
            case InvalidSymbol:
                return "simbolo_invalido";
            case InvalidComment:
                return "comentario_mal_formado";
            default:
                return super.toString();
        }
    }
}
