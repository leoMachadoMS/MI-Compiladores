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
