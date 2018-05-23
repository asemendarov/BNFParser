package Translate;

import Token.ListTokenIterator;

public class Translate {
    private AbstractTranslate translate;

    public Translate(AbstractTranslate translate){
        this.translate = translate;
    }

    public String translate(ListTokenIterator listToken){
        return this.translate.translate();
    }
}
