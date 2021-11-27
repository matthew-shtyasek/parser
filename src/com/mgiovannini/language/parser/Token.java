package com.mgiovannini.language.parser;

public class Token {
    private TokenType type;
    private String text;

    public Token() { }
    public Token(TokenType type, String text) {
        this.type = type;
        this.text = text;
    }

    public TokenType getType() {
        return type;
    }
    public String getText() {
        return text;
    }

    public void setType(TokenType type) {
        this.type = type;
    }
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return type + " " + text;
    }
}
