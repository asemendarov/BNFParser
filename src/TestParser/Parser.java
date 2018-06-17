package TestParser;

public class Parser extends ParserBase {
    Integrator integrator = new Integrator();

    void parse(String text){
        super.text = text;

        program();
        equations();
        begin_conditions();
        integration_conditions();

//        System.out.println(integrator);

//        try {
//            System.out.println(integrator.euler()); // Test
//        }
//        catch (Exception ex){
//            ConsoleProxy.print(ex);
//        }
    }

    void program(){
        passSpace();
        isNextWord(Program);
        space();
        programName();
    }

    void equations() {
        passSpace();
        isNextWord(Equations);
        while ((current_index < text.length() - BeginConditions.length()) &&
                (!text.substring(current_index, current_index + BeginConditions.length()).equals(BeginConditions))) {
            equation();
            passSpace();
        }
    }

    void equation() {
        passSpace();
        String var = integrationVariable();
        isNextWordWithoutLineBreak("=");
        String block = right_part();
        integrator.equations.put(var, block);
        isNextWordWithoutLineBreak(";");
    }

    String right_part() {
        passSpace(true);
        String block = "";
        if (isNextWordWithoutLineBreak("-", false)) {
            block += "-";
        }
        while (true) {
            block += additionBlock();
            if (isEOL()) {
                break;
            }
            if (isNextWordWithoutLineBreak(")", false)) {
                current_index -= 1;
                break;
            }
        }
        return block;
    }

    String additionBlock() {
        String add_block = multiplicationBlock();
        while (true) {
            if (isNextWordWithoutLineBreak("+", false)) {
                add_block += " + " + multiplicationBlock();
            } else if (isNextWordWithoutLineBreak("-", false)) {
                add_block += " - " + multiplicationBlock();
            } else {
                break;
            }
        }
        return add_block;
    }

    String multiplicationBlock() {
        String block = degreeBlock();
        while (true) {
            if (isNextWordWithoutLineBreak("*", false)) {
                block += " * " + varBlock();
            } else if (isNextWordWithoutLineBreak("/", false)) {
                block += " / " + varBlock();
            } else {
                break;
            }
        }
        return block;
    }

    String degreeBlock(){
        String block = varBlock();
        while (true) {
            if (isNextWordWithoutLineBreak("^", false)) {
                block += " ** " + varBlock();
            } else {
                break;
            }
        }
        return block;
    }

    String varBlock() {
        String block = "";
        if (isNextWordWithoutLineBreak("(", false)) {
            block += "(";
            block += right_part(); // !!!!!!!!!!!!!!!!!!!!!!!! str(right_part())
            if (isNextWordWithoutLineBreak(")")) {
                block += ")";
            }
        } else if (isDigital()) {
            block += Number();
        } else {
            String var = var();
            add_not_init_var(var);
            block += var;
        }

        return block;
    }

    void method() {
        passSpace();
    }

    void begin_conditions() {
        passSpace();
        isNextWord(BeginConditions);
        passSpace();

        while ((current_index < text.length() - IntegrationConfitions.length()) &&
                (!text.substring(current_index, current_index + IntegrationConfitions.length()).equals(IntegrationConfitions))) {
            begin_condition();
            if (isEOL()) {
                current_index += 1;
            }
            passSpace();
        }

        if (not_init_vars.size() > 0){
            goToStartWord();
            throw new ParsingException("Ошибка. Не проинициализированны следующие переменные: " + not_init_vars.toString()) {{
                setIndex(current_index);
            }};
        }
    }

    void begin_condition() {
        String var = var();
        free_not_init_var(var);
        isNextWord("=");
        String value = Number();
        integrator.begin_conditions.put(var, value);
        isNextWordWithoutLineBreak(";");
        passSpace();
    }

    void integration_conditions() {
        isNextWord(IntegrationConfitions);
        integration_method();
        integration_var();
        integration_var_step();
    }

    void integration_method() {
        passSpace();
        isNextWord(IntegrationMethod);
        isNextWord("=");
        integration_method_name();
        isNextWordWithoutLineBreak(";");
    }

    String integration_method_name() {
        passSpace(true);


        if (isNextWordWithoutLineBreak(Integrator.Methods.Euler.toString(), false)) {
            integrator.integration_method = Integrator.Methods.Euler.toString();
            return Integrator.Methods.Euler.toString();
        }
        if (isNextWordWithoutLineBreak(Integrator.Methods.ModifyEuler.toString(), false)) {
            integrator.integration_method = Integrator.Methods.ModifyEuler.toString();
            return Integrator.Methods.ModifyEuler.toString();
        }
        if (isNextWordWithoutLineBreak(Integrator.Methods.RungeKutti1.toString(), false)) {
            integrator.integration_method = Integrator.Methods.RungeKutti1.toString();
            return Integrator.Methods.RungeKutti1.toString();
        }
        if (isNextWordWithoutLineBreak(Integrator.Methods.RungeKutti2.toString(), false)) {
            integrator.integration_method = Integrator.Methods.RungeKutti2.toString();
            return Integrator.Methods.RungeKutti2.toString();
        }
        if (isNextWordWithoutLineBreak(Integrator.Methods.RungeKutti3.toString(), false)) {
            integrator.integration_method = Integrator.Methods.RungeKutti3.toString();
            return Integrator.Methods.RungeKutti3.toString();
        }
        if (isNextWordWithoutLineBreak(Integrator.Methods.RungeKutti4.toString(), false)) {
            integrator.integration_method = Integrator.Methods.RungeKutti4.toString();
            return Integrator.Methods.RungeKutti4.toString();
        }

        throw new ParsingException("Неизвестный метод интегрирования. Укажите один из следующих:\n" +
                "Euler; Modify-Euler; Runge-Kutti-1; Runge-Kutti-2; Runge-Kutti-3; Runge-Kutti-4") {{
            setIndex(current_index);
        }};
    }

    // Переменная интегрирования
    void integration_var() {
        isNextWord(IntegrationVar);
        isNextWordWithoutLineBreak("=");
        String value = Number();
        integrator.integration_var_value = value;
        isNextWordWithoutLineBreak(";");
    }

    // Шаг интегрирования
    void integration_var_step() {
        isNextWord(IntegrationVarStep);
        isNextWordWithoutLineBreak("=");
        String value = Number();
        integrator.integration_var_step_value = value;
        isNextWordWithoutLineBreak(";");
    }
}