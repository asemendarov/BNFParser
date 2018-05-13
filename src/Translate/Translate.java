package Translate;

import Token.ListToken;

public class Translate {
    private AbstractTranslate translate;

    public Translate(AbstractTranslate translate){
        this.translate = translate;
    }

    public String translate(ListToken listToken){
        return this.translate.translate();
    }
}
