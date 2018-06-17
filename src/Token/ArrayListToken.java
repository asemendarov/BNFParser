package Token;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.function.Consumer;

public class ArrayListToken{
    private ArrayList<Token> listToken = new ArrayList<>();

    public void add(Token token){
        listToken.add(token);
    }

    public void add(String token, TypeToken type, int index){
        listToken.add(new Token(token, type, index));
    }

    public void add(StringBuilder token, TypeToken type, int index){
        listToken.add(new Token(token.toString(), type, index));
    }

    public void add(char token, TypeToken type, int index){
        listToken.add(new Token(token, type, index));

        listToken.listIterator();
    }

    public ListIterator<Token> listIterator(){
        return listToken.listIterator();
    }
}
