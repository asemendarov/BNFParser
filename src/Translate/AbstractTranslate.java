package Translate;

import Token.ListTokenIterator;

public abstract class AbstractTranslate {
    private ListTokenIterator listToken;
    public AbstractTranslate(ListTokenIterator listToken){
        this.listToken = listToken;
    }

    public abstract String translate();

    public ListTokenIterator getListToken() {
        return listToken;
    }
}
