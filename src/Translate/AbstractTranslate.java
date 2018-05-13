package Translate;

import Token.ListToken;

public abstract class AbstractTranslate {
    private ListToken listToken;
    public AbstractTranslate(ListToken listToken){
        this.listToken = listToken;
    }

    public abstract String translate();

    public ListToken getListToken() {
        return listToken;
    }
}
