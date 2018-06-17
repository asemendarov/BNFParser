package Parser;

import Token.ListTokenIterator;

public class Parsing {
    private AbstractParsing parsing;

    public Parsing(AbstractParsing parsing){
        this.parsing = parsing;
    }

    public ListTokenIterator parsing(){
        return parsing.parsing();
    }
}
