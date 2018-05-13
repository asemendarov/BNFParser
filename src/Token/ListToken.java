package Token;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

public class ListToken { // Proxy
    private ListIterator<Token> listToken;

    public ListToken(){
       listToken = new ArrayList<Token>().listIterator();
    }

    public ListToken(List<Token> list){
        listToken = list.listIterator();
    }

    // возвращает true, если в коллекции имеется следующий элемент, иначе возвращает false
    public boolean hasNext() {
        return listToken.hasNext();
    }

    // возвращает текущий элемент и переходит к следующему, если такого нет,
    // то генерируется исключение NoSuchElementException
    public Token next() {
        return listToken.next();
    }

    // возвращает true, если в коллекции имеется предыдущий элемент, иначе возвращает false
    public boolean hasPrevious() {
        return listToken.hasPrevious();
    }

    // возвращает текущий элемент и переходит к предыдущему, если такого нет,
    // то генерируется исключение NoSuchElementException
    public Token previous() {
        return listToken.previous();
    }

    // возвращает индекс следующего элемента. Если такого нет, то возвращается размер списка
    public int nextIndex() {
        return listToken.nextIndex();
    }

    // возвращает индекс предыдущего элемента. Если такого нет, то возвращается число -1
    public int previousIndex() {
        return listToken.previousIndex();
    }

    // удаляет текущий элемент из списка. Таким образом, этот метод должен быть вызван
    // после методов next() или previous(), иначе будет сгенерировано исключение IlligalStateException
    public void remove() {
        listToken.remove();
    }

    // присваивает текущему элементу, выбранному вызовом методов next() или previous(), ссылку на объект Token
    public void set(Token token) {
        listToken.set(token);
    }

    // присваивает текущему элементу, выбранному вызовом методов next() или previous(), ссылку на объект Token
    public void set(String token, TypeToken type, int index){
        listToken.set(new Token(token, type, index));
    }

    // присваивает текущему элементу, выбранному вызовом методов next() или previous(), ссылку на объект Token
    public void set(char token, TypeToken type, int index){
        listToken.set(new Token(token, type, index));
    }

    // Вставляет объект Token перед элементом, который должен быть возвращен следующим вызовом next()
    public void add(Token token) {
        listToken.add(token);
    }

    // Вставляет объект Token перед элементом, который должен быть возвращен следующим вызовом next()
    public void add(String token, TypeToken type, int index){
        listToken.add(new Token(token, type, index));
    }

    // Вставляет объект Token перед элементом, который должен быть возвращен следующим вызовом next()
    public void add(char token, TypeToken type, int index){
        listToken.add(new Token(token, type, index));
    }
}
