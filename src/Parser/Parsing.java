package Parser;

import Token.ListToken;

public class Parsing {
    private AbstractParsing parsing;

    public Parsing(AbstractParsing parsing){
        this.parsing = parsing;
    }

    public ListToken parsing(){
        return parsing.parsing();
    }
}
