package TestParser;

import java.util.ArrayList;

public class ParserBase {
    String Program = "Program:";
    String Equations = "Equations:";
    String BeginConditions = "BeginConditions:";
    String IntegrationConfitions = "IntegrationConditions:";
    String IntegrationMethod = "method";
    String IntegrationVar = "t";
    String IntegrationVarStep = 'd' + IntegrationVar;
    String SYMBOL_DIFF = IntegrationVarStep;

    String text = "";
    int current_index = 0;
    ArrayList<String> not_init_vars = new ArrayList<>();

    void passSpace(){
        passSpace(false);
    }

    void passSpace(boolean only_spaces){
        while (current_index < text.length()){
            if ((text.charAt(current_index) == ' ') ||
                    (!only_spaces && ((text.charAt(current_index) == '\r') || text.charAt(current_index) == '\n')))
                current_index += 1;
            else
                break;
        }
    }

    boolean isNextWord(String word){
        return isNextWord(word, true);
    }

    boolean isNextWord(String word, boolean force){
        passSpace();
        return _isNextWord(word, force);
    }

    boolean isNextWordWithoutLineBreak(String word){
        return isNextWordWithoutLineBreak(word, true);
    }

    boolean isNextWordWithoutLineBreak(String word, boolean force){
        passSpace(true);
        return _isNextWord(word, force);
    }

    boolean _isNextWord(String word, boolean force){
        String find_word = text.substring(current_index, current_index + word.length());
        if (!find_word.equals(word)){
            if(force){
                throw new ParsingException(String.format("Ошибка. Ожидалось слово \"%s\", а у вас написано \"%s\"", word, find_word)){{
                    setIndex(current_index);
                }};
            }
            return false;
        }
        current_index += word.length();
        return true;
    }

    boolean space(){
        if (isSpace()){
            current_index += 1;
            return true;
        }

        throw new ParsingException("Нужен пробел."){{
            setIndex(current_index);
        }};
    }

    boolean isSpace(){
        return text.charAt(current_index) == ' ';
    }

    boolean isDigital(){
        return Character.isDigit(text.charAt(current_index));
    }

    boolean isChar(){
        return Character.isLetter(text.charAt(current_index));
    }

    boolean programName(){
        passSpace();
        int i = 0;
        while (current_index < text.length()){
            if (!readSymbol()){
                break;
            }
            i += 1;
        }
        if (i == 0){
            throw new ParsingException("Ошибка. Ожидалось название программы."){{
                setIndex(current_index);
            }};
        }
        return true;
    }

    String var(){
        String var_name = "";
        passSpace(true);
        int i = 0;
        while (current_index < text.length()){
            if (!readVarSymbol()){
                break;
            }
            var_name += text.substring(current_index - 1, current_index);
            i += 1;
        }
        if(i < 1){
            throw new ParsingException(String.format("Ошибка (var). Ожидалась переменная. Длина переменной 3+ символа. Получено: %s длина %d",
                    text.substring(current_index - 5, current_index), i)){{
                setIndex(current_index);
            }};
        }
        return var_name;
    }

    String integrationVariable(){
        String var_name = var();
        add_not_init_var(var_name);
        isNextWord("/");
        isNextWord(IntegrationVarStep);
        return var_name + "/" + IntegrationVarStep;
    }

    boolean readSymbol(){
        if (isDigital() || isChar() || text.charAt(current_index) == '_' || text.charAt(current_index) == '.'){
            current_index += 1;
            return true;
        }
        return false;
    }

    boolean readVarSymbol(){
        if (isDigital() || isChar() || text.charAt(current_index) == '_') {
            current_index += 1;
            return true;
        }
        return false;
    }


    String Number(){
        String number = IntNumber();
        if (isNextWordWithoutLineBreak(".", false)){
            number += ".";
            number += IntNumber();
        }
        if (number.length() < 1){
            throw new ParsingException("Ожидалось число"){{
                setIndex(current_index);
            }};
        }
        return number;
    }

    String IntNumber(){
        passSpace(true);
        String number = "";
        while (true){
            if(isDigital()){
                number += text.substring(current_index, current_index + 1);
                current_index += 1;
            }
            else {
                break;
            }
        }
        return number;
    }

    void goToStartWord(){
        while (!isSpace()){
            current_index -= 1;
        }
    }

    boolean isEOL(){
        passSpace(true);
        return ((text.charAt(current_index) == ';') ||
                (text.charAt(current_index) == '\r') ||
                (text.charAt(current_index) == '\n')) ;
    }

    // Добавляет переменную в список не инициализированных переменных
    void add_not_init_var(String var){
        if (!not_init_vars.contains(var)){
            not_init_vars.add(var);
        }
    }

    // Удаляет переменную из списка после ее инициализации
    void free_not_init_var(String var){
        if (not_init_vars.contains(var)){
            not_init_vars.remove(var);
        }
    }
}
