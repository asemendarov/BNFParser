package Tool;

public class Configuration {
    public static final String BNF = /* БНФ в нотации Корягина Сергея Викторовича !!! */
            "Language = \"Program\" ProgramName Equations BeginConditions IntegrationConfitions\n" +
            "ProgramName = Var\n" +
            "Equations = \"Equations\" \":\" Equation ... Equation\n" +
            "BeginConditions = \"BeginConditions\" \":\" BeginCondition ... BeginCondition\n" +
            "IntegrationConditions = \"IntegrationConditions\" \":\"  IntegrationCondition\n" +
            "\n" +
            "Equation = IntegrationVar \"=\" RightBlock\n" +
            "RightBlock = </ \"-\" /> AdditionBlock (\"+\" ! \"-\") ... AdditionBlock\n" +
            "AdditionBlock = MultiplicationBlock (\"*\" ! \"/\") ... MultiplicationBlock \n" +
            "MultiplicationBlock = Var ! Number ! \"(\" RightBlock \")\"\n" +
            "\n" +
            "BeginContition = Var \"=\" Number\n" +
            "\n" +
            "IntegrationCondition = IntegrationConditionMethod IntegrationConditionPeriod IntegrationConditionStep\n" +
            "IntegrationConditionMethod = \"method\" \"=\" (\"Euler\" ! \"Runge-Kutti-1\" ! \"Runge-Kutti-2\" ! ... ! \"Runge-Kutti-4\")\n" +
            "IntegrationConditionPeriod = \"t\" \"=\" Number\n" +
            "IntegrationConditionStep = \"dt\" \"=\" Number\n" +
            "\n" +
            "IntegrationVar = Var \"/dt\"\n" +
            "Var = Symbol ... Symbol\n" +
            "Symbol = Character ! Number\n" +
            "Number = FloatNumber ! IntNumber\n" +
            "IntNumber = Digit ... Digit\n" +
            "FloatNumber = IntNumber \".\" IntNumber\n" +
            "Character = \"a\" ! \"b\" ! ... ! \"z\" ! SpecialCharacter\n" +
            "SpecialCharacter = \"_\"\n" +
            "Digit = 0 ! 1 ! ... ! 9";

    public static final String NAME_PROGRAM = "BNFParser";
}
