package Parser;

import Token.ListToken;

import java.util.Scanner;

public abstract class AbstractParsing{
    private String inText;
    private int index;

    public AbstractParsing(String inText){
        rewrite(inText);
    }

    public abstract ListToken parsing();

    public boolean hasNext() {
        return index + 1 < inText.length();
    }

    public char next() {
        index = index + 1;
        return inText.charAt(index);
    }

    public void rewrite(String inText){
        this.inText = inText;
        this.index = -1;
    }

    public int getIndex() {
        return index;
    }
}

