package Parser;

import Token.ListTokenIterator;
import Token.TypeToken;
import Token.ArrayListToken;

public class BNFParsing extends AbstractParsing{

    private ArrayListToken listToken = new ArrayListToken();
    private int bufferIndex;

    public BNFParsing(String inText) {
        super(inText);
    }

    @Override
    public ListTokenIterator parsing() {
        passSpace();
        buildWordProgram();


        return new ListTokenIterator(listToken);
    }

    private void buildWordProgram(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(super.getBufferSymbol());

        bufferIndex = super.getIndex();

        while (super.hasNext()){
            if (Character.isWhitespace(super.next()))
                break;
            stringBuilder.append(super.getBufferSymbol());
        }

        if (!stringBuilder.toString().equals(TypeToken.PROGRAM.toString()))
            createException("Ожидалось слово 'Programm'");

        listToken.add(stringBuilder, TypeToken.PROGRAM, bufferIndex);
    }

    // Пропустить пробел
    private void passSpace(){
        while (super.hasNext()){
            if (!isWhitespace(super.next()))
                break;
        }
    }

    // Следующий элемент должен быть пробельным
    private void space(){
        if (!(super.hasNext() && isWhitespace(super.next())))
            createException("Нужен пробел.");
    }

    // Является ли следующий элемент пробельным?
    private boolean isWhitespace(char symbol){
        return Character.isWhitespace(symbol);
    }

    // Явялется ли символ symbol (по БНФ)
    private boolean readSymbol(char symbol){
        return isDigital(symbol) || isChar(symbol) || symbol == '_' || symbol == '.';
    }

    // Проверяет, является ли символ цифрой
    private boolean isDigital(char symbol){
        return Character.isDigit(symbol);
    }

    // Проверяет, является ли текущий символ буквой
    private boolean isChar(char symbol){
        return Character.isLetter(symbol);
    }

    // Иногда требучется разделитель
    private boolean isEOL(char symbol){
        return symbol == ';';
    }

    // Создает ошибку
    private void createException(String message){
        throw new ParsingException(message){{
            setIndex(bufferIndex);
        }};
    }
}
