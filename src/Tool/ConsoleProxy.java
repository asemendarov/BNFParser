package Tool;

import Parser.ParsingException;

import java.util.ListIterator;

public class ConsoleProxy {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

//    System.out.println(ANSI_WHITE + "Test " + ANSI_RED + "This text is red!" + ANSI_RESET);

    public static void print(ParsingException ex, String string){
        int index = ex.getIndex();

        StringBuilder stringBuilder = new StringBuilder(string);

        stringBuilder.replace(index, index + 1, ANSI_RED + string.charAt(index) + ANSI_WHITE);

        stringBuilder.append("\nParsingException: ").append(ex.getMessage());

        System.out.println(stringBuilder.toString());
    }


}
