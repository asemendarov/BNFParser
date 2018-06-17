package TestParser;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Map;
import java.util.Map.Entry;


public class MathSolver {
    private static final ScriptEngineManager manager = new ScriptEngineManager();
    private static final ScriptEngine engine = manager.getEngineByName("js");

    public static class FlagException{
        private static Exception e = null;

        FlagException(Exception ex){
            e = ex;
        }

        public static Exception get(){
            return e;
        }
    }

    public static double solv(String equation, Map<String, String> params) throws Exception {

//        System.out.println(equation);
//        System.out.println(params);

        for (Entry entry: params.entrySet()){
            equation = equation.replace((String)entry.getKey(), (String)entry.getValue());
        }

//        System.out.println(equation);

        try {
            Object value = engine.eval(equation);
            double printTest = Double.parseDouble(value.toString());

//            System.out.println(printTest);

            return printTest;
        } catch (ScriptException e) {
            new FlagException(e);
            throw new Exception("Не удалось решить уравнение. " + e.getMessage());
        } catch (Exception e) {
            new FlagException(e);
            throw new Exception("Что-то пошло не так, при решении уравнения. " + e.getMessage());
        }
    }
}
