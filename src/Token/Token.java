package Token;

import java.util.Objects;

public class Token {

    private static int nextId = 0;
    public final int ID;

    private TypeToken type;
    private int index; // Хранит index строки, где начинается токен
    private String token;

    {
        this.ID = nextId;
        Token.nextId++;
    }

    public Token(String token, TypeToken type, int index) {
        this.token = token;
        this.type = type;
        this.index = index;
    }

    public Token(char token, TypeToken type, int index) {
        this.token = Character.toString(token);
        this.type = type;
        this.index = index;
    }

    public int getID() {
        return this.ID;
    }

    public int getIndex() {
        return index;
    }

    public String getToken() {
        return this.token;
    }

    public TypeToken getType() {
        return type;
    }

    public int getTokenLength() {
        return this.token.length();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Token token = (Token) o;
        return this.ID == token.ID &&
                this.index == token.index &&
                Objects.equals(this.token, token.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.ID, this.index, this.token);
    }

    @Override
    public String toString() {
        return "Token{" +
                "ID=" + ID +
                ", type=" + type +
                ", index=" + index +
                ", token='" + token + '\'' +
                '}';
    }
}
