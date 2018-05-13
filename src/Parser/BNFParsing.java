package Parser;

import Token.ListToken;
import Token.Token;
import Token.TypeToken;

import java.util.ArrayList;

public class BNFParsing extends AbstractParsing{

    public BNFParsing(String inText) {
        super(inText);
    }

    @Override
    public ListToken parsing() {
        ArrayList<Token> listToken = new ArrayList<>();

        char symbol;

        while (super.hasNext()){
            symbol = super.next();

            if (passSpace(symbol))
                continue;

            listToken.add(new Token(symbol, TypeToken.TEST, super.getIndex()));
        }

        return new ListToken(listToken);
    }

    private boolean passSpace(char symbol){
        return Character.isWhitespace(symbol);
    }


}
