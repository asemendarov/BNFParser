package Parser;

import Token.ListTokenIterator;

public abstract class AbstractParsing{
    private String inText; // LowerCase
    private char bufferSymbol;
    private int index;

    public AbstractParsing(String inText){
        rewrite(inText);
    }

    public abstract ListTokenIterator parsing();

    public boolean hasNext() {
        return index + 1 < inText.length();
    }

    public char next() {
        index = index + 1;
        bufferSymbol = inText.charAt(index);
        return bufferSymbol;
    }

    public void rewrite(String inText){
        this.inText = inText.toLowerCase();
        this.index = -1;
    }

    public int getIndex() {
        return index;
    }

    public char getBufferSymbol() {
        return bufferSymbol;
    }
}

