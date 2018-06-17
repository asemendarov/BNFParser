package Token;

public enum TypeToken {

    TEST("Test"),

    DIGIT("Digit"),
    LETTER("Letter"),

    // Word
    PROGRAM("Programm"),
    EQUATIONS("Equations"),
    BEGIN_CONDITIONS("BeginConditions"),
    INTEGRATION_CONDITIONS("IntegrationConditions"),
    METHOD("Method"),

    // Method
    EULER("Euler"),
    RUNGE_KUTTI_1("Runge-Kutti-1"),
    RUNGE_KUTTI_2("Runge-Kutti-2"),
    RUNGE_KUTTI_3("Runge-Kutti-3"),
    RUNGE_KUTTI_4("Runge-Kutti-4"),

    // Operator
    PLUS("+"),
    MINUS("-"),
    TIMES("*"),
    DEVIDED("/"),

    // SpecialCharacter
    SYMBOL_T("t"),
    SYMBOL_DT("dt"),
    SYMBOL_SLASH_DT("/dt"),
    SYMBOL_UNDERSCORE("_");

    private String name;

    TypeToken(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name.toLowerCase(); // toLowerCase() - используется просто для удабства
    }
}
