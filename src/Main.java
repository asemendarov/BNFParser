// Status: Active Development
/*
    Task:
    1. Необходимо переработать класс ListToken
    2. Пересмотреть возможные типы токенов
    3. Завершить работу с дроблением текста на токены
 */

import Parser.BNFParsing;
import Token.ListToken;

public class Main {
    public static void main(String[] args){
        Helper helper = new Helper();
        helper.parsing(new BNFParsing("1 \t  \n 23"));
        ListToken listToken = helper.getListToken();
        while (listToken.hasNext()){
            System.out.println(listToken.next());
        }
    }
}
