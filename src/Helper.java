import Parser.AbstractParsing;
import Parser.ParsingException;
import Token.ListTokenIterator;
import Tool.LoggerProxy;
import Translate.AbstractTranslate;
import Translate.TranslateException;

public class Helper {

    public ListTokenIterator parsing(AbstractParsing parser) {
        return parser.parsing();
    }

    public String translate(AbstractTranslate translat){
        return translat.translate();
    }
}
