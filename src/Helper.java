import Parser.AbstractParsing;
import Parser.ParsingException;
import Token.ListToken;
import Tool.LoggerProxy;
import Translate.AbstractTranslate;
import Translate.TranslateException;

public class Helper {
    private ListToken listToken;
    private String outText;

    public void parsing(AbstractParsing parsing) {
        try {
            this.listToken = parsing.parsing();
        }
        catch (ParsingException e){
            LoggerProxy.info(e);
        }
    }

    public void translate(AbstractTranslate translate){
        try {
            this.outText = translate.translate();
        }
        catch (TranslateException e){
            LoggerProxy.info(e);
        }
    }

    public ListToken getListToken() {
        if (listToken == null)
            throw new ParsingException("Ошибка в порядке обработки. ListToken == null.");
        return listToken;
    }

    public String getOutText() {
        if (outText == null)
            throw new TranslateException("Ошибка в порядке обработки. OutText == null.");
        return outText;
    }
}
